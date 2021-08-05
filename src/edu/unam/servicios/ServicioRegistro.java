package edu.unam.servicios;

import edu.unam.repositorio.Repositorio;

import java.time.LocalDate;
import java.util.List;

import edu.unam.modelo.Cosecha;
import edu.unam.modelo.Registro;

public class ServicioRegistro extends Servicio {
    public ServicioRegistro(Repositorio repositorio) {
        super(repositorio);
    }

    public List<Registro> listarRegistros() {
        return this.repositorio.buscarTodos(Registro.class);
    }

    public Registro buscarRegistro(int idCosecha) {
        return this.repositorio.buscar(Registro.class, idCosecha);
    }

    public void agregarRegistro(int idCosecha, float pesoReg, LocalDate tiempoReg) {
        this.repositorio.iniciarTransaccion();
        Cosecha cosecha = this.repositorio.buscar(Cosecha.class, idCosecha);
        if (cosecha == null) {
            this.repositorio.descartarTransaccion();
            throw new IllegalArgumentException("La cosecha no existe");
        }
        this.repositorio.insertar(new Registro(pesoReg, cosecha.getFecha(), cosecha.getCuadros(), cosecha.getEmpleados(), cosecha.getSecadero(), tiempoReg));
        this.repositorio.confirmarTransaccion();
    }

    public int editarRegistro(int idCosecha, float pesoReg, LocalDate tiempoReg) {
        this.repositorio.iniciarTransaccion();
        Cosecha cosecha = this.repositorio.buscar(Cosecha.class, idCosecha);
        if (cosecha == null) {
            this.repositorio.descartarTransaccion();
            throw new IllegalArgumentException("La cosecha no existe");
        }
        Registro registro = buscarRegistro(idCosecha);
        if (registro != null) {
            registro.setPesoReg(pesoReg);
            registro.setTiempoReg(tiempoReg);
            this.repositorio.confirmarTransaccion();
            return 0;
        } else {
            this.repositorio.descartarTransaccion();
            return 1;
        }
    }

    public int eliminarRegistro(int idCosecha) {
        this.repositorio.iniciarTransaccion();
        Cosecha cosecha = this.repositorio.buscar(Cosecha.class, idCosecha);
        if (cosecha == null) {
            this.repositorio.descartarTransaccion();
            throw new IllegalArgumentException("La cosecha no existe");
        }
        Registro registro = buscarRegistro(idCosecha);
        if (registro != null) {
            this.repositorio.eliminar(registro);
            this.repositorio.confirmarTransaccion();
            return 0;
        } else {
            this.repositorio.descartarTransaccion();
            return 1;
        }
    }
}

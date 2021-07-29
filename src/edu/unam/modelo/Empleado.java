package edu.unam.modelo;

import java.util.*;
import java.time.LocalDate;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idEmpleado;

    @ManyToMany(targetEntity = Cosecha.class, mappedBy = "empleados")
    private List<Cosecha> cosechas = new ArrayList<>();

    private String legajo;
    private int dni;
    private String apellidos;
    private String nombres;
    private LocalDate ingreso;
    private LocalDate nacimiento;
    private long cuil;

    public Empleado() {
        this.setLegajo("");
        this.setDni(0);
        this.setApellidos("");
        this.setNombres("");
        this.setIngreso(LocalDate.now());
        this.setNacimiento(LocalDate.now());
        this.setCuil(0);
    }

    public Empleado(String legajo, int dni, String apellidos, String nombres, LocalDate ingreso, LocalDate nacimiento,
            long cuil) {
        this.setLegajo(legajo);
        this.setDni(dni);
        this.setApellidos(apellidos);
        this.setNombres(nombres);
        this.setIngreso(ingreso);
        this.setNacimiento(nacimiento);
        this.setCuil(cuil);
    }

    public long getIdEmpleado() {
        return this.idEmpleado;
    }

    public List<Cosecha> getCosechas() {
        return this.cosechas;
    }

    public String getLegajo() {
        return this.legajo;
    }

    public int getDni() {
        return this.dni;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public String getNombres() {
        return this.nombres;
    }

    public LocalDate getIngreso() {
        return this.ingreso;
    }

    public LocalDate getNacimiento() {
        return this.nacimiento;
    }

    public long getCuil() {
        return this.cuil;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setIngreso(LocalDate ingreso) {
        this.ingreso = ingreso;
    }

    public void setNacimiento(LocalDate nacimiento) {
        this.nacimiento = nacimiento;
    }

    public void setCuil(long cuil) {
        this.cuil = cuil;
    }
}

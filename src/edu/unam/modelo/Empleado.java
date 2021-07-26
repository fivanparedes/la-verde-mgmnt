package modelo;

import java.time.LocalDate;
import java.util.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idEmpleado;

    @OneToMany(mappedBy = "empleado")
    private List<Cosecha> cosechas = new ArrayList<>();

    private String legajo;
    private int dni;
    private String apellidos;
    private String nombres;
    private LocalDate ingreso;
    private LocalDate nacimiento;
    private long cuil;

    public Empleado() {
        this.legajo = "[LEGAJO IND.]";
        this.dni = 999999;
        this.apellidos = "[APELLIDO IND.]";
        this.nombres = "[NOMBRE IND.]";
        this.ingreso = LocalDate.now();
        this.nacimiento = LocalDate.now();
        this.cuil = 119999991;
    }
    public Empleado(int idEmpleado, String legajo, int dni, String apellidos, String nombres, LocalDate ingreso,
            LocalDate nacimiento, long cuil) {
        this.legajo = legajo;
        this.dni = dni;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.ingreso = ingreso;
        this.nacimiento = nacimiento;
        this.cuil = cuil;
    }
    public int getIdEmpleado() {
        return idEmpleado;
    }
    public String getLegajo() {
        return legajo;
    }
    public int getDni() {
        return dni;
    }
    public String getApellidos() {
        return apellidos;
    }
    public String getNombres() {
        return nombres;
    }
    public LocalDate getIngreso() {
        return ingreso;
    }
    public LocalDate getNacimiento() {
        return nacimiento;
    }
    public long getCuil() {
        return cuil;
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

package edu.unam.modelo;

import java.time.LocalDate;

import jakarta.persistence.Entity;

@Entity
public class Entrega extends Cosecha {
    private float pesoEnt;

    public Entrega() {
        super();
        this.setPesoEnt(0.0f);
    }

    public Entrega(float pesoEnt, LocalDate fecha) {
        super(fecha);
        this.setPesoEnt(pesoEnt);
    }

    public float getPesoEnt() {
        return this.pesoEnt;
    }

    public void setPesoEnt(float pesoEnt) {
        this.pesoEnt = pesoEnt;
    }
}

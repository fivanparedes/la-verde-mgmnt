package edu.unam.modelo;

import java.time.LocalDate;

import jakarta.persistence.Entity;

@Entity
public class Entrega extends Cosecha {
    private float pesoEnt;
    
    public Entrega() {
        super();
        pesoEnt = 0.0f;
    }

    public Entrega(float p, LocalDate f) {
        super(f);
        pesoEnt = p;
    }
    public float getPesoEnt() {
        return pesoEnt;
    }

    public void setPesoEnt(float p) {
        pesoEnt = p;
    }
}

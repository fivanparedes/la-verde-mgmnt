package edu.unam.modelo;

import java.time.LocalDate;

import jakarta.persistence.Entity;

@Entity
public class Registro extends Cosecha {
    private float pesoReg;

    public Registro() {
        super();
        pesoReg = 0.0f;
    }

    public Registro(float p, LocalDate f) {
        super(f);
        pesoReg = p;
    }

    public float getPesoReg() {
        return pesoReg;
    }

    public void setPesoReg(float p) {
        pesoReg = p;
    }
    
}

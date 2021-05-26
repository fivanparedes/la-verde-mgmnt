package modelo;

import java.util.Date;

public abstract class Yerba {

    private Date fecha;
    private double peso;

    public Yerba() {
        fecha = new Date();
        peso = 1000.0;
    }
    
}

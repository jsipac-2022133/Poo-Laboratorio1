package modelo;

import java.util.Random;

public class HabilidadEspecial {
    private String nombre;
    private String efecto; 
    private int valor;
    private int probabilidad; 
    private Random random;

    public HabilidadEspecial(String nombre, String efecto, int valor, int probabilidad) {
        this.nombre = nombre;
        this.efecto = efecto;
        this.valor = valor;
        this.probabilidad = probabilidad;
        this.random = new Random();
    }

    public boolean seActiva() {
        return random.nextInt(100) < probabilidad;
    }

    public String getEfecto() {
        return efecto;
    }

    public int getValor() {
        return valor;
    }

    public String getNombre() {
        return nombre;
    }
}

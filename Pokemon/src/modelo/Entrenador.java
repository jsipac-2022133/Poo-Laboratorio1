package modelo;

public class Entrenador {
    private String nombre;
    private Pokemon[] equipo;
    private int puntos;

    public Entrenador(String nombre) {
        this.nombre = nombre;
        this.equipo = new Pokemon[4];
        this.puntos = 0;
    }

    public void setPokemon(int index, Pokemon pokemon) {
        this.equipo[index] = pokemon;
    }

    public Pokemon getPokemon(int index) {
        return this.equipo[index];
    }

    public int getPuntos() {
        return puntos;
    }

    public void sumarPunto() {
        puntos++;
    }

    public String getNombre() {
        return nombre;
    }
}

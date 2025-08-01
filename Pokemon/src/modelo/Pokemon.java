package modelo;

public class Pokemon {

    private String nombre;
    private Tipo tipo;
    private int ataque;
    private int defensa;
    private HabilidadEspecial habilidad;
    private int rondasConHabilidad;
    private boolean habilidadActiva;

    public Pokemon(String nombre, Tipo tipo, int ataque, int defensa, HabilidadEspecial habilidad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.ataque = ataque;
        this.defensa = defensa;
        this.habilidad = habilidad;
        this.rondasConHabilidad = 0;
        this.habilidadActiva = false;
    }

    public void intentarActivarHabilidad(boolean deseaActivar) {
        if (deseaActivar && habilidad.seActiva()) {
            habilidadActiva = true;
            rondasConHabilidad = 2;
        } else {
            habilidadActiva = false;
            rondasConHabilidad = 0;
        }
    }

    public int calcularPoderTotal(int defensaOponente, Tipo tipoOponente) {
        int bonusTipo = calcularEfectividadTipo(tipoOponente);
        int ataqueTotal = ataque - defensaOponente + bonusTipo;

        if (habilidadActiva && habilidad.getEfecto().equalsIgnoreCase("ataque")) {
            ataqueTotal += habilidad.getValor();
        }

        return ataqueTotal;
    }

    public int penalizarOponente() {
        if (habilidadActiva && habilidad.getEfecto().equalsIgnoreCase("daño")) {
            return habilidad.getValor(); 
        }
        return 0;
    }

    public void reducirDuracionHabilidad() {
        if (habilidadActiva) {
            rondasConHabilidad--;
            if (rondasConHabilidad <= 0) {
                habilidadActiva = false;
                rondasConHabilidad = 0;
            }
        }
    }

    private int calcularEfectividadTipo(Tipo tipoOponente) {
        if ((tipo == Tipo.FUEGO && tipoOponente == Tipo.PLANTA)
                || (tipo == Tipo.PLANTA && tipoOponente == Tipo.AGUA)
                || (tipo == Tipo.AGUA && tipoOponente == Tipo.FUEGO)
                || (tipo == Tipo.ELECTRICO && tipoOponente == Tipo.AGUA)) {
            return 20;
        } else if ((tipo == Tipo.FUEGO && tipoOponente == Tipo.AGUA)
                || (tipo == Tipo.AGUA && tipoOponente == Tipo.PLANTA)
                || (tipo == Tipo.PLANTA && tipoOponente == Tipo.FUEGO)
                || (tipo == Tipo.AGUA && tipoOponente == Tipo.ELECTRICO)) {
            return -10;
        }
        return 0;
    }

    public String getNombre() {
        return nombre;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public HabilidadEspecial getHabilidad() {
        return habilidad;
    }

    public int getDefensa() {
    if (habilidadActiva && habilidad.getEfecto().equalsIgnoreCase("defensa")) {
        return defensa + habilidad.getValor();
    }
    return defensa;
}


    public boolean isHabilidadActiva() {
        return habilidadActiva;
    }
}

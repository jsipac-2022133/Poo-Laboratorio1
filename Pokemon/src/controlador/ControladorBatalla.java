package controlador;

import modelo.Pokemon;

public class ControladorBatalla {

    public int combatir(Pokemon poke1, Pokemon poke2) {
        int poder1 = poke1.calcularPoderTotal(poke2.getDefensa(), poke2.getTipo());
        int poder2 = poke2.calcularPoderTotal(poke1.getDefensa(), poke1.getTipo());

        poder1 += poke2.penalizarOponente();
        poder2 += poke1.penalizarOponente();

        if (poder1 < 0) poder1 = 0;
        if (poder2 < 0) poder2 = 0;

        poke1.reducirDuracionHabilidad();
        poke2.reducirDuracionHabilidad();

        if (poder1 > poder2) return 1;
        else if (poder2 > poder1) return 2;
        else return 0;
    }
}

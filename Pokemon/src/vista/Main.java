package vista;

import modelo.*;
import controlador.ControladorBatalla;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Pokemon[] disponibles = new Pokemon[10];
        disponibles[0] = new Pokemon("Charizard", Tipo.FUEGO, 80, 60, new HabilidadEspecial("Llama Final", "ataque", 15, 30));
        disponibles[1] = new Pokemon("Venusaur", Tipo.PLANTA, 75, 70, new HabilidadEspecial("Escudo Natural", "defensa", 20, 40));
        disponibles[2] = new Pokemon("Blastoise", Tipo.AGUA, 78, 80, new HabilidadEspecial("Tsunami", "daño", -10, 20));
        disponibles[3] = new Pokemon("Pikachu", Tipo.ELECTRICO, 90, 40, new HabilidadEspecial("Impacto Relámpago", "daño", -10, 50));
        disponibles[4] = new Pokemon("Arcanine", Tipo.FUEGO, 85, 55, new HabilidadEspecial("Furia Ígnea", "ataque", 10, 25));
        disponibles[5] = new Pokemon("Leafeon", Tipo.PLANTA, 70, 65, new HabilidadEspecial("Hojas Protectoras", "defensa", 20, 35));
        disponibles[6] = new Pokemon("Vaporeon", Tipo.AGUA, 72, 78, new HabilidadEspecial("Corriente Marina", "ataque", 12, 30));
        disponibles[7] = new Pokemon("Jolteon", Tipo.ELECTRICO, 88, 50, new HabilidadEspecial("Descarga", "daño", -15, 40));
        disponibles[8] = new Pokemon("Flareon", Tipo.FUEGO, 82, 58, new HabilidadEspecial("Explosión Ígnea", "ataque", 18, 35));
        disponibles[9] = new Pokemon("Raichu", Tipo.ELECTRICO, 85, 45, new HabilidadEspecial("Voltio Cruel", "daño", -12, 30));

        boolean[] yaElegidos = new boolean[10];
        Pokemon[] equipoJames = new Pokemon[4];
        Pokemon[] equipoBrian = new Pokemon[4];

        System.out.println("Listado de Pokémon disponibles:");
        for (int i = 0; i < 10; i++) {
            System.out.println((i + 1) + ". " + disponibles[i].getNombre() + " (" + disponibles[i].getTipo() + ")");
        }

        int seleccionadosJames = 0;
        int seleccionadosBrian = 0;
        boolean turnoJames = true;

        while (seleccionadosJames < 4 || seleccionadosBrian < 4) {
            System.out.print((turnoJames ? "\nJames" : "Brian") + ", elige un número (1-10): ");
            int opcion = sc.nextInt();

            if (opcion >= 1 && opcion <= 10 && !yaElegidos[opcion - 1]) {
                if (turnoJames && seleccionadosJames < 4) {
                    equipoJames[seleccionadosJames] = disponibles[opcion - 1];
                    seleccionadosJames++;
                } else if (!turnoJames && seleccionadosBrian < 4) {
                    equipoBrian[seleccionadosBrian] = disponibles[opcion - 1];
                    seleccionadosBrian++;
                }
                yaElegidos[opcion - 1] = true;
                turnoJames = !turnoJames; 
            } else {
                System.out.println("Opción inválida o ya elegida.");
            }
        }

        Entrenador james = new Entrenador("James");
        for (int i = 0; i < 4; i++) {
            james.setPokemon(i, equipoJames[i]);
        }

        Entrenador brian = new Entrenador("Brian");
        for (int i = 0; i < 4; i++) {
            brian.setPokemon(i, equipoBrian[i]);
        }

        ControladorBatalla batalla = new ControladorBatalla();

        for (int ronda = 0; ronda < 4; ronda++) {
            System.out.println("\n--- RONDA " + (ronda + 1) + " ---");

            Pokemon pokeJames = james.getPokemon(ronda);
            System.out.println(james.getNombre() + " usará a " + pokeJames.getNombre());
            System.out.print("¿Desea usar habilidad? (1=Sí, 0=No): ");
            pokeJames.intentarActivarHabilidad(sc.nextInt() == 1);

            Pokemon pokeBrian = brian.getPokemon(ronda);
            System.out.println(brian.getNombre() + " usará a " + pokeBrian.getNombre());
            System.out.print("¿Desea usar habilidad? (1=Sí, 0=No): ");
            pokeBrian.intentarActivarHabilidad(sc.nextInt() == 1);

            int resultado = batalla.combatir(pokeJames, pokeBrian);

            if (resultado == 1) {
                System.out.println(james.getNombre() + " gana la ronda");
                james.sumarPunto();
            } else if (resultado == 2) {
                System.out.println(brian.getNombre() + " gana la ronda");
                brian.sumarPunto();
            } else {
                System.out.println("¡Empate!");
            }
        }

        System.out.println("\n--- RESULTADO FINAL ---");
        if (james.getPuntos() > brian.getPuntos()) {
            System.out.println("Ganador del combate: " + james.getNombre());
        } else if (brian.getPuntos() > james.getPuntos()) {
            System.out.println("Ganador del combate: " + brian.getNombre());
        } else {
            System.out.println("¡El combate termina en EMPATE!");
        }

        sc.close();
    }
}

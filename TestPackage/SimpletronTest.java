/* Flores Olivarez Ángel Mauricio
Morales Sandoval Diego Alejandro
*/

package TestPackage;

import edu.KurtP.Simpletron.Simpletron;
import java.util.Scanner;

public class SimpletronTest {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean continueProgram = true;

        System.out.println("Bienvenido a la resolucion de problemas Deitel 6.32 entre a-d, selecciona una letra");

        while (continueProgram) {
            char choice = displayMenu(input);

            switch (choice) {
                case 'a':
                    Simpletron s = new Simpletron(1000); // a) Extender la memoria a 1000 posiciones
                    s.run();
                    break;

                case 'b':
                case 'c':
                    Simpletron simpletron = new Simpletron();
                    simpletron.run(); // Ejecutar el Simpletron con las instrucciones dadas
                    break;

                case 'd':
                    Simpletron sHex = new Simpletron();
                    sHex.enableHexadecimalMode(); // d) Habilitar el modo hexadecimal
                    sHex.run();
                    break;

                case 'e':
                    continueProgram = false;
                    System.out.println("Saliendo del programa...");
                    break;
            }

            if (choice != 'e') {
                System.out.print("Desea realizar otra operacion? (s/n): ");
                char response = input.next().toLowerCase().charAt(0);
                if (response != 's') {
                    continueProgram = false;
                    System.out.println("Saliendo del programa...");
                }
            }
        }
    }

    private static char displayMenu(Scanner input) {
        char choice;
        do {
            System.out.println("\nMenu de Opciones:");
            System.out.println("a) Extender memoria del simulador Simpletron a 1000 posiciones");
            System.out.println("b) Permitir calculos de residuo");
            System.out.println("c) Permitir calculos de exponenciacion");
            System.out.println("d) Usar valores hexadecimales para las instrucciones SML");
            System.out.println("e) Salir del programa");
            System.out.print("Seleccione una opcion (a-e): ");
            choice = input.next().toLowerCase().charAt(0);
        } while (choice < 'a' || choice > 'e');

        return choice;
    }
}

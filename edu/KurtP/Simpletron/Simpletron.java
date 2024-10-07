/* Flores Olivarez Ángel Mauricio
 Morales Sandoval Diego Alejandro
*/

package edu.KurtP.Simpletron;

import java.util.Scanner;

public class Simpletron extends SimpletronOperationCodes {

    private int[] memory;
    private int accumulator;
    private int instructionCounter;
    private int instructionRegister;
    private int operationCode;
    private int operand;
    private boolean run = true;
    private boolean hexadecimalMode = false;

    public Simpletron() {
        this(100); // valor por defecto de 100 posiciones de memoria
    }

    public Simpletron(int memorySize) {
        memory = new int[memorySize];
    }

    public void enableHexadecimalMode() {
        hexadecimalMode = true;
    }

    public void run() {
        welcomeMessage();
        execute();
    }

    private void welcomeMessage() {
        System.out.println("***            Bienvenido a Simpletron!           ***");
        System.out.println("*** Por favor ingrese su programa, una instruccion a la vez ***");
        System.out.println("*** (o palabra de datos). Mostrare el numero de la ubicacion y un signo de interrogacion (?) ***");
        System.out.println("*** Luego ingrese la palabra para esa ubicacion. Escriba -99999 para terminar de ingresar su programa. ***");
    }

    private void execute() {
        Scanner codeInputter = new Scanner(System.in);
        int instructionInput = 0;
        int memoryPointer = 0;

        do {
            System.out.printf("%02d ? ", memoryPointer);
            instructionInput = codeInputter.nextInt();
            memory[memoryPointer] = instructionInput;
            memoryPointer++;
        } while (instructionInput != -99999);

        System.out.printf("\n%s\n%s\n\n", "***  Carga del programa completada ***", "*** Comienzo de la ejecucion del programa ***");

        while (run) {
            loadCode();
            operations(operationCode, operand);
        }
    }

    private void loadCode() {
        instructionRegister = memory[instructionCounter];
        operationCode = instructionRegister / 100;
        operand = instructionRegister % 100;
    }

    private void operations(int operationCode, int operand) {
        boolean branching = false;

        switch (operationCode) {
            case READ:
                Scanner read = new Scanner(System.in);
                System.out.print("Ingrese un numero: ");
                int number = read.nextInt();
                memory[operand] = number;
                break;

            case WRITE:
                if (hexadecimalMode) {
                    System.out.printf("0x%X\n", memory[operand]);
                } else {
                    System.out.println(memory[operand]);
                }
                break;

            case LOAD:
                accumulator = memory[operand];
                break;

            case STORE:
                memory[operand] = accumulator;
                break;

            case ADD:
                accumulator += memory[operand];
                break;

            case SUBTRACT:
                accumulator -= memory[operand];
                break;

            case DIVIDE:
                if (memory[operand] == 0) {
                    System.out.printf("\n%s\n%s\n", "*** NO SE PUEDE DIVIDIR POR CERO ***", "*** SALIENDO AHORA ***");
                    System.exit(-1);
                } else {
                    accumulator /= memory[operand];
                }
                break;

            case MULTIPLY:
                accumulator *= memory[operand];
                break;

            case MODULO: // Nueva instrucción para el cálculo de residuo
                if (memory[operand] == 0) {
                    System.out.printf("\n%s\n%s\n", "*** NO SE PUEDE CALCULAR EL RESIDUO CON DIVISOR CERO ***", "*** SALIENDO AHORA ***");
                    System.exit(-1);
                } else {
                    accumulator = accumulator % memory[operand];
                }
                break;

            case EXPONENTIATE: // Nueva instrucción para la exponenciación
                int base = accumulator;
                int exponent = memory[operand];

                if (exponent < 0) {
                    System.out.printf("\n%s\n%s\n", "*** NO SE PUEDE CALCULAR EXPONENCIACION CON EXPONENTE NEGATIVO ***", "*** SALIENDO AHORA ***");
                    System.exit(-1);
                }

                long result = 1;
                for (int i = 0; i < exponent; i++) {
                    result *= base;

                    // Verificar si se excede el valor máximo permitido para un entero.
                    if (result > Integer.MAX_VALUE) {
                        System.out.printf("\n%s\n%s\n", "*** RESULTADO DE EXPONENCIACION EXCEDE EL VALOR MAXIMO ***", "*** SALIENDO AHORA ***");
                        System.exit(-1);
                    }
                }
                accumulator = (int) result; // Actualizar el acumulador con el resultado de la exponenciación
                break;

            case BRANCH:
                instructionCounter = operand;
                branching = true;
                break;

            case BRANCHNEG:
                if (accumulator < 0) {
                    instructionCounter = operand;
                    branching = true;
                }
                break;

            case BRANCHZERO:
                if (accumulator == 0) {
                    instructionCounter = operand;
                    branching = true;
                }
                break;

            case HALT:
                System.out.println("Procesamiento completo...");
                run = false;
                memoryDump();
                break;
        }

        if (!branching) {
            instructionCounter++;
        }
    }

    private void memoryDump() {
        int tens, ones;
        System.out.printf("\t%02d\t%02d\t%02d\t%02d\t%02d\t%02d\t%02d\t%02d\t%02d\t%02d\n", 0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        for (tens = 0; tens < memory.length; tens += 10) {
            System.out.printf("%02d\t", tens);
            for (ones = 0; ones < 10 && (tens + ones) < memory.length; ones++) {
                System.out.printf("%04d\t", memory[tens + ones]);
            }
            System.out.println();
        }
    }
}

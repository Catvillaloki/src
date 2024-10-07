/* Flores Olivarez Ángel Mauricio
Morales Sandoval Diego Alejandro 
*/

package edu.KurtP.Simpletron;

public abstract class SimpletronOperationCodes {
    protected static final int READ = 10;
    protected static final int WRITE = 11;

    protected static final int LOAD = 20;
    protected static final int STORE = 21;

    protected static final int ADD = 30;
    protected static final int SUBTRACT = 31;
    protected static final int DIVIDE = 32;
    protected static final int MULTIPLY = 33;
    protected static final int MODULO = 34; // Nuevo código de operación para residuo
    protected static final int EXPONENTIATE = 35; // Nuevo código de operación para exponenciación

    protected static final int BRANCH = 40;
    protected static final int BRANCHNEG = 41;
    protected static final int BRANCHZERO = 42;
    protected static final int HALT = 43;
}

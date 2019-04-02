package Comando;

import Variavel.*;

public class ComandoWriteVar extends Comando {

    char variavel;

    public ComandoWriteVar(int lin, String txt) {

    }

    public int executa() {

        return linha + 1;
    }
}

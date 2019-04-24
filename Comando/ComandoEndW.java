package Comando;

import Expressao.*;

public class ComandoEndW extends Comando {

    int linha;
    int linhaWhile;
    Expressao exp;

    public ComandoEndW(int lin, int linW) {
        this.linha = lin;
        this.linhaWhile = linW;
    }

    public int executa() {
        return this.linhaWhile;
    }
}

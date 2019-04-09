package Comando;

import Variavel.*;

public class ComandoElse extends Comando implements Condicao {

    int linhaEnd;

    public ComandoElse(int lin) {
        this.linhaEnd = lin;
    }

    public void setLinhaEnd(int lin) {
        this.linhaEnd = lin;
    }

    public int executa() {
        return this.linhaEnd;
    }
}

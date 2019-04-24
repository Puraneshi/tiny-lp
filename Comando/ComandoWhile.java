package Comando;

import Expressao.*;

public class ComandoWhile extends Comando implements Condicao {

    int linha;
    int linhaEnd;
    Expressao exp;

    public ComandoWhile(int lin, Expressao raizArvoreExpressao) {
        this.linha = lin;
        this.exp = raizArvoreExpressao;
    }

    public void setLinha(int lin) {
        this.linha = lin;
    }

    public void setLinhaEnd(int lin) {
        this.linhaEnd = lin;
    }

    public int executa() {
        if (exp.avalia() == 1) {
            return linha + 1;
        } else {
            return linhaEnd + 1;
        }
    }
}

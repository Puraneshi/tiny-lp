package Comando;

import Variavel.*;
import Expressao.*;

public class ComandoIf extends Comando implements Condicao {

    int linhaEnd;
    int linhaElse;
    Expressao exp;

    public ComandoIf(int lin, Expressao raizArvoreExpressao) {
        this.linhaEnd = lin;
        this.exp = raizArvoreExpressao;
    }

    public void setLinhaEnd(int lin) {
        this.linhaEnd = lin;
    }
    
    public void setLinhaElse(int lin){
        this.linhaElse = lin;
    }
    
    public int executa() {
        if(exp.avalia()==1) return linhaEnd + 1;
        
        else return linhaElse + 1;
    }
}

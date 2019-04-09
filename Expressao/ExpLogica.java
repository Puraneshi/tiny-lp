package Expressao;

import Variavel.*;

public class ExpLogica extends Expressao {

    String op;
    Object exp1, exp2;
    Expressao opr1, opr2;
    static final double TRUE = 1;
    static final double FALSE = 0;

    public ExpLogica(String op, Object exp1, Object exp2) {
        this.op = op;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    public double avalia() {
        opr1 = (Expressao) exp1;
        opr2 = (Expressao) exp2;
        
        switch(op){
            case "and":
                return (opr1.avalia()==1) && (opr2.avalia()==1) ? TRUE:FALSE;
            case "or":
                return (opr1.avalia()==1) || (opr2.avalia()==1) ? TRUE:FALSE;
            
            // case "not": nao sei o que fazer com o not...
            default:
                return FALSE;
        }

    }
}

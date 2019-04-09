package Expressao;

import Variavel.*;

public class ExpComparativa extends Expressao {

    String op;
    Object exp1, exp2;
    Expressao opr1, opr2;
    static final double TRUE = 1;
    static final double FALSE = 0;

    public ExpComparativa(String op, Object exp1, Object exp2) {
        this.op = op;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    public double avalia() {
        opr1 = (Expressao)exp1;
        opr2 = (Expressao)exp2;
        // com switch case, podemos encurtar o codigo com o 'default'
        switch(op){
            case ">":
                if(opr1.avalia() > opr2.avalia()) return TRUE;
                else return FALSE;
            case ">=":
                if(opr1.avalia() >= opr2.avalia()) return TRUE;
                else return FALSE;
            case "<":
                return opr1.avalia() < opr2.avalia() ? TRUE:FALSE;
            case "<=":
                return opr1.avalia() <= opr2.avalia() ? TRUE:FALSE;
            case "=":
                return opr1.avalia() == opr2.avalia() ? TRUE:FALSE;
            case "<>":
                return opr1.avalia() != opr2.avalia() ? TRUE:FALSE;
            default:
                return FALSE;
        }
    }
}

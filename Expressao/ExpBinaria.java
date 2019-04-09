package Expressao;

import Variavel.*;

public class ExpBinaria extends Expressao {

    String op;
    Expressao exp1, exp2;
    Expressao cmd1, cmd2;

    public ExpBinaria(String op, Object exp1, Object exp2) {
        this.op = op;
        this.exp1 = (Expressao) exp1;
        this.exp2 = (Expressao) exp2;
    }

    public double avalia() {
        // switch case melhor que cadeia de if-else
        switch (op) {
            case "+":
                return exp1.avalia() + exp2.avalia();
            case "-":
                return exp1.avalia() - exp2.avalia();
            case "*":
                return exp1.avalia() * exp2.avalia();
            case "/":
                return exp1.avalia() / exp2.avalia();
            default:
                return 0;
        }
    }
}

package Expressao;

import Variavel.*;

public class ExpVariavel extends Expressao {

    char variavel;

    public ExpVariavel(char nomevar) {
        variavel = nomevar;
    }

    public double avalia() {
        return Variaveis.var[variavel - 97];
    }

}

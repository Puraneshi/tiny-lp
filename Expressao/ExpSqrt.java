package Expressao;

import Variavel.*;

public class ExpSqrt extends Expressao {

    String sqrt;

    public ExpSqrt(String sqrt) {
        this.sqrt = sqrt;
    }

    public double avalia() {
        int posicao = (int)sqrt.charAt(0) - 97;
        double valor = Variaveis.var[posicao];

        valor = Math.sqrt(valor);
        return valor;
    }

}

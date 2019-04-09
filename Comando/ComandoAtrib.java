package Comando;

import Variavel.*;
import Expressao.*;

public class ComandoAtrib extends Comando {

    char variavel;
    Expressao exp;

    public ComandoAtrib(int lin, char var, Expressao raizArvoreExpressao) {
        this.variavel = var;
        this.linha = lin;
        this.exp = raizArvoreExpressao;
    }

    public int executa() {
        double valor;
        valor = exp.avalia();
        int posicao = (int)variavel - 97;
        Variaveis.var[posicao] = valor;
        return linha + 1;
    }
}

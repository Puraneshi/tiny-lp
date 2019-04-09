package Comando;

import Variavel.*;

public class ComandoWriteVar extends Comando {

    char variavel;

    public ComandoWriteVar(int lin, String txt) {
        linha = lin;
        variavel = txt.charAt(0);
    }

    public int executa() {
        int posicao = (int)variavel - 97;
        System.out.print(Variaveis.var[posicao]);
        
        return linha + 1;
    }
}

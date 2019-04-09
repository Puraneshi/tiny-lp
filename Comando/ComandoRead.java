package Comando;

import Variavel.*;
import java.util.*;

public class ComandoRead extends Comando {

    Scanner teclado = new Scanner(System.in);
    char variavel;

    public ComandoRead(int lin, String txt) {
        this.linha = lin;
        this.variavel = txt.charAt(0);
    }
    @Override
    public int executa() {

        try {
            int c = (int) variavel - 97;

            String valor = teclado.nextLine();

            Variaveis.var[c] = Float.valueOf(valor);
        } catch (Exception e) {
            System.out.println("ERRO: " + e);
        }
        return linha + 1;
    }
}

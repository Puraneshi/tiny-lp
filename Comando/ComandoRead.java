package Comando;

import Variavel.*;
import java.io.*;

public class ComandoRead extends Comando {

    BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
    char variavel;

    public ComandoRead(int lin, String txt) {

    }

    public int executa() {

        try {

        } catch (Exception e) {
            System.out.println("ERRO: " + e);
        }
        return linha + 1;
    }
}

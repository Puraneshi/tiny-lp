package Comando;

public class ComandoWriteLn extends Comando {

    public ComandoWriteLn(int lin) {
        linha = lin;
    }

    public int executa() {
        System.out.println();
        return linha + 1;
    }
}

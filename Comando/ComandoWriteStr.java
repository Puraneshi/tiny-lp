package Comando;

public class ComandoWriteStr extends Comando {

    String texto;

    public ComandoWriteStr(int lin, String txt) {
    }

    public int executa() {
        return linha + 1;
    }
}

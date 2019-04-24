import Variavel.*;
import Comando.*;
import Expressao.*;
import java.util.*;
import lp.*;

public class Interpretador {

    private ArquivoFonte arq;
    private Vector comandos;
    private Stack pilha;
    private String palavraAtual;
    private Expressao raizArvoreExpressao;

    public Interpretador(String nome) {
        arq = new ArquivoFonte(nome);
        comandos = new Vector();
    }

    public void listaArquivo() {
        String palavra;

        do {
            palavra = arq.proximaPalavra();
            System.out.println("Palavra: " + palavra);
        } while (!palavra.equals("EOF"));
    }

    public void leArquivo() {

        Stack pilhaC = new Stack();
        String comandoAtual;
        int linha = 0;

        pilhaC.push(linha);

        do {
            comandoAtual = arq.proximaPalavra();

            if (comandoAtual.equals("endp")) {
                trataComandoEndp(linha);
                linha++;
            } else if (comandoAtual.equals("writeStr")) {
                comandoAtual = arq.proximaPalavra();
                comandoAtual = arq.proximaPalavra();
                trataComandoWriteStr(linha, comandoAtual);
                linha++;
            } else if (comandoAtual.equals("writeVar")) {
                comandoAtual = arq.proximaPalavra();
                comandoAtual = arq.proximaPalavra();
                trataComandoWriteVar(linha, comandoAtual);
                linha++;
            } else if (comandoAtual.equals("writeln")) {
                trataComandoWriteln(linha);
                linha++;
            } else if (comandoAtual.equals("read")) {
                comandoAtual = arq.proximaPalavra();
                comandoAtual = arq.proximaPalavra();
                trataComandoRead(linha, comandoAtual);
                linha++;
            // *************** IF-ELSE ************************
            } else if (comandoAtual.equals("if")) {
                pilhaC.push(linha);
                trataComandoIf(linha);
                linha++;
            } else if (comandoAtual.equals("else")) {
                int linhaIf = (Integer) pilhaC.pop();
                pilhaC.push(linha);
                trataComandoElse(linha, linhaIf);
                linha++;
            } else if (comandoAtual.equals("endif")) {
                int linhaIf = (Integer) pilhaC.pop();
                trataComandoEndif(linha, linhaIf);
            // ****************** WHILE ***********************
            } else if (comandoAtual.equals("while")) {
                pilhaC.push(linha);
                trataComandoWhile(linha);
                linha++;
            } else if (comandoAtual.equals("endw")) {
                int linhaWhile = (Integer)pilhaC.pop();
                trataComandoEndW(linha, linhaWhile);
                linha++;
            // **************** FOR *******************************
            } else if(comandoAtual.equals("for")){
                pilhaC.push(linha);
                String var = arq.proximaPalavra();
                arq.proximaPalavra();
                int val = Integer.parseInt(arq.proximaPalavra());
                String tipo = arq.proximaPalavra();
                trataComandoFor(linha, var, val, tipo);
                linha++;
            } else if(comandoAtual.equals("endfor")){
                int linhaFor = (Integer)pilhaC.pop();
                trataComandoEndF(linha, linhaFor);
                linha++;
            // ****************** VARIAVEL ***********************
            } else if (comandoAtual.length() == 1 && comandoAtual.charAt(0) >= 'a' && comandoAtual.charAt(0) <= 'z') {
                String variavel = comandoAtual;
                comandoAtual = arq.proximaPalavra();
                trataComandoAtrib(linha, variavel);
                linha++;
            }

        } while (!comandoAtual.equals("endp"));
    }

    // ***************** Tratacomandos *************************
    private void trataComandoEndp(int lin) {

        ComandoEndp c = new ComandoEndp(lin);
        comandos.addElement(c);
    }

    private void trataComandoWriteStr(int lin, String txt) {

        ComandoWriteStr c = new ComandoWriteStr(lin, txt);
        comandos.addElement(c);
    }

    private void trataComandoWriteVar(int lin, String txt) {

        ComandoWriteVar c = new ComandoWriteVar(lin, txt);
        comandos.addElement(c);
    }

    private void trataComandoWriteln(int lin) {

        ComandoWriteLn c = new ComandoWriteLn(lin);
        comandos.addElement(c);
    }

    private void trataComandoRead(int lin, String txt) {

        ComandoRead c = new ComandoRead(lin, txt);
        comandos.addElement(c);
    }
    
    //******************** IF-ELSE *********************************
    private void trataComandoIf(int lin) {
        trataExpressao();
        ComandoIf c = new ComandoIf(lin, raizArvoreExpressao);
        comandos.addElement(c);
        c.setLinhaElse(-1);
    }

    private void trataComandoElse(int lin, int linIf) {
        ComandoIf cmd = (ComandoIf) comandos.elementAt(linIf);
        cmd.setLinhaElse(lin);
        ComandoElse c = new ComandoElse(lin);
        comandos.addElement(c);
    }

    private void trataComandoEndif(int lin, int linIfElse) {
        Condicao cmd = (Condicao) comandos.elementAt(linIfElse);
        cmd.setLinhaEnd(lin);
    }
    // ************************* WHILE ******************************
    private void trataComandoWhile(int lin) {
        trataExpressao();
        ComandoWhile c = new ComandoWhile(lin, raizArvoreExpressao);
        comandos.addElement(c);
    }
    
    private void trataComandoEndW(int lin, int linWhile) {
        Condicao cmd = (Condicao) comandos.elementAt(linWhile);
        cmd.setLinhaEnd(lin);
        ComandoEndW c = new ComandoEndW(lin, linWhile);
        comandos.addElement(c);
    }
    
    // ************************* FOR *************************
    private void trataComandoFor(int lin, String variavel, int valor, String tipo) {
        trataExpressao();
        ComandoFor c = new ComandoFor(lin, variavel.charAt(0), valor, tipo, raizArvoreExpressao);
        comandos.addElement(c);
    }
    
    private void trataComandoEndF(int lin, int linFor) {
        ComandoFor cmd = (ComandoFor) comandos.elementAt(linFor);
        cmd.setLinhaEnd(lin);
        ComandoEndF c = new ComandoEndF(lin, linFor, cmd.getVar(), cmd.getOperador());
        comandos.addElement(c);
    }
    
    
    private void trataComandoAtrib(int lin, String txt) {
        char var = txt.charAt(0);
        trataExpressao();
        ComandoAtrib c = new ComandoAtrib(lin, var, raizArvoreExpressao);
        comandos.addElement(c);
    }
    // ******************** BOOLEANOS ****************
    private void trataExpressao() {
        palavraAtual = arq.proximaPalavra();
        pilha = new Stack();
        expressaoLogica();
        raizArvoreExpressao = (Expressao) pilha.pop();
    }

    private void expressaoLogica() {
        expressaoComparativa();
        while (palavraAtual.equals("and") || palavraAtual.equals("or") || palavraAtual.equals("not")) {
            String op = palavraAtual;
            palavraAtual = arq.proximaPalavra();
            expressaoComparativa();
            Object exp1 = pilha.pop();
            Object exp2 = pilha.pop();
            pilha.push(new ExpLogica(op, exp1, exp2));
        }
    }

    private void expressaoComparativa() {
        expressao();
        while (palavraAtual.equals("<") || palavraAtual.equals(">") || palavraAtual.equals(">=")
                || palavraAtual.equals("<=") || palavraAtual.equals("<>") || palavraAtual.equals("=")) {
            String op = palavraAtual;
            palavraAtual = arq.proximaPalavra();
            expressao();
            Object exp1 = pilha.pop();
            Object exp2 = pilha.pop();
            pilha.push(new ExpComparativa(op, exp2, exp1));
        }
    }
    // ************************ CALCULOS **************************
    private void expressao() {
        termo();
        while (palavraAtual.equals("+") || palavraAtual.equals("-")) {
            String op = palavraAtual;
            palavraAtual = arq.proximaPalavra();
            termo();
            Object exp1 = pilha.pop();
            Object exp2 = pilha.pop();
            // ordem de processamento invertida pela forma que a pilha funciona
            pilha.push(new ExpBinaria(op, exp2, exp1));
        }
    }

    private void termo() {
        fator();
        while (palavraAtual.equals("*") || palavraAtual.equals("/")) {
            String op = palavraAtual;
            palavraAtual = arq.proximaPalavra();
            fator();
            Object exp1 = pilha.pop();
            Object exp2 = pilha.pop();
            // ordem de processamento invertida pela forma que a pilha funciona
            pilha.push(new ExpBinaria(op, exp2, exp1));
        }
    }

    private void fator() {
        if (palavraAtual.equals("sqrt")) {
            palavraAtual = arq.proximaPalavra();
            palavraAtual = arq.proximaPalavra();
            pilha.push(new ExpSqrt(palavraAtual));
            palavraAtual = arq.proximaPalavra();
            palavraAtual = arq.proximaPalavra();
        } else if (palavraAtual.charAt(0) >= '0' && palavraAtual.charAt(0) <= '9') {
            pilha.push(new ExpConstante(Double.parseDouble(palavraAtual)));
            palavraAtual = arq.proximaPalavra();
        } else if (palavraAtual.charAt(0) >= 'a' && palavraAtual.charAt(0) <= 'z') {
            pilha.push(new ExpVariavel(palavraAtual.charAt(0)));
            palavraAtual = arq.proximaPalavra();
        } else if (palavraAtual.equals("(")) {
            palavraAtual = arq.proximaPalavra();
            expressaoLogica();

            if (palavraAtual.equals(")")) {
                palavraAtual = arq.proximaPalavra();
            }

        }

    }
    // ***************************** EXECUTA ************************
    public void executa() {

        Comando cmd;
        int pc = 0;
        do {
            cmd = (Comando) comandos.elementAt(pc);
            //System.out.println("\nLinha " + pc + ":    "  + cmd);
            pc = cmd.executa();
            //System.out.println("Retorno da execucao :    " + pc);
        } while (pc != -1);
    }

}

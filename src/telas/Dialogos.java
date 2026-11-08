package telas;
import java.awt.*;
import javax.swing.*;

// metodos auxiliares de interface
public class Dialogos {

    private Dialogos() {
    }
    // mostra as opcoes como botoes empilhados
    public static int escolherOpcao(String titulo, String mensagem, String[] opcoes) {
        JOptionPane pane = new JOptionPane(null, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}); // sem os botoes padrao

        JPanel painel = new JPanel(new GridLayout(0, 1, 5, 5));
        painel.add(new JLabel(mensagem));
        for (int i = 0; i < opcoes.length; i++) {// numero de botoes criados equivale ao numero elementos no array opcoes. Cada botao fecha a janela com o valor do indice correspondente
            int indice = i;
            JButton botao = new JButton(opcoes[i]);
            botao.addActionListener(e -> pane.setValue(indice)); // fecha a janela com esse valor
            painel.add(botao);
        }
        pane.setMessage(painel);

        JDialog dialog = pane.createDialog(null, titulo);
        dialog.setVisible(true); // espera ate o usuario clicar ou fechar
        dialog.dispose();

        Object valor = pane.getValue();
        return (valor instanceof Integer indice) ? indice : -1;
    }
}

package telas;
import animais.*;
import java.awt.*;
import java.time.format.*;
import javax.swing.*;

// metodos auxiliares de interface
public class Dialogos {
    public static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Dialogos() {
    }

    // caixa de texto com varias linhas, para textos longos como diagnostico e prescricao
    public static JTextArea criarAreaDeTexto() {
        JTextArea area = new JTextArea(5, 30); // 5 linhas visiveis, 30 colunas de largura
        area.setLineWrap(true);       // quebra a linha quando chega no fim da caixa
        area.setWrapStyleWord(true);  // quebra entre palavras, nao no meio delas
        return area;
    }

    // monta um painel com um rotulo em cima de cada campo; devolve false se o usuario cancelar
    public static boolean mostrarFormulario(String titulo, String[] rotulos, JComponent[] campos) {
        // BoxLayout em vez de GridLayout: o GridLayout deixa todas as linhas do mesmo tamanho,
        // o BoxLayout respeita o tamanho de cada campo, entao as areas de texto ficam maiores
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        for (int i = 0; i < campos.length; i++) {
            JLabel rotulo = new JLabel(rotulos[i]);
            // JTextArea vai dentro de um JScrollPane para ganhar barra de rolagem quando o texto for grande
            JComponent campo = (campos[i] instanceof JTextArea) ? new JScrollPane(campos[i]) : campos[i];
            rotulo.setAlignmentX(Component.LEFT_ALIGNMENT); // alinha tudo a esquerda
            campo.setAlignmentX(Component.LEFT_ALIGNMENT);
            painel.add(rotulo);
            painel.add(campo);
            painel.add(Box.createVerticalStrut(5)); // espaco entre um campo e o proximo
        }
        int opcao = JOptionPane.showConfirmDialog(null, painel, titulo,
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        return opcao == JOptionPane.OK_OPTION;
    }

    // mostra um botao por animal e devolve o escolhido, ou null se o usuario voltar
    public static Animal escolherAnimal(String titulo, Animal[] animais) {
        if (animais.length == 0) {
            JOptionPane.showMessageDialog(null, "Nenhum animal encontrado");
            return null;
        }
        String[] nomes = new String[animais.length + 1];
        for (int i = 0; i < animais.length; i++) {
            nomes[i] = animais[i].getNome() + " (" + animais[i].getEspecie() + ")";
        }
        nomes[animais.length] = "Voltar";

        int escolha = escolherOpcao(titulo, "Escolha o animal", nomes);
        return (escolha >= 0 && escolha < animais.length) ? animais[escolha] : null;
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

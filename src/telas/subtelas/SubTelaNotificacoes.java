package telas.subtelas;
import java.util.*;
import javax.swing.*;
import usuarios.*;

// ainda nao existe nada no sistema que gere notificacoes (Adocao e Eventos estao vazias),
// entao por enquanto a lista so e preenchida por quem chamar adicionarNotificacao
public class SubTelaNotificacoes {
    private final Administrador admin;
    private final List<String> notificacoes = new ArrayList<>();

    public SubTelaNotificacoes(Administrador admin) {
        this.admin = admin;
    }

    public void adicionarNotificacao(String mensagem) {
        if (mensagem == null || mensagem.isBlank()) {
            throw new IllegalArgumentException("Notificacao nao pode ser vazia");
        }
        notificacoes.add(mensagem);
    }

    public void exibir() {
        if (notificacoes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma notificacao para " + admin.getNome(),
                    "Notificacoes", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder texto = new StringBuilder();
        for (String n : notificacoes) {
            texto.append("- ").append(n).append("\n");
        }
        JOptionPane.showMessageDialog(null, texto.toString(), "Notificacoes", JOptionPane.INFORMATION_MESSAGE);
    }
}

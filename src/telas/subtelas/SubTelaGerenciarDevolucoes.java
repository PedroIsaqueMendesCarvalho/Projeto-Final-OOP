package telas.subtelas;
import adocao.*;
import java.util.*;
import javax.swing.*;
import telas.*;

public class SubTelaGerenciarDevolucoes {
    private final AdocoesCadastradas adocoesCadastradas;

    public SubTelaGerenciarDevolucoes(AdocoesCadastradas adocoesCadastradas) {
        this.adocoesCadastradas = adocoesCadastradas;
    }

    public void exibir() {
        while (true) {
            // so adocoes aprovadas podem ser devolvidas
            List<Adocao> aprovadas = adocoesCadastradas.getPorStatus(StatusAdocao.APROVADA);
            if (aprovadas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhuma adocao aprovada no momento");
                return;
            }

            String[] opcoes = new String[aprovadas.size() + 1];
            for (int i = 0; i < aprovadas.size(); i++) {
                Adocao a = aprovadas.get(i);
                opcoes[i] = a.getAnimal().getNome() + " - " + a.getCandidato().getNome()
                          + " (adotado em " + a.getDataConclusao().format(Dialogos.FORMATO_DATA) + ")";
            }
            opcoes[aprovadas.size()] = "Voltar";

            int escolha = Dialogos.escolherOpcao("Gerenciar devolucoes", "Escolha a adocao a ser devolvida", opcoes);
            if (escolha < 0 || escolha >= aprovadas.size()) {
                return; // usuario clicou em Voltar ou fechou a janela
            }

            Adocao adocao = aprovadas.get(escolha);
            String motivo = JOptionPane.showInputDialog(null, "Motivo da devolucao de " + adocao.getAnimal().getNome() + ":",
                                                        "Registrar devolucao", JOptionPane.PLAIN_MESSAGE);
            if (motivo == null) {
                continue; // cancelou, volta para a lista
            }

            try {
                // guarda o motivo e a data, e o animal volta a ficar disponivel
                adocao.registrarDevolucao(motivo);
                JOptionPane.showMessageDialog(null, adocao.getAnimal().getNome() + " voltou a ficar disponivel para adocao.");
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

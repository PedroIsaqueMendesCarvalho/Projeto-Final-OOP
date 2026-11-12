package telas.subtelas;
import adocao.*;
import java.util.*;
import javax.swing.*;
import telas.*;
import usuarios.*;

public class SubTelaAprovarAdocoes {
    private final Administrador admin; // aprovar e recusar exigem um administrador
    private final AdocoesCadastradas adocoesCadastradas;

    public SubTelaAprovarAdocoes(Administrador admin, AdocoesCadastradas adocoesCadastradas) {
        this.admin = admin;
        this.adocoesCadastradas = adocoesCadastradas;
    }

    public void exibir() {
        while (true) {
            List<Adocao> pendentes = adocoesCadastradas.getPorStatus(StatusAdocao.PENDENTE);
            if (pendentes.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhuma solicitacao de adocao pendente");
                return;
            }

            String[] opcoes = new String[pendentes.size() + 1];
            for (int i = 0; i < pendentes.size(); i++) {
                Adocao a = pendentes.get(i);
                opcoes[i] = a.getAnimal().getNome() + " - " + a.getCandidato().getNome()
                          + " (" + a.getCandidato().getCpf() + ")";
            }
            opcoes[pendentes.size()] = "Voltar";

            int escolha = Dialogos.escolherOpcao("Aprovar adocoes", "Escolha a solicitacao", opcoes);
            if (escolha < 0 || escolha >= pendentes.size()) {
                return; // usuario clicou em Voltar ou fechou a janela
            }

            Adocao adocao = pendentes.get(escolha);
            int decisao = Dialogos.escolherOpcao("Solicitacao de adocao", detalhes(adocao),
                                                 new String[]{"Aprovar", "Recusar", "Voltar"});
            try {
                if (decisao == 0) {
                    // pelo AdocoesCadastradas, para recusar as outras solicitacoes do mesmo animal
                    adocoesCadastradas.aprovar(adocao, admin);
                    JOptionPane.showMessageDialog(null, "Adocao de " + adocao.getAnimal().getNome() + " aprovada!");
                } else if (decisao == 1) {
                    adocao.recusar(admin);
                    JOptionPane.showMessageDialog(null, "Solicitacao recusada.");
                }
            } catch (IllegalStateException | IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // JLabel nao entende \n, mas aceita HTML: <br> quebra a linha
    private String detalhes(Adocao adocao) {
        Candidato c = adocao.getCandidato();
        return "<html>Animal: " + adocao.getAnimal().getNome() + " (" + adocao.getAnimal().getEspecie() + ")"
             + "<br>Candidato: " + c.getNome()
             + "<br>CPF: " + c.getCpf()
             + "<br>Telefone: " + c.getTelefone()
             + "<br>Email: " + c.getEmail()
             + "<br>Solicitada em " + adocao.getDataSolicitacao().format(Dialogos.FORMATO_DATA) + "</html>";
    }
}

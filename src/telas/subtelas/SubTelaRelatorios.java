package telas.subtelas;
import adocao.*;
import animais.*;
import animais.enums.*;
import controledesaude.*;
import java.time.*;
import java.time.format.*;
import javax.swing.*;
import telas.*;
import usuarios.*;

public class SubTelaRelatorios {
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final int DIAS_ALERTA_VACINA = 30;
    private final UsuariosCadastrados usuariosCadastrados;
    private final AnimaisCadastrados animaisCadastrados;
    private final AdocoesCadastradas adocoesCadastradas;

    public SubTelaRelatorios(UsuariosCadastrados usuariosCadastrados, AnimaisCadastrados animaisCadastrados,
                             AdocoesCadastradas adocoesCadastradas) {
        this.usuariosCadastrados = usuariosCadastrados;
        this.animaisCadastrados = animaisCadastrados;
        this.adocoesCadastradas = adocoesCadastradas;
    }

    public void exibir() {
        String[] opcoes = {"Animais disponiveis", "Animais adotados", "Vacinas proximas",
                           "Atendimentos por animal", "Usuarios cadastrados",
                           "Historico de adocoes", "Candidatos", "Voltar"};
        while (true) {
            int escolha = Dialogos.escolherOpcao("Relatorios", "Escolha o relatorio", opcoes);

            switch (escolha) {
                case 0 -> mostrar("Animais disponiveis", relatorioPorStatus(Disponibilidade.DISPONIVEL));
                case 1 -> mostrar("Animais adotados", relatorioPorStatus(Disponibilidade.ADOTADO));
                case 2 -> mostrar("Vacinas proximas", relatorioVacinasProximas());
                case 3 -> mostrar("Atendimentos por animal", relatorioAtendimentos());
                case 4 -> mostrar("Usuarios cadastrados", relatorioUsuarios());
                case 5 -> mostrar("Historico de adocoes", relatorioAdocoes());
                case 6 -> mostrar("Candidatos", relatorioCandidatos());
                default -> {
                    return; // usuario clicou em Voltar ou fechou a janela
                }
            }
        }
    }

    private String relatorioPorStatus(Disponibilidade status) {
        StringBuilder texto = new StringBuilder();
        for (Animal a : animaisCadastrados.getAnimais()) {
            if (a.getStatusDeAdocao() == status) {
                texto.append(a.getNome()).append(" - ").append(a.getEspecie()).append(", ").append(a.getRaca())
                     .append(", resgatado em ").append(a.getDataDeResgate().format(FORMATO_DATA)).append("\n");
            }
        }
        return texto.isEmpty() ? "Nenhum animal encontrado" : texto.toString();
    }

    // vacinas cuja proxima aplicacao esta atrasada ou vence nos proximos dias
    private String relatorioVacinasProximas() {
        LocalDate limite = LocalDate.now().plusDays(DIAS_ALERTA_VACINA);
        StringBuilder texto = new StringBuilder();
        for (Animal a : animaisCadastrados.getAnimais()) {
            for (Vacina v : a.getControleDeSaude().getVacinas()) {
                LocalDate proxima = v.getProximaAplicacao();
                if (proxima != null && !proxima.isAfter(limite)) {
                    texto.append(a.getNome()).append(" - ").append(v.getNomeVacina())
                         .append(" em ").append(proxima.format(FORMATO_DATA));
                    if (proxima.isBefore(LocalDate.now())) {
                        texto.append(" (ATRASADA)");
                    }
                    texto.append("\n");
                }
            }
        }
        return texto.isEmpty() ? "Nenhuma vacina nos proximos " + DIAS_ALERTA_VACINA + " dias" : texto.toString();
    }

    private String relatorioAtendimentos() {
        StringBuilder texto = new StringBuilder();
        for (Animal a : animaisCadastrados.getAnimais()) {
            ControleDeSaude saude = a.getControleDeSaude();
            texto.append(a.getNome()).append(": ")
                 .append(saude.getVacinas().size()).append(" vacinas, ")
                 .append(saude.getConsultas().size()).append(" consultas, ")
                 .append(saude.getMedicamentos().size()).append(" medicamentos, ")
                 .append(saude.getCirurgias().size()).append(" cirurgias\n");
        }
        return texto.isEmpty() ? "Nenhum animal cadastrado" : texto.toString();
    }

    private String relatorioUsuarios() {
        StringBuilder texto = new StringBuilder();
        for (Usuarios u : usuariosCadastrados.getUsuarios()) {
            texto.append(u.getClass().getSimpleName()).append(" - ")
                 .append(u.getNome()).append(" (").append(u.getEmail()).append(")\n");
        }
        return texto.isEmpty() ? "Nenhum usuario cadastrado" : texto.toString();
    }

    // todas as adocoes, em qualquer status, na ordem em que foram solicitadas
    private String relatorioAdocoes() {
        StringBuilder texto = new StringBuilder();
        for (Adocao a : adocoesCadastradas.getAdocoes()) {
            texto.append(a.getAnimal().getNome()).append(" - ").append(a.getCandidato().getNome())
                 .append(" | ").append(a.getStatus())
                 .append(" | solicitada em ").append(a.getDataSolicitacao().format(FORMATO_DATA));
            // dataConclusao e null enquanto a adocao esta pendente
            if (a.getDataConclusao() != null) {
                texto.append(" | concluida em ").append(a.getDataConclusao().format(FORMATO_DATA))
                     .append(" por ").append(a.getResponsavel().getNome());
            }
            if (a.getStatus() == StatusAdocao.DEVOLVIDA) {
                texto.append(" | devolvida em ").append(a.getDataDevolucao().format(FORMATO_DATA))
                     .append(": ").append(a.getMotivoDevolucao());
            }
            texto.append("\n");
        }
        return texto.isEmpty() ? "Nenhuma adocao registrada" : texto.toString();
    }

    private String relatorioCandidatos() {
        StringBuilder texto = new StringBuilder();
        for (Candidato c : adocoesCadastradas.getCandidatos()) {
            int solicitacoes = 0;
            for (Adocao a : adocoesCadastradas.getAdocoes()) {
                if (a.getCandidato() == c) {
                    solicitacoes++;
                }
            }
            texto.append(c.getNome()).append(" - CPF ").append(c.getCpf())
                 .append(", ").append(c.getTelefone()).append(", ").append(c.getEmail())
                 .append(" | ").append(solicitacoes).append(" solicitacao(oes)\n");
        }
        return texto.isEmpty() ? "Nenhum candidato cadastrado" : texto.toString();
    }

    private void mostrar(String titulo, String texto) {
        JOptionPane.showMessageDialog(null, texto, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
}

package telas.subtelas;
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

    public SubTelaRelatorios(UsuariosCadastrados usuariosCadastrados, AnimaisCadastrados animaisCadastrados) {
        this.usuariosCadastrados = usuariosCadastrados;
        this.animaisCadastrados = animaisCadastrados;
    }

    public void exibir() {
        String[] opcoes = {"Animais disponiveis", "Animais adotados", "Vacinas proximas",
                           "Atendimentos por animal", "Usuarios cadastrados", "Voltar"};
        while (true) {
            int escolha = Dialogos.escolherOpcao("Relatorios", "Escolha o relatorio", opcoes);

            switch (escolha) {
                case 0 -> mostrar("Animais disponiveis", relatorioPorStatus(Disponibilidade.DISPONIVEL));
                case 1 -> mostrar("Animais adotados", relatorioPorStatus(Disponibilidade.ADOTADO));
                case 2 -> mostrar("Vacinas proximas", relatorioVacinasProximas());
                case 3 -> mostrar("Atendimentos por animal", relatorioAtendimentos());
                case 4 -> mostrar("Usuarios cadastrados", relatorioUsuarios());
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

    private void mostrar(String titulo, String texto) {
        JOptionPane.showMessageDialog(null, texto, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
}

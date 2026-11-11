package telas.subtelas;
import animais.*;
import controledesaude.*;
import javax.swing.*;
import telas.*;

// usada pelos menus do administrador, veterinario e voluntario
public class SubTelaHistoricoDeSaude {
    private final AnimaisCadastrados animaisCadastrados;

    public SubTelaHistoricoDeSaude(AnimaisCadastrados animaisCadastrados) {
        this.animaisCadastrados = animaisCadastrados;
    }

    public void exibir() {
        Animal animal = Dialogos.escolherAnimal("Historico de saude", animaisCadastrados.getAnimais().toArray(new Animal[0]));
        if (animal == null) {
            return;
        }

        ControleDeSaude saude = animal.getControleDeSaude();
        saude.orderByData();
        StringBuilder texto = new StringBuilder("Historico de " + animal.getNome() + "\n");

        texto.append("\nVacinas:\n");
        for (Vacina v : saude.getVacinas()) {
            texto.append("- ").append(v.getNomeVacina()).append(" em ").append(v.getDataAplicacao().format(Dialogos.FORMATO_DATA));
            if (v.getProximaAplicacao() != null) {
                texto.append(" (proxima: ").append(v.getProximaAplicacao().format(Dialogos.FORMATO_DATA)).append(")");
            }
            texto.append("\n");
        }
        texto.append("\nConsultas:\n");
        for (Consulta c : saude.getConsultas()) {
            texto.append("- ").append(c.getData().format(Dialogos.FORMATO_DATA)).append(" ").append(c.getHorario())
                 .append(" - ").append(c.getTipoDeConsulta()).append(": ").append(c.getDiagnostico()).append("\n");
            // a prescricao e opcional, so aparece quando foi preenchida
            if (!c.getPrescricao().isBlank()) {
                texto.append("    Prescricao: ").append(c.getPrescricao()).append("\n");
            }
        }
        texto.append("\nMedicamentos:\n");
        for (Medicamento m : saude.getMedicamentos()) {
            texto.append("- ").append(m.getNomeMedicamento()).append(" (").append(m.getDosagem())
                 .append(") em ").append(m.getDataAplicacao().format(Dialogos.FORMATO_DATA)).append("\n");
        }
        texto.append("\nCirurgias:\n");
        for (Cirurgia c : saude.getCirurgias()) {
            texto.append("- ").append(c.getNomeCirurgia()).append(" em ").append(c.getDataCirurgia().format(Dialogos.FORMATO_DATA))
                 .append(": ").append(c.getDescricao()).append("\n");
        }
        JOptionPane.showMessageDialog(null, texto.toString(), "Historico de saude", JOptionPane.INFORMATION_MESSAGE);
    }
}

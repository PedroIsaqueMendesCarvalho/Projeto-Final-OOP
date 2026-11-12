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
            texto.append("- " + v.getNomeVacina() + " em " + v.getDataAplicacao().format(Dialogos.FORMATO_DATA));
            if (v.getProximaAplicacao() != null) {
                texto.append(" (proxima: " + v.getProximaAplicacao().format(Dialogos.FORMATO_DATA) + ")");
            }
            texto.append("\n");
        }
        texto.append("\nConsultas:\n");
        for (Consulta c : saude.getConsultas()) {
            texto.append("- " + c.getData().format(Dialogos.FORMATO_DATA) + " " + c.getHorario()
                         + " - " + c.getTipoDeConsulta() + ": " + c.getDiagnostico() + "\n");
            // a prescricao e opcional, so aparece quando foi preenchida
            if (!c.getPrescricao().isBlank()) {
                texto.append("    Prescricao: " + c.getPrescricao() + "\n");
            }
        }
        texto.append("\nMedicamentos:\n");
        for (Medicamento m : saude.getMedicamentos()) {
            texto.append("- " + m.getNomeMedicamento() + " (" + m.getDosagem() + ") em "
                         + m.getDataAplicacao().format(Dialogos.FORMATO_DATA) + "\n");
        }
        texto.append("\nCirurgias:\n");
        for (Cirurgia c : saude.getCirurgias()) {
            texto.append("- " + c.getNomeCirurgia() + " em " + c.getDataCirurgia().format(Dialogos.FORMATO_DATA)
                         + ": " + c.getDescricao() + "\n");
        }
        JOptionPane.showMessageDialog(null, texto.toString(), "Historico de saude", JOptionPane.INFORMATION_MESSAGE);
    }
}

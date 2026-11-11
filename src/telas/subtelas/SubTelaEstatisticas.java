package telas.subtelas;
import animais.*;
import animais.enums.*;
import javax.swing.*;
import usuarios.*;

public class SubTelaEstatisticas {
    private final UsuariosCadastrados usuariosCadastrados;
    private final AnimaisCadastrados animaisCadastrados;

    public SubTelaEstatisticas(UsuariosCadastrados usuariosCadastrados, AnimaisCadastrados animaisCadastrados) {
        this.usuariosCadastrados = usuariosCadastrados;
        this.animaisCadastrados = animaisCadastrados;
    }

    public void exibir() {
        int total = 0, caes = 0, gatos = 0, disponiveis = 0, adotados = 0, somaIdades = 0;
        int pequenos = 0, medios = 0, grandes = 0;
        for (Animal a : animaisCadastrados.getAnimais()) {
            total++;
            somaIdades += a.getIdade();
            if (a instanceof Cao) {
                caes++;
            } else if (a instanceof Gato) {
                gatos++;
            }
            if (a.getStatusDeAdocao() == Disponibilidade.DISPONIVEL) {
                disponiveis++;
            } else {
                adotados++;
            }
            // switch com enum: os cases usam o nome da constante, sem "Porte."
            switch (a.getPorte()) {
                case PEQUENO -> pequenos++;
                case MEDIO -> medios++;
                case GRANDE -> grandes++;
            }
        }

        int administradores = 0, veterinarios = 0, voluntarios = 0;
        for (Usuarios u : usuariosCadastrados.getUsuarios()) {
            if (u instanceof Administrador) {
                administradores++;
            } else if (u instanceof Veterinario) {
                veterinarios++;
            } else if (u instanceof Voluntario) {
                voluntarios++;
            }
        }

        StringBuilder texto = new StringBuilder();
        texto.append("ANIMAIS\n");
        texto.append("Total: ").append(total).append("\n");
        texto.append("Caes: ").append(caes).append(" | Gatos: ").append(gatos).append("\n");
        texto.append("Disponiveis: ").append(disponiveis).append(" | Adotados: ").append(adotados).append("\n");
        texto.append("Porte pequeno: ").append(pequenos).append(" | medio: ").append(medios)
             .append(" | grande: ").append(grandes).append("\n");
        if (total > 0) {
            texto.append(String.format("Idade media: %.1f anos%n", (double) somaIdades / total));
            texto.append(String.format("Taxa de adocao: %.0f%%%n", 100.0 * adotados / total));
        }
        texto.append("\nUSUARIOS\n");
        texto.append("Administradores: ").append(administradores).append("\n");
        texto.append("Veterinarios: ").append(veterinarios).append("\n");
        texto.append("Voluntarios: ").append(voluntarios).append("\n");

        JOptionPane.showMessageDialog(null, texto.toString(), "Estatisticas", JOptionPane.INFORMATION_MESSAGE);
    }
}

package telas;
import animais.*;
import animais.enums.*;
import javax.swing.*;
import telas.subtelas.*;
import usuarios.*;

public class MenuVoluntario {
    private final Voluntario vol;
    private final AnimaisCadastrados animaisCadastrados;

    public MenuVoluntario(Voluntario vol, AnimaisCadastrados animaisCadastrados) {
        this.vol = vol;
        this.animaisCadastrados = animaisCadastrados;
    }

    public void exibir() {
        String[] opcoes = {"Ver animais disponiveis", "Ver historico de saude", "Sair"};
        while (true) {
            int escolha = Dialogos.escolherOpcao("Menu Voluntario", "Bem-vindo(a), " + vol.getNome(), opcoes);

            switch (escolha) {
                case 0 -> listarDisponiveis();
                case 1 -> new SubTelaHistoricoDeSaude(animaisCadastrados).exibir();
                default -> {
                    return; // usuario clicou em Sair ou fechou a janela
                }
            }
        }
    }

    private void listarDisponiveis() {
        StringBuilder texto = new StringBuilder();
        for (Animal a : animaisCadastrados.getAnimais()) {
            if (a.getStatusDeAdocao() == Disponibilidade.DISPONIVEL) {
                texto.append(a.getNome()).append(" - ").append(a.getEspecie())
                     .append(", ").append(a.getRaca())
                     .append(", ").append(a.getIdade()).append(" anos\n");
            }
        }
        if (texto.isEmpty()) {
            texto.append("Nenhum animal disponivel para adocao");
        }
        JOptionPane.showMessageDialog(null, texto.toString(), "Animais disponiveis", JOptionPane.INFORMATION_MESSAGE);
    }
}

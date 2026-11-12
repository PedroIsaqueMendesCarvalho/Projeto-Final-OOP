package telas;
import adocao.*;
import animais.*;
import animais.enums.*;
import java.util.*;
import javax.swing.*;
import telas.subtelas.*;
import usuarios.*;

public class MenuVoluntario {
    private final Voluntario vol;
    private final AnimaisCadastrados animaisCadastrados;
    private final AdocoesCadastradas adocoesCadastradas;

    public MenuVoluntario(Voluntario vol, AnimaisCadastrados animaisCadastrados, AdocoesCadastradas adocoesCadastradas) {
        this.vol = vol;
        this.animaisCadastrados = animaisCadastrados;
        this.adocoesCadastradas = adocoesCadastradas;
    }

    public void exibir() {
        String[] opcoes = {"Ver animais disponiveis", "Registrar solicitacao de adocao", "Ver historico de saude", "Sair"};
        while (true) {
            int escolha = Dialogos.escolherOpcao("Menu Voluntario", "Bem-vindo(a), " + vol.getNome(), opcoes);

            switch (escolha) {
                case 0 -> listarDisponiveis();
                case 1 -> registrarSolicitacao();
                case 2 -> new SubTelaHistoricoDeSaude(animaisCadastrados).exibir();
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

    // o voluntario registra o pedido de um candidato (pessoa de fora, sem login no sistema)
    private void registrarSolicitacao() {
        // animal adotado nao pode receber solicitacoes, entao so os disponiveis aparecem
        List<Animal> disponiveis = new ArrayList<>();
        for (Animal a : animaisCadastrados.getAnimais()) {
            if (a.getStatusDeAdocao() == Disponibilidade.DISPONIVEL) {
                disponiveis.add(a);
            }
        }
        Animal animal = Dialogos.escolherAnimal("Solicitacao de adocao", disponiveis.toArray(new Animal[0]));
        if (animal == null) {
            return;
        }

        JTextField campoNome = new JTextField();
        JTextField campoCpf = new JTextField();
        JTextField campoTelefone = new JTextField();
        JTextField campoEmail = new JTextField();
        String[] rotulos = {"Nome do candidato:", "CPF (000.000.000-00):", "Telefone ((11) 98765-4321):", "Email:"};
        JComponent[] campos = {campoNome, campoCpf, campoTelefone, campoEmail};

        while (Dialogos.mostrarFormulario("Solicitar adocao de " + animal.getNome(), rotulos, campos)) {
            try {
                // metodo default da interface Cadastro, que o Voluntario implementa
                Candidato candidato = vol.CadastroDeCandidato(campoNome.getText(), campoCpf.getText(),
                                                              campoEmail.getText(), campoTelefone.getText());
                adocoesCadastradas.solicitar(animal, candidato);
                JOptionPane.showMessageDialog(null, "Solicitacao registrada! Aguardando aprovacao de um administrador.");
                return;
            } catch (IllegalArgumentException e) {
                // validacoes do Candidato e regras do AdocoesCadastradas
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

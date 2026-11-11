package telas.subtelas;
import animais.*;
import animais.enums.*;
import java.util.*;
import javax.swing.*;
import telas.*;
import usuarios.*;

// versao simples: o motivo e pedido mas ainda nao e guardado (Adocao ja tem o campo motivoDevolucao para isso)
public class SubTelaGerenciarDevolucoes {
    private final UsuariosCadastrados usuariosCadastrados;
    private final AnimaisCadastrados animaisCadastrados;

    public SubTelaGerenciarDevolucoes(UsuariosCadastrados usuariosCadastrados, AnimaisCadastrados animaisCadastrados) {
        this.usuariosCadastrados = usuariosCadastrados;
        this.animaisCadastrados = animaisCadastrados;
    }

    public void exibir() {
        while (true) {
            List<Animal> adotados = new ArrayList<>();
            for (Animal a : animaisCadastrados.getAnimais()) {
                if (a.getStatusDeAdocao() == Disponibilidade.ADOTADO) {
                    adotados.add(a);
                }
            }
            if (adotados.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum animal adotado no momento");
                return;
            }

            String[] nomes = new String[adotados.size() + 1];
            for (int i = 0; i < adotados.size(); i++) {
                nomes[i] = adotados.get(i).getNome() + " (" + adotados.get(i).getEspecie() + ")";
            }
            nomes[adotados.size()] = "Voltar";

            int escolha = Dialogos.escolherOpcao("Gerenciar devolucoes", "Escolha o animal devolvido", nomes);
            if (escolha < 0 || escolha >= adotados.size()) {
                return; // usuario clicou em Voltar ou fechou a janela
            }

            Animal animal = adotados.get(escolha);
            String motivo = JOptionPane.showInputDialog(null, "Motivo da devolucao de " + animal.getNome() + ":",
                                                        "Registrar devolucao", JOptionPane.PLAIN_MESSAGE);
            if (motivo == null) {
                continue; // cancelou, volta para a lista
            }
            if (motivo.isBlank()) {
                JOptionPane.showMessageDialog(null, "O motivo nao pode ser vazio", "Erro", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            animal.setStatusDeAdocao(Disponibilidade.DISPONIVEL); // volta a ficar disponivel
            JOptionPane.showMessageDialog(null, animal.getNome() + " voltou a ficar disponivel para adocao.\nMotivo: " + motivo);
        }
    }
}

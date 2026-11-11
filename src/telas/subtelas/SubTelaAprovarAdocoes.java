package telas.subtelas;
import animais.*;
import animais.enums.*;
import java.util.*;
import javax.swing.*;
import telas.*;
import usuarios.*;

// versao simples: enquanto a classe Adocao nao estiver pronta, aprovar so muda o status do animal
public class SubTelaAprovarAdocoes {
    private final UsuariosCadastrados usuariosCadastrados;
    private final AnimaisCadastrados animaisCadastrados;

    public SubTelaAprovarAdocoes(UsuariosCadastrados usuariosCadastrados, AnimaisCadastrados animaisCadastrados) {
        this.usuariosCadastrados = usuariosCadastrados;
        this.animaisCadastrados = animaisCadastrados;
    }

    public void exibir() {
        while (true) {
            List<Animal> disponiveis = new ArrayList<>(); // lista de animais disponiveis para adocao
            for (Animal a : animaisCadastrados.getAnimais()) {
                if (a.getStatusDeAdocao() == Disponibilidade.DISPONIVEL) {
                    disponiveis.add(a);
                }
            }
            if (disponiveis.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum animal disponivel para adocao");
                return;
            }

            String[] nomes = new String[disponiveis.size() + 1];
            for (int i = 0; i < disponiveis.size(); i++) {
                nomes[i] = disponiveis.get(i).getNome() + " (" + disponiveis.get(i).getEspecie() + ")";
            }
            nomes[disponiveis.size()] = "Voltar";

            int escolha = Dialogos.escolherOpcao("Aprovar adocoes", "Escolha o animal a ser adotado", nomes);// usa o metodo da classe Dialogos para mostrar os botoes e devolver o indice do escolhido
            if (escolha < 0 || escolha >= disponiveis.size()) {
                return; 
            }

            Animal animal = disponiveis.get(escolha);
            int confirmacao = JOptionPane.showConfirmDialog(null,
                    "Confirmar a adocao de " + animal.getNome() + "?", "Aprovar adocao", JOptionPane.YES_NO_OPTION);
            if (confirmacao == JOptionPane.YES_OPTION) {
                animal.setStatusDeAdocao(Disponibilidade.ADOTADO);
                JOptionPane.showMessageDialog(null, "Adocao de " + animal.getNome() + " aprovada!");
            }
        }
    }
}

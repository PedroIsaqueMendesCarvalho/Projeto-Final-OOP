package telas;
import adocao.*;
import animais.*;
import usuarios.*;
public class TelaPrincipal {
    private final UsuariosCadastrados usuariosCadastrados;
    private final AnimaisCadastrados animaisCadastrados;
    private final AdocoesCadastradas adocoesCadastradas;

    public TelaPrincipal(UsuariosCadastrados usuariosCadastrados, AnimaisCadastrados animaisCadastrados,
                         AdocoesCadastradas adocoesCadastradas) {
        this.usuariosCadastrados = usuariosCadastrados;
        this.animaisCadastrados = animaisCadastrados;
        this.adocoesCadastradas = adocoesCadastradas;
    }

    public void exibir() {
        String[] opcoes = {"Entrar", "Cadastrar-se", "Sair"};
        while (true) {
            int escolha = Dialogos.escolherOpcao("Inicio", "Bem-vindo ao sistema da ONG", opcoes);

            if (escolha == 0) {
                new TelaLogin(usuariosCadastrados, animaisCadastrados, adocoesCadastradas).exibir();
            } else if (escolha == 1) {
                new TelaCadastro(usuariosCadastrados).exibir();
            } else {
                return; // usuario clicou em Sair ou fechou a janela
            }
        }
    }
}

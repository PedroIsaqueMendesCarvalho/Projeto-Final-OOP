package telas;
import animais.*;
import usuarios.*;
public class TelaPrincipal {
    private final UsuariosCadastrados usuariosCadastrados;
    private final AnimaisCadastrados animaisCadastrados;

    public TelaPrincipal(UsuariosCadastrados usuariosCadastrados, AnimaisCadastrados animaisCadastrados) {
        this.usuariosCadastrados = usuariosCadastrados;
        this.animaisCadastrados = animaisCadastrados;
    }

    public void exibir() {
        String[] opcoes = {"Entrar", "Cadastrar-se", "Sair"};
        while (true) {
            int escolha = Dialogos.escolherOpcao("Inicio", "Bem-vindo ao sistema da ONG", opcoes);

            if (escolha == 0) {
                new TelaLogin(usuariosCadastrados, animaisCadastrados).exibir();
            } else if (escolha == 1) {
                new TelaCadastro(usuariosCadastrados).exibir();
            } else {
                return; // usuario clicou em Sair ou fechou a janela
            }
        }
    }
}

package telas;
import usuarios.*;
public class TelaPrincipal {
    private final UsuariosCadastrados usuariosCadastrados;

    public TelaPrincipal(UsuariosCadastrados usuariosCadastrados) {
        this.usuariosCadastrados = usuariosCadastrados;
    }

    public void exibir() {
        String[] opcoes = {"Entrar", "Cadastrar-se", "Sair"};
        while (true) {
            int escolha = Dialogos.escolherOpcao("Inicio", "Bem-vindo ao sistema da ONG", opcoes);

            if (escolha == 0) {
                new TelaLogin(usuariosCadastrados).exibir();
            } else if (escolha == 1) {
                new TelaCadastro(usuariosCadastrados).exibir();
            } else {
                return; // usuario clicou em Sair ou fechou a janela
            }
        }
    }
}

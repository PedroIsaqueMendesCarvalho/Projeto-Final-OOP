package telas;
import telas.subtelas.*;
import usuarios.*;
import java.awt.*;
import javax.swing.*;
import animais.*;

public class MenuAdministrador {
    private final Administrador admin;
    private final UsuariosCadastrados usuariosCadastrados;
    private final AnimaisCadastrados animaisCadastrados;
    public MenuAdministrador(Administrador admin, UsuariosCadastrados usuariosCadastrados, AnimaisCadastrados animaisCadastrados) {
        this.admin = admin;
        this.usuariosCadastrados = usuariosCadastrados;
        this.animaisCadastrados = animaisCadastrados;
    }

    public void exibir() {
        String[] opcoes = {"Gerenciar animais", "Gerenciar usuários", "Aprovar adoções", "Gerenciar devoluções", "Relatórios", "Estatísticas", "Notificações", "Sair"};
        while (true) {
            int escolha = Dialogos.escolherOpcao("Menu Administrador", "Bem-vindo, " + admin.getNome(), opcoes);

            switch (escolha) {
                case 0 -> new SubTelaGerenciarAnimais(animaisCadastrados).exibir();
                case 1 -> new SubTelaGerenciarUsuarios(usuariosCadastrados).exibir();
                case 2 -> new SubTelaAprovarAdocoes(usuariosCadastrados, animaisCadastrados).exibir();
                case 3 -> new SubTelaGerenciarDevolucoes(usuariosCadastrados, animaisCadastrados).exibir();
                case 4 -> new SubTelaRelatorios(usuariosCadastrados, animaisCadastrados).exibir();
                case 5 -> new SubTelaEstatisticas(usuariosCadastrados, animaisCadastrados).exibir();
                case 6 -> new SubTelaNotificacoes(admin).exibir();
                default -> {
                    return; // usuario clicou em Sair ou fechou a janela
                }
            }
        }
    }

}

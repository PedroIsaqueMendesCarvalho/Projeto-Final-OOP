package telas;
import adocao.*;
import telas.subtelas.*;
import usuarios.*;
import java.awt.*;
import javax.swing.*;
import animais.*;

public class MenuAdministrador {
    private final Administrador admin;
    private final UsuariosCadastrados usuariosCadastrados;
    private final AnimaisCadastrados animaisCadastrados;
    private final AdocoesCadastradas adocoesCadastradas;
    public MenuAdministrador(Administrador admin, UsuariosCadastrados usuariosCadastrados, AnimaisCadastrados animaisCadastrados,
                             AdocoesCadastradas adocoesCadastradas) {
        this.admin = admin;
        this.usuariosCadastrados = usuariosCadastrados;
        this.animaisCadastrados = animaisCadastrados;
        this.adocoesCadastradas = adocoesCadastradas;
    }

    public void exibir() {
        String[] opcoes = {"Gerenciar animais", "Gerenciar usuários", "Aprovar adoções", "Gerenciar devoluções", "Relatórios", "Estatísticas", "Notificações", "Sair"};
        while (true) {
            int escolha = Dialogos.escolherOpcao("Menu Administrador", "Bem-vindo, " + admin.getNome(), opcoes);

            switch (escolha) {
                case 0 -> new SubTelaGerenciarAnimais(animaisCadastrados).exibir();
                case 1 -> new SubTelaGerenciarUsuarios(usuariosCadastrados).exibir();
                case 2 -> new SubTelaAprovarAdocoes(admin, adocoesCadastradas).exibir();
                case 3 -> new SubTelaGerenciarDevolucoes(adocoesCadastradas).exibir();
                case 4 -> new SubTelaRelatorios(usuariosCadastrados, animaisCadastrados, adocoesCadastradas).exibir();
                case 5 -> new SubTelaEstatisticas(usuariosCadastrados, animaisCadastrados).exibir();
                case 6 -> new SubTelaNotificacoes(admin).exibir();
                default -> {
                    return; // usuario clicou em Sair ou fechou a janela
                }
            }
        }
    }

}

package telas;
import adocao.*;
import animais.*;
import java.awt.*;
import javax.swing.*;
import usuarios.*;
public class TelaLogin {
    private final UsuariosCadastrados usuariosCadastrados;
    private final AnimaisCadastrados animaisCadastrados;
    private final AdocoesCadastradas adocoesCadastradas;

    public TelaLogin(UsuariosCadastrados usuariosCadastrados, AnimaisCadastrados animaisCadastrados,
                     AdocoesCadastradas adocoesCadastradas) {
        this.usuariosCadastrados = usuariosCadastrados;
        this.animaisCadastrados = animaisCadastrados;
        this.adocoesCadastradas = adocoesCadastradas;
    }

    public void exibir() {
        Usuarios logado = null;
        int tentativas = 0;
        while (logado == null && tentativas < 3) {
            // painel com os dois campos, mostrado dentro de uma unica janela
            JTextField campoEmail = new JTextField();
            JPasswordField campoSenha = new JPasswordField();
            JPanel painel = new JPanel(new GridLayout(0, 1));
            painel.add(new JLabel("Email:"));
            painel.add(campoEmail);
            painel.add(new JLabel("Senha:"));
            painel.add(campoSenha);

            int opcao = JOptionPane.showConfirmDialog(null, painel, "Login",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (opcao != JOptionPane.OK_OPTION) {
                return; // usuario clicou em Cancelar ou fechou a janela
            }

            String email = campoEmail.getText();
            String senha = new String(campoSenha.getPassword());

            try {
                logado = usuariosCadastrados.login(email, senha);
            } catch (SecurityException e) {
                tentativas++;
                JOptionPane.showMessageDialog(null,
                        e.getMessage() + " (" + (3 - tentativas) + " tentativas restantes)",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (logado == null) {
            JOptionPane.showMessageDialog(null, "Numero maximo de tentativas atingido.","Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(null, "Bem-vindo(a), " + logado.getNome() + "!");

        if (logado instanceof Administrador admin) {
            new MenuAdministrador(admin, usuariosCadastrados, animaisCadastrados, adocoesCadastradas).exibir();
        } else if (logado instanceof Veterinario vet) {
            new MenuVeterinario(vet, animaisCadastrados).exibir();
        } else if (logado instanceof Voluntario vol) {
            new MenuVoluntario(vol, animaisCadastrados, adocoesCadastradas).exibir();
        }
    }

    
}

import java.awt.GridLayout;
import javax.swing.*;
import telas.*;
import usuarios.*;
public class Main {
    public static void main(String[] args) {
        UsuariosCadastrados usuariosCadastrados = new UsuariosCadastrados();
        usuariosCadastrados.cadastrarUsuario(new Administrador("Admin", "123.456.789-09", "senhaadmin1",
                                                              "admin@ong.com", "(11) 98765-4321"));

        String[] opcoes = {"Entrar", "Cadastrar-se", "Sair"};
        while (true) {
            int escolha = JOptionPane.showOptionDialog(null, "Bem-vindo ao sistema da ONG", "Inicio",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

            if (escolha == 0) {
                new TelaInicial(usuariosCadastrados).exibir();
            } else if (escolha == 1) {
                fazerCadastro(usuariosCadastrados);
            } else {
                return; // usuario clicou em Sair ou fechou a janela
            }
        }
    }

    private static void fazerCadastro(UsuariosCadastrados usuariosCadastrados) {
        JTextField campoNome = new JTextField();
        JTextField campoCpf = new JTextField();
        JTextField campoEmail = new JTextField();
        JTextField campoTelefone = new JTextField();
        JPasswordField campoSenha = new JPasswordField();
        JPasswordField campoConfirmarSenha = new JPasswordField();
        //administradores sao gerenciados pelo sistema, possivelmente com um cadastro inicial. Talvez administradores possam cadastrar outros administradores no futuro.
        JComboBox<String> campoTipo = new JComboBox<>(new String[]{"Voluntario", "Veterinario"});

        JPanel painel = new JPanel(new GridLayout(0, 1));
        painel.add(new JLabel("Tipo de usuario:"));
        painel.add(campoTipo);
        painel.add(new JLabel("Nome:"));
        painel.add(campoNome);
        painel.add(new JLabel("CPF (000.000.000-00):"));
        painel.add(campoCpf);
        painel.add(new JLabel("Email:"));
        painel.add(campoEmail);
        painel.add(new JLabel("Telefone ((11) 98765-4321):"));
        painel.add(campoTelefone);
        painel.add(new JLabel("Senha (minimo 9 caracteres):"));
        painel.add(campoSenha);
        painel.add(new JLabel("Confirmar senha:"));
        painel.add(campoConfirmarSenha);

        while (true) {
            int opcao = JOptionPane.showConfirmDialog(null, painel, "Cadastro",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (opcao != JOptionPane.OK_OPTION) {
                return; // usuario clicou em Cancelar ou fechou a janela
            }

            String senha = new String(campoSenha.getPassword());
            String confirmarSenha = new String(campoConfirmarSenha.getPassword());
            if (!senha.equals(confirmarSenha)) {
                JOptionPane.showMessageDialog(null, "As senhas nao coincidem",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            try {
                Usuarios novo;
                if (campoTipo.getSelectedIndex() == 0) {
                    novo = new Voluntarios(campoNome.getText(), campoCpf.getText(), senha,
                                           campoEmail.getText(), campoTelefone.getText());
                } else {
                    novo = new Veterinario(campoNome.getText(), campoCpf.getText(), senha,
                                           campoEmail.getText(), campoTelefone.getText());
                }
                usuariosCadastrados.cadastrarUsuario(novo);
                JOptionPane.showMessageDialog(null, "Cadastro realizado! Faca login para continuar.");
                return;
            } catch (IllegalArgumentException e) {
                // as validacoes ficam no construtor de Usuarios
                JOptionPane.showMessageDialog(null, e.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

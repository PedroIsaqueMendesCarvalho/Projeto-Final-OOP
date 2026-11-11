package telas.subtelas;
import java.awt.*;
import javax.swing.*;
import telas.*;
import usuarios.*;

public class SubTelaGerenciarUsuarios {
    private final UsuariosCadastrados usuariosCadastrados;

    public SubTelaGerenciarUsuarios(UsuariosCadastrados usuariosCadastrados) {
        this.usuariosCadastrados = usuariosCadastrados;
    }

    public void exibir() {
        String[] opcoes = {"Listar usuarios", "Cadastrar usuario", "Voltar"};
        while (true) {
            int escolha = Dialogos.escolherOpcao("Gerenciar usuarios", "Escolha uma opcao", opcoes);

            switch (escolha) {
                case 0 -> listarUsuarios();
                case 1 -> cadastrarUsuario();
                default -> {
                    return; // usuario clicou em Voltar ou fechou a janela
                }
            }
        }
    }

    private void listarUsuarios() {
        StringBuilder texto = new StringBuilder();
        for (Usuarios u : usuariosCadastrados.getUsuarios()) {
            // getSimpleName devolve o nome da classe: Administrador, Veterinario ou Voluntario
            texto.append(u.getClass().getSimpleName()).append(" - ")
                 .append(u.getNome()).append(" (").append(u.getEmail()).append(")\n");
        }
        if (texto.isEmpty()) {
            texto.append("Nenhum usuario cadastrado");
        }
        JOptionPane.showMessageDialog(null, texto.toString(), "Usuarios cadastrados", JOptionPane.INFORMATION_MESSAGE);
    }

    // igual a TelaCadastro, mas aqui o administrador tambem pode criar outros administradores
    private void cadastrarUsuario() {
        JTextField campoNome = new JTextField();
        JTextField campoCpf = new JTextField();
        JTextField campoEmail = new JTextField();
        JTextField campoTelefone = new JTextField();
        JPasswordField campoSenha = new JPasswordField();
        JComboBox<String> campoTipo = new JComboBox<>(new String[]{"Voluntario", "Veterinario", "Administrador"});

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

        while (true) {
            int opcao = JOptionPane.showConfirmDialog(null, painel, "Cadastrar usuario",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (opcao != JOptionPane.OK_OPTION) {
                return; // usuario clicou em Cancelar ou fechou a janela
            }

            String senha = new String(campoSenha.getPassword());
            try {
                Usuarios novo = switch (campoTipo.getSelectedIndex()) {
                    case 0 -> new Voluntario(campoNome.getText(), campoCpf.getText(), senha,
                                             campoEmail.getText(), campoTelefone.getText());
                    case 1 -> new Veterinario(campoNome.getText(), campoCpf.getText(), senha,
                                              campoEmail.getText(), campoTelefone.getText());
                    default -> new Administrador(campoNome.getText(), campoCpf.getText(), senha,
                                                 campoEmail.getText(), campoTelefone.getText());
                };
                usuariosCadastrados.cadastrarUsuario(novo);
                JOptionPane.showMessageDialog(null, "Usuario cadastrado!");
                return;
            } catch (IllegalArgumentException e) {
                // as validacoes ficam no construtor de Usuarios
                JOptionPane.showMessageDialog(null, e.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

package telas;
import animais.*;
import controledesaude.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import javax.swing.*;
import telas.subtelas.*;
import usuarios.*;

public class MenuVeterinario {
    private final Veterinario vet;
    private final AnimaisCadastrados animaisCadastrados;

    public MenuVeterinario(Veterinario vet, AnimaisCadastrados animaisCadastrados) {
        this.vet = vet;
        this.animaisCadastrados = animaisCadastrados;
    }

    public void exibir() {
        String[] opcoes = {"Registrar vacina", "Registrar consulta", "Registrar medicamento",
                           "Registrar cirurgia", "Remover registro", "Ver historico de saude", "Sair"};
        while (true) {
            int escolha = Dialogos.escolherOpcao("Menu Veterinario", "Bem-vindo(a), " + vet.getNome(), opcoes);

            switch (escolha) {
                case 0 -> registrarVacina();
                case 1 -> registrarConsulta();
                case 2 -> registrarMedicamento();
                case 3 -> registrarCirurgia();
                case 4 -> removerRegistro();
                case 5 -> new SubTelaHistoricoDeSaude(animaisCadastrados).exibir();
                default -> {
                    return; // usuario clicou em Sair ou fechou a janela
                }
            }
        }
    }

    private void registrarVacina() {
        Animal animal = escolherAnimal("Registrar vacina");
        if (animal == null) {
            return;
        }
        JTextField campoNome = new JTextField();
        JTextField campoData = new JTextField(LocalDate.now().format(Dialogos.FORMATO_DATA));
        JTextField campoProxima = new JTextField();
        String[] rotulos = {"Nome da vacina:", "Data de aplicacao (dd/mm/aaaa):", "Proxima aplicacao (opcional):"};
        JTextField[] campos = {campoNome, campoData, campoProxima};

        while (Dialogos.mostrarFormulario("Vacina de " + animal.getNome(), rotulos, campos)) {
            try {
                // proxima aplicacao em branco vira null, a classe Vacina aceita isso
                LocalDate proxima = campoProxima.getText().isBlank() ? null : lerData(campoProxima);
                vet.registrarVacina(animal, campoNome.getText(), lerData(campoData), proxima);
                JOptionPane.showMessageDialog(null, "Vacina registrada!");
                return;
            } catch (DateTimeParseException e) {
                mostrarErro("Data invalida, use o formato dd/mm/aaaa");
            } catch (IllegalArgumentException e) {
                mostrarErro(e.getMessage()); // as validacoes ficam nas classes do controledesaude
            }
        }
    }

    private void removerRegistro() {
        String[] tipos = {"Vacina", "Consulta", "Medicamento", "Cirurgia", "Voltar"};
        int tipo = Dialogos.escolherOpcao("Remover registro", "Qual tipo de registro?", tipos);
        if (tipo < 0 || tipo > 3) {
            return; // usuario clicou em Voltar ou fechou a janela
        }
        Animal animal = escolherAnimal("Remover " + tipos[tipo].toLowerCase());
        if (animal == null) {
            return;
        }

        // cada tipo monta a descricao dos seus registros; a escolha e a confirmacao sao iguais para todos
        ControleDeSaude saude = animal.getControleDeSaude();
        int i; // indice do registro escolhido, ou -1 se o usuario desistiu
        switch (tipo) {
            case 0 -> {
                List<Vacina> vacinas = saude.getVacinas();
                String[] descricoes = new String[vacinas.size()];
                for (int j = 0; j < vacinas.size(); j++) {
                    descricoes[j] = vacinas.get(j).getNomeVacina() + " - "
                                  + vacinas.get(j).getDataAplicacao().format(Dialogos.FORMATO_DATA);
                }
                i = escolherParaRemover(animal, descricoes);
                if (i >= 0) {
                    vet.removerVacina(animal, vacinas.get(i));
                }
            }
            case 1 -> {
                List<Consulta> consultas = saude.getConsultas();
                String[] descricoes = new String[consultas.size()];
                for (int j = 0; j < consultas.size(); j++) {
                    descricoes[j] = consultas.get(j).getData().format(Dialogos.FORMATO_DATA) + " "
                                  + consultas.get(j).getHorario() + " - " + consultas.get(j).getTipoDeConsulta();
                }
                i = escolherParaRemover(animal, descricoes);
                if (i >= 0) {
                    vet.removerConsulta(animal, consultas.get(i));
                }
            }
            case 2 -> {
                List<Medicamento> medicamentos = saude.getMedicamentos();
                String[] descricoes = new String[medicamentos.size()];
                for (int j = 0; j < medicamentos.size(); j++) {
                    descricoes[j] = medicamentos.get(j).getNomeMedicamento() + " - "
                                  + medicamentos.get(j).getDataAplicacao().format(Dialogos.FORMATO_DATA);
                }
                i = escolherParaRemover(animal, descricoes);
                if (i >= 0) {
                    vet.removerMedicamento(animal, medicamentos.get(i));
                }
            }
            default -> {
                List<Cirurgia> cirurgias = saude.getCirurgias();
                String[] descricoes = new String[cirurgias.size()];
                for (int j = 0; j < cirurgias.size(); j++) {
                    descricoes[j] = cirurgias.get(j).getNomeCirurgia() + " - "
                                  + cirurgias.get(j).getDataCirurgia().format(Dialogos.FORMATO_DATA);
                }
                i = escolherParaRemover(animal, descricoes);
                if (i >= 0) {
                    vet.removerCirurgia(animal, cirurgias.get(i));
                }
            }
        }
        if (i >= 0) {
            JOptionPane.showMessageDialog(null, "Registro removido!");
        }
    }

    // mostra um botao por registro e pede confirmacao; devolve o indice escolhido, ou -1 se desistir
    private int escolherParaRemover(Animal animal, String[] descricoes) {
        if (descricoes.length == 0) {
            JOptionPane.showMessageDialog(null, animal.getNome() + " nao tem registros desse tipo");
            return -1;
        }
        String[] opcoes = Arrays.copyOf(descricoes, descricoes.length + 1); // copia com uma posicao a mais
        opcoes[descricoes.length] = "Voltar";

        int escolha = Dialogos.escolherOpcao("Remover registro", "Escolha o registro de " + animal.getNome(), opcoes);
        if (escolha < 0 || escolha >= descricoes.length) {
            return -1; // usuario clicou em Voltar ou fechou a janela
        }
        int confirmacao = JOptionPane.showConfirmDialog(null, "Remover " + descricoes[escolha] + "?",
                                                        "Remover registro", JOptionPane.YES_NO_OPTION);
        return (confirmacao == JOptionPane.YES_OPTION) ? escolha : -1;
    }

    private void registrarConsulta() {
        Animal animal = escolherAnimal("Registrar consulta");
        if (animal == null) {
            return;
        }
        JTextField campoData = new JTextField(LocalDate.now().format(Dialogos.FORMATO_DATA));
        JTextField campoHorario = new JTextField();
        JTextField campoTipo = new JTextField();
        JTextArea campoDiagnostico = Dialogos.criarAreaDeTexto();
        JTextArea campoPrescricao = Dialogos.criarAreaDeTexto();
        String[] rotulos = {"Data (dd/mm/aaaa):", "Horario (HH:mm):", "Tipo de consulta:", "Diagnostico:", "Prescricao (opcional):"};
        JComponent[] campos = {campoData, campoHorario, campoTipo, campoDiagnostico, campoPrescricao};

        while (Dialogos.mostrarFormulario("Consulta de " + animal.getNome(), rotulos, campos)) {
            try {
                vet.registrarConsulta(animal, lerData(campoData), campoHorario.getText(), campoTipo.getText(),
                                      campoDiagnostico.getText(), campoPrescricao.getText());
                JOptionPane.showMessageDialog(null, "Consulta registrada!");
                return;
            } catch (DateTimeParseException e) {
                mostrarErro("Data invalida, use o formato dd/mm/aaaa");
            } catch (IllegalArgumentException e) {
                mostrarErro(e.getMessage());
            }
        }
    }

    private void registrarMedicamento() {
        Animal animal = escolherAnimal("Registrar medicamento");
        if (animal == null) {
            return;
        }
        JTextField campoNome = new JTextField();
        JTextField campoData = new JTextField(LocalDate.now().format(Dialogos.FORMATO_DATA));
        JTextField campoDosagem = new JTextField();
        String[] rotulos = {"Nome do medicamento:", "Data de aplicacao (dd/mm/aaaa):", "Dosagem:"};
        JTextField[] campos = {campoNome, campoData, campoDosagem};

        while (Dialogos.mostrarFormulario("Medicamento de " + animal.getNome(), rotulos, campos)) {
            try {
                vet.registrarMedicamento(animal, campoNome.getText(), lerData(campoData), campoDosagem.getText());
                JOptionPane.showMessageDialog(null, "Medicamento registrado!");
                return;
            } catch (DateTimeParseException e) {
                mostrarErro("Data invalida, use o formato dd/mm/aaaa");
            } catch (IllegalArgumentException e) {
                mostrarErro(e.getMessage());
            }
        }
    }

    private void registrarCirurgia() {
        Animal animal = escolherAnimal("Registrar cirurgia");
        if (animal == null) {
            return;
        }
        JTextField campoNome = new JTextField();
        JTextField campoData = new JTextField(LocalDate.now().format(Dialogos.FORMATO_DATA));
        JTextField campoDescricao = new JTextField();
        String[] rotulos = {"Nome da cirurgia:", "Data (dd/mm/aaaa):", "Descricao:"};
        JTextField[] campos = {campoNome, campoData, campoDescricao};

        while (Dialogos.mostrarFormulario("Cirurgia de " + animal.getNome(), rotulos, campos)) {
            try {
                vet.registrarCirurgia(animal, campoNome.getText(), lerData(campoData), campoDescricao.getText());
                JOptionPane.showMessageDialog(null, "Cirurgia registrada!");
                return;
            } catch (DateTimeParseException e) {
                mostrarErro("Data invalida, use o formato dd/mm/aaaa");
            } catch (IllegalArgumentException e) {
                mostrarErro(e.getMessage());
            }
        }
    }

    private Animal escolherAnimal(String titulo) {
        return Dialogos.escolherAnimal(titulo, animaisCadastrados.getAnimais().toArray(new Animal[0]));
    }

    private LocalDate lerData(JTextField campo) {
        return LocalDate.parse(campo.getText().trim(), Dialogos.FORMATO_DATA);
    }

    private void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}

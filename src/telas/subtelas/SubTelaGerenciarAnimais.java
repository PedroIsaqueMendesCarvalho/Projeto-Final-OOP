package telas.subtelas;
import animais.*;
import animais.enums.*;
import controledesaude.*;
import java.awt.*;
import java.time.*;
import java.time.format.*;
import javax.swing.*;
import telas.*;

public class SubTelaGerenciarAnimais {
    private static final DateTimeFormatter FORMATO_DATA = Dialogos.FORMATO_DATA;
    private final AnimaisCadastrados animaisCadastrados;

    public SubTelaGerenciarAnimais(AnimaisCadastrados animaisCadastrados) {
        this.animaisCadastrados = animaisCadastrados;
    }

    public void exibir() {
        String[] opcoes = {"Cadastrar animal", "Listar animais", "Ver historico de saude", "Voltar"};
        while (true) {
            int escolha = Dialogos.escolherOpcao("Gerenciar animais", "Escolha uma opcao", opcoes);

            switch (escolha) {
                case 0 -> cadastrarAnimal();
                case 1 -> listarAnimais();
                case 2 -> new SubTelaHistoricoDeSaude(animaisCadastrados).exibir();
                default -> {
                    return;
                }
            }
        }
    }

    private void cadastrarAnimal() {
        int tipo = Dialogos.escolherOpcao("Cadastrar animal", "Qual o tipo do animal?", new String[]{"Cao", "Gato", "Cancelar"});
        if (tipo != 0 && tipo != 1) {
            return;
        }

        JTextField campoNome = new JTextField();
        JTextField campoRaca = new JTextField();
        JTextField campoIdade = new JTextField();
        JTextField campoDataResgate = new JTextField(LocalDate.now().format(FORMATO_DATA));
        // values() devolve todas as constantes do enum; o combo mostra o toString() de cada uma
        JComboBox<Porte> campoPorte = new JComboBox<>(Porte.values());
        JComboBox<EstadoDeSaude> campoEstado = new JComboBox<>(EstadoDeSaude.values());
        // campos especificos de Cao
        JComboBox<SimNaoParcial> campoAdestrado = new JComboBox<>(SimNaoParcial.values());
        JComboBox<NivelDeEnergia> campoEnergia = new JComboBox<>(NivelDeEnergia.values());
        JComboBox<SimNaoParcial> campoConvivencia = new JComboBox<>(SimNaoParcial.values());
        // campos especificos de Gato
        JComboBox<ResultadoExame> campoFivFelv = new JComboBox<>(ResultadoExame.values());
        JComboBox<UsoCaixaDeAreia> campoCaixaDeAreia = new JComboBox<>(UsoCaixaDeAreia.values());

        JPanel painel = new JPanel(new GridLayout(0, 1));
        painel.add(new JLabel("Nome:"));
        painel.add(campoNome);
        painel.add(new JLabel("Raca:"));
        painel.add(campoRaca);
        painel.add(new JLabel("Idade (anos):"));
        painel.add(campoIdade);
        painel.add(new JLabel("Porte:"));
        painel.add(campoPorte);
        painel.add(new JLabel("Estado de saude:"));
        painel.add(campoEstado);
        painel.add(new JLabel("Data de resgate (dd/mm/aaaa):"));
        painel.add(campoDataResgate);
        if (tipo == 0) {
            painel.add(new JLabel("Adestrado:"));
            painel.add(campoAdestrado);
            painel.add(new JLabel("Nivel de energia:"));
            painel.add(campoEnergia);
            painel.add(new JLabel("Convive com outros animais:"));
            painel.add(campoConvivencia);
        } else {
            painel.add(new JLabel("FIV/FeLV positivo:"));
            painel.add(campoFivFelv);
            painel.add(new JLabel("Usa caixa de areia:"));
            painel.add(campoCaixaDeAreia);
        }

        while (true) {
            int opcao = JOptionPane.showConfirmDialog(null, painel, "Cadastrar animal",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (opcao != JOptionPane.OK_OPTION) {
                return; 
            }

            try {
                String nome = campoNome.getText().trim();
                if (nome.isBlank()) {
                    throw new IllegalArgumentException("Nome nao pode ser vazio");
                }
                int idade = Integer.parseInt(campoIdade.getText().trim());
                if (idade < 0) {
                    throw new IllegalArgumentException("Idade nao pode ser negativa");
                }
                LocalDate dataDeResgate = LocalDate.parse(campoDataResgate.getText().trim(), FORMATO_DATA);
                if (dataDeResgate.isAfter(LocalDate.now())) {
                    throw new IllegalArgumentException("Data de resgate nao pode ser no futuro");
                }
                // getSelectedItem devolve Object, por isso o cast para o tipo do enum
                Porte porte = (Porte) campoPorte.getSelectedItem();
                EstadoDeSaude estado = (EstadoDeSaude) campoEstado.getSelectedItem();

                Animal novo;
                if (tipo == 0) {
                    novo = new Cao(new ControleDeSaude(), nome, "Cao", campoRaca.getText().trim(), idade, porte, estado,
                                   dataDeResgate, Disponibilidade.DISPONIVEL,
                                   (SimNaoParcial) campoAdestrado.getSelectedItem(),
                                   (NivelDeEnergia) campoEnergia.getSelectedItem(),
                                   (SimNaoParcial) campoConvivencia.getSelectedItem());
                } else {
                    novo = new Gato(new ControleDeSaude(), nome, "Gato", campoRaca.getText().trim(), idade, porte, estado,
                                    dataDeResgate, Disponibilidade.DISPONIVEL,
                                    (ResultadoExame) campoFivFelv.getSelectedItem(),
                                    (UsoCaixaDeAreia) campoCaixaDeAreia.getSelectedItem());
                }
                animaisCadastrados.cadastrarAnimal(novo);
                JOptionPane.showMessageDialog(null, "Animal cadastrado!");
                return;
            } catch (NumberFormatException e) {
                // vem antes de IllegalArgumentException porque e uma subclasse dela
                JOptionPane.showMessageDialog(null, "Idade deve ser um numero inteiro",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "Data invalida, use o formato dd/mm/aaaa",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void listarAnimais() {
        Animal[] animais = animaisCadastrados.getAnimais().toArray(new Animal[0]);
        if (animais.length == 0) {
            JOptionPane.showMessageDialog(null, "Nenhum animal cadastrado");
            return;
        }

        StringBuilder texto = new StringBuilder();
        for (Animal a : animais) {
            texto.append(a.getNome()).append(" - ").append(a.getEspecie())
                 .append(", ").append(a.getRaca())
                 .append(", ").append(a.getIdade()).append(" anos")
                 .append(", resgatado em ").append(a.getDataDeResgate().format(FORMATO_DATA))
                 .append(", ").append(a.getStatusDeAdocao())
                 .append("\n");
        }
        JOptionPane.showMessageDialog(null, texto.toString(), "Animais cadastrados", JOptionPane.INFORMATION_MESSAGE);
    }
}

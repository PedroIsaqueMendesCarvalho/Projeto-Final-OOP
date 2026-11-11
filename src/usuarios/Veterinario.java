package usuarios;
import animais.*;
import controledesaude.*;
import java.time.*;

 public class Veterinario extends Usuarios implements Cadastro {
    
    public Veterinario(String nome, String cpf, String senha, String email, String telefone) {
        super(nome, cpf, senha, email, telefone);
    }
    // cada metodo cria o registro (validado no construtor) e adiciona ao controle de saude do animal
    public void registrarVacina(Animal animal, String nomeVacina, LocalDate dataAplicacao, LocalDate dataProximaAplicacao) {
        Vacina vacina = new Vacina(nomeVacina, dataAplicacao, dataProximaAplicacao);
        animal.getControleDeSaude().addVacina(vacina);
    }
    public void removerVacina(Animal animal, Vacina vacina) {
        animal.getControleDeSaude().removerVacina(vacina);
    }
    public void removerConsulta(Animal animal, Consulta consulta) {
        animal.getControleDeSaude().removerConsulta(consulta);
    }
    public void removerMedicamento(Animal animal, Medicamento medicamento) {
        animal.getControleDeSaude().removerMedicamento(medicamento);
    }
    public void removerCirurgia(Animal animal, Cirurgia cirurgia) {
        animal.getControleDeSaude().removerCirurgia(cirurgia);
    }
    public void registrarConsulta(Animal animal, LocalDate data, String horario, String tipoDeConsulta, String diagnostico, String prescricao) {
        Consulta consulta = new Consulta(data, horario, tipoDeConsulta, diagnostico, prescricao);
        animal.getControleDeSaude().addConsulta(consulta);
    }
    public void registrarMedicamento(Animal animal, String nomeMedicamento, LocalDate dataAplicacao, String dosagem) {
        Medicamento medicamento = new Medicamento(nomeMedicamento, dataAplicacao, dosagem);
        animal.getControleDeSaude().addMedicamento(medicamento);
    }
    public void registrarCirurgia(Animal animal, String nomeCirurgia, LocalDate dataCirurgia, String descricao) {
        Cirurgia cirurgia = new Cirurgia(nomeCirurgia, dataCirurgia, descricao);
        animal.getControleDeSaude().addCirurgia(cirurgia);
    }

    public void CadastroDeCliente(){};
    
}

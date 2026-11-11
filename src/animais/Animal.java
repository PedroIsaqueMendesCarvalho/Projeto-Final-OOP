package animais;
import animais.enums.*;
import controledesaude.*;

public abstract class Animal {
    private ControleDeSaude controleDeSaude = new ControleDeSaude();
    private String nome;
    private String especie;
    private String raca;
    private int idade;
    private Porte porte;
    private EstadoDeSaude estadoDeSaude;
    private java.time.LocalDate dataDeResgate;
    private Disponibilidade statusDeAdocao;
    public Animal(ControleDeSaude controleDeSaude, String nome, String especie, String raca, int idade,
                 Porte porte, EstadoDeSaude estadoDeSaude, java.time.LocalDate dataDeResgate, Disponibilidade statusDeAdocao) {
        setControleDeSaude(controleDeSaude);
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.idade = idade;
        this.porte = porte;
        this.estadoDeSaude = estadoDeSaude;
        this.dataDeResgate = dataDeResgate;
        this.statusDeAdocao = statusDeAdocao;
    }
    public void setControleDeSaude(ControleDeSaude controleDeSaude) {
        if(controleDeSaude == null){
            throw new IllegalArgumentException("Controle de saude nao pode ser nulo");
        }
        else
        this.controleDeSaude = controleDeSaude;
    }
    public ControleDeSaude getControleDeSaude() {
        return controleDeSaude;
    }
    public String getNome() {
        return nome;
    }
    public String getEspecie() {
        return especie;
    }
    public String getRaca() {
        return raca;
    }
    public int getIdade() {
        return idade;
    }
    public Porte getPorte() {
        return porte;
    }
    public EstadoDeSaude getEstadoDeSaude() {
        return estadoDeSaude;
    }
    public java.time.LocalDate getDataDeResgate() {
        return dataDeResgate;
    }
    public Disponibilidade getStatusDeAdocao() {
        return statusDeAdocao;
    }
    public void setStatusDeAdocao(Disponibilidade statusDeAdocao) {
        this.statusDeAdocao = statusDeAdocao;
    }
}
    //sem seguranca, ja que apenas usuarios com acesso a senha do sistema podem alterar os dados do animal, e eles ja tem acesso a senha do sistema.

package animais;
import controledesaude.*;

public abstract class Animal {
    private ControleDeSaude controleDeSaude = new ControleDeSaude();
    private String nome;
    private String especie;
    private String raca;
    private int idade;
    private int porte; //1=pequeno,2=medio,3=grande
    private int estadoDeSaude;//1=critico,2=ruim,3=razoavel,4=saudavel
    private java.time.LocalDate dataDeResgate;
    private int statusDeAdocao; //1=disponivel,2=adotado
    public Animal(ControleDeSaude controleDeSaude, String nome, String especie, String raca, int idade, int porte, int estadoDeSaude, java.time.LocalDate dataDeResgate, int statusDeAdocao) {
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
    public int getPorte() {
        return porte;
    }
    public int getEstadoDeSaude() {
        return estadoDeSaude;
    }
    public java.time.LocalDate getDataDeResgate() {
        return dataDeResgate;
    }
    public int getStatusDeAdocao() {
        return statusDeAdocao;
    }
    public void setStatusDeAdocao(int statusDeAdocao) {
        this.statusDeAdocao = statusDeAdocao;
    }
}
    //sem seguranca, ja que apenas usuarios com acesso a senha do sistema podem alterar os dados do animal, e eles ja tem acesso a senha do sistema.

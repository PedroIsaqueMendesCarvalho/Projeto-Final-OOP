package animais;
import controledesaude.ControleDeSaude;

public class Gato extends Animal{
    private int fivFelvPositivo; //1=sim,2=nao,3=desconhecido
    private int usacaCaixaDeAreia; //1=sim,2=nao,3=apenas quando conveniente
    public Gato(ControleDeSaude controleDeSaude, String nome, String especie, String raca, int idade, int porte, int estadoDeSaude, java.time.LocalDate dataDeResgate, int statusDeAdocao, int fivFelvPositivo, int usacaCaixaDeAreia) {
        super(controleDeSaude, nome, especie, raca, idade, porte, estadoDeSaude, dataDeResgate, statusDeAdocao);
        this.fivFelvPositivo = fivFelvPositivo;
        this.usacaCaixaDeAreia = usacaCaixaDeAreia;
    }
    public int getFivFelvPositivo() {
        return fivFelvPositivo;
    }
    public int getUsacaCaixaDeAreia() {
        return usacaCaixaDeAreia;
    }
    public void setFivFelvPositivo(int fivFelvPositivo) {
        this.fivFelvPositivo = fivFelvPositivo;
    }
    public void setUsacaCaixaDeAreia(int usacaCaixaDeAreia) {
        this.usacaCaixaDeAreia = usacaCaixaDeAreia;
    }

    
}

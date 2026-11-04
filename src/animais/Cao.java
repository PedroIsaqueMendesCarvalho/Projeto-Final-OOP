package animais;
import controledesaude.ControleDeSaude;

public class Cao extends Animal{
    private int adestrado; //1=sim,2=nao,3=parcialmente
    private int nivelDeEnergia; //1=baixo,2=medio,3=alto
    private int convivenciaComOutrosAnimais; //1=sim,2=nao,3=parcialmente

    public Cao(ControleDeSaude controleDeSaude, String nome, String especie, String raca, int idade, int porte, int estadoDeSaude, java.time.LocalDate dataDeResgate, int statusDeAdocao, int adestrado, int nivelDeEnergia, int convivenciaComOutrosAnimais) {
        super(controleDeSaude, nome, especie, raca, idade, porte, estadoDeSaude, dataDeResgate, statusDeAdocao);
        this.adestrado = adestrado;
        this.nivelDeEnergia = nivelDeEnergia;
        this.convivenciaComOutrosAnimais = convivenciaComOutrosAnimais;
    }
    public int getAdestrado() {
        return adestrado;
    }
    public int getNivelDeEnergia() {
        return nivelDeEnergia;
    }
    public int getConvivenciaComOutrosAnimais() {
        return convivenciaComOutrosAnimais;
    }
    public void setAdestrado(int adestrado) {
        this.adestrado = adestrado;
    }
    public void setNivelDeEnergia(int nivelDeEnergia) {
        this.nivelDeEnergia = nivelDeEnergia;
    }
    public void setConvivenciaComOutrosAnimais(int convivenciaComOutrosAnimais) {
        this.convivenciaComOutrosAnimais = convivenciaComOutrosAnimais;
    }
    
}

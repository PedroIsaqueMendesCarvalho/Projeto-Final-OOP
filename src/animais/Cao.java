package animais;
import animais.enums.*;
import controledesaude.*;

public class Cao extends Animal{
    private SimNaoParcial adestrado;
    private NivelDeEnergia nivelDeEnergia;
    private SimNaoParcial convivenciaComOutrosAnimais;

    public Cao(ControleDeSaude controleDeSaude, String nome, String especie, String raca, int idade, Porte porte, EstadoDeSaude estadoDeSaude, java.time.LocalDate dataDeResgate, Disponibilidade statusDeAdocao, SimNaoParcial adestrado, NivelDeEnergia nivelDeEnergia, SimNaoParcial convivenciaComOutrosAnimais) {
        super(controleDeSaude, nome, especie, raca, idade, porte, estadoDeSaude, dataDeResgate, statusDeAdocao);
        this.adestrado = adestrado;
        this.nivelDeEnergia = nivelDeEnergia;
        this.convivenciaComOutrosAnimais = convivenciaComOutrosAnimais;
    }
    public SimNaoParcial getAdestrado() {
        return adestrado;
    }
    public NivelDeEnergia getNivelDeEnergia() {
        return nivelDeEnergia;
    }
    public SimNaoParcial getConvivenciaComOutrosAnimais() {
        return convivenciaComOutrosAnimais;
    }
    public void setAdestrado(SimNaoParcial adestrado) {
        this.adestrado = adestrado;
    }
    public void setNivelDeEnergia(NivelDeEnergia nivelDeEnergia) {
        this.nivelDeEnergia = nivelDeEnergia;
    }
    public void setConvivenciaComOutrosAnimais(SimNaoParcial convivenciaComOutrosAnimais) {
        this.convivenciaComOutrosAnimais = convivenciaComOutrosAnimais;
    }

}

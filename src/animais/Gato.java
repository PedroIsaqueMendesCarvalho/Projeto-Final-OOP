package animais;
import animais.enums.*;
import controledesaude.*;

public class Gato extends Animal{
    private ResultadoExame fivFelv;
    private UsoCaixaDeAreia usaCaixaDeAreia;
    public Gato(ControleDeSaude controleDeSaude, String nome, String especie, String raca, int idade, Porte porte, EstadoDeSaude estadoDeSaude, java.time.LocalDate dataDeResgate, Disponibilidade statusDeAdocao, ResultadoExame fivFelv, UsoCaixaDeAreia usaCaixaDeAreia) {
        super(controleDeSaude, nome, especie, raca, idade, porte, estadoDeSaude, dataDeResgate, statusDeAdocao);
        this.fivFelv = fivFelv;
        this.usaCaixaDeAreia = usaCaixaDeAreia;
    }
    public ResultadoExame getFivFelv() {
        return fivFelv;
    }
    public UsoCaixaDeAreia getUsaCaixaDeAreia() {
        return usaCaixaDeAreia;
    }
    public void setFivFelv(ResultadoExame fivFelv) {
        this.fivFelv = fivFelv;
    }
    public void setUsaCaixaDeAreia(UsoCaixaDeAreia usaCaixaDeAreia) {
        this.usaCaixaDeAreia = usaCaixaDeAreia;
    }


}

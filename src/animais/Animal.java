package animais;
import controledesaude.*;

public abstract class Animal {
    private ControleDeSaude controleDeSaude;
    private String nome;
    private String especia;
    private String raca;
    private int idade;
    private int porte; //1=pequeno,2=medio,3=grande
    private int estadoDeSaude;//1=critico,2=ruim,3=razoavel,4=saudavel
    private String dataDeResgate;
    private String statusDeAdocao;
    
}

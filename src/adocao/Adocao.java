package adocao;
import java.time.LocalDate;
import animais.Animal;
import usuarios.*;
import controledesaude.*;


public class Adocao {
    private final Animal animal;
    private final Candidato candidato;
    private final LocalDate dataSolicitacao;
    private StatusAdocao status = StatusAdocao.PENDENTE;
    private Administrador responsavel;     
    private LocalDate dataConclusao;
    private LocalDate dataDevolucao;
    private String motivoDevolucao;

}
    


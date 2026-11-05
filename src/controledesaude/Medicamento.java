package controledesaude;
import java.time.LocalDate;

public class Medicamento{
    private String nomeMedicamento;
    private LocalDate dataAplicacao;
    private String dosagem;

    public Medicamento(String nomeMedicamento, LocalDate dataAplicacao, String dosagem) {
        setNomeMedicamento(nomeMedicamento);
        setDataAplicacao(dataAplicacao);
        setDosagem(dosagem);
    }

    public LocalDate getDataAplicacao() {
        return dataAplicacao;
    }
    public String getDosagem() {
        return dosagem;
    }
    public String getNomeMedicamento() {
        return nomeMedicamento;
    }
    public void setDataAplicacao(LocalDate dataAplicacao) {
        if(dataAplicacao == null){
            throw new IllegalArgumentException("Data de aplicacao e obrigatoria");
        }else if(dataAplicacao.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Data de aplicacao nao pode ser no futuro");
        }else this.dataAplicacao = dataAplicacao;
    }
    public void setDosagem(String dosagem) {
        if(dosagem == null || dosagem.isBlank()){
            throw new IllegalArgumentException("Dosagem e obrigatoria");
        }else this.dosagem = dosagem;
    }
    public void setNomeMedicamento(String nomeMedicamento) {
        if(nomeMedicamento == null || nomeMedicamento.isBlank()){
            throw new IllegalArgumentException("Nome do medicamento e obrigatorio");
        }else this.nomeMedicamento = nomeMedicamento;
    }

}

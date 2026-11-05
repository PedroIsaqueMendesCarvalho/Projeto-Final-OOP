package controledesaude;
import java.time.LocalDate;

public class Vacina{
    private String nomeVacina;
    private LocalDate dataAplicacao;
    private LocalDate proximaAplicacao;

    public Vacina(String nomeVacina, LocalDate dataAplicacao, LocalDate proximaAplicacao) {
        setNomeVacina(nomeVacina);
        setDataAplicacao(dataAplicacao);
        setProximaAplicacao(proximaAplicacao);
    }

    public LocalDate getDataAplicacao() {
        return dataAplicacao;
    }

    public LocalDate getProximaAplicacao() {
        return proximaAplicacao;
    }
    public String getNomeVacina() {
        return nomeVacina;
    }
    public void setNomeVacina(String nomeVacina) {
        if(nomeVacina == null || nomeVacina.isBlank()){
            throw new IllegalArgumentException("Nome da vacina e obrigatorio");
        }else this.nomeVacina = nomeVacina;
    }
    public void setDataAplicacao(LocalDate dataAplicacao) {
        if(dataAplicacao == null){
            throw new IllegalArgumentException("Data de aplicacao e obrigatoria");
        }else if(dataAplicacao.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Data de aplicacao nao pode ser no futuro");
        }else if(proximaAplicacao != null && !proximaAplicacao.isAfter(dataAplicacao)){
            throw new IllegalArgumentException("Data de aplicacao deve ser antes da proxima aplicacao");
        }else this.dataAplicacao = dataAplicacao;
    }
    public void setProximaAplicacao(LocalDate proximaAplicacao) {
        //proxima aplicacao e opcional, ja que nem toda vacina tem reforco
        if(proximaAplicacao != null && !proximaAplicacao.isAfter(dataAplicacao)){
            throw new IllegalArgumentException("Proxima aplicacao deve ser depois da data de aplicacao");
        }else this.proximaAplicacao = proximaAplicacao;
    }

}

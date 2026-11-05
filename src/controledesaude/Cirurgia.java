package controledesaude;
import java.time.*;

public class Cirurgia{
    private String nomeCirurgia;
    private LocalDate dataCirurgia;
    private String descricao;

    public Cirurgia(String nomeCirurgia, LocalDate dataCirurgia, String descricao) {
        setNomeCirurgia(nomeCirurgia);
        setDataCirurgia(dataCirurgia);
        setDescricao(descricao);
    }

    public LocalDate getDataCirurgia() {
        return dataCirurgia;
    }
    public String getDescricao() {
        return descricao;
    }
    public String getNomeCirurgia() {
        return nomeCirurgia;
    }
    public void setDataCirurgia(LocalDate dataCirurgia) {
        if(dataCirurgia == null){
            throw new IllegalArgumentException("Data da cirurgia e obrigatoria");
        }else if(dataCirurgia.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Data da cirurgia nao pode ser no futuro");
        }else this.dataCirurgia = dataCirurgia;
    }
    public void setDescricao(String descricao) {
        if(descricao == null || descricao.isBlank()){
            throw new IllegalArgumentException("Descricao da cirurgia e obrigatoria");
        }else this.descricao = descricao;
    }
    public void setNomeCirurgia(String nomeCirurgia) {
        if(nomeCirurgia == null || nomeCirurgia.isBlank()){
            throw new IllegalArgumentException("Nome da cirurgia e obrigatorio");
        }else this.nomeCirurgia = nomeCirurgia;
    }

}

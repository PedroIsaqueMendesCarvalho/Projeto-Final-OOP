package controledesaude;

import java.time.LocalDate;

public class Cirurgia{
    private String nomeCirurgia;
    private LocalDate dataCirurgia;
    private String descricao;

    public Cirurgia(String nomeCirurgia, LocalDate dataCirurgia, String descricao) {
        this.nomeCirurgia = nomeCirurgia;
        this.dataCirurgia = dataCirurgia;
        this.descricao = descricao;
    }

    public LocalDate getDataCirurgia() {
        return dataCirurgia;
    }

}

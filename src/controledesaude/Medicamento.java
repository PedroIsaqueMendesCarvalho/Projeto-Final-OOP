package controledesaude;

import java.time.LocalDate;

public class Medicamento{
    private String nomeMedicamento;
    private LocalDate dataAplicacao;
    private String dosagem;

    public Medicamento(String nomeMedicamento, LocalDate dataAplicacao, String dosagem) {
        this.nomeMedicamento = nomeMedicamento;
        this.dataAplicacao = dataAplicacao;
        this.dosagem = dosagem;
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

}

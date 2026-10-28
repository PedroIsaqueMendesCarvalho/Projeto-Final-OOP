package controledesaude;

import java.time.LocalDate;

public class Vacina{
    private String nomeVacina;
    private LocalDate dataAplicacao;
    private LocalDate proximaAplicacao;

    public Vacina(String nomeVacina, LocalDate dataAplicacao, LocalDate proximaAplicacao) {
        this.nomeVacina = nomeVacina;
        this.dataAplicacao = dataAplicacao;
        this.proximaAplicacao = proximaAplicacao;
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

}

package adocao;
import animais.*;
import animais.enums.*;
import java.time.*;
import usuarios.*;

public class Adocao {
    private final Animal animal;
    private final Candidato candidato;
    private final LocalDate dataSolicitacao;
    private StatusAdocao status = StatusAdocao.PENDENTE;
    private Administrador responsavel;
    private LocalDate dataConclusao;
    private LocalDate dataDevolucao;
    private String motivoDevolucao;

    public Adocao(Animal animal, Candidato candidato) {
        if (animal == null) {
            throw new IllegalArgumentException("Animal nao pode ser nulo");
        }
        if (candidato == null) {
            throw new IllegalArgumentException("Candidato nao pode ser nulo");
        }
        if (animal.getStatusDeAdocao() != Disponibilidade.DISPONIVEL) {
            throw new IllegalArgumentException("Animal nao esta disponivel para adocao");
        }
        this.animal = animal;
        this.candidato = candidato;
        this.dataSolicitacao = LocalDate.now();
    }

    // so um administrador pode aprovar ou recusar, e so enquanto estiver pendente
    public void aprovar(Administrador responsavel) {
        concluir(responsavel, StatusAdocao.APROVADA);
        animal.setStatusDeAdocao(Disponibilidade.ADOTADO);
    }

    public void recusar(Administrador responsavel) {
        concluir(responsavel, StatusAdocao.RECUSADA);
    }

    private void concluir(Administrador responsavel, StatusAdocao novoStatus) {
        if (responsavel == null) {
            throw new IllegalArgumentException("Responsavel nao pode ser nulo");
        }
        if (status != StatusAdocao.PENDENTE) {
            throw new IllegalStateException("Esta adocao ja foi concluida");
        }
        this.responsavel = responsavel;
        this.status = novoStatus;
        this.dataConclusao = LocalDate.now();
    }

    public void registrarDevolucao(String motivo) {
        if (status != StatusAdocao.APROVADA) {
            throw new IllegalStateException("So adocoes aprovadas podem ser devolvidas");
        }
        if (motivo == null || motivo.isBlank()) {
            throw new IllegalArgumentException("Motivo da devolucao e obrigatorio");
        }
        this.status = StatusAdocao.DEVOLVIDA;
        this.motivoDevolucao = motivo;
        this.dataDevolucao = LocalDate.now();
        animal.setStatusDeAdocao(Disponibilidade.DISPONIVEL); // volta a ficar disponivel
    }

    public Animal getAnimal() {
        return animal;
    }
    public Candidato getCandidato() {
        return candidato;
    }
    public LocalDate getDataSolicitacao() {
        return dataSolicitacao;
    }
    public StatusAdocao getStatus() {
        return status;
    }
    public Administrador getResponsavel() {
        return responsavel;
    }
    public LocalDate getDataConclusao() {
        return dataConclusao;
    }
    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }
    public String getMotivoDevolucao() {
        return motivoDevolucao;
    }
}

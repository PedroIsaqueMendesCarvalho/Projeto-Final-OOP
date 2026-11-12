package adocao;
import animais.*;
import java.util.*;
import usuarios.*;

// guarda todas as adocoes (o historico completo) e os candidatos
public class AdocoesCadastradas {
    private final List<Adocao> adocoes = new ArrayList<>();
    private final List<Candidato> candidatos = new ArrayList<>();

    // se o CPF ja existe, reaproveita o candidato em vez de duplicar
    public Candidato cadastrarCandidato(Candidato novo) {
        if (novo == null) {
            throw new IllegalArgumentException("Candidato nao pode ser nulo");
        }
        Candidato existente = buscarCandidato(novo.getCpf());
        if (existente != null) {
            return existente;
        }
        candidatos.add(novo);
        return novo;
    }

    // devolve o candidato com esse CPF ou null se nao houver
    public Candidato buscarCandidato(String cpf) {
        for (Candidato c : candidatos) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        return null;
    }

    public Adocao solicitar(Animal animal, Candidato candidato) {
        if (candidato == null) {
            throw new IllegalArgumentException("Candidato nao pode ser nulo");
        }
        Candidato existente = buscarCandidato(candidato.getCpf());
        Candidato c = (existente != null) ? existente : candidato;

        for (Adocao a : adocoes) {
            if (a.getAnimal() == animal && a.getCandidato() == c && a.getStatus() == StatusAdocao.PENDENTE) {
                throw new IllegalArgumentException("Este candidato ja tem uma solicitacao pendente para este animal");
            }
        }
        Adocao adocao = new Adocao(animal, c); // o construtor ja recusa animal adotado

        // so cadastra o candidato depois que a solicitacao passou por todas as regras
        cadastrarCandidato(c);
        adocoes.add(adocao);
        return adocao;
    }

    // esse metodo deve ser usado em vez de adocao.aprovar, para recusar as outras solicitacoes do mesmo animal
    public void aprovar(Adocao adocao, Administrador admin) {
        adocao.aprovar(admin);
        for (Adocao a : adocoes) {
            if (a != adocao && a.getAnimal() == adocao.getAnimal() && a.getStatus() == StatusAdocao.PENDENTE) {
                a.recusar(admin);
            }
        }
    }

    public List<Adocao> getAdocoes() {
        return List.copyOf(adocoes);
    }
    public List<Adocao> getPorStatus(StatusAdocao status) {
        List<Adocao> resultado = new ArrayList<>();
        for (Adocao a : adocoes) {
            if (a.getStatus() == status) {
                resultado.add(a);
            }
        }
        return resultado;
    }
    public List<Candidato> getCandidatos() {
        return List.copyOf(candidatos);
    }
}

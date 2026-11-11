package animais.enums;

// situacao do animal na ONG (diferente de adocao.StatusAdocao, que e a situacao de um pedido de adocao)
public enum Disponibilidade {
    DISPONIVEL("Disponivel"),
    ADOTADO("Adotado");

    private final String descricao;

    Disponibilidade(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}

package animais.enums;

public enum UsoCaixaDeAreia {
    SIM("Sim"),
    NAO("Nao"),
    QUANDO_CONVENIENTE("Apenas quando conveniente");

    private final String descricao;

    UsoCaixaDeAreia(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}

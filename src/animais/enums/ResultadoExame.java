package animais.enums;

public enum ResultadoExame {
    POSITIVO("Positivo"),
    NEGATIVO("Negativo"),
    DESCONHECIDO("Desconhecido");

    private final String descricao;

    ResultadoExame(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}

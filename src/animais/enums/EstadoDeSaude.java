package animais.enums;

public enum EstadoDeSaude {
    CRITICO("Critico"),
    RUIM("Ruim"),
    RAZOAVEL("Razoavel"),
    SAUDAVEL("Saudavel");

    private final String descricao;

    EstadoDeSaude(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}

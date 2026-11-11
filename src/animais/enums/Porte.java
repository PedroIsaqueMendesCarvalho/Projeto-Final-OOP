package animais.enums;

public enum Porte {
    PEQUENO("Pequeno"),
    MEDIO("Medio"),
    GRANDE("Grande");

    private final String descricao;

    Porte(String descricao) {
        this.descricao = descricao;
    }

    // texto mostrado nos JComboBox e relatorios, em vez do nome da constante (MEDIO)
    @Override
    public String toString() {
        return descricao;
    }
}

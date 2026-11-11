package animais.enums;

public enum NivelDeEnergia {
    BAIXO("Baixo"),
    MEDIO("Medio"),
    ALTO("Alto");

    private final String descricao;

    NivelDeEnergia(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}

package animais.enums;

// usado em mais de um campo do Cao (adestrado e convivencia com outros animais)
public enum SimNaoParcial {
    SIM("Sim"),
    NAO("Nao"),
    PARCIALMENTE("Parcialmente");

    private final String descricao;

    SimNaoParcial(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}

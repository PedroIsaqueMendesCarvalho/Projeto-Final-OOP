package controledesaude;

import java.time.LocalDate;

public class Consulta {
    private LocalDate data;
    private String horario;
    private String tipoDeConsulta;
    private String diagnostico;
    private String prescricao;

    public Consulta(LocalDate data, String horario, String tipoDeConsulta, String diagnostico, String prescricao) {
        this.data = data;
        this.horario = horario;
        this.tipoDeConsulta = tipoDeConsulta;
        this.diagnostico = diagnostico;
        this.prescricao = prescricao;
    }

    public LocalDate getData() {
        return data;
    }
    public String getHorario() {
        return horario;
    }
    public String getTipoDeConsulta() {
        return tipoDeConsulta;
    }
    public String getDiagnostico() {
        return diagnostico;
    }
    public String getPrescricao() {
        return prescricao;
    }

}

//Sem seguranca ja que apenas admnistradores e veterinarios podem acessar o historico medico do animal, e eles ja tem acesso a senha do sistema. Também porque é chato.
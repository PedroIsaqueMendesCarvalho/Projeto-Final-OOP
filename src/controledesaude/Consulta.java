package controledesaude;
import java.time.*;

public class Consulta {
    private LocalDate data;
    private String horario;
    private String tipoDeConsulta;
    private String diagnostico;
    private String prescricao;

    public Consulta(LocalDate data, String horario, String tipoDeConsulta, String diagnostico, String prescricao) {
        setData(data);
        setHorario(horario);
        setTipoDeConsulta(tipoDeConsulta);
        setDiagnostico(diagnostico);
        setPrescricao(prescricao);
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
    public void setData(LocalDate data) {
        if(data == null){
            throw new IllegalArgumentException("Data da consulta e obrigatoria");
        }else if(data.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Data da consulta nao pode ser no futuro");
        }else this.data = data;
    }
    public void setHorario(String horario) {
        if(horario == null || !horario.matches("([01]\\d|2[0-3]):[0-5]\\d")){
            throw new IllegalArgumentException("Horario invalido, use o formato HH:mm");
        }else this.horario = horario;
    }
    public void setTipoDeConsulta(String tipoDeConsulta) {
        if(tipoDeConsulta == null || tipoDeConsulta.isBlank()){
            throw new IllegalArgumentException("Tipo de consulta e obrigatorio");
        }else this.tipoDeConsulta = tipoDeConsulta;
    }
    public void setDiagnostico(String diagnostico) {
        if(diagnostico == null || diagnostico.isBlank()){
            throw new IllegalArgumentException("Diagnostico e obrigatorio");
        }else this.diagnostico = diagnostico;
    }
    public void setPrescricao(String prescricao) {
        //prescricao pode ficar vazia, nem toda consulta precisa de medicamento
        if(prescricao == null){
            throw new IllegalArgumentException("Prescricao nao pode ser nula");
        }else this.prescricao = prescricao;
    }

}

//Sem seguranca ja que apenas admnistradores e veterinarios podem acessar o historico medico do animal, e eles ja tem acesso a senha do sistema. Também porque é chato.

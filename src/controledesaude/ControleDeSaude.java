package controledesaude;
import java.util.*;

public class ControleDeSaude {
    private final  List<Consulta> consultas = new ArrayList<>(); //Historico medico subentendido ja que cada consulta é um registro médico do animal
    private final List<Vacina> vacinas = new ArrayList<>(); //Historico de vacinas do animal
    private final List<Medicamento> medicamentos = new ArrayList<>(); //Historico de medicamentos do animal
    private final List<Cirurgia> cirurgias = new ArrayList<>(); //Historico de cirurgias do animal
    public ControleDeSaude() {
    }
    public ControleDeSaude(List<Consulta> consultas, List<Vacina> vacinas, List<Medicamento> medicamentos, List<Cirurgia> cirurgias) {
        this.consultas.addAll(consultas);
        this.vacinas.addAll(vacinas);
        this.medicamentos.addAll(medicamentos);
        this.cirurgias.addAll(cirurgias);
    }
    public void addConsulta(Consulta consulta) {
        consultas.add(consulta);
    }
    public void addVacina(Vacina vacina) {
        vacinas.add(vacina);
    }
    public void addMedicamento(Medicamento medicamento) {
        medicamentos.add(medicamento);
    }  
    public void addCirurgia(Cirurgia cirurgia) {
        cirurgias.add(cirurgia);
    }  
    public List<Consulta> getConsultas() {
        return List.copyOf(consultas);
    }
    public List<Vacina> getVacinas() {
        return List.copyOf(vacinas);
    }
    public List<Medicamento> getMedicamentos() {
        return List.copyOf(medicamentos);
    }
    public List<Cirurgia> getCirurgias() {
        return List.copyOf(cirurgias);
    }
    public void orderByData() {
        consultas.sort(Comparator.comparing(Consulta::getData));
        vacinas.sort(Comparator.comparing(Vacina::getDataAplicacao));
        medicamentos.sort(Comparator.comparing(Medicamento::getDataAplicacao));
        cirurgias.sort(Comparator.comparing(Cirurgia::getDataCirurgia));
    }


}

import adocao.*;
import animais.*;
import animais.enums.*;
import controledesaude.*;
import java.time.*;
import telas.*;
import usuarios.*;
public class Main {
    public static void main(String[] args) {
        UsuariosCadastrados usuariosCadastrados = new UsuariosCadastrados();
        usuariosCadastrados.cadastrarUsuario(new Administrador("Admin", "123.456.789-09", "senhaadmin1",
                                                              "admin@ong.com", "(11) 98765-4321"));
        Veterinario vet = new Veterinario("Veterinario", "987.654.321-09", "senhavet1",
                                          "vet@ong.com", "(11) 91234-5678");
        usuariosCadastrados.cadastrarUsuario(vet);
        Voluntario vol = new Voluntario("Voluntario", "321.654.987-09", "senhavol1",
                                        "vol@ong.com", "(11) 94567-8901");
        usuariosCadastrados.cadastrarUsuario(vol);
        AnimaisCadastrados animaisCadastrados = new AnimaisCadastrados();
        Cao rex = new Cao(new ControleDeSaude(), "Rex", "Cao", "Vira-lata", 3, Porte.MEDIO, EstadoDeSaude.SAUDAVEL,
                          LocalDate.of(2025, 5, 10), Disponibilidade.DISPONIVEL,
                          SimNaoParcial.SIM, NivelDeEnergia.ALTO, SimNaoParcial.SIM);
        animaisCadastrados.cadastrarAnimal(rex);

        vet.registrarConsulta(rex, LocalDate.of(2025, 5, 11), "09:30", "Triagem de resgate",
                              "Desidratacao leve e pulgas", "Soro oral e antipulgas");
        vet.registrarMedicamento(rex, "Antipulgas", LocalDate.of(2025, 5, 11), "1 comprimido");
        vet.registrarVacina(rex, "V10", LocalDate.of(2025, 5, 20), LocalDate.of(2026, 5, 20));
        vet.registrarVacina(rex, "Antirrabica", LocalDate.of(2025, 5, 20), LocalDate.of(2026, 5, 20));
        vet.registrarCirurgia(rex, "Castracao", LocalDate.of(2025, 6, 15), "Castracao sem complicacoes");
        vet.registrarConsulta(rex, LocalDate.of(2025, 6, 25), "14:00", "Retorno pos-cirurgico",
                              "Cicatrizacao normal", "");
        vet.registrarVacina(rex, "V10", LocalDate.of(2026, 5, 20), LocalDate.of(2026, 11, 25)); // reforco, vence em breve
        vet.registrarMedicamento(rex, "Vermifugo", LocalDate.of(2026, 5, 20), "1 comprimido a cada 3 meses");

        AdocoesCadastradas adocoesCadastradas = new AdocoesCadastradas();
        // candidata com uma solicitacao pendente, para testar a tela "Aprovar adocoes" do administrador
        Candidato ana = vol.CadastroDeCandidato("Ana Souza", "111.222.333-44", "ana@email.com", "(11) 91111-2222");
        adocoesCadastradas.solicitar(rex, ana);

        new TelaPrincipal(usuariosCadastrados, animaisCadastrados, adocoesCadastradas).exibir();
    }
}

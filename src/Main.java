import telas.*;
import usuarios.*;
public class Main {
    public static void main(String[] args) {
        UsuariosCadastrados usuariosCadastrados = new UsuariosCadastrados();
        usuariosCadastrados.cadastrarUsuario(new Administrador("Admin", "123.456.789-09", "senhaadmin1",
                                                              "admin@ong.com", "(11) 98765-4321"));
        usuariosCadastrados.cadastrarUsuario(new Veterinario("Veterinario", "987.654.321-09", "senhavet1",
                                                              "vet@ong.com", "(11) 91234-5678"));
        usuariosCadastrados.cadastrarUsuario(new Voluntario("Voluntario", "321.654.987-09", "senhavol1",
                                                              "vol@ong.com", "(11) 94567-8901"));

        new TelaPrincipal(usuariosCadastrados).exibir();
    }
}

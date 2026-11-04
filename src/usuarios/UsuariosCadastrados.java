package usuarios;
import java.util.ArrayList;
import java.util.List;

public class UsuariosCadastrados {
    private List<Usuarios> usuarios = new ArrayList<>();

    public void cadastrarUsuario(Usuarios usuario) {
        usuarios.add(usuario);
    }

    public List<Usuarios> getUsuarios() {
        return usuarios;
    }
    public Usuarios login(String email, String senha) {
        for (Usuarios u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email) && u.autenticar(senha)) {
                return u;
            }
        }
        throw new SecurityException("Email ou senha incorretos");
    }

   

    
}

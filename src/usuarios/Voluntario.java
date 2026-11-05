package usuarios;
public class Voluntario extends Usuarios implements Cadastro{

    public Voluntario(String nome, String cpf, String senha, String email, String telefone) {
        super(nome, cpf, senha, email, telefone);
    }

    @Override
    public void CadastroDeCliente(){};

    public void VisualizarAnimais(){};
  
    
}

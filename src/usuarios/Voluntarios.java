package usuarios;
class Voluntarios extends Usuarios implements Cadastro{

    public Voluntarios(String nome, String cpf, String senha, String email, String telefone) {
        super(nome, cpf, senha, email, telefone);
    }

    @Override
    public void CadastroDeCliente(){};

    void VisualizarAnimais(){};
  
    
}

package usuarios;

 class Veterinario extends Usuarios implements Cadastro {
    
    public Veterinario(String nome, String cpf, String senha, String email, String telefone) {
        super(nome, cpf, senha, email, telefone);
    }
    @Override
    public void CadastroDeCliente(){};
    
}

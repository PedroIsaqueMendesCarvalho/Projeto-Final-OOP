package usuarios;
 public abstract class Usuarios{
    private static final String REGEX_CPF = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";
    private static final String REGEX_EMAIL = "[\\w.+-]+@[\\w-]+(\\.[\\w-]+)*\\.[a-zA-Z]{2,}";
    private static final String REGEX_TELEFONE = "\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}";
    private String nome;
    private String cpf;
    private String senha;
    private String email;
    private String telefone;
    Usuarios(String nome, String cpf, String senha, String email, String telefone){
        if(nome == null || nome.isBlank()){
            throw  new IllegalArgumentException("Campo nome e obrigatorio");
        }else this.nome = nome;
        if (cpf == null || cpf.isBlank() || !cpf.matches(REGEX_CPF)) {
            throw new IllegalArgumentException("CPF invalido");
        }else this.cpf = cpf;
        if(senha == null || senha.length() < 9 || senha.isBlank()){
            throw new IllegalArgumentException("Senha invalida");
        }else this.senha = senha;
        if(email == null || email.isBlank() || !email.matches(REGEX_EMAIL)){
            throw new IllegalArgumentException("Email invalido");
        }else this.email = email;
        if(telefone == null || telefone.isBlank() || !telefone.matches(REGEX_TELEFONE)){
            throw new IllegalArgumentException("Telefone invalido");
        }else this.telefone = telefone;
    }

    public void setNome(String nome, String senha){
        if (!this.senha.equals(senha)) throw new SecurityException("Senha incorreta");
        else this.nome = nome;
    }

    public String getNome(){
        return nome;
    }

    public void setEmail(String email, String senha){
        if (!this.senha.equals(senha)) throw new SecurityException("Senha incorreta"); 
        else this.email = email;
    }
    
    
    

    
    


    
}

package usuarios;
 public abstract class Usuarios{  
    private String nome;
    private String cpf;
    private String senha;
    private String email;
    Usuarios(String nome, String cpf, String senha, String email){
        if(nome == null || nome.isBlank()){
            throw  new IllegalArgumentException("Campo nome e obrigatorio");
        }else this.nome = nome;
        if (cpf == null || cpf.isBlank() || !cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
            throw new IllegalArgumentException("CPF invalido");
        }else this.cpf = cpf;
        if(senha == null || senha.length() >= 9 || senha.isBlank()){
            throw new IllegalArgumentException("Senha invalida");
        }else this.senha = senha;
        if(email == null || email.isBlank() || email.matches("\"[\\\\w.+-]+@[\\\\w-]+(\\\\.[\\\\w-]+)*\\\\.[a-zA-Z]{2,}\"")){
            throw new IllegalArgumentException("Email invalido");
        }else this.email = email;
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

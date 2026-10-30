package adocao;

public class Candidato {
    private static final String REGEX_CPF = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";
    private static final String REGEX_EMAIL = "[\\w.+-]+@[\\w-]+(\\.[\\w-]+)*\\.[a-zA-Z]{2,}";
    private static final String REGEX_TELEFONE = "\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}";
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    public Candidato(String nome, String cpf, String telefone, String email){
        if(nome == null || nome.isBlank()){
            throw  new IllegalArgumentException("Campo nome e obrigatorio");
        }else this.nome = nome;
        if (cpf == null || cpf.isBlank() || !cpf.matches(REGEX_CPF)) {
            throw new IllegalArgumentException("CPF invalido");
        }else this.cpf = cpf;
        if(telefone == null || telefone.isBlank()){
            throw new IllegalArgumentException("Telefone invalido");
        }else this.telefone = telefone;
        if(email == null || email.isBlank() || !email.matches(REGEX_EMAIL)){
            throw new IllegalArgumentException("Email invalido");
        }else this.email = email;
    }
    
    
}

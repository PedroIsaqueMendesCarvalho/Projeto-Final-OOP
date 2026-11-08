package usuarios;
import adocao.*;
import animais.*;

public interface Cadastro{
    default Candidato CadastroDeCandidato(String nome,String cpf, String email, String telefone){ //implementar, possivelmente default. Usado para permitir o cadastro de Candidato(?)
        return new Candidato(nome, cpf, email, telefone);

        
    }
    default AnimaisCadastrados CadastroDeAnimal(AnimaisCadastrados animaisCadastrados, Animal animal){ 
        animaisCadastrados.cadastrarAnimal(animal);
        return animaisCadastrados;
    }
    
}

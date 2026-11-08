package animais;
import java.util.*;

public class AnimaisCadastrados {
    private List<Animal> animais = new ArrayList<>();

    public void cadastrarAnimal(Animal animal) {
        if (animal == null) {
            throw new IllegalArgumentException("Animal nao pode ser nulo");
        }
        animais.add(animal);
    }

    public List<Animal> getAnimais() {
        return List.copyOf(animais);
    }
}

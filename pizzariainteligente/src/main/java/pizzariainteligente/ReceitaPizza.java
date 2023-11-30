import java.util.HashMap;
import java.util.Map;

public class ReceitaPizza {

    private final Map<String, Integer> ingredientes;

    public ReceitaPizza() {
        ingredientes = new HashMap<>();
    }

    public void addIngrediente(String ingredient, int quantity) {
        ingredientes.put(ingredient, quantity);
    }

    public Map<String, Integer> getIngredients() {
        return ingredientes;
    }
}

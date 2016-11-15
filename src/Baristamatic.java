import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Baristamatic {
	 public static void main(String[] args) {
	        List<Ingredient> ingredients = IngredientsDrinksList.getDefaultIngredients();
	        Map<Ingredient, Integer> ingredientMap = ingredients.stream().
	            collect(Collectors.toMap(ingredient -> ingredient, ingredient -> 10));
	        List<Drink> drinks = IngredientsDrinksList.getDefaultDrinks();

	        CoffeeMachine coffeeMachine = new CoffeeMachine(ingredients, ingredientMap, drinks);
	        CoffeeMachineDisplay coffeeMachineDisplay = new CoffeeMachineDisplay(coffeeMachine);

	        do {
	            coffeeMachineDisplay.displayStock();
	            coffeeMachineDisplay.displayMenu();
	        } while (coffeeMachineDisplay.processInput());
	    }

}

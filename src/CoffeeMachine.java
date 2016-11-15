import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoffeeMachine {
	 private final List<Ingredient> ingredients = new ArrayList<>();
	    private final Map<Ingredient, Integer> ingredientMap = new HashMap<>();
	    private final List<Drink> drinks = new ArrayList<>();

	    public CoffeeMachine(final Collection<? extends Ingredient> ingredients, final Map<? extends Ingredient, Integer> ingredientMap, final Collection<? extends Drink> drinks) {
	        this.ingredients.addAll(ingredients);
	        this.ingredientMap.putAll(ingredientMap);
	        this.drinks.addAll(drinks);

	        this.ingredients.forEach(ingredient -> this.ingredientMap.putIfAbsent(ingredient, 0));
	    }

	    public List<Ingredient> getIngredients() {
	        return new ArrayList<>(ingredients);
	    }

	    public List<Drink> getDrinks() {
	        return new ArrayList<>(drinks);
	    }

	    public int getStock(final Ingredient ingredient) {
	        IsValidIngredient(ingredient);
	        return ingredientMap.get(ingredient);
	    }

	    public boolean isOutOfStock(final Drink drink) {
	        IsValidDrink(drink);
	        return drink.getIngredientCount().entrySet().stream()
	            .anyMatch(entry -> {
	                Ingredient ingredient = entry.getKey();
	                long count = entry.getValue();
	                return (ingredientMap.get(ingredient) < count);
	            });
	    }

	    public void restock(final Map<? extends Ingredient, Integer> newingredientMap) {
	        this.ingredientMap.putAll(newingredientMap);
	    }

	    public void makeDrink(final Drink drink) {
	        IsValidDrink(drink);
	        if (isOutOfStock(drink)) {
	            throw new IllegalArgumentException("Out of stock: "+drink.getName());
	        }
	        drink.getIngredients().forEach(this::decrementStock);
	    }

	    private void decrementStock(final Ingredient ingredient) {
	        ingredientMap.compute(ingredient, (innerIngredient, stock) -> stock - 1);
	    }

	    private void IsValidIngredient(final Ingredient ingredient) {
	        if (!ingredients.contains(ingredient)) {
	            throw new IllegalArgumentException("Invalid selection: " + ingredient);
	        }
	    }

	    private void IsValidDrink(final Drink drink) {
	        if (!drinks.contains(drink)) {
	            throw new IllegalArgumentException("Invalid selection: " + drink);
	        }
	    }

}

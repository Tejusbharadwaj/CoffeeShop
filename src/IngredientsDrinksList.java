import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class IngredientsDrinksList {
	private IngredientsDrinksList() {
        throw new UnsupportedOperationException();
    }

    private static final List<Ingredient> LIST_OF_INGREDIENTS =
        Arrays.asList(
            new Ingredient("Coffee", new BigDecimal("0.75")),
            new Ingredient("Decaf Coffee", new BigDecimal("0.75")),
            new Ingredient("Sugar", new BigDecimal("0.25")),
            new Ingredient("Cream", new BigDecimal("0.25")),
            new Ingredient("Steamed Milk", new BigDecimal("0.35")),
            new Ingredient("Foamed Milk", new BigDecimal("0.35")),
            new Ingredient("Espresso", new BigDecimal("1.10")),
            new Ingredient("Cocoa", new BigDecimal("0.90")),
            new Ingredient("Whipped Cream", new BigDecimal("1.00"))
        );

    public static List<Ingredient> getDefaultIngredients() {
        return new ArrayList<>(LIST_OF_INGREDIENTS);
    }

    private static final List<Drink> LIST_OF_DRINKS = new ArrayList<>();
    static {
    	SearchIngredient searchIngredient = new SearchIngredient(getDefaultIngredients());
        LIST_OF_DRINKS.add(new Drink("Coffee", Arrays.asList(
            searchIngredient.search("Coffee"),
            searchIngredient.search("Coffee"),
            searchIngredient.search("Coffee"),
            searchIngredient.search("Sugar"),
            searchIngredient.search("Cream")
        )));
        LIST_OF_DRINKS.add(new Drink("Decaf Coffee", Arrays.asList(
            searchIngredient.search("Decaf Coffee"),
            searchIngredient.search("Decaf Coffee"),
            searchIngredient.search("Decaf Coffee"),
            searchIngredient.search("Sugar"),
            searchIngredient.search("Cream")
        )));
        LIST_OF_DRINKS.add(new Drink("Caffe Latte", Arrays.asList(
            searchIngredient.search("Espresso"),
            searchIngredient.search("Espresso"),
            searchIngredient.search("Steamed Milk")
        )));
        LIST_OF_DRINKS.add(new Drink("Caffe Americano", Arrays.asList(
            searchIngredient.search("Espresso"),
            searchIngredient.search("Espresso"),
            searchIngredient.search("Espresso")
        )));
        LIST_OF_DRINKS.add(new Drink("Caffe Mocha", Arrays.asList(
            searchIngredient.search("Espresso"),
            searchIngredient.search("Cocoa"),
            searchIngredient.search("Steamed Milk"),
            searchIngredient.search("Whipped Cream")
        )));
        LIST_OF_DRINKS.add(new Drink("Cappuccino", Arrays.asList(
            searchIngredient.search("Espresso"),
            searchIngredient.search("Espresso"),
            searchIngredient.search("Steamed Milk"),
            searchIngredient.search("Foamed Milk")
        )));
    }

    public static List<Drink> getDefaultDrinks() {
        return new ArrayList<>(LIST_OF_DRINKS);
    }

}

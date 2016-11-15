import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CoffeeMachineDisplay {
	private final CoffeeMachine coffeeMachine;
    private final Scanner scanner = new Scanner(System.in);

    private final List<Ingredient> ingredientsList;
    private final List<Drink> drinksList;

    public CoffeeMachineDisplay(final CoffeeMachine coffeeMachine) {
        this.coffeeMachine = Objects.requireNonNull(coffeeMachine, "coffeeMachine");

        this.ingredientsList = coffeeMachine.getIngredients().stream()
            .sorted(Comparator.comparing(Ingredient::getName))
            .collect(Collectors.toList());
        this.drinksList = coffeeMachine.getDrinks().stream()
            .sorted(Comparator.comparing(Drink::getName))
            .collect(Collectors.toList());
    }

    public void displayStock() {
    	System.out.println("Inventory:");
        for (Ingredient ingredient : ingredientsList) {
            int currentStock = coffeeMachine.getStock(ingredient);
            System.out.println(ingredient.getName() + "," + currentStock);
        }
        System.out.println();
    }

    public void displayMenu() {
    	System.out.println("Menu:");
        for (int i = 0; i < drinksList.size(); i++) {
            Drink drink = drinksList.get(i);
            System.out.print((i + 1) + "," + drink.getName() + "," + drink.getCost() + ",");
            if (coffeeMachine.isOutOfStock(drink)) {
                System.out.print("false");
            }
            else{
            	System.out.print("true");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static final Pattern IS_DIGIT_PATTERN = Pattern.compile("^\\d+$");
    private static final Pattern IS_R_PATTERN = Pattern.compile("^[rR]$");
    private static final Pattern IS_Q_PATTERN = Pattern.compile("^[qQ]$");

    public boolean processInput() {
        //System.out.print("Dispensing: ");

        String input = scanner.next();
        System.out.println();
        if (IS_DIGIT_PATTERN.matcher(input).matches()) {
            int selectedDrink = Integer.parseInt(input);
            if (selectedDrink < 1 || selectedDrink > drinksList.size()) {
                System.out.println("Invalid selection: "+input);
                System.out.println();
                return true;
            }
            Drink drink = drinksList.get(selectedDrink - 1);
            if (coffeeMachine.isOutOfStock(drink)) {
                System.out.println("Out of stock: "+drink.getName());
                System.out.println();
                return true;
            }
            coffeeMachine.makeDrink(drink);
            System.out.println("Dispensing: " + drink.getName());
            return true;
        }
        if (IS_R_PATTERN.matcher(input).matches()) {
            Map<Ingredient, Integer> newStock = coffeeMachine.getIngredients().stream()
                .collect(Collectors.toMap(ingredient -> ingredient, ingredient -> 10));
            coffeeMachine.restock(newStock);
//            System.out.println("Restocked");
//            System.out.println();
            return true;
        }
        if (IS_Q_PATTERN.matcher(input).matches()) {
//            System.out.println("Quit");
            return false;
        }
        System.out.println("Invalid selection: "+input);
        System.out.println();
        return true;
    }

}

import java.util.Collection;

public class SearchIngredient extends Searcher<String, Ingredient> {
    public SearchIngredient(final Collection<? extends Ingredient> ingredients) {
        super(Ingredient::getName, ingredients);
    }

}

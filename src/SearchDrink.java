import java.util.Collection;

public class SearchDrink extends Searcher<String, Drink> {
    public SearchDrink(final Collection<? extends Drink> drinks) {
        super(Drink::getName, drinks);
    }

}

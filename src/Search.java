import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 */

/**
 * @author bryan
 *
 */

public class Search {
/*
	public static ArrayList<Recipe> searchByName(String searchedString,
			ArrayList<Recipe> recipeArray) {

		ArrayList<Recipe> foundRecipesArray = new ArrayList<Recipe>();
		for (Recipe recipe : recipeArray) {
			if (recipe.getName().toLowerCase()
					.contains(searchedString.toLowerCase())) {
				foundRecipesArray.add(recipe);
			}
		}

		return foundRecipesArray;

	}

	public static ArrayList<Recipe> searchByIngredient(String searchedString,
			ArrayList<Recipe> recipeArray) {

		ArrayList<Recipe> foundRecipesArray = new ArrayList<Recipe>();
		for (Recipe recipe : recipeArray) {
			for (String ingredient : recipe.getIngredients()) {
				if (ingredient.toLowerCase().contains(
						searchedString.toLowerCase())) {
					foundRecipesArray.add(recipe);
					break;
				}
			}
		}

		return foundRecipesArray;

	}

	public static ArrayList<Recipe> searchByCategory(String searchedString,
			ArrayList<Recipe> recipeArray) {

		ArrayList<Recipe> foundRecipesArray = new ArrayList<Recipe>();
		for (Recipe recipe : recipeArray) {
			for (String category : recipe.getCategories()) {
				if (category.toLowerCase().contains(
						searchedString.toLowerCase())) {
					foundRecipesArray.add(recipe);
					break;
				}
			}
		}

		return foundRecipesArray;

	}

	public static Set<String> findCategories(ArrayList<Recipe> recipeArray) {
		Set<String> categorySet = new HashSet<String>();
        for (Recipe r : recipeArray) {
            for (String i : r.getCategories()) {
                categorySet.add(i.toLowerCase());
            }
        }
		return categorySet;

	}

	public static Set<String> findIngredients(
			ArrayList<Recipe> recipeArray) {

		Set<String> ingredientsSet = new HashSet<String>();
        for (Recipe r : recipeArray) {
            for (String i : r.getIngredients()) {
                ingredientsSet.add(i.toLowerCase());
            }
        }

		return ingredientsSet;

	}

	public static ArrayList<Recipe> sortRecipeAlphabetical(
			ArrayList<Recipe> recipeArray) {
		ArrayList<Recipe> alphabeticalList = new ArrayList<Recipe>();

		for (Recipe recipe : recipeArray) {
			alphabeticalList.add(recipe);
		}

		Collections.sort(alphabeticalList, new Comparator<Recipe>() {
			@Override
			public int compare(Recipe o1, Recipe o2) {
				return o1.getName().toLowerCase()
						.compareTo(o2.getName().toLowerCase());
			}
		});

		return alphabeticalList;
	}
*/
}

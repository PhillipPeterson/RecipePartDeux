import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 
 */

/**
 * @author bryan
 *
 */

public class Search {

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

	public static ArrayList<String> findCategories(ArrayList<Recipe> recipeArray) {
		ArrayList<String> categoryList = new ArrayList<String>();
		categoryList.add("Test");
		boolean copy = false;
		for (Recipe recipe : recipeArray) {
			for (String category : recipe.getCategories()) {
				copy = true;
				for (int i = 0; i < categoryList.size(); i++) {
					if (category.toLowerCase().equals(
							categoryList.get(i).toLowerCase())) {
						copy = false;
					}
				}
				if (copy) {
					categoryList.add("" + category + "");
				}
			}
		}
		categoryList.remove(0);
		return categoryList;

	}

	public static ArrayList<String> findIngredients(
			ArrayList<Recipe> recipeArray) {

		ArrayList<String> ingredientList = new ArrayList<String>();

		ingredientList.add("Placeholder");

		boolean copy = false;

		for (Recipe recipe : recipeArray) {

			for (String ingredient : recipe.getIngredients()) {

				copy = true;

				for (int i = 0; i < ingredientList.size(); i++) {

					if (ingredient.toLowerCase().equals(
							ingredientList.get(i).toLowerCase())) {

						copy = false;

					}

				}

				if (copy) {

					ingredientList.add("" + ingredient + "");

				}

			}

		}

		ingredientList.remove(0);

		return ingredientList;

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

}

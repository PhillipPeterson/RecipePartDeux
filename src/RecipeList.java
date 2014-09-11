import java.io.FileNotFoundException;
import java.util.*;

/**
 * Class RecipeList:
 * A container for all of the methods associated with the ArrayList<Recipes>,
 * such as add, edit, remove, search, etc.
 *
 * functions:
 * addRecipe(Recipe _newRecipe) - adds _newRecipe to the list, return void
 * searchByName(String searchedString) - return an ArrayList<Recipe> containing appropriate Recipes
 * searchByIngredient(String searchedString) - return an ArrayList<Recipe> containing appropriate Recipes
 * searchByCategory(String searchedString)
 * Set<String> findCategories()
 * Set<String> findIngredients()
 * ArrayList<Recipe> sortRecipeAlphabetical()0
 */

public class RecipeList {
    ArrayList<Recipe> recipeList;
    RecipeFileReader recRead;

    public RecipeList(String recipeListFileName) {
        recRead = new RecipeFileReader(recipeListFileName);
        try {
            recipeList = recRead.readInRecipes();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    public void addRecipe (Recipe _newRecipe) {
        recipeList.add(_newRecipe);
    }
    public void addRecipe (String _recipeName, String[] _ingredients, String[] _categories, String[] _directions) {
        Recipe newRecipe = new Recipe(_recipeName, _ingredients, _categories, _directions);
        addRecipe(newRecipe);
    }

    public ArrayList<Recipe> searchByName(String searchedString) {

        ArrayList<Recipe> foundRecipesArray = new ArrayList<Recipe>();
        for (Recipe recipe : recipeList) {
            if (recipe.getName().toLowerCase()
                    .contains(searchedString.toLowerCase())) {
                foundRecipesArray.add(recipe);
            }
        }

        return foundRecipesArray;

    }

    public ArrayList<Recipe> searchByIngredient(String searchedString) {

        ArrayList<Recipe> foundRecipesArray = new ArrayList<Recipe>();
        for (Recipe recipe : recipeList) {
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

    public ArrayList<Recipe> searchByCategory(String searchedString) {

        ArrayList<Recipe> foundRecipesArray = new ArrayList<Recipe>();
        for (Recipe recipe : recipeList) {
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

    public Set<String> findCategories() {
        Set<String> categorySet = new HashSet<String>();
        for (Recipe r : recipeList) {
            for (String i : r.getCategories()) {
                categorySet.add(i.toLowerCase());
            }
        }
        return categorySet;

    }

    public Set<String> findIngredients() {

        Set<String> ingredientsSet = new HashSet<String>();
        for (Recipe r : recipeList) {
            for (String i : r.getIngredients()) {
                ingredientsSet.add(i.toLowerCase());
            }
        }

        return ingredientsSet;

    }

    public ArrayList<Recipe> sortRecipeAlphabetical() {
        ArrayList<Recipe> alphabeticalList = new ArrayList<Recipe>();

        for (Recipe recipe : recipeList) {
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

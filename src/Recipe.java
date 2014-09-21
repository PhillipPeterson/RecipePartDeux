

public class Recipe {
	
	public String name;
    public String description;
	public String[] ingredients;
    public String[] ingredientAmounts;
	public String[] categories;
	public String directions;
    public int recipeId;
	
	Recipe(String _name, String _description, String[] _ingredients, String[] _ingredientAmounts, String[] _categories, String _directions) {
		name = _name;
        description = _description;
		ingredients = _ingredients;
        ingredientAmounts = _ingredientAmounts;
		categories = _categories;
		directions = _directions;
		
	}
	
	public String toString() {
		String output = "\n--- " + name + " ---";
        output += "\nDescription: " + description;
		
		output += "\n\tIngredients:";
		for (int i = 0; i < ingredients.length; i++) {
			output += "\n\t\t" + ingredients[i] + "(" + ingredientAmounts[i] + ")";		//
		}
		output += "\n\tCategories:";
		for (int c = 0; c < categories.length; c++) {
			output += "\n\t\t" + categories[c];			// Each of these goes through the array of strings and prints each
		}
		output += "\n\tDirections:";
			output += "\n\t\t" + directions;			//

		return output;
	}
	
    public static void main(String [] args) {
        System.out.println("Testing the Recipe Class:");
        System.out.println(" - Recipe.equals(): " + recipeEqualsTest());

    }

    public boolean equals(Recipe other) {
        boolean output = this.name.equalsIgnoreCase(other.name);
        output &= equalStringArrays(ingredients, other.ingredients);
        output &= equalStringArrays(ingredientAmounts, other.ingredientAmounts);
        output &= equalStringArrays(categories, other.categories);
        output &= directions.equals(other.directions);
        return output;
    }

    public static boolean equalDatabaseEntryArrays(DatabaseEntry[] arr1, DatabaseEntry[] arr2) {
        boolean output = true;
        if(arr1 == null){
            if(arr2 != null) {
                output = false;
            }
        }
        else if(arr2 == null) {
            output = false;
        }
        else if(arr1.length == arr2.length) {
            for(int i =0; i< arr1.length; i++) {
                output &= arr1[i].name.equalsIgnoreCase(arr2[i].name);
            }
        }
        else
            output = false;
        return output;
    }

    public static boolean equalStringArrays(String[] arr1, String[] arr2) {
        boolean equal = true;
        equal &= arr1.length == arr2.length;
        if(equal) {
            for(int i = 0; i < arr1.length; i++) {
                equal &= arr1[i].equals(arr2[i]);
            }
        }
        return equal;
    }

    private static String recipeEqualsTest() {
        // create some recipes to test...
        String[] ingredients = new String[]{"ingredient1", "ingredient2"};
        String[] ingredients2 = new String[]{"ingredient2", "ingredient2"};
        String[] category = new String[]{"Drinks"};
        String[] direction = new String[]{"Stir"};
        Recipe first  = new Recipe("testRecipe", "testRecipe", ingredients, ingredients2, category, "test");
        Recipe second  = new Recipe("TESTRECIPE","testRecipe", ingredients, ingredients2, category, "test");
        Recipe third  = new Recipe("testRecipe2", "testRecipe", ingredients, ingredients2, category, "test");
        Recipe fourth  = new Recipe("testRecipe", "testRecipe", ingredients, ingredients, category, "test");

        String returnString = "";
        boolean testSuccessful;
        //compare first and first - should be true
        boolean test1Success = first.equals(first);
        if(!test1Success)
            returnString += "test1, ";

        //compare first and second - should be true (it should ignore case)
        boolean test2Success = first.equals(second);
        if(!test2Success)
            returnString += "test2, ";

        // compare the first and third - should be false due to different titles
        boolean test3Success = !first.equals(third);
        if(!test3Success)
            returnString += "test3, ";

        // compare the first and fourth - should be false
        boolean test4Success = !first.equals(fourth);
        if(!test4Success)
            returnString += "test4, ";


        testSuccessful = test1Success && test2Success && test3Success && test4Success;

        if(testSuccessful)
            returnString = "success";
        else
            returnString = "failure: " + returnString;
        return returnString;
    }

}

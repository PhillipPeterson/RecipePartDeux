

public class Recipe {
	
	public String name;
    public String description;
	public DatabaseEntry[] ingredients;
    public DatabaseEntry[] ingredientAmounts;
	public DatabaseEntry[] categories;
	public DatabaseEntry[] directions;
    public int recipeId;
	
	Recipe(String _name, String _description, DatabaseEntry[] _ingredients, DatabaseEntry[] _ingredientAmounts, DatabaseEntry[] _categories, DatabaseEntry[] _directions) {
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
		for (int d = 0; d < directions.length; d++) {
			output += "\n\t\t" + directions[d];			//
		}
		
		return output;
	}
	
    public static void main(String [] args) {
        System.out.println("Testing the Recipe Class:");
        System.out.println(" - Recipe.equals(): " + recipeEqualsTest());

    }

    public boolean equals(Recipe other) {
        boolean output = this.name.equalsIgnoreCase(other.name);
        output &= equalDatabaseEntryArrays(ingredients, other.ingredients);
        output &= equalDatabaseEntryArrays(categories, other.categories);
        output &= equalDatabaseEntryArrays(directions, other.directions);
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

    private static String recipeEqualsTest() {
        // create some recipes to test...
        DatabaseEntry ingredient1 = new DatabaseEntry(1, "ingredient1");
        DatabaseEntry ingredient2 = new DatabaseEntry(2, "ingredient1");
        DatabaseEntry ingredient3 = new DatabaseEntry(1, "ingredient1");
        DatabaseEntry category = new DatabaseEntry(1, "Drinks");
        DatabaseEntry direction = new DatabaseEntry(1, "Stir");
        Recipe first  = new Recipe("testRecipe", "testRecipe", new DatabaseEntry[]{ingredient1, ingredient2}, new DatabaseEntry[]{ingredient1, ingredient2}, new DatabaseEntry[]{category}, new DatabaseEntry[]{direction});
        Recipe second  = new Recipe("TESTRECIPE","testRecipe", new DatabaseEntry[]{ingredient1, ingredient2}, new DatabaseEntry[]{ingredient1, ingredient2}, new DatabaseEntry[]{category}, new DatabaseEntry[]{direction});
        Recipe third  = new Recipe("testRecipe2", "testRecipe", new DatabaseEntry[]{ingredient1, ingredient2}, new DatabaseEntry[]{ingredient1, ingredient2}, new DatabaseEntry[]{category}, new DatabaseEntry[]{direction});
        Recipe fourth  = new Recipe("testRecipe", "testRecipe", new DatabaseEntry[]{ingredient3, ingredient2}, new DatabaseEntry[]{ingredient1, ingredient2}, new DatabaseEntry[]{category}, new DatabaseEntry[]{direction});

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

        // compare the first and fourth - should be true
        boolean test4Success = first.equals(fourth);
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

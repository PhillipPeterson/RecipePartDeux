

public class Recipe {
	
	String name;
	String[] ingredients;
	String[] categories;
	String[] directions;
	
	Recipe(String _name, String[] _ingredients, String[] _categories, String[] _directions) {
		this.name = _name;
		this.ingredients = _ingredients;
		this.categories = _categories;
		this.directions = _directions;
		
	}
	
	public String getName(){
		return this.name;
	}
	
	public String[] getCategories(){
		return this.categories;
		
	}
	
	public String[] getIngredients(){
		return this.ingredients;
		
	}
	
    public String[] getDirections(){
		 return this.directions;
		 }
	 
	 
	public String toString() {
		String output = "\n--- " + name + " ---";
		
		output += "\n\tIngredients:";
		for (int i = 0; i < ingredients.length; i++) {
			output += "\n\t\t" + ingredients[i];		//
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
        output &= equalStringArrays(ingredients, other.ingredients);
        output &= equalStringArrays(categories, other.categories);
        output &= equalStringArrays(directions, other.directions);
        return output;
    }

    public static boolean equalStringArrays(String[] arr1, String[] arr2) {
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
                output &= arr1[i].equalsIgnoreCase(arr2[i]);
            }
        }
        else
            output = false;
        return output;
    }


    private static String recipeEqualsTest() {
        // create some recipes to test...
        Recipe first  = new Recipe("testRecipe", new String[]{"ingredient1", "ingredient2"}, new String[]{"category1", "category2"}, new String[]{"direction1", "direction2"});
        Recipe second = new Recipe("testRecipe", new String[]{"ingredient1", "ingredient2"}, new String[]{"category1", "category2"}, new String[]{"direction1", "direction2"});
        Recipe third = new Recipe("testRecipe2", new String[]{"ingredient1", "ingredient2"}, new String[]{"category1", "category2"}, new String[]{"direction1", "direction2"});
        Recipe fourth = new Recipe("testRecipe", new String[]{"ingredient1", "ingredient3"}, new String[]{"category1", "category2"}, new String[]{"direction1", "direction2"});
        Recipe fifth = new Recipe("testRecipe", new String[]{"ingredient1", "ingredient2"}, new String[]{"category1"}, new String[]{"direction1", "direction2"});

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

        // compare the first and fourth - should be false due to different ingredients
        boolean test4Success = !first.equals(fourth);
        if(!test4Success)
            returnString += "test4, ";

        // compare the first and fifth - should be false due to different categories
        boolean test5Success = !first.equals(fifth);
        if(!test5Success)
            returnString += "test5, ";

        testSuccessful = test1Success && test2Success && test3Success && test4Success && test5Success;

        if(testSuccessful)
            returnString = "success";
        else
            returnString = "failure: " + returnString;
        return returnString;
    }

}

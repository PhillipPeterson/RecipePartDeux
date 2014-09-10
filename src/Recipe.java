

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
	


}

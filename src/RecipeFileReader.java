import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RecipeFileReader {

	
	String filePath;
	
	//Delimiters used in file
	final String RECIPE_NAME_DELIM = "Name:";
	final String RECIPE_INGREDIENTS_DELIM = "Ingredients:";
	final String RECIPE_PREPARATION_DELIM = "Preparation:";
	final String RECIPE_CATEGORY_DELIM = "Category:";
	final String RECIPE_END_DELIM = "EndOfRecipe";
	final String DELIMETER = "\\|\\|\\|";
	
	RecipeFileReader(String filePath)
	{
		this.filePath = filePath;
	}
	
	public ArrayList<Recipe> readInRecipes() throws FileNotFoundException
	{
		ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
		
		File file = new File(filePath);
		Scanner fileScanner = new Scanner(file);
		
		String recipeName = null;
		String[] recipeInstructions = null;
		String[] recipeIngredients = null;
		String[] recipeCategory = null;
		
		while(fileScanner.hasNext())
		{
			String line = fileScanner.nextLine();
		
			//if the line contains info on the name
			if(line.contains(RECIPE_NAME_DELIM))
			{
				recipeName = line.replace(RECIPE_NAME_DELIM, "");
			}
			
			//if the line contains info on the ingredients
			else if(line.contains(RECIPE_INGREDIENTS_DELIM))
			{
				line = line.replace(RECIPE_INGREDIENTS_DELIM, "");
				
				recipeIngredients = line.split(DELIMETER);
			
			}
			
			//if the line contains info on the preparation
			else if(line.contains(RECIPE_PREPARATION_DELIM))
			{
				line = line.replace(RECIPE_PREPARATION_DELIM, "");
				
				recipeInstructions = line.split(DELIMETER);
			}
			
			//if the line contains info on the category
			else if(line.contains(RECIPE_CATEGORY_DELIM))
			{
				line = line.replace(RECIPE_CATEGORY_DELIM, "");
				
				recipeCategory = line.split(DELIMETER);
			}
			
			//All recipe info is finished, create recipe object and add it to the list
			else if(line.contains(RECIPE_END_DELIM))
			{
				recipeList.add(new Recipe(recipeName, recipeIngredients, recipeCategory, recipeInstructions));
			}
		}
		
		fileScanner.close();
		
		return recipeList;
	}
	
}

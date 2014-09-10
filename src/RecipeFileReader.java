import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.Writer;

public class RecipeFileReader {

	
	String filePath;
	
	//Delimiters used in file
	final String RECIPE_NAME_DELIM = "Name:";
	final String RECIPE_INGREDIENTS_DELIM = "Ingredients:";
	final String RECIPE_PREPARATION_DELIM = "Preparation:";
	final String RECIPE_CATEGORY_DELIM = "Category:";
	final String RECIPE_END_DELIM = "EndOfRecipe";
	final String DELIMETER = "\\|\\|\\|";
    final String DELIM_TO_TEXT = "|||";
    final String NEWLINE = "\r\n";
	
	RecipeFileReader(String filePath)
	{
		this.filePath = filePath;
	}


    //convert the information into a string to store in the raw text for persistance
    //example output
    //Name:Apple Cornbread
    //Ingredients:2 apples peeled and chopped thinly|||1 1/2 Cup unbleached white flour|||1 1/2 cup blue corn meal or regular yellow corn meal|||3 1/2 tsp baking soda|||1/2 tsp salt|||1 tbsp Sucanat or Brown Sugar||| 2 1/4 cup vanilla soymilk|||1 tsp cinnamon|||1/4 cup apple sauce|||2 tbsp maple syrup|||
    //Preparation:1. Preheat oven to 400�F.|||2. In a large bowl, combine all ingredients except the apple.|||3. Mix in the apple. Do not overmix as the bread could become tough.|||4. Bake 35-45 minutes on the top shelf of the oven.|||5. Bread is done when an inserted knife comes out clean, about 40 minutes.|||
    //Category:Breakfast
    //EndOfRecipe
    public void writeToFile(ArrayList<Recipe> recipeList)
    {
        String outputString = "";
        for(Integer i = 0; i < recipeList.size(); i++) {
            outputString += RECIPE_NAME_DELIM + recipeList.get(i).getName() + NEWLINE;
            outputString += RECIPE_INGREDIENTS_DELIM;
            String[] ingredients = recipeList.get(i).getIngredients();
            for(Integer j = 0; j < ingredients.length; j++) {
                outputString += ingredients[j] + DELIM_TO_TEXT;
            }
            outputString += NEWLINE + RECIPE_PREPARATION_DELIM;
            String[] directions = recipeList.get(i).getDirections();
            for(Integer j = 0; j < directions.length; j++) {
                outputString += directions[j] + DELIM_TO_TEXT;
            }
            outputString += NEWLINE + RECIPE_CATEGORY_DELIM;
            String[] categories = recipeList.get(i).getCategories();
            for(Integer j = 0; j < categories.length; j++) {
                outputString += categories[j] + DELIM_TO_TEXT;
            }
            outputString += NEWLINE + RECIPE_END_DELIM + NEWLINE;
        }
        for (Integer i = 0; i < recipeList.size(); i++) {
            System.out.println(recipeList.get(i).getName());
        }



        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(filePath), "utf-8"));
            writer.write(outputString);
        } catch (IOException ex) {
            // report
        } finally {
            try {writer.close();} catch (Exception ex) {}
        }


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

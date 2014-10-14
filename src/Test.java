import java.sql.SQLException;

/**
 * Created by Jeff on 9/23/2014.
 */
public class Test {
    private static Recipe ramen = new Recipe("ramen noodles", "dehydrated salty noodles", new String[]{"dry ramen", "water"}, new String[]{"one package", "1 cup"}, new String[]{"snacks"}, "boil water, place ramen in boiling water, consume");
    private static Recipe soup = new Recipe("soup", "canned soup", new String[]{"soup", "water"}, new String[]{"one can", "one cup"}, new String[]{"snacks","side dishes"}, "combine ingredients, add heat");

    public static void main(String[] args) {
        RecipeDatabase RD = new RecipeDatabase("test.db");
        RD.init();
        boolean success;

        //public DatabaseEntry[] getRecipes()
        resetDatabase(RD);
        System.out.print("Testing getRecipes... ");
        DatabaseEntry[] recipeList = RD.getRecipes();
        success = recipeList.length == 2;
        reportSuccess(success);

        //public DatabaseEntry[] getRecipesWithName(String name)
        resetDatabase(RD);
        System.out.print("Testing getRecipesWithName... ");
        recipeList = RD.getRecipesWithName("soup");
        success = recipeList.length == 1;
        recipeList = RD.getRecipesWithName("s");
        success &= recipeList.length == 2;
        reportSuccess(success);

        //public DatabaseEntry[] getRecipesInCategory(String category)
        resetDatabase(RD);
        System.out.print("Testing getRecipesInCategory... ");
        recipeList = RD.getRecipesInCategory("side dishes");
        success = recipeList.length == 1;
        recipeList = RD.getRecipesInCategory("snacks");
        success &= recipeList.length == 2;
        reportSuccess(success);

        //public Recipe readRecipe(int recipeId)
        resetDatabase(RD);
        System.out.print("Testing readRecipe... ");
        recipeList = RD.getRecipesWithName("ramen");
        Recipe retreivedRecipe = RD.readRecipe(recipeList[0].id);
        ramen.recipeId = retreivedRecipe.recipeId;
        success = ramen.equals(retreivedRecipe);
        reportSuccess(success);

        //public String [] getCategories()
        resetDatabase(RD);
        System.out.print("Testing getCategories... ");
        String[] categories = RD.getCategories();
        success = categories.length == 2;
        reportSuccess(success);

        //public void deleteRecipe(int recipeId)
        resetDatabase(RD);
        System.out.print("Testing deleteRecipe... ");
        recipeList = RD.getRecipes();
        RD.deleteRecipe(recipeList[0].id);
        recipeList = RD.getRecipes();
        success = recipeList.length == 1;
        reportSuccess(success);

        //public int updateRecipe(Recipe recipe), returns recipeId of the new recipe
        resetDatabase(RD);
        System.out.print("Testing updateRecipe... ");
        recipeList = RD.getRecipes();
        ramen.recipeId = RD.getRecipesWithName("ramen")[0].id;
        soup.recipeId = ramen.recipeId;
        RD.updateRecipe(soup);
        retreivedRecipe = RD.readRecipe(ramen.recipeId);
        success = soup.equals(retreivedRecipe);
        reportSuccess(success);


        RD.close();
    }

    public static void resetDatabase(RecipeDatabase RD) {
        try {
            RD.stmt.executeUpdate("delete from recipes;");
            RD.insertRecipe(ramen);
            RD.insertRecipe(soup);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void reportSuccess(boolean success) {
        if(success) {
            System.out.println("Success");
        }
        else {
            System.out.println("Failure");
        }
    }

}

import java.sql.*;

// create a recipeDatabase objected using the constructor
// RecipeDatabase DB = new RecipeDatabase("filename.db");

//public DatabaseEntry[] getRecipes()
//public DatabaseEntry[] getRecipesWithName(String name)
//public DatabaseEntry[] getRecipesInCategory(String category)
//public Recipe readRecipe(int recipeId)
//public String [] getCategories()
//public void deleteRecipe(int recipeId)
//public int insertRecipe (String _recipeName, String _description, String[] _ingredients, String[] _ingredientAmounts, String[] _categories, String _directions), returns recipeId of the new recipe
//public int updateRecipe(Recipe recipe), returns recipeId of the new recipe

public class RecipeDatabase {
    private String dbFileName;
    private Connection connection;
    private Statement stmt;

    public RecipeDatabase() {
        this("recipe.db");
    }
    public RecipeDatabase(String _fileName) {
        dbFileName = _fileName;
        init();
    }
    
    public static void main(String[] args) {
        try {
            RecipeDatabase RD = new RecipeDatabase("recipe.db");
            RD.init();


            RD.printRecipes();
            RD.stmt.executeUpdate("delete from recipes;");
            RD.insertRecipe("ramen noodles", "dehydrated salty noodles", new String[]{"dry ramen", "water"}, new String[]{"one package", "1 cup"}, new String[]{"snacks"}, "boil water, place ramen in boiling water, consume");
            RD.insertRecipe("soup", "canned soup", new String[]{"soup", "water"}, new String[]{"one can", "one cup"}, new String[]{"snacks","side dishes"}, "combine ingredients, add heat");
            RD.insertRecipe("blph", "canned soup", new String[]{"soup", "water"}, new String[]{"one can", "one cup"}, new String[]{"snacks","side dishes"}, "combine ingredients, add heat");
            RD.insertRecipe("blafiojd", "canned soup", new String[]{"soup", "water"}, new String[]{"one can", "one cup"}, new String[]{"snacks","side dishes"}, "combine ingredients, add heat");
            RD.insertRecipe("blah", "canned soup", new String[]{"soup", "water"}, new String[]{"one can", "one cup"}, new String[]{"snacks","side dishes"}, "combine ingredients, add heat");
            RD.insertRecipe("blang", "canned soup", new String[]{"soup", "water"}, new String[]{"one can", "one cup"}, new String[]{"snacks","side dishes"}, "combine ingredients, add heat");
            RD.insertRecipe("blah", "canned soup", new String[]{"soup", "water"}, new String[]{"one can", "one cup"}, new String[]{"snacks","side dishes"}, "combine ingredients, add heat");
            RD.insertRecipe("black", "canned soup", new String[]{"soup", "water"}, new String[]{"one can", "one cup"}, new String[]{"snacks","side dishes"}, "combine ingredients, add heat");
            RD.insertRecipe("blah", "canned soup", new String[]{"soup", "water"}, new String[]{"one can", "one cup"}, new String[]{"snacks","side dishes"}, "combine ingredients, add heat");
            RD.insertRecipe("blah", "canned soup", new String[]{"soup", "water"}, new String[]{"one can", "one cup"}, new String[]{"snacks","side dishes"}, "combine ingredients, add heat");
            
            RD.printRecipes();


            for(DatabaseEntry recipe:RD.getRecipesWithName("s")) {
                System.out.println(RD.readRecipe(recipe.id).toString());
            }

            System.out.println("Category Names: ");
            String[] categoryNames = RD.getCategories();
            for(String categoryName: categoryNames) {
                System.out.println(categoryName);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
}

    public void init() {
        try {
            String sDbUrl;
            int iTimeout = 30;

            // register the driver
            String sDriverName = "org.sqlite.JDBC";
            Class.forName(sDriverName);

            // now we set up a set of fairly basic string variables to use in the body of the code proper
            String sJdbc = "jdbc:sqlite";
            // which will produce a legitimate Url for SqlLite JDBC :
            // jdbc:sqlite:test.db
            sDbUrl = sJdbc + ":" + dbFileName;

            Class.forName(sDriverName);
            connection = DriverManager.getConnection(sDbUrl);
            stmt = connection.createStatement();
        } catch (Exception e) {
            System.err.println("Error initializing database: " + e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void createCategory(String categoryName) throws Exception{
        ResultSet rs = stmt.executeQuery("select count(1) from categories where name='" + categoryName +"';");
        if(rs.getInt(1) == 0) {
            stmt.executeUpdate("insert into categories (name) values ('" + categoryName + "');");
        }
        else {
            System.out.println("Category " + categoryName + " already exists");
        }
    }

    public String [] getCategories() {
        try {
            ResultSet rs = stmt.executeQuery("select count(1) from categories");
            if (rs == null) {
                return new String[0];
            }
            int size = rs.getInt(1);
            String[] output = new String[size];
            int index = 0;
            rs = stmt.executeQuery("select * from categories");
            while (rs.next()) {
                output[index++] = rs.getString("name");
            }
            return output;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteRecipe(int recipeId) {
        try {
            stmt.executeUpdate("delete from recipes where id = " + recipeId + ";");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public int insert(String table, String name) throws Exception{
        int id = 0;
        ResultSet rs = stmt.executeQuery("select count(1) from " + table + " where name='" + name +"';");
        if(rs.getInt(1) == 0) {
            stmt.executeUpdate("insert into " + table + " (name) values ('" + name + "');");
            id = stmt.getGeneratedKeys().getInt(1);
        }
        else {
            // item already exists, fetching id
            id = stmt.executeQuery("select id from " + table + " where name='" + name +"';").getInt(1);

        }
        return id;
    }

    public String databaseEntryArrayToString(DatabaseEntry[] arr) {
        String output = "";
        for(DatabaseEntry entry: arr) {
            output += entry.id + ": " + entry.name + ", ";
        }
        return output;
    }

    public int insertRecipe (String _recipeName, String _description, String[] _ingredients, String[] _ingredientAmounts, String[] _categories, String _directions) {
        int recipeId = 0;
        try {

            stmt.executeUpdate("insert into recipes (name,description) values ('" + _recipeName + "', '" + _description + "');");
            ResultSet rs = stmt.getGeneratedKeys();
            recipeId = rs.getInt(1);
            for(int i = 0; i < _ingredients.length; i++) {
                int ingredientId = insert("ingredients", _ingredients[i]); // safe insert in case the ingredient already exists
                stmt.executeUpdate("insert into recipes_ingredients (recipeId, ingredientId, ingredientAmount) values (" + recipeId + "," + ingredientId + ",'" + _ingredientAmounts[i] + "');");
            }
            for(String category: _categories) {
                int categoryId = insert("categories", category);
                stmt.executeUpdate("insert into recipes_categories (recipeId, categoryId) values (" + recipeId + "," + categoryId + ");");
            }
            stmt.executeUpdate("insert into directions (recipeId, direction) values (" + recipeId + ", '" + _directions + "');");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return recipeId;
    }

    public Recipe readRecipe(int recipeId) {
        try {
            //first read the name
            ResultSet rs = stmt.executeQuery("select * from recipes where id = '" + recipeId + "';");
            String recipeName = rs.getString("name");
            String description = rs.getString("description");


            //reading categories
            int num = stmt.executeQuery("select count(1) from recipes_categories where recipeId = '" + recipeId + "';").getInt(1);
            String[] categories = new String[num];
            rs = stmt.executeQuery("select categories.id,categories.name from categories " +
                    "join recipes_categories on categories.id = recipes_categories.categoryId " +
                    "where recipes_categories.recipeId=" + recipeId + ";");
            for (int i = 0; rs.next(); i++) {
                categories[i] = rs.getString(2);
            }

            //now reading ingredients
            num = stmt.executeQuery("select count(1) from recipes_ingredients where recipeId = '" + recipeId + "';").getInt(1);
            String[] ingredients = new String[num];
            String[] ingredientAmounts = new String[num];
            rs = stmt.executeQuery("select ingredients.id,name,ingredientAmount from ingredients " +
                    "join recipes_ingredients on ingredients.id = recipes_ingredients.ingredientId " +
                    "where recipes_ingredients.recipeId=" + recipeId + ";");
            for (int i = 0; rs.next(); i++) {
                ingredients[i] = rs.getString(2);
                ingredientAmounts[i] = rs.getString(3);
            }

            //now reading directions
            /*num = stmt.executeQuery("select count(1) from directions where recipeId = '" + recipeId + "';").getInt(1);
            DatabaseEntry[] directionsDE = new DatabaseEntry[num];
            rs = stmt.executeQuery("select id, direction from directions where recipeId=" + recipeId + ";");
            for (int i = 0; rs.next(); i++) {
                directionsDE[i] = new DatabaseEntry(rs.getInt(1), rs.getString(2));
            }
            */
            String directions = stmt.executeQuery("select direction from directions where recipeId='" + recipeId + "';").getString(1);

            return new Recipe(recipeName, description, ingredients, ingredientAmounts, categories, directions);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // outputs an array of DatabaseEntry objects, sorted by name
    public DatabaseEntry[] getRecipes() {
        try {
            ResultSet rs = stmt.executeQuery("select count(1) from recipes;");
            DatabaseEntry[] output = new DatabaseEntry[rs.getInt(1)];
            rs = stmt.executeQuery("select * from recipes order by name");
            for (int i = 0; rs.next(); i++) {
                output[i] = new DatabaseEntry(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
            return output;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void printRecipes() {
        try {
            DatabaseEntry[] cookbook = getRecipes();
            System.out.println("Printing " + cookbook.length + " recipes");
            for (DatabaseEntry recipe : cookbook) {
                System.out.println(recipe.id + ": " + recipe.name + " - " + recipe.description);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public DatabaseEntry[] getRecipesInCategory(String category) throws Exception {
        try {
            ResultSet rs = stmt.executeQuery("select count(1) from recipes_categories " +
                    "join categories on recipes_categories.categoryId = categories.id " +
                    "where categories.name='" + category + "';");

            DatabaseEntry[] output = new DatabaseEntry[rs.getInt(1)];
            //System.out.println("retrieving " + rs.getInt(1) + " entries");
            rs = stmt.executeQuery("select recipes.id, recipes.name, recipes.description from recipes " +
                    "join recipes_categories on recipes.id=recipes_categories.recipeId " +
                    "join categories on recipes_categories.categoryId = categories.id " +
                    "where categories.name='" + category + "';");
            for (int i = 0; rs.next(); i++) {
                output[i] = new DatabaseEntry(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
            return output;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public DatabaseEntry[] getRecipesWithName(String name) {
        try {
            ResultSet rs = stmt.executeQuery("select count(1) from recipes where name like '%" + name + "%';");
            DatabaseEntry[] output = new DatabaseEntry[rs.getInt(1)];
            //System.out.println("retrieving " + rs.getInt(1) + " entries");
            rs = stmt.executeQuery("select recipes.id, recipes.name, recipes.description from recipes where name like '%" + name + "%';");
            for (int i = 0; rs.next(); i++) {
                output[i] = new DatabaseEntry(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
            return output;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int updateRecipe(Recipe recipe) {
        deleteRecipe(recipe.recipeId);
        String [] ingredients = new String[recipe.ingredients.length];
        for(int i = 0; i < ingredients.length; i++) {
            ingredients[i] = recipe.ingredients[i];
        }
        String [] ingredientAmounts = new String[recipe.ingredientAmounts.length];
        for(int i = 0; i < ingredientAmounts.length; i++) {
            ingredientAmounts[i] = recipe.ingredientAmounts[i];
        }
        String [] categories = new String[recipe.categories.length];
        for(int i = 0; i < categories.length; i++) {
            categories[i] = recipe.categories[i];
        }
        return insertRecipe(recipe.name, recipe.description, ingredients, ingredientAmounts, categories, recipe.directions);
    }
}


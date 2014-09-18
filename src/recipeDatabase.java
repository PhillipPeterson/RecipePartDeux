import java.sql.*;


public class recipeDatabase {
    private static String sTempDb = "recipe.db";
    private static Connection connection;
    private static Statement stmt;


    public static void main(String[] args) {
        try {
            init();


            printRecipes();
            stmt.executeUpdate("delete from recipes;");
            insertRecipe("ramen", "dehydrated salty noodles", new String[]{"dry ramen", "water"}, new String[]{"one package", "1 cup"}, new String[]{"snacks"}, new String[]{"boil water", "place ramen in boiling water", "consume"});
            insertRecipe("soup", "canned soup", new String[]{"soup", "water"}, new String[]{"one can", "one cup"}, new String[]{"snacks","side dishes"}, new String[]{"combine ingredients", "add heat"});
            printRecipes();

            for(DatabaseEntry recipe:sortedList()) {
                System.out.println(readRecipe(recipe.id).toString());
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }
}


    public static void init() throws Exception {
        String sDbUrl;
        int iTimeout = 30;

        // register the driver
        String sDriverName = "org.sqlite.JDBC";
        Class.forName(sDriverName);

        // now we set up a set of fairly basic string variables to use in the body of the code proper
        String sJdbc = "jdbc:sqlite";
        // which will produce a legitimate Url for SqlLite JDBC :
        // jdbc:sqlite:test.db
        sDbUrl = sJdbc + ":" + sTempDb;

        try {
            Class.forName(sDriverName);
            connection = DriverManager.getConnection(sDbUrl);
            stmt = connection.createStatement();
        } catch (Exception e) {
            System.err.println("Error initializing database: " + e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public static void createCategory(String categoryName) throws Exception{
        ResultSet rs = stmt.executeQuery("select count(1) from categories where name='" + categoryName +"';");
        if(rs.getInt(1) == 0) {
            stmt.executeUpdate("insert into categories (name) values ('" + categoryName + "');");
        }
        else {
            System.out.println("Category " + categoryName + " already exists");
        }
    }

    public static String [] getCategories() throws Exception {
        ResultSet rs = stmt.executeQuery("select count(1) from categories");
        if(rs == null) {
            return new String[0];
        }
        int size = rs.getInt(1);
        String[] output = new String[size];
        int index = 0;
        rs = stmt.executeQuery("select * from categories");
        while (rs.next()) {
            output[index++] = rs.getInt("id") + ": " + rs.getString("name");
        }
        return output;
    }

    public static DatabaseEntry [] select(String table) throws Exception {
        ResultSet rs = stmt.executeQuery("select count(1) from " + table);
        int size = rs.getInt(1);
        if(size == 0) {
            return new DatabaseEntry[0];
        }
        DatabaseEntry[] output = new DatabaseEntry[size];
        int index = 0;
        rs = stmt.executeQuery("select * from " + table + ";");
        //System.out.println(rs.getInt(1));
        while (rs.next()) {
            output[index++] = new DatabaseEntry(rs.getInt(1), rs.getString(2));

        }
        return output;
    }

    public static void delete(String table, String name) throws Exception {
        stmt.executeUpdate("delete from " + table + " where name = '" + name + "';");
    }

    public static int insert(String table, String name) throws Exception{
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

    public static String databaseEntryArrayToString(DatabaseEntry[] arr) {
        String output = "";
        for(DatabaseEntry entry: arr) {
            output += entry.id + ": " + entry.name + ", ";
        }
        return output;
    }

    public static int insertRecipe (String _recipeName, String _description, String[] _ingredients, String[] _ingredientAmounts, String[] _categories, String[] _directions) {
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
            for(String direction: _directions) {
                stmt.executeUpdate("insert into directions (recipeId, direction) values (" + recipeId + ", '" + direction + "');");
            }


        }
        catch (Exception e) {

        }
        return recipeId;
    }

    public static Recipe readRecipe(int recipeId) throws  Exception {
        //first read the name
        ResultSet rs = stmt.executeQuery("select * from recipes where id = '" + recipeId + "';");
        String recipeName = rs.getString("name");
        String description = rs.getString("description");


        //reading categories
        int num = stmt.executeQuery("select count(1) from recipes_categories where recipeId = '" + recipeId + "';").getInt(1);
        DatabaseEntry[] categoriesDE = new DatabaseEntry[num];
        rs = stmt.executeQuery("select categories.id,categories.name from categories " +
                "join recipes_categories on categories.id = recipes_categories.categoryId " +
                "where recipes_categories.recipeId=" + recipeId + ";");
        for(int i = 0; rs.next(); i++) {
            categoriesDE[i] = new DatabaseEntry(rs.getInt(1), rs.getString(2));
        }

        //now reading ingredients
        num = stmt.executeQuery("select count(1) from recipes_ingredients where recipeId = '" + recipeId + "';").getInt(1);
        DatabaseEntry[] ingredientsDE = new DatabaseEntry[num];
        DatabaseEntry[] ingredientAmountsDE = new DatabaseEntry[num];
        rs = stmt.executeQuery("select ingredients.id,name,ingredientAmount from ingredients " +
                "join recipes_ingredients on ingredients.id = recipes_ingredients.ingredientId " +
                "where recipes_ingredients.recipeId=" + recipeId + ";");
        for(int i = 0; rs.next(); i++) {
            ingredientsDE[i] = new DatabaseEntry(rs.getInt(1), rs.getString(2));
            ingredientAmountsDE[i] = new DatabaseEntry(rs.getInt(1), rs.getString(3));
        }

        //now reading directions
        num = stmt.executeQuery("select count(1) from directions where recipeId = '" + recipeId + "';").getInt(1);
        DatabaseEntry[] directionsDE = new DatabaseEntry[num];
        rs = stmt.executeQuery("select id, direction from directions where recipeId=" + recipeId + ";");
        for(int i = 0; rs.next(); i++) {
            directionsDE[i] = new DatabaseEntry(rs.getInt(1), rs.getString(2));
        }

        return new Recipe(recipeName, description, ingredientsDE, ingredientAmountsDE, categoriesDE, directionsDE);
    }

    // outputs an array of DatabaseEntry objects, sorted by name
    public static DatabaseEntry[] sortedList() throws Exception{
        ResultSet rs = stmt.executeQuery("select count(1) from recipes;");
        DatabaseEntry[] output = new DatabaseEntry[rs.getInt(1)];
        rs = stmt.executeQuery("select * from recipes order by name");
        for(int i = 0; rs.next(); i++) {
            output[i] = new DatabaseEntry(rs.getInt(1), rs.getString(2), rs.getString(3));
        }
        return output;
    }

    public static void printRecipes() throws Exception{
        DatabaseEntry[] cookbook = sortedList();
        System.out.println("Printing " + cookbook.length + " recipes");
        for(DatabaseEntry recipe: cookbook) {
            System.out.println(recipe.id + ": " + recipe.name + " - " + recipe.description);
        }
    }





    //todo public void addRecipe (Recipe _newRecipe)
    //todo public void addRecipe (String _recipeName, String[] _ingredients, String[] _categories, String[] _directions) {
    //todo public ArrayList<Recipe> searchByName(String searchedString) {
    //todo public ArrayList<Recipe> searchByIngredient(String searchedString) {
    //todo public ArrayList<Recipe> searchByCategory(String searchedString) {
    //todo public Set<String> findCategories() {
    //todo public Set<String> findIngredients() {
    //todo public ArrayList<Recipe> sortRecipeAlphabetical() {
}


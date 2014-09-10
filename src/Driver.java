<<<<<<< HEAD
import java.awt.Dimension;
=======
import java.io.Console;
>>>>>>> f93f838c34b9e71a28578c3a4fd27aa464add659
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;


/*
 * 
 *	Pardon my java, haven't used it all summer. 
 *  Feel free to completely ignore this, I just wanted something as a 
 *  starting point but its completely open to suggestions.
 *  
 */


public class Driver {
<<<<<<< HEAD
	
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		
		JPanel mainPanel = new JPanel();
		mainPanel.add(new LeftPanel());
		mainPanel.add(new RightPanel());
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.X_AXIS));
		
		JFrame frame = new JFrame();
		frame.add(mainPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setPreferredSize(new Dimension(1000,600));
		frame.pack();
		
		

//		Scanner searchScanner = new Scanner(System.in);
//		int choice = 400; //arbitrary number to make while loop run
//		Scanner UIscan = new Scanner(System.in);
//		String check = "";
//		ArrayList<Recipe> searchedList;
//		String filename = "./src/RecipeList.txt";
//		RecipeFileReader RecRead = new RecipeFileReader(filename);
//
//		
//		 ArrayList<Recipe>recipeList = new ArrayList<Recipe>();
//
//		 
//		 recipeList = RecRead.readInRecipes();
//		 
//		 
//			
//		System.out.println("####################################\n"
//					+ "  Welcome to your virtual cook book\n"
//					+ "####################################");
//					
//			
//		while(choice!=0){
//						
//			while(choice!=1 && choice!= 2  && choice!= 3  && choice!= 4 && choice!=0){	
//				System.out.println("\nPlease enter the number of the option you would like to perform.\n"
//						+ "If you wish to see the whole recipe use the search function.\n"
//						+"_____________________________________________________________"
//							+ "\n1:See recipes listed alphabetically\n"
//							+ "2:See recipes sorted by category\n"
//							+ "3:See recipes sorted by ingredient\n"
//							+ "4:Search recipes\n"
//							+ "0:Exit");
//				check = UIscan.nextLine(); //takes in user choice as string 
//				try{
//						choice = Integer.parseInt(check);
//					} catch(NumberFormatException e){
//						choice=400; //set back to arbitrary number to continue while loop
//						System.out.println("Please enter the number of one of the above options");
//					}
//					
//				}
//				if(choice==1){
//					choice = 400;
//					//print out list of recipes alphabetically
//					ArrayList<Recipe> alphabeticalList = Search.sortRecipeAlphabetical(recipeList);
//					System.out.println("--Sorted Alphabetically--\n");
//					for(Recipe recipe : alphabeticalList)
//					{
//					        System.out.println(recipe.getName());
//					}
//
//				}
//				else if(choice==2){
//					choice=400;
//					System.out.println("---Sorted By Category---");
//					Set<String> categoryList = Search.findCategories(recipeList); // get list of all used categories
//
//					for(String tempCategory : categoryList) {
//						ArrayList<Recipe> foundList = Search.searchByCategory(tempCategory, recipeList);
//						// perform category search on each category
//						System.out.println(tempCategory + ": ");
//						for (Recipe recipe : foundList){
//							 System.out.println("\t"+recipe.getName());
//						}
//					}
//				}
//				else if(choice==3){
//					choice=400;
//					System.out.println("---Sorted By Ingredients---");
//					Set<String> ingredientList = Search.findIngredients(recipeList); // get list of all used ingredients
//
//					for(String tempIngredient : ingredientList) {
//						ArrayList<Recipe> foundList = Search.searchByIngredient(tempIngredient, recipeList);
//						// perform ingredient search on each ingredient
//						System.out.println("\n" + tempIngredient + ": ");
//						for (Recipe recipe : foundList){
//							 System.out.println("\t-"+recipe.getName());
//						}
//					}
//					//print out list of recipes sorted by ingredient
//				}
//				else if(choice==4){
//					choice = 400; //arbitrary #
//					System.out.println("How would you like to search?\n"
//							+"1: Search by recipe name\n"
//							+ "2: Search by ingredients\n"
//							+ "3: Search by category");
//					check = UIscan.nextLine(); //takes in user choice as string 
//					try{
//						choice = Integer.parseInt(check);
//					} catch(NumberFormatException e){
//						choice=400; //set back to arbitrary number to continue while loop
//						System.out.println("That input is not recognised.");
//					}
//					if(choice==1){
//						choice = 400; //reset
//						System.out.println("Please enter in the name of the recipe you would like to search for: ");
//						String searchName = searchScanner.nextLine();
//						 
//						 searchedList = Search.searchByName(searchName, recipeList);
//						 
//						 System.out.println("Recipes that match your search term:");
//						 for (Recipe recipe : searchedList){
//							 System.out.println(recipe);
//						 }
//						
//					}
//					else if(choice==2){
//						choice = 400; //reset
//						System.out.println("Please enter in a ingredient of the recipe you would like to search for: ");
//						String searchIngredient = searchScanner.nextLine();
//						 
//						 searchedList = Search.searchByIngredient(searchIngredient, recipeList);
//						 
//						 System.out.println("Recipes that match your search term:");
//						 for (Recipe recipe : searchedList){
//							 System.out.println(recipe);
//						 }
//						
//					}
//					else if(choice==3){
//						choice = 400; //reset
//						System.out.println("Please enter in the category of the recipe you would like to search for: ");
//						String searchCategory = searchScanner.nextLine();
//						 
//						searchedList = Search.searchByCategory(searchCategory, recipeList);
//						 
//						 System.out.println("Recipes that match your search term:");
//						 for (Recipe recipe : searchedList){
//							 System.out.println(recipe);
//						 }
//						
//					}
//					
//					
//					
//					
//					
//				}
//				
//				
//				
//				
//				
//			}
//		
//		 
//		
//			 
		
		 
=======
>>>>>>> f93f838c34b9e71a28578c3a4fd27aa464add659


    public static void main(String[] args) throws FileNotFoundException {

        Scanner searchScanner = new Scanner(System.in);
        int choice = 400; //arbitrary number to make while loop run
        Scanner UIscan = new Scanner(System.in);
        String check = "";
        ArrayList<Recipe> searchedList;
        String filename = "./src/RecipeList.txt";
        RecipeFileReader RecRead = new RecipeFileReader(filename);


        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();


        recipeList = RecRead.readInRecipes();


        System.out.println("####################################\n"
                + "  Welcome to your virtual cook book\n"
                + "####################################");


        while (choice != 0) {

            while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 0 && choice != 5) {
                System.out.println("\nPlease enter the number of the option you would like to perform.\n"
                        + "If you wish to see the whole recipe use the search function.\n"
                        + "_____________________________________________________________"
                        + "\n1:See recipes listed alphabetically\n"
                        + "2:See recipes sorted by category\n"
                        + "3:See recipes sorted by ingredient\n"
                        + "4:Search recipes\n"
                        + "5:Add a recipe\n"
                        + "0:Exit");
                check = UIscan.nextLine(); //takes in user choice as string
                try {
                    choice = Integer.parseInt(check);
                } catch (NumberFormatException e) {
                    choice = 400; //set back to arbitrary number to continue while loop
                    System.out.println("Please enter the number of one of the above options");
                }

            }
            if (choice == 1) {
                choice = 400;
                //print out list of recipes alphabetically
                ArrayList<Recipe> alphabeticalList = Search.sortRecipeAlphabetical(recipeList);

                for (Recipe recipe : alphabeticalList) {
                    System.out.println(recipe.getName());
                }
            } else if (choice == 2) {
                choice = 400;
                System.out.println("---Sorted By Category---");
                ArrayList<String> categoryList = Search.findCategories(recipeList); // get list of all used categories

                for (String tempCategory : categoryList) {
                    ArrayList<Recipe> foundList = Search.searchByCategory(tempCategory, recipeList);
                    // perform category search on each category
                    System.out.println(tempCategory + ": ");
                    for (Recipe recipe : foundList) {
                        System.out.println("\t" + recipe.getName());
                    }
                }
            } else if (choice == 3) {
                choice = 400;
                System.out.println("---Sorted By Ingredients---");
                ArrayList<String> ingredientList = Search.findIngredients(recipeList); // get list of all used categories

                for (String tempIngredient : ingredientList) {
                    ArrayList<Recipe> foundList = Search.searchByIngredient(tempIngredient, recipeList);
                    // perform category search on each category
                    System.out.println(tempIngredient + ": ");
                    for (Recipe recipe : foundList) {
                        System.out.println("\t" + recipe.getName());
                    }
                }
                //print out list of recipes sorted by ingredient
            } else if (choice == 4) {
                choice = 400; //arbitrary #
                System.out.println("How would you like to search?\n"
                        + "1: Search by recipe name\n"
                        + "2: Search by ingredients\n"
                        + "3: Search by category");
                check = UIscan.nextLine(); //takes in user choice as string
                try {
                    choice = Integer.parseInt(check);
                } catch (NumberFormatException e) {
                    choice = 400; //set back to arbitrary number to continue while loop
                    System.out.println("That input is not recognised.");
                }
                if (choice == 1) {
                    choice = 400; //reset
                    System.out.println("Please enter in the name of the recipe you would like to search for: ");
                    String searchName = searchScanner.nextLine();

                    searchedList = Search.searchByName(searchName, recipeList);

                    System.out.println("Recipes that match your search term:");
                    for (Recipe recipe : searchedList) {
                        System.out.println(recipe);
                    }

                } else if (choice == 2) {
                    choice = 400; //reset
                    System.out.println("Please enter in a ingredient of the recipe you would like to search for: ");
                    String searchIngredient = searchScanner.nextLine();

                    searchedList = Search.searchByIngredient(searchIngredient, recipeList);

                    System.out.println("Recipes that match your search term:");
                    for (Recipe recipe : searchedList) {
                        System.out.println(recipe);
                    }

                } else if (choice == 3) {
                    choice = 400; //reset
                    System.out.println("Please enter in the category of the recipe you would like to search for: ");
                    String searchCategory = searchScanner.nextLine();

                    searchedList = Search.searchByCategory(searchCategory, recipeList);

                    System.out.println("Recipes that match your search term:");
                    for (Recipe recipe : searchedList) {
                        System.out.println(recipe);
                    }

                }
            } else if (choice == 5) {
                choice = 400;
                Scanner scanner = new Scanner(System.in);
                String recipeName = "";
                ArrayList<String> ingredients = new ArrayList<String>();
                ArrayList<String> categories = new ArrayList<String>();
                ArrayList<String> directions = new ArrayList<String>();
                System.out.println("Enter a name for the new recipe");
                recipeName = scanner.nextLine();
                System.out.println("Enter the ingredients one at a time. Enter a blank line when done");
                String nextLine = "400";
                while (!(nextLine = scanner.nextLine()).isEmpty()) {
                    ingredients.add(nextLine);
                }
                System.out.println("Enter the categories one at a time. Enter a blank line when done");
                while (!(nextLine = scanner.nextLine()).isEmpty()) {
                    categories.add(nextLine);
                }
                System.out.println("Enter directions one at a time. Enter a blank line when done");
                while (!(nextLine = scanner.nextLine()).isEmpty()) {
                    directions.add(nextLine);
                }

                addRecipe(recipeList, RecRead, recipeName, ingredients.toArray(new String[ingredients.size()]), categories.toArray(new String[categories.size()]), directions.toArray(new String[directions.size()]));
            }

        }


    }

    public static void addRecipe(ArrayList<Recipe> _recipeList, RecipeFileReader recRead, String _recipeName, String[] _ingredients, String[] _categories, String[] _directions) {
        _recipeList.add(new Recipe(_recipeName, _ingredients, _categories, _directions));
        recRead.writeToFile(_recipeList);
    }

}

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
public class interactingWithGrid {

    public interactingWithGrid(populateGrid grid){
        intGridToInteractWith = grid.getGridWithInsertedShips();
        for (int rowIterator = 0; rowIterator < gridGenerator.userRequestedRows; rowIterator++){
            for (int colIterator = 0; colIterator < gridGenerator.userRequestedColumns; colIterator++){
                stringGridToShowUser[rowIterator][colIterator] = "0";
            }
        }
        System.out.println();
        for (String[] array : stringGridToShowUser){
            System.out.println(Arrays.toString(array));
        }
    }
    private int[][] intGridToInteractWith;
    private String [][] stringGridToShowUser = new String[gridGenerator.userRequestedRows][gridGenerator.userRequestedColumns];
    public int[][] getIntGridToInteractWith() {
        return intGridToInteractWith;
    }
    public void setIntGridToInteractWith(int[][] intGridToInteractWith) {
        this.intGridToInteractWith = intGridToInteractWith;
    }

    public void printOutStringUserVisibleGrid() throws InterruptedException {
        generalMethods.printStringSlowlyToScreen("This is the current state of the war: ");
        for (String[] array : stringGridToShowUser) {
            System.out.println(Arrays.toString(array));
        }
    }

    public boolean alreadyTried(String[][] stringGridToCheck, int[] rowColCoordsToCheck){
        int rowToCheck = rowColCoordsToCheck[0];
        int colToCheck = rowColCoordsToCheck[1];
        if (((Objects.equals(stringGridToCheck[rowToCheck][colToCheck], "X"))) || (Objects.equals(stringGridToCheck[rowToCheck][colToCheck], "M"))){
            return true; //It has already been tried
        }
        return false;
    }

    public boolean hitOrMiss(int[] arrayOfUserGuessRowCol) throws InterruptedException {
        int rowToCheck = arrayOfUserGuessRowCol[0];
        int colToCheck = arrayOfUserGuessRowCol[1];
        if ((intGridToInteractWith[rowToCheck][colToCheck] == 1) && (alreadyTried(stringGridToShowUser, arrayOfUserGuessRowCol)) == false){

            generalMethods.printStringSlowlyToScreen("A sailor stands facing the sun, he closes his eyes, " +
                    "standing on the side of the ship seemingly harvesting the warmth. " +
                    "He steals away a smile, imagines his family, a real cup of tea from his dad, " +
                    "the odd comfort of shared discontent. " +
                    "He knows he will see those days again, the days of normality - he would look back at this moment as formative, " +
                    "as a blip on his conscience. He longed to nestle into the warm embrace of the everyday." + "\n" +
                    "HIT - the forces of war ricochet through the ship. Metal buckles. Men cheer. Men scream. " +
                    "The sailor is eviscerated, his smile turned into a red mist.");
            stringGridToShowUser[rowToCheck][colToCheck] = "X";
            printOutStringUserVisibleGrid();
            return true;
        }
        else if ((intGridToInteractWith[rowToCheck][colToCheck] == 0) && (alreadyTried(stringGridToShowUser, arrayOfUserGuessRowCol)) == false){
            System.out.println();
            generalMethods.printStringSlowlyToScreen("MISS - the sea swallows the shell, like an ashamed mother, " +
                    "sweeping crumbs under the carpet.");
            stringGridToShowUser[rowToCheck][colToCheck] = "M";
            printOutStringUserVisibleGrid();
            return false;
        }
        else if ((alreadyTried(stringGridToShowUser, arrayOfUserGuessRowCol)) == true){
            generalMethods.printStringSlowlyToScreen("Some say war is stupid, ravenous, unthinking. But this is wrong - it is a cold clockwork that will, yes WILL, have the next second. " +
                    "Shooting the same spot will not achieve this..." +
                    "try again.");
            return false;
        }
        return false;
    }

    public void userGuessLoop() throws InterruptedException {
        int userRowGuess = 0;
        int userColumnGuess = 0;
        boolean guessEntered = false;
        int [] arrayOfGuesses = new int[2];
        int countOfHits = 0;


        while (countOfHits < populateGrid.countOfTakenUpSpaces()){
            try{
                generalMethods.printStringSlowlyToScreen("Enter row of guess. Must be less than " + gridGenerator.userRequestedRows + ": ");

                Scanner userGuessIntInput = new Scanner(System.in);
                userRowGuess = userGuessIntInput.nextInt();
                while (userRowGuess > gridGenerator.userRequestedRows){
                    generalMethods.printStringSlowlyToScreen("Must be less than " + gridGenerator.userRequestedRows + ": ");
                    userRowGuess = userGuessIntInput.nextInt();
                }
                generalMethods.printStringSlowlyToScreen("Enter column of guess. Must be less than " + gridGenerator.userRequestedRows + ": ");
                userColumnGuess = userGuessIntInput.nextInt();
                while (userColumnGuess > gridGenerator.userRequestedColumns){
                    generalMethods.printStringSlowlyToScreen("Must be less than " + gridGenerator.userRequestedColumns + ": ");
                    userColumnGuess = userGuessIntInput.nextInt();
                }
                arrayOfGuesses[0] = userRowGuess;
                arrayOfGuesses[1] = userColumnGuess;

                if ((hitOrMiss(arrayOfGuesses)) == true){
                    countOfHits++;
                }


            }
            catch (Exception InputMismatchException) {
                System.out.println();
                generalMethods.printStringSlowlyToScreen("ERROR Something other than a number was entered. Try again.");
                System.out.println();
            }
        }
        generalMethods.printStringSlowlyToScreen("You have succeeded, the sea is quiet. + The smell of salt and fuel is in the air. " +
                "The seagulls caw in delight, for you have served their fleshy dinner.");

    }
}

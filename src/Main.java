import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        gridGenerator gridWithoutShips = new gridGenerator();

        gridWithoutShips.inputUserRequestedRowsAndColumns();

        populateGrid objectOfPopulateGridToAddShips = new populateGrid(gridWithoutShips);


        interactingWithGrid gridToTestForNow = new interactingWithGrid(objectOfPopulateGridToAddShips);

        gridToTestForNow.userGuessLoop();




    }
}
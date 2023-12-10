import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        gridGenerator gridWithoutShips = new gridGenerator();

        gridWithoutShips.inputUserRequestedRowsAndColumns();

        populateGrid objectOfPopulateGridToAddShips = new populateGrid(gridWithoutShips);

        for(int[] array : objectOfPopulateGridToAddShips.getGridWithInsertedShips()){
            System.out.println(Arrays.toString(array));
        }














    }
}
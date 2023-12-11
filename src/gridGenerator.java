import java.util.Scanner;
public class gridGenerator {

    public gridGenerator(){
        int amountOfShips = 0;
        for (Ships ships : Ships.values()){
            amountOfShips++;
        }
        int [] arrayOfShipSizes = new int[amountOfShips];
        int arrayOfShipSizesIterator = 0;
        for (Ships ships : Ships.values()){
            arrayOfShipSizes[arrayOfShipSizesIterator] = ships.spaceOnGrid;
            arrayOfShipSizesIterator++;
        }

        int max = arrayOfShipSizes[0];

        for (int numberInArray : arrayOfShipSizes){
            if (numberInArray > max){
                max = numberInArray;
//                System.out.println("The biggest found is currently: " + max + "and the size I compared that to " + numberInArray);
            }
        }
//        System.out.println("This is the calculated max ship " + max);

        int[] arrayOfShipSizesWithoutLargest = arrayOfShipSizes;

        for (int locationInArray = 0; locationInArray < arrayOfShipSizesWithoutLargest.length; locationInArray++){
            if (arrayOfShipSizesWithoutLargest[locationInArray] == max){
                arrayOfShipSizesWithoutLargest[locationInArray] = 0;
                break; // break so even if there are duplicates only one of the values is reduced to zero
            }
        }

        int gridMinSizeFromArray = 0;
        for (int finalArrayToSumNums : arrayOfShipSizesWithoutLargest){
            gridMinSizeFromArray += finalArrayToSumNums;
        }

        gridMinSizeFromArray += 2;

//        System.out.println(gridMinSizeFromArray);

        gridMinSize = gridMinSizeFromArray;



    }

    private int userRequestedRows;
    private int userRequestedColumns;
    private int [][] userCreatedGrid;

    private int gridMinSize;

    public int[][] getUserCreatedGrid() {
        return userCreatedGrid;
    }

    public void setUserCreatedGrid(int [][] userCreatedGrid) {
        this.userCreatedGrid = userCreatedGrid;
    }


    public int getUserRequestedColumns() {
        return userRequestedColumns;
    }

    public void setUserRequestedColumns(int getUserRequestedYAxis) {
        this.userRequestedColumns = getUserRequestedYAxis;
    }


    public int getUserRequestedRows() {
        return userRequestedRows;
    }

    public void setUserRequestedRows(int userRequestedXAxis) {
        this.userRequestedRows = userRequestedXAxis;
    }

    /*
    EBI you retain correct input from first guess and only ask user to correct
    input for second guess (if they correctly entered first)

    Put the validity checks in a constructor instead of calling getters and setters

     */
    public void inputUserRequestedRowsAndColumns() {
        boolean numsEntered = false;
        int rows;
        int columns;
        while (numsEntered == false) {
            try {
                Scanner userIntInput = new Scanner(System.in);
                System.out.print("Please enter the amount of rows you want for the next game and press enter (must be at least " + gridMinSize + "): ");
                System.out.println();
                rows = userIntInput.nextInt();
                while (rows < gridMinSize){
                    System.out.println("Must be at least " + gridMinSize + ",try again");
                    rows = userIntInput.nextInt();
                }
                setUserRequestedRows(rows);
                System.out.print("Please enter the amount of columns you want for the next game and press enter (must be at least " + gridMinSize + "): ");
                System.out.println();
                columns = userIntInput.nextInt();
                while (columns < 5){
                    System.out.println("Must be at least " + gridMinSize + ",try again");
                    columns = userIntInput.nextInt();
                }
                setUserRequestedColumns(columns);
                createGrid();
                numsEntered = true;
            } catch (Exception InputMismatchException) {
                System.out.println();
                System.out.println("ERROR Something other than a number was entered. Try again.");
                System.out.println();
            }
        }
    }

    public void createGrid(){
            int[][] gridToInput = new int[getUserRequestedRows()][getUserRequestedColumns()];
            setUserCreatedGrid(gridToInput);
        }
}





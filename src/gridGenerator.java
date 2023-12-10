import java.util.Scanner;
public class gridGenerator {

    private int userRequestedRows;
    private int userRequestedColumns;
    private int [][] userCreatedGrid;


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
                System.out.print("Please enter the amount of rows you want for the next game and press enter (must be more than 5): ");
                System.out.println();
                rows = userIntInput.nextInt();
                while (rows < 5){
                    System.out.println("Must be more than 5, try again");
                    rows = userIntInput.nextInt();
                }
                setUserRequestedRows(rows);
                System.out.print("Please enter the columns you want for the next game and press enter (must be more than 5): ");
                System.out.println();
                columns = userIntInput.nextInt();
                while (columns < 5){
                    System.out.println("Must be more than 5, try again");
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





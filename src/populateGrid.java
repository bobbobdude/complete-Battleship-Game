import java.util.Arrays;
import java.util.Random;
public class populateGrid {
    private int[][] gridWithInsertedShips;

    public populateGrid(gridGenerator gridWithoutShips) {
        gridWithInsertedShips = insertShipsVerticallyOrHorizontally(gridWithoutShips);
    }

    public int[][] getGridWithInsertedShips() {
        return gridWithInsertedShips;
    }


    static int totalUsedCoordinatesBasedOnEnumClass;


    public static int countOfTakenUpSpaces() {
        for (Ships ships : Ships.values()) {
            totalUsedCoordinatesBasedOnEnumClass += ships.spaceOnGrid;
        }
        return totalUsedCoordinatesBasedOnEnumClass;
    }

    public int[][] insertShipsVerticallyOrHorizontally(gridGenerator grid){
        Random randRow = new Random(); //This is now the number that increases
        Random randColumn = new Random();
        int[][] userGridWithInsertedShips = grid.getUserCreatedGrid();

        for (Ships ships : Ships.values()){
            int shipToAddLength = ships.spaceOnGrid;
            Random hOrV = new Random();
            int horizontalOrVertical = hOrV.nextInt(2);

            if (horizontalOrVertical == 0) {
                int randomRowCoordinate = randRow.nextInt(grid.getUserRequestedRows() - shipToAddLength + 1); //+1 allows for ship coordinates to populate last row
                int randomColumnCoordinate = randColumn.nextInt(grid.getUserRequestedColumns()); //This does not change

                int rowToGetTo = randomRowCoordinate + shipToAddLength;
                int[][] storePotentialPlaces = new int[shipToAddLength][2];

                int columnCoordinate = randomColumnCoordinate;
                int rowIteratorForStore = 0;

                for (int rowStartingPosition = randomRowCoordinate; rowStartingPosition <= rowToGetTo && rowIteratorForStore < storePotentialPlaces.length; rowStartingPosition++) {
                    storePotentialPlaces[rowIteratorForStore][0] = rowStartingPosition;
                    storePotentialPlaces[rowIteratorForStore][1] = columnCoordinate;
                    rowIteratorForStore++;
                    //System.out.println("This is row to get to in for loop 1: " + rowToGetTo);
                }

                while (coordsAreFree(storePotentialPlaces, userGridWithInsertedShips) == false) {
                    randomRowCoordinate = randRow.nextInt(grid.getUserRequestedRows() - shipToAddLength);
                    randomColumnCoordinate = randColumn.nextInt(grid.getUserRequestedColumns());
                    columnCoordinate = randomColumnCoordinate;
                    rowIteratorForStore = 0;

                    for (int rowStartingPosition = randomRowCoordinate; rowStartingPosition <= rowToGetTo && rowIteratorForStore < storePotentialPlaces.length; rowStartingPosition++) {
                        storePotentialPlaces[rowIteratorForStore][0] = rowStartingPosition;
                        storePotentialPlaces[rowIteratorForStore][1] = columnCoordinate;
                        rowIteratorForStore++;
                        //System.out.println("This is row to get to in for loop 2: " + rowToGetTo);
                    }

                }

                for (int[] array : storePotentialPlaces) {
                    int rowToPlace = array[0];
                    int columnToPlace = array[1];
//                    System.out.println(ships);
//                    System.out.println("Row Coord, Column Coord = " + rowToPlace + ", " + columnToPlace);
                    userGridWithInsertedShips[rowToPlace][columnToPlace] = 1;
//                    System.out.println("Length of arrays of stored coordinates, should be 2, 3 and 4 respectively: " + storePotentialPlaces.length);
//                    System.out.println();
//                    System.out.println(storePotentialPlaces.length);
                }
            } else if (horizontalOrVertical == 1) {
                int randomRowCoordinate = randRow.nextInt(grid.getUserRequestedRows());
                int randomColumnCoordinate = randColumn.nextInt(grid.getUserRequestedColumns() - shipToAddLength + 1); //+1 seems to allow the coordinates to populate the last column


                int columnToGetTo = randomColumnCoordinate + shipToAddLength;
                int[][] storePotentialPlaces = new int[shipToAddLength][2];

                //Below for loop populates 2d array with potential coordinates of all the cells of the ship,

                //Row does not change as currently I am only working on placing the ships horizontally not vertically
                int rowVariable = randomRowCoordinate;
                int rowIteratorForStore = 0;
                for (int columnStartingPosition = randomColumnCoordinate; columnStartingPosition < columnToGetTo; columnStartingPosition++){
                    storePotentialPlaces[rowIteratorForStore][0] = rowVariable;
                    storePotentialPlaces[rowIteratorForStore][1] = columnStartingPosition;
                    rowIteratorForStore++;
//                    System.out.println("This is column to get to in for loop 1: " + columnToGetTo);
                }

                while (coordsAreFree(storePotentialPlaces, userGridWithInsertedShips) == false){
                    randomRowCoordinate = randRow.nextInt(grid.getUserRequestedRows());
                    randomColumnCoordinate = randColumn.nextInt(grid.getUserRequestedColumns() - shipToAddLength);
                    rowVariable = randomRowCoordinate;
                    rowIteratorForStore = 0;
                    for (int columnStartingPosition = randomColumnCoordinate; columnStartingPosition < columnToGetTo && rowIteratorForStore < storePotentialPlaces.length; columnStartingPosition++){
                        storePotentialPlaces[rowIteratorForStore][0] = rowVariable;
                        storePotentialPlaces[rowIteratorForStore][1] = columnStartingPosition;
                        rowIteratorForStore++;
//                        System.out.println("This is column to get to in for loop 2: " + columnToGetTo);
                    }
                }

                for(int[] array : storePotentialPlaces){
                    int rowToPlace = array[0];
                    int columnToPlace = array[1];
//                    System.out.println(ships);
//                    System.out.println("Row Coord, Column Coord = " + rowToPlace + ", " + columnToPlace);
                    userGridWithInsertedShips[rowToPlace][columnToPlace] = 1;
//                    System.out.println("Length of arrays of stored coordinates, should be 2, 3 and 4 respectively: " + storePotentialPlaces.length);
//                    System.out.println();
//                    System.out.println(storePotentialPlaces.length);
                }
            }
        }

        return userGridWithInsertedShips;
    }

    public int[][] insertShipsHorizontally(gridGenerator grid) {
        /*This needs to generate a random location for the ships to be placed within the grid, something to keep in
        mind is that the ships must always be in a straight line.
         */
        Random randRow = new Random();
        Random randColumn = new Random();
        int[][] userGridWithInsertedShips = grid.getUserCreatedGrid();

        for (Ships ships : Ships.values()) {
            int shipToAddLength = ships.spaceOnGrid;
            int randomRowCoordinate = randRow.nextInt(grid.getUserRequestedRows());
            int randomColumnCoordinate = randColumn.nextInt(grid.getUserRequestedColumns() - shipToAddLength + 1); //+1 seems to allow the coordinates to populate the last column


            int columnToGetTo = randomColumnCoordinate + shipToAddLength;
            int[][] storePotentialPlaces = new int[shipToAddLength][2];

            //Below for loop populates 2d array with potential coordinates of all the cells of the ship,

            //Row does not change as currently I am only working on placing the ships horizontally not vertically
            int rowVariable = randomRowCoordinate;
            int rowIteratorForStore = 0;
            for (int columnStartingPosition = randomColumnCoordinate; columnStartingPosition < columnToGetTo; columnStartingPosition++){
                storePotentialPlaces[rowIteratorForStore][0] = rowVariable;
                storePotentialPlaces[rowIteratorForStore][1] = columnStartingPosition;
                rowIteratorForStore++;
            }

            while (coordsAreFree(storePotentialPlaces, userGridWithInsertedShips) == false){
                randomRowCoordinate = randRow.nextInt(grid.getUserRequestedRows());
                randomColumnCoordinate = randColumn.nextInt(grid.getUserRequestedColumns() - shipToAddLength);
                rowVariable = randomRowCoordinate;
                rowIteratorForStore = 0;
                for (int columnStartingPosition = randomColumnCoordinate; columnStartingPosition < columnToGetTo && rowIteratorForStore < storePotentialPlaces.length; columnStartingPosition++){
                    storePotentialPlaces[rowIteratorForStore][0] = rowVariable;
                    storePotentialPlaces[rowIteratorForStore][1] = columnStartingPosition;
                    rowIteratorForStore++;
                }
            }

            for(int[] array : storePotentialPlaces){
                int rowToPlace = array[0];
                int columnToPlace = array[1];
                System.out.println(ships);
                System.out.println("Row Coord, Column Coord = " + rowToPlace + ", " + columnToPlace);
                userGridWithInsertedShips[rowToPlace][columnToPlace] = 1;
                System.out.println("Length of arrays of stored coordinates, should be 2, 3 and 4 respectively: " + storePotentialPlaces.length);
                System.out.println();
                System.out.println(storePotentialPlaces.length);
            }
        }
        return userGridWithInsertedShips;
    }
    public boolean coordsAreFree(int[][] potentialCoords, int[][] userGrid){
        for(int[] array : potentialCoords){
            int rowValueInPotentialCoords = array[0];
            int columnValueInPotentialCoords = array[1];
            if (userGrid[rowValueInPotentialCoords][columnValueInPotentialCoords] == 1){
                return false;
            }
        }
        return true;
    }
}



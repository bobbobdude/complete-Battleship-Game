import java.util.Arrays;
import java.util.Random;
public class populateGrid {
    private int[][] gridWithInsertedShips;

    public populateGrid(gridGenerator gridToPutInMethod) {
        gridWithInsertedShips = insertShips(gridToPutInMethod);
    }

    public int[][] getGridWithInsertedShips() {
        return gridWithInsertedShips;
    }


    static int totalUsedCoordinatesBasedOnEnumClass;


    public static int countOfTakenUpSpaces() {
        for (Ships ships : Ships.values()) {
            totalUsedCoordinatesBasedOnEnumClass += ships.spaceOnGrid;
        }
        System.out.println(totalUsedCoordinatesBasedOnEnumClass);
        return totalUsedCoordinatesBasedOnEnumClass;
    }

    public int[][] insertShips(gridGenerator grid) {
        /*This needs to generate a random location for the ships to be placed within the grid, something to keep in
        mind is that the ships must always be in a straight line.
        PROBLEM TO SOLVE NEXT: Ships can overlay each other, so we need to check whether the spot we are trying to put
        ship in is already populated by a "1"
         */
        Random randRow = new Random();
        Random randColumn = new Random();
        int[][] userGridWithInsertedShips = grid.getUserCreatedGrid();
        int[][] usedCoordinates = new int[countOfTakenUpSpaces()][2];

        for (Ships ships : Ships.values()) {
            int shipToAddLength = ships.spaceOnGrid;
            int randomRowCoordinate = randRow.nextInt(grid.getUserRequestedRows());
            int randomColumnCoordinate = randColumn.nextInt(grid.getUserRequestedColumns() - shipToAddLength);

/*            System.out.println("This is the ship length: " + shipToAddLength);
            System.out.println("This is the random row: " + randomRowCoordinate);
            System.out.println("This is the result of grid.getUserRequestedColumns: " + grid.getUserRequestedColumns());
            System.out.println("This is the random column: " + randomColumnCoordinate);
            System.out.println();*/

            int columnToGetTo = randomColumnCoordinate + shipToAddLength;
            int[][] storePotentialPlaces = new int[shipToAddLength][2];

            //Below for loop populates 2d array with potential coordinates of all the cells of the ship,

            //Row does not change as currently I am only working on placing the ships horizontally not vertically
            int rowVariable = randomRowCoordinate;
            int rowIterator = 0;
            for (int columnStartingPosition = randomColumnCoordinate; columnStartingPosition < columnToGetTo; columnStartingPosition++){
                storePotentialPlaces[rowIterator][0] = rowVariable;
                storePotentialPlaces[rowIterator][1] = columnStartingPosition;
                rowIterator++;
            }

            while (coordsAreFree(storePotentialPlaces, userGridWithInsertedShips) == false){
                randomRowCoordinate = randRow.nextInt(grid.getUserRequestedRows());
                randomColumnCoordinate = randColumn.nextInt(grid.getUserRequestedColumns() - shipToAddLength);
                rowVariable = randomRowCoordinate;
                rowIterator = 0;
                for (int columnStartingPosition = randomColumnCoordinate; columnStartingPosition < columnToGetTo && rowIterator < storePotentialPlaces.length ; columnStartingPosition++){
                    storePotentialPlaces[rowIterator][0] = rowVariable;
                    storePotentialPlaces[rowIterator][1] = columnStartingPosition;
                    rowIterator++;
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



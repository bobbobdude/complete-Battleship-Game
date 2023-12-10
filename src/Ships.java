public enum Ships {
    FRIGATE(2),
    TUG_BOAT(3),
    BATTLESHIP(4);

    final int spaceOnGrid;

    Ships(int spaceOnGrid){
        this.spaceOnGrid = spaceOnGrid;
    }

}

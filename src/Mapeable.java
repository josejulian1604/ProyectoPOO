public abstract class Mapeable {
    protected int[][] position = new int[4][2];
    protected int xPos;
    protected int yPos;

    public Mapeable() {}

    protected void fillPositions() {
        position[1][0] = xPos + 1;
        position[1][1] = yPos;
        position[2][0] = xPos;
        position[2][1] = yPos + 1;
        position[3][0] = xPos + 1;
        position[3][1] = yPos + 1;
    }

    protected int[][] getPositions() {
        return position;
    }

    protected int getXPos() {
        return xPos;
    }

    protected int getYPos() {
        return yPos;
    }
}

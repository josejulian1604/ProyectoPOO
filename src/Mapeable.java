public abstract class Mapeable {
    protected int[][] position = new int[4][2]; //Array de cuatro coordenadas del objeto
    protected int xPos; //Posición en X del objeto
    protected int yPos; //Posición en Y del objeto

    public Mapeable() {}

    protected void fillPositions() { //Llena el array de cuatro coordenadas del objeto

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

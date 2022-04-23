public class Amenaza extends Mapeable{
    private int vida; //Cantidad de vida en la amenaza

    public Amenaza(int vida, int x, int y) {
        this.vida = vida;
        xPos = x;
        yPos = y;
        position[0][0] = x;
        position[0][1] = y;
        fillPositions();
    }

    public void reducirVida() {
        vida--;
    }

    public int getVida() {
        return vida;
    }
}

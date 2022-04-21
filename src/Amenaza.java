public class Amenaza extends Mapeable{
    private int vida;

    public Amenaza(int vida, int x, int y) {
        this.vida = vida;
        xPos = x;
        yPos = y;
        position[0][0] = x;
        position[0][1] = y;
        fillPositions();
    }

    void reducirVida() {
        vida--;
    }

    int getVida() {
        return vida;
    }
}

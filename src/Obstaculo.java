public class Obstaculo extends Mapeable{

    public Obstaculo(int x, int y) {
        xPos = x;
        yPos = y;
        position[0][0] = x;
        position[0][1] = y;
        fillPositions();
    }
}

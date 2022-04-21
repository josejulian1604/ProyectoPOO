public class Base extends Mapeable{
    
    private int recursos;

    public Base(int xPos, int yPos) {
        position[0][0] = xPos;
        position[0][1] = yPos;
        this.xPos = xPos;
        this.yPos = yPos;
        fillPositions();
    }

    public void recursoAgregado() {
        this.recursos++;
    }

    public int getRecursos() {
        return this.recursos;
    }

}

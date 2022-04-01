public class Base {
    
    private int recursos;
    private int xPos;
    private int yPos;

    public Base(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getRecursos() {
        return this.recursos;
    }

    public void recursoAgregado() {
        this.recursos++;
    }

}

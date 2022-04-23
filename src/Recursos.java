public class Recursos extends Mapeable{
    private int cantidad; //Cantidad de recurso en el objeto

    public Recursos(int cantidad, int x, int y) {
        this.cantidad = cantidad;
        xPos = x;
        yPos = y;
        position[0][0] = x;
        position[0][1] = y;
        fillPositions();
    }

    public void reducirCantidad() {
        cantidad--;
    }

    protected int getCantidad() {
        return cantidad;
    }
}

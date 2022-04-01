import java.util.ArrayList;

public class Recolectores extends Agente{

    public Recolectores(String name, int x, int y) {
        this.name = name;
        xPos = x;
        yPos = y;
        currentResource = false;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return xPos;
    }

    public int getY() {
        return yPos;
    }

    public boolean getCurrentResource() {
        return currentResource;
    }

    public void lookingForResources() {
        System.out.println("Soy un " + name + " y busco recurso");
    }

    public void resourceFound() {
        System.out.println("Soy un " + name + " y busco recursos");
    }

    public void threatDetected() {
        System.out.println("Soy un " + name + " y encontre una amenaza");
    }

    public void move() {
        if (currentResource)
            resourceFound();
        else
            lookingForResources();
    }
}

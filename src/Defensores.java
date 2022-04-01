public class Defensores extends Agente{
    
    /*private int xPos;
    private int yPos;
    private boolean currentResource;
    private String name;*/

    public Defensores(String name, int x, int y) {
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
        System.out.println("Soy un " + name + " y encontre un recurso");
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
 
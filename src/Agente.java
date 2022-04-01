public abstract class Agente {

    protected int xPos;
    protected int yPos;
    protected boolean currentResource;
    protected String name;
    protected int goToX;
    protected int goToY;
    protected int resourceFoundX;
    protected int resourceFoundY;

    public abstract void resourceFound();
    public abstract void threatDetected();
    public abstract void lookingForResources();
    public abstract void move();

}

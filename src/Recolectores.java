import java.util.ArrayList;

public class Recolectores extends Agente{

    public Recolectores(String name, int x, int y) {
        this.name = name;
        xPos = x;
        yPos = y;
        recurso = null;
        amenaza = null;
        currentResource = false;
        lastMoveInX = false;
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

    public boolean threatDetected(ArrayList<Amenaza> amenazas) {
        for(int i = 0; i < amenazas.size(); i++) {

            //VERIFY IF AGENT IS 1 SPACE AWAY FROM THREAT
            if((xPos - amenazas.get(i).getPositions()[0][0] == -1 || xPos - amenazas.get(i).getPositions()[2][0] == -1) & (yPos == amenazas.get(i).getYPos() || yPos == amenazas.get(i).getYPos() + 1)) {
                if(xPos - 1 > 39 || xPos - 1 < 0)
                    return true;
                xPos--;
                amenaza = amenazas.get(i);
                return true;
            }
            else if((xPos - amenazas.get(i).getPositions()[1][0] == 1 || xPos - amenazas.get(i).getPositions()[3][0] == 1) & (yPos == amenazas.get(i).getYPos() || yPos == amenazas.get(i).getYPos() + 1)) {
                if(xPos + 1 > 39 || xPos + 1 < 0)
                    return true;
                xPos++;
                amenaza = amenazas.get(i);
                return true;
            }
            else if((yPos - amenazas.get(i).getPositions()[0][1] == -1 || yPos - amenazas.get(i).getPositions()[1][1] == -1) & (xPos == amenazas.get(i).getXPos() || xPos == amenazas.get(i).getXPos() + 1)) {
                if(yPos - 1 > 39 || yPos - 1 < 0)
                    return true;
                yPos--;
                amenaza = amenazas.get(i);
                return true;
            }
            else if((yPos - amenazas.get(i).getPositions()[2][1] == 1 || yPos - amenazas.get(i).getPositions()[3][1] == 1) & (xPos == amenazas.get(i).getXPos() || xPos == amenazas.get(i).getXPos() + 1)) {
                if(yPos + 1 > 39 || yPos + 1 < 0)
                    return true;
                yPos++;
                amenaza = amenazas.get(i);
                return true;
            }

            //VERIFY X POSITIONS
            else if((xPos - amenazas.get(i).getPositions()[0][0] == -2 || xPos - amenazas.get(i).getPositions()[2][0] == -2) & (yPos == amenazas.get(i).getYPos() || yPos == amenazas.get(i).getYPos() + 1)) {
                if(xPos - 1 > 39 || xPos - 1 < 0)
                    return true;
                xPos--;
                return true;
            }
            else if((xPos - amenazas.get(i).getPositions()[1][0] == 2 || xPos - amenazas.get(i).getPositions()[3][0] == 2) & (yPos == amenazas.get(i).getYPos() || yPos == amenazas.get(i).getYPos() + 1)) {
                if(xPos + 1 > 39 || xPos + 1 < 0)
                    return true;
                xPos++;
                return true;
            }

            //VERIFY Y POSITIONS
            else if((yPos - amenazas.get(i).getPositions()[0][1] == -2 || yPos - amenazas.get(i).getPositions()[1][1] == -2) & (xPos == amenazas.get(i).getXPos() || xPos == amenazas.get(i).getXPos() + 1)) {
                if(yPos - 1 > 39 || yPos - 1 < 0)
                    return true;
                yPos--;
                return true;
            }
            
            else if((yPos - amenazas.get(i).getPositions()[2][1] == 2 || yPos - amenazas.get(i).getPositions()[3][1] == 2) & (xPos == amenazas.get(i).getXPos() || xPos == amenazas.get(i).getXPos() + 1)) {
                if(yPos + 1 > 39 || yPos + 1 < 0)
                    return true;
                yPos++;
                return true;
            }
            //Diagonal verification 1 campo
            else if((xPos - amenazas.get(i).getPositions()[0][0] == -1) & (yPos - amenazas.get(i).getPositions()[0][1] == -1)) {
                if(yPos - 1 > 39 || yPos - 1 < 0)
                    return true;
                yPos--;
                return true;
            }
            else if((xPos - amenazas.get(i).getPositions()[1][0] == 1) & (yPos - amenazas.get(i).getPositions()[1][1] == -1)) {
                if(yPos - 1 > 39 || yPos - 1 < 0)
                    return true;
                yPos--;
                return true;
            }
            else if((xPos - amenazas.get(i).getPositions()[2][0] == -1) & (yPos - amenazas.get(i).getPositions()[2][1] == 1)) {
                if(yPos + 1 > 39 || yPos + 1 < 0)
                    return true;
                yPos++;
                return true;
            }
            else if((xPos - amenazas.get(i).getPositions()[3][0] == 1) & (yPos - amenazas.get(i).getPositions()[3][1] == 1)) {
                if(yPos + 1 > 39 || yPos + 1 < 0)
                    return true;
                yPos++;
                return true;
            }
        }
        return false;
    }
}

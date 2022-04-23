import java.util.ArrayList;
import java.util.Random;

public abstract class Agente {

    protected int xPos;
    protected int yPos;
    protected boolean currentResource; 
    protected String name;
    protected Recursos recurso;
    protected Amenaza amenaza;
    protected boolean lastMoveInX;

    public abstract boolean threatDetected(ArrayList<Amenaza> amenazas);
    
    public void move(ArrayList<Agente> agentes, ArrayList<Recursos> recursos, ArrayList<Amenaza> amenazas, ArrayList<Obstaculo> obstaculos, Base base) {
        if(currentResource) 
            resourceFound(agentes, recursos, amenazas, obstaculos, base);
        else
            lookingForResources(agentes, recursos, amenazas, obstaculos);
    }

    public void lookingForResources(ArrayList<Agente> agentes, ArrayList<Recursos> recursos, ArrayList<Amenaza> amenazas, ArrayList<Obstaculo> obstaculos) {        
        //verify close resources
        if(verifyCloseResources(recursos))
            return;

        //verify close threats
        else if(threatDetected(amenazas))
            return;
        
        //verify close obstacles
        else if(verifyCloseObstacles(obstaculos))
            return;
        
        else if(goToResource(agentes))
            return;
        
        else 
            moveRandomly();
        }

    public boolean verifyCloseResources(ArrayList<Recursos> recursos) {
        for(int i = 0; i < recursos.size(); i++) {

            //VERIFY IF AGENT IS 1 SPACE AWAY FROM RESOURCE
            if((xPos - recursos.get(i).getPositions()[0][0] == -1 || xPos - recursos.get(i).getPositions()[2][0] == -1) & (yPos == recursos.get(i).getYPos() || yPos == recursos.get(i).getYPos() + 1)) {
                currentResource = true;
                recursos.get(i).reducirCantidad();
                recurso = recursos.get(i);
                return true;
            }
            else if((xPos - recursos.get(i).getPositions()[1][0] == 1 || xPos - recursos.get(i).getPositions()[3][0] == 1) & (yPos == recursos.get(i).getYPos() || yPos == recursos.get(i).getYPos() + 1)) {
                currentResource = true;
                recursos.get(i).reducirCantidad();
                recurso = recursos.get(i);
                return true;
            }
            else if((yPos - recursos.get(i).getPositions()[0][1] == -1 || yPos - recursos.get(i).getPositions()[1][1] == -1) & (xPos == recursos.get(i).getXPos() || xPos == recursos.get(i).getXPos() + 1)) {
                currentResource = true;
                recursos.get(i).reducirCantidad();
                recurso = recursos.get(i);
                return true;
            }
            else if((yPos - recursos.get(i).getPositions()[2][1] == 1 || yPos - recursos.get(i).getPositions()[3][1] == 1) & (xPos == recursos.get(i).getXPos() || xPos == recursos.get(i).getXPos() + 1)) {
                currentResource = true;
                recursos.get(i).reducirCantidad();
                recurso = recursos.get(i);
                return true;
            }

            //VERIFY X POSITIONS
            else if((xPos - recursos.get(i).getPositions()[0][0] == -2 || xPos - recursos.get(i).getPositions()[2][0] == -2) & (yPos == recursos.get(i).getYPos() || yPos == recursos.get(i).getYPos() + 1)) {
                xPos++;
                return true;
            }
            else if((xPos - recursos.get(i).getPositions()[1][0] == 2 || xPos - recursos.get(i).getPositions()[3][0] == 2) & (yPos == recursos.get(i).getYPos() || yPos == recursos.get(i).getYPos() + 1)) {
                xPos--;
                return true;
            }

            //VERIFY Y POSITIONS
            else if((yPos - recursos.get(i).getPositions()[0][1] == -2 || yPos - recursos.get(i).getPositions()[1][1] == -2) & (xPos == recursos.get(i).getXPos() || xPos == recursos.get(i).getXPos() + 1)) {
                yPos++;
                return true;
            }
            
            else if((yPos - recursos.get(i).getPositions()[2][1] == 2 || yPos - recursos.get(i).getPositions()[3][1] == 2) & (xPos == recursos.get(i).getXPos() || xPos == recursos.get(i).getXPos() + 1)) {
                yPos--;
                return true;
            }
            //Diagonal Resource
            else if((xPos - recursos.get(i).getPositions()[0][0] == -1) & (yPos - recursos.get(i).getPositions()[0][1] == -1)) {
                yPos++;
                return true;
            }
            else if((xPos - recursos.get(i).getPositions()[1][0] == 1) & (yPos - recursos.get(i).getPositions()[1][1] == -1)) {
                yPos++;
                return true;
            }
            else if((xPos - recursos.get(i).getPositions()[2][0] == -1) & (yPos - recursos.get(i).getPositions()[2][1] == 1)) {
                yPos--;
                return true;
            }
            else if((xPos - recursos.get(i).getPositions()[3][0] == 1) & (yPos - recursos.get(i).getPositions()[3][1] == 1)) {
                yPos--;
                return true;
            }
        }
        return false;
    }

    public boolean verifyCloseObstacles(ArrayList<Obstaculo> obstaculos) {
        for(int i = 0; i < obstaculos.size(); i++) {

            //VERIFY IF AGENT IS 1 SPACE AWAY FROM THREAT
            if((xPos - obstaculos.get(i).getPositions()[0][0] == -1 || xPos - obstaculos.get(i).getPositions()[2][0] == -1) & (yPos == obstaculos.get(i).getYPos() || yPos == obstaculos.get(i).getYPos() + 1)) {
                if (xPos - 1 > 39 || xPos - 1 < 0)
                    return true;
                else 
                    xPos--;
                return true;
            }
            else if((xPos - obstaculos.get(i).getPositions()[1][0] == 1 || xPos - obstaculos.get(i).getPositions()[3][0] == 1) & (yPos == obstaculos.get(i).getYPos() || yPos == obstaculos.get(i).getYPos() + 1)) {
                if(xPos + 1 > 39 || xPos + 1 < 0)
                    return true;
                else
                    xPos++;
                return true;
            }
            else if((yPos - obstaculos.get(i).getPositions()[0][1] == -1 || yPos - obstaculos.get(i).getPositions()[1][1] == -1) & (xPos == obstaculos.get(i).getXPos() || xPos == obstaculos.get(i).getXPos() + 1)) {
                if(yPos - 1 > 39 || yPos - 1 < 0)
                    return true;
                else
                    yPos--;
                return true;
            }
            else if((yPos - obstaculos.get(i).getPositions()[2][1] == 1 || yPos - obstaculos.get(i).getPositions()[3][1] == 1) & (xPos == obstaculos.get(i).getXPos() || xPos == obstaculos.get(i).getXPos() + 1)) {
                if(yPos + 1 > 39 || yPos + 1 < 0)
                    return true;
                else
                    yPos++;
                return true;
            }

            //VERIFY X POSITIONS
            else if((xPos - obstaculos.get(i).getPositions()[0][0] == -2 || xPos - obstaculos.get(i).getPositions()[2][0] == -2) & (yPos == obstaculos.get(i).getYPos() || yPos == obstaculos.get(i).getYPos() + 1)) {
                if (xPos - 1 > 39 || xPos - 1 < 0)
                    return true;
                else 
                    xPos--;
                return true;
            }
            else if((xPos - obstaculos.get(i).getPositions()[1][0] == 2 || xPos - obstaculos.get(i).getPositions()[3][0] == 2) & (yPos == obstaculos.get(i).getYPos() || yPos == obstaculos.get(i).getYPos() + 1)) {
                if(xPos + 1 > 39 || xPos + 1 < 0)
                    return true;
                else
                    xPos++;
                return true;
            }

            //VERIFY Y POSITIONS
            else if((yPos - obstaculos.get(i).getPositions()[0][1] == -2 || yPos - obstaculos.get(i).getPositions()[1][1] == -2) & (xPos == obstaculos.get(i).getXPos() || xPos == obstaculos.get(i).getXPos() + 1)) {
                if(yPos - 1 > 39 || yPos - 1 < 0)
                    return true;
                else
                    yPos--;
                return true;
            }
            
            else if((yPos - obstaculos.get(i).getPositions()[2][1] == 2 || yPos - obstaculos.get(i).getPositions()[3][1] == 2) & (xPos == obstaculos.get(i).getXPos() || xPos == obstaculos.get(i).getXPos() + 1)) {
                if(yPos + 1 > 39 || yPos + 1 < 0)
                    return true;
                else
                    yPos++;
                return true;
            }
            //Diagonal verification 1 campo
            else if((xPos - obstaculos.get(i).getPositions()[0][0] == -1) & (yPos - obstaculos.get(i).getPositions()[0][1] == -1)) {
                if(yPos - 1 > 39 || yPos - 1 < 0){
                    return true;
                }
                else
                    yPos--;
                
                return true;
            }
            else if((xPos - obstaculos.get(i).getPositions()[1][0] == 1) & (yPos - obstaculos.get(i).getPositions()[1][1] == -1)) {
                if(yPos - 1 > 39 || yPos - 1 < 0){
                    return true;
                }
                else
                    yPos--;
                
                return true;
            }
            else if((xPos - obstaculos.get(i).getPositions()[2][0] == -1) & (yPos - obstaculos.get(i).getPositions()[2][1] == 1)) {
                if(yPos + 1 > 39 || yPos + 1 < 0){
                    return true;
                }
                else
                    yPos++;
                
                return true;
            }
            else if((xPos - obstaculos.get(i).getPositions()[3][0] == 1) & (yPos - obstaculos.get(i).getPositions()[3][1] == 1)) {
                if(yPos + 1 > 39 || yPos + 1 < 0){
                    return true;
                }
                else
                    yPos++;
                
                return true;
            }
        }
        return false;
    }

    public void verifyCloseAgents(ArrayList<Agente> agentes) {
        for(int i = 0; i < agentes.size(); i++) {
            //1 CASILLA
            if(xPos + 1 == agentes.get(i).xPos & yPos == agentes.get(i).yPos) {
                recurso = agentes.get(i).recurso;
                return;
            }
            else if(xPos - 1 == agentes.get(i).xPos & yPos == agentes.get(i).yPos) {
                recurso = agentes.get(i).recurso;
                return;
            }
            else if(yPos + 1 == agentes.get(i).yPos & xPos == agentes.get(i).xPos) {
                recurso = agentes.get(i).recurso;
                return;
            }
            else if(yPos - 1 == agentes.get(i).yPos & xPos == agentes.get(i).xPos) {
                recurso = agentes.get(i).recurso;
                return;
            }
            //2 CASILLAS
            else if(xPos + 2 == agentes.get(i).xPos & yPos == agentes.get(i).yPos) {
                recurso = agentes.get(i).recurso;
                return;
            }
            else if(xPos - 2 == agentes.get(i).xPos & yPos == agentes.get(i).yPos) {
                recurso = agentes.get(i).recurso;
                return;
            }
            else if(yPos + 2 == agentes.get(i).yPos & xPos == agentes.get(i).xPos) {
                recurso = agentes.get(i).recurso;
                return;
            }
            else if(yPos - 2 == agentes.get(i).yPos & xPos == agentes.get(i).xPos) {
                recurso = agentes.get(i).recurso;
                return;
            }
            //verificar diagonal
            else if((xPos - agentes.get(i).xPos == -1) & (yPos - agentes.get(i).yPos == -1)) {
                recurso = agentes.get(i).recurso;
                return;
            }
            else if((xPos - agentes.get(i).xPos == 1) & (yPos - agentes.get(i).yPos == -1)) {
                recurso = agentes.get(i).recurso;
                return;
            }
            else if((xPos - agentes.get(i).xPos == -1) & (yPos - agentes.get(i).yPos == 1)) {
                recurso = agentes.get(i).recurso;
                return;
            }
            else if((xPos - agentes.get(i).xPos == 1) & (yPos - agentes.get(i).yPos == 1)) {
                recurso = agentes.get(i).recurso;
                return;
            }
        }
    }

    public boolean goToResource(ArrayList<Agente> agentes) {
        verifyCloseAgents(agentes);
        if(recurso == null) 
            return false;
        
        if(xPos < recurso.xPos & lastMoveInX == false) {
            xPos++;
            lastMoveInX = true;
            return true;
        }
        if(xPos > recurso.xPos & lastMoveInX == false) {
            xPos--;
            lastMoveInX = true;
            return true;
        }
        if(yPos < recurso.yPos & lastMoveInX == true) {
            yPos++;
            lastMoveInX = false;
            return true;
        }
        if(yPos > recurso.yPos & lastMoveInX == true) {
            yPos--;
            lastMoveInX = false;
            return true;
        }
        return false;
    }

    public void moveRandomly() {
        int[][] randomMove = {{-1, 1}, {-1, 1}};
        int rand1 = new Random().nextInt(2);
        int rand2 = new Random().nextInt(2);
        if(rand1 == 0) {
            if(xPos + randomMove[rand1][rand2] > 39 || xPos + randomMove[rand1][rand2] < 0) {
                moveRandomly();
                return;
            }
            xPos += randomMove[rand1][rand2];
        }
        else {
            if(yPos + randomMove[rand1][rand2] > 39 || yPos + randomMove[rand1][rand2] < 0) {
                moveRandomly();
                return;
            }
            yPos += randomMove[rand1][rand2];
        }
    }

    public void resourceFound(ArrayList<Agente> agentes, ArrayList<Recursos> recursos, ArrayList<Amenaza> amenazas, ArrayList<Obstaculo> obstaculos, Base base) {
        if(threatDetected(amenazas))
            return;
            
        if(verifyCloseObstacles(obstaculos))
            return;
        
        if(xPos - 1 >= 0 & lastMoveInX == false) {
            xPos--;
            lastMoveInX = true;
            verifyLastMove();
            return;
        }
        
        else if(yPos - 1 >= 0 & lastMoveInX == true) {
            yPos--;
            lastMoveInX = false;
            verifyLastMove();
            return;
        }
        else {
            currentResource = false;
            base.recursoAgregado();
            return;
        }
    }

    public void verifyLastMove() {
        if(xPos == 0)
            lastMoveInX = true;
        if(yPos == 0)
            lastMoveInX = false;

    }

}

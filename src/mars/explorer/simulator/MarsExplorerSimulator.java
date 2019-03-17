/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mars.explorer.simulator;

/**
 *
 * @author Alfan Galmani
 */
public class MarsExplorerSimulator {
    
    Explorer exp;
    int boardSize = 5;
    boolean onBoard = false;
    
    public void getInput(String in) {
        String input[] = in.split("\\r?\\n");
        
        for (int i = 0; i < input.length; i++) {
            String command = input[i].substring(0, 4);
            switch (command) {
                case "PLAC":
                    placeCommand(input[i].substring(6));
                    break;
                case "MOVE":
                    moveCommand(input[i].substring(5));
                    break;
                case "REPO":
                    reportCommand();
                    break;
                default:
                    break;
            }
        }
    }
    
    public void placeCommand(String in) {
        String input[] = in.split(",");
        try {
            if (Integer.parseInt(input[0]) < boardSize
                    && Integer.parseInt(input[1]) < boardSize
                    && Integer.parseInt(input[0]) >= 0
                    && Integer.parseInt(input[1]) >= 0) {
                
                onBoard = true;
                exp = new Explorer(Integer.parseInt(input[0]),
                        Integer.parseInt(input[1]));
            }
        } catch (Exception e) {
            
        }
    }
    
    public void moveCommand(String in) {
        if (onBoard) {
            String input[] = in.split(",");
            try {
                if (Integer.parseInt(input[0]) < boardSize
                        && Integer.parseInt(input[1]) < boardSize
                        && Integer.parseInt(input[0]) >= 0
                        && Integer.parseInt(input[1]) >= 0) {
                    
                    int currentX = exp.getxAxis();
                    int currentY = exp.getyAxis();
                    int destinationX = Integer.parseInt(input[0]);
                    int destinationY = Integer.parseInt(input[1]);
                    
                    exp.setPath("M: (" + currentX + "," + currentY + ")");
                    
                    while (currentX != destinationX) {
                        if (currentX < destinationX) {
                            currentX = currentX + 1;
                        } else {
                            currentX = currentX - 1;
                        }
                        exp.setxAxis(currentX);
                        exp.setPath(exp.getPath() + " (" + currentX + ","
                                + currentY + ")");
                    }
                    while (currentY != destinationY) {
                        if (currentY < destinationY) {
                            currentY = currentY + 1;
                        } else {
                            currentY = currentY - 1;
                        }
                        exp.setyAxis(currentY);
                        exp.setPath(exp.getPath() + " (" + currentX + ","
                                + currentY + ")");
                    }
                    
                    System.out.println(exp.getPath());
                }
            } catch (Exception e) {
                
            }
        }
    }
    
    public void reportCommand() {
        if (onBoard) {
            System.out.println("P: (" + exp.getxAxis() + "," + exp.getyAxis()
                    + ")");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String input = "PLACE -2,0"
                + "\nPLACE 1,3\n"
                + "MOVE 2,1\n"
                + "REPORT";
        
        MarsExplorerSimulator m = new MarsExplorerSimulator();
        m.getInput(input);
        
    }
    
}

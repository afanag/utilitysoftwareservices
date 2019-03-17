/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mars.explorer.simulator;

/**
 *
 */
public class MarsExplorerSimulator {

    Explorer exp;
    int boardSize = 5;
    boolean onBoard = false;

    /*
    Get Input and parse according to the command
    Parameter: String (Command X,Y)
     */
    public void getInput(String in) {
        //Geting Command line by line
        String input[] = in.split("\\r?\\n");

        for (int i = 0; i < input.length; i++) {
            String command = input[i].substring(0, 4);
            //Call methods according to the commands and passing coordinates
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

    /*
    Place Command
    Parameter: String (X,Y) Coordinate
    */
    public void placeCommand(String in) {
        //Splits X nad Y coordinate
        String input[] = in.split(",");
        try {
            //Checking X nad Y coordinate are on the board
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

    /*
    Move Command
    Parameter: String (X,Y) Coordinate
    */
    public void moveCommand(String in) {
        if (onBoard) {
            //Splits X nad Y coordinate
            String input[] = in.split(",");
            try {
                //Checking X nad Y coordinate are on the board
                if (Integer.parseInt(input[0]) < boardSize
                        && Integer.parseInt(input[1]) < boardSize
                        && Integer.parseInt(input[0]) >= 0
                        && Integer.parseInt(input[1]) >= 0) {

                    //Set current location
                    int currentX = exp.getxAxis();
                    int currentY = exp.getyAxis();
                    //Set destination location
                    int destinationX = Integer.parseInt(input[0]);
                    int destinationY = Integer.parseInt(input[1]);

                    //Set Path
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

    /*
    Report Command
    */
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
        String input = "PLACE -2,0\n"
                + "PLACE 1,3\n"
                + "MOVE 2,1\n"
                + "REPORT\n"
                + "MOVE 2,4\n"
                + "REPORT";

        MarsExplorerSimulator m = new MarsExplorerSimulator();
        m.getInput(input);

    }

}

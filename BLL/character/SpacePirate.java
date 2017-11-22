package BLL.character;

import java.util.Scanner;

public class SpacePirate extends SpecificNPC implements NPC, Stationary {

    private Scanner scanner;

    public SpacePirate() {
        scanner = new Scanner(System.in);
    }

    public String[] getPirateMsg() {
        //TODO implement x,y coordiantes to initiate get piratemsg
        return new String[]{

                "you have been intercepted and captured by space pirates",
                "Would you like to pay the ransom to them in order to proceed on your voyage?",
                "Answer Y/N"


        };

    }

    public boolean hasAcceptedOffer(String answer) {
	    return answer.equalsIgnoreCase("y");
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean isGood() {return false; }

    @Override
    public String[] message() {
        return new String[0];
    }

    @Override
    public boolean isStationary() {
        return false;
    }
}
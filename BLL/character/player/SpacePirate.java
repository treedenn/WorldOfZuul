package BLL.character.player;

import java.util.Scanner;

public class SpacePirate {
    private Scanner scanner;

    public SpacePirate () {scanner=new Scanner(System.in);}

    public String[] getPirateMsg(){
        //TODO implement x,y coordiantes to initiate get piratemsg
        return new String[]{

                "you have been intercepted and captured by space pirates",
                "Would you like to pay the ransom to them in order to proceed on your voyage?",
                "Answer Y/N "


        };

    }
    public boolean hasAcceptedOffer(char c) {
        return c == 'Y' || c == 'y';
    }

}

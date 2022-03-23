package workshop.triviaGame;

import java.util.ArrayList;

public class Player {
    public String playerName;
    public int places;
    public int purses;
    public boolean inPenaltyBox;

    public Player(String playerName) {
        this.playerName = playerName;
        this.places = 0;
        this.purses = 0;
        this.inPenaltyBox = false;
    }

    public boolean didPlayerWin() {
        return this.purses != 6;
    }

    public void move(int roll){
        places += roll;
        if(this.places > 11)
            this.places -= 12;
    }
}

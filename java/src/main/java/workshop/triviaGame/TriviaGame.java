package workshop.triviaGame;

import java.util.ArrayList;
import java.util.LinkedList;

public class TriviaGame {
    ArrayList<Player> players = new ArrayList<>();
    ArrayList<Question> questions = new ArrayList<>();
//    ArrayList players = new ArrayList();
//    int[] places = new int[6];
//    int[] purses = new int[6];
//    boolean[] inPenaltyBox = new boolean[6];

    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public TriviaGame() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast("Rock Question " + i);
        }
    }

    public boolean isPlayable() {
        return (howManyPlayers() >= 2);
    }

    public boolean add(String playerName) {

        players.add(new Player(playerName));
//        players.add(playerName);
//        places[howManyPlayers()] = 0;
//        purses[howManyPlayers()] = 0;
//        inPenaltyBox[howManyPlayers()] = false;

        announce(playerName + " was added");
        announce("They are player number " + players.size());
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        announce(players.get(currentPlayer).playerName + " is the current player");
        announce("They have rolled a " + roll);

        if (players.get(currentPlayer).inPenaltyBox) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;
                announce(players.get(currentPlayer) + " is getting out of the penalty box");
                announcePlayerLocationAndCatagory(roll); // method extracted
            }
            else {
                announce(players.get(currentPlayer) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }
        }
        else
            announcePlayerLocationAndCatagory(roll);

    }

    private void announcePlayerLocationAndCatagory(int roll) {
        players.get(currentPlayer).move(roll);
        announce(players.get(currentPlayer)
                + "'s new location is "
                + players.get(currentPlayer).places);
        announce("The category is " + currentCategory());
        askQuestion();
    }

    private void askQuestion() {
        for(Question question : questions){
            if(question.typeOfQues(currentCategory()))
                question.generateResponse();
        }
    }


    private String currentCategory() {
        String category = "";
        for(Question question : questions){
            category+=question.categoryOfPlace(players.get(currentPlayer).places);
        }
        return category;
    }

    public boolean wasCorrectlyAnswered() {
        boolean correctAnswer = checkPenalty();
        shiftTurn();
        return correctAnswer;
    }

    public boolean checkPenalty(){
        if (players.get(currentPlayer).inPenaltyBox) {
            if (isGettingOutOfPenaltyBox){
                announceCorrectAns();
                return players.get(currentPlayer).didPlayerWin();
            }
            return true;
        }

        announceCorrectAns();
        return players.get(currentPlayer).didPlayerWin();
    }

    private void announceCorrectAns() {
        announce("Answer was correct!!!!");
        players.get(currentPlayer).purses++;
        announce(players.get(currentPlayer)
                + " now has "
                + players.get(currentPlayer).purses
                + " Gold Coins.");
    }

    private void shiftTurn() {
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
    }

    public boolean wrongAnswer() {
        announce("Question was incorrectly answered");
        announce(players.get(currentPlayer) + " was sent to the penalty box");
        players.get(currentPlayer).inPenaltyBox = true;

        shiftTurn();
        return true;
    }

/* moved to Player*/
//    private boolean didPlayerWin() {
//        return players.get(currentPlayer).purses != 6;
//    }
    protected void announce(Object message) {
        System.out.println(message);
    }
}
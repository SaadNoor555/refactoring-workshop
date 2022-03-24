package workshop.triviaGame;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.LinkedList;

public class TriviaGame {
    ArrayList<Player> players = new ArrayList<>();
    ArrayList<Question> questions = new ArrayList<>();

//    LinkedList popQuestions = new LinkedList();
//    LinkedList scienceQuestions = new LinkedList();
//    LinkedList sportsQuestions = new LinkedList();
//    LinkedList rockQuestions = new LinkedList();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public TriviaGame() {
        PopQuestion popQuestion = new PopQuestion();
        RockQuestion rockQuestion = new RockQuestion();
        ScienceQuestion scienceQuestion = new ScienceQuestion();
        SportsQuestion sportsQuestion = new SportsQuestion();
        for (int i = 0; i < 50; i++) {
            popQuestion.addQuestion("Pop Question " + i);
            scienceQuestion.addQuestion("Science Question " + i);
            sportsQuestion.addQuestion("Sports Question " + i);
            rockQuestion.addQuestion("Rock Question " + i);
        }
        questions.add(popQuestion);
        questions.add(scienceQuestion);
        questions.add(sportsQuestion);
        questions.add(rockQuestion);
    }

    public boolean isPlayable() {
        return (howManyPlayers() >= 2);
    }

    public boolean add(String playerName) {

        players.add(new Player(playerName));
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
        announce(players.get(currentPlayer).playerName
                + "'s new location is "
                + players.get(currentPlayer).places);
        announce("The category is " + currentCategory());
        askQuestion();
    }

    private void askQuestion() {
        for(Question question : questions){
            if(question.typeOfQues(currentCategory())){
                System.out.println("hi");
                announce(question.generateResponse());
            }

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
        announce(players.get(currentPlayer).playerName
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
        announce(players.get(currentPlayer).playerName + " was sent to the penalty box");
        players.get(currentPlayer).inPenaltyBox = true;

        shiftTurn();
        return true;
    }

    protected void announce(Object message) {
        System.out.println(message);
    }
}
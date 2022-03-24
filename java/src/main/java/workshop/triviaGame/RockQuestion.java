package workshop.triviaGame;

import java.util.ArrayList;

public class RockQuestion implements Question{
    ArrayList<String> questions = new ArrayList();

    @Override
    public boolean typeOfQues(String catagory) {
        return catagory.equals("Rock");
    }

    @Override
    public String generateResponse() {
        String ques = questions.get(0);
        questions.remove(0);
        return ques;
    }

    public String categoryOfPlace(int place) {
        if(place % 4 == 3) return "Rock";
        return "";
    }
    @Override
    public void addQuestion(String question) {
        questions.add(question);
    }
}


package workshop.triviaGame;

import java.util.ArrayList;

public class PopQuestion implements Question{
    ArrayList<String> questions = new ArrayList();
    @Override
    public boolean typeOfQues(String catagory) {
        return catagory.equals("POP");
    }

    @Override
    public String generateResponse() {
        String ques = questions.get(0);
        questions.remove(0);
        return ques;
    }

    @Override
    public String categoryOfPlace(int place) {
        if(place % 4 == 0) return "Pop";
        return "";
    }

    @Override
    public void addQuestion(String question) {
        questions.add(question);
    }
}

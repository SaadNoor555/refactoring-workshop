package workshop.triviaGame;

import java.util.ArrayList;

public class RockQuestion implements Question{
    ArrayList questions = new ArrayList();

    @Override
    public boolean typeOfQues(String catagory) {
        return catagory == "Science";
    }

    @Override
    public void generateResponse() {
        System.out.println(questions.remove(0));
    }
    public String categoryOfPlace(int place) {
        if(place % 4 == 3) return "Rock";
        return "";
    }
}


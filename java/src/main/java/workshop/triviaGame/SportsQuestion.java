package workshop.triviaGame;

import java.util.ArrayList;

public class SportsQuestion implements Question{
    ArrayList questions = new ArrayList();

    @Override
    public boolean typeOfQues(String catagory) {
        return catagory == "Sports";
    }

    @Override
    public void generateResponse() {
        System.out.println(questions.remove(0));
    }

    public String categoryOfPlace(int place) {
        if(place % 4 == 2) return "Sports";
        return "";
    }
}


package workshop.triviaGame;

import java.util.ArrayList;

public class PopQuestion implements Question{
    ArrayList questions = new ArrayList();
    @Override
    public boolean typeOfQues(String catagory) {
        return catagory == "POP";
    }

    @Override
    public void generateResponse() {
        System.out.println(questions.remove(0));
    }

    @Override
    public String categoryOfPlace(int place) {
        if(place % 4 == 0) return "Pop";
        return "";
    }
}

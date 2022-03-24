package workshop.triviaGame;

import java.util.ArrayList;

public interface Question {
    public boolean typeOfQues(String catagory);
    public String generateResponse();
    public String categoryOfPlace(int place);
    public void addQuestion(String qeues);
}

package workshop.triviaGame;

import java.util.ArrayList;

public interface Question {
    public boolean typeOfQues(String catagory);
    public void generateResponse();
    public String categoryOfPlace(int place);
}

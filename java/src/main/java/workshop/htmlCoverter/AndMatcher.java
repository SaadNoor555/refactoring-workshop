package workshop.htmlCoverter;

import java.util.List;

public class AndMatcher implements CharacterMatcher{
    @Override
    public boolean matches(char characterToConvert) {
        return characterToConvert=='&';
    }

    @Override
    public void addNewCharacter(List<String> convertedLine) {
        convertedLine.add("&amp;");
    }
}

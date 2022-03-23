package workshop.htmlCoverter;

import java.util.List;

public class NewLineMatcher implements CharacterMatcher{

    @Override
    public boolean matches(char characterToConvert) {
        return characterToConvert=='\n';
    }

    @Override
    public void addNewCharacter(List<String> convertedLine) {
        convertedLine.add("<br />");
//        convertedLine.clear();
    }
}

package workshop.htmlCoverter;

import java.util.List;

public interface CharacterMatcher {
    public boolean matches(char characterToConvert);
    public void addNewCharacter(List<String> convertedLine);
}

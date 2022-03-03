package workshop;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GreaterThanMatcher implements CharacterMatcher {
    @Override
    public boolean matches(char characterToConvert) {
        return characterToConvert=='>';
    }

    @Override
    public void addNewCharacter(List<String> convertedLine, String characterToConvert) {
        convertedLine.add("&gt;");
    }
}

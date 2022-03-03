package workshop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlaintextToHtmlConverter {
    /*removed some fields*/
    /*
    String source;
    int i;
    List<String> result;
    List<String> convertedLine;
    String characterToConvert;
    */

    public String toHtml() throws Exception {
        String text = read();
        return basicHtmlEncode(text);
    }

    private String read() throws IOException {
        //making more readable
        Path filePath = Paths.get("sample.txt");
        byte[] fileByteArray = Files.readAllBytes(filePath);
        return new String(fileByteArray);
    }

    private String basicHtmlEncode(String source) {
        int i = 0;
        List<String>result = new ArrayList<>();
        List<String>convertedLine = new ArrayList<>();
//        String characterToConvert = stashNextCharacterAndAdvanceThePointer(source, i);
        List<CharacterMatcher> characterMatchers = new ArrayList(Arrays.asList(new GreaterThanMatcher(), new LessThanMatcher(), new AndMatcher(), new NewLineMatcher()));

        for(char characterToConvert : source.toCharArray()) {
            for(CharacterMatcher matcher : characterMatchers) {
                if(matcher.matches(characterToConvert)){
                    matcher.addNewCharacter(convertedLine, Character.toString(characterToConvert));
                }
            }
        }
        AddToOutput.addANewLine(result, convertedLine);
        convertedLine.clear();
        return AddToOutput.addBreakLineToOutput(result);
    }
    /*

    REMOVED BECAUSE OF PRIMITIVE OBSESSION
    private String stashNextCharacterAndAdvanceThePointer(String source, int i) {
        char c = source.charAt(i);
//        i+=1;
        return String.valueOf(c);
    }
    */

    //stringfy convertedLine array and push into result
    //reset convertedLine
    /*

    REMOVED TO SHORTEN THE CLASS
    private void addANewLine(List<String> result, List<String>convertedLine) {
        String line = String.join("", convertedLine);
        result.add(line);
        convertedLine = new ArrayList<>();
    }
    */
}

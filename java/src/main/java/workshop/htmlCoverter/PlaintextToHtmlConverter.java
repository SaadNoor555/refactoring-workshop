package workshop.htmlCoverter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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

    protected String read() throws IOException {
        Scanner fileScanner = new Scanner(new File("sample.txt"));
        String content = "";
        while (fileScanner.hasNextLine()){
            content+=(fileScanner.nextLine()+"\n");
        }
        return removeFinalEndLine(content);
    }

    private String removeFinalEndLine(String content) {
        int end = content.length() - 1;
        if(content.charAt(end) == '\n')
            content = content.substring(0, end);
        return content;
    }

    private String basicHtmlEncode(String source) {
        List<String>result = new ArrayList<>();
        List<String>convertedLine = new ArrayList<>();
//        String characterToConvert = stashNextCharacterAndAdvanceThePointer(source, i);
        List<CharacterMatcher> characterMatchers = new ArrayList(Arrays.asList(new GreaterThanMatcher(), new LessThanMatcher(), new AndMatcher(), new NewLineMatcher()));

        for(char characterToConvert : source.toCharArray()) {
            boolean flag = true;
            for(CharacterMatcher matcher : characterMatchers) {
                if(matcher.matches(characterToConvert)) {
                    matcher.addNewCharacter(convertedLine);
                    flag = false;
                    break;
                }
            }
            if(flag)
                convertedLine.add(String.valueOf(characterToConvert));
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

    REMOVED FOR SINGLE RESPONSIBILITY
    private void addANewLine(List<String> result, List<String>convertedLine) {
        String line = String.join("", convertedLine);
        result.add(line);
        convertedLine = new ArrayList<>();
    }
    */
}

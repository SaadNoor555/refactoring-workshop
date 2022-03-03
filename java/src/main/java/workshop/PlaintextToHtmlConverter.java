package workshop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PlaintextToHtmlConverter {
    /*removed some fields*/
//    String source;
//    int i;
//    List<String> result;
//    List<String> convertedLine;
//    String characterToConvert;

    public String toHtml() throws Exception {
        String text = read();
        String htmlLines = basicHtmlEncode(text);
        return htmlLines;
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
        String characterToConvert = stashNextCharacterAndAdvanceThePointer(source);

        while (i <= source.length()) {
            switch (characterToConvert) {
                case "<":
                    convertedLine.add("&lt;");
                    break;
                case ">":
                    convertedLine.add("&gt;");
                    break;
                case "&":
                    convertedLine.add("&amp;");
                    break;
                case "\n":
                    addANewLine(result, convertedLine);
                    break;
                default:
                    pushACharacterToTheOutput(convertedLine, characterToConvert);
            }

            if (i >= source.length()) break;

            characterToConvert = stashNextCharacterAndAdvanceThePointer(source, i);
            i+=1;
        }
        addANewLine(result, convertedLine);
        String finalResult = String.join("<br />", result);
        return finalResult;
    }

    //pick the character from source string
    //and increment the pointer
    private String stashNextCharacterAndAdvanceThePointer(String source, int i) {
        char c = source.charAt(i);
//        i+=1;
        return String.valueOf(c);
    }

    //stringfy convertedLine array and push into result
    //reset convertedLine
    private void addANewLine(List<String> result, List<String>convertedLine) {
        String line = String.join("", convertedLine);
        result.add(line);
        convertedLine = new ArrayList<>();
    }

    private void pushACharacterToTheOutput(List<String>convertedLine, String characterToConvert) {
        convertedLine.add(characterToConvert);
    }
}

package workshop;

import java.util.ArrayList;
import java.util.List;

public class AddToOutput {
    public static void addANewLine(List<String> result, List<String>convertedLine) {
        String line = String.join("", convertedLine);
        result.add(line);
    }

    public static String addBreakLineToOutput(List<String>result){
        return String.join("<br />", result);
    }
}

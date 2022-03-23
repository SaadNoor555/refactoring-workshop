package workshop.fizzBuzz;


import java.util.ArrayList;
import java.util.List;

/**
 * Requirements:
 * For factor of three print Fizz instead of the number
 * For factor of five print Buzz instead of the number
 * For numbers which are factors of both three and five print FizzBuzz instead of the number
 */
public class FizzBuzz {
    private List<PatternMatcher> patternMatchers;
    private PatternMatcher nullObjectPattern;
//    public FizzBuzz()
    public FizzBuzz(List<PatternMatcher> patternMatchers, PatternMatcher nullObjectPattern) {
        this.patternMatchers = patternMatchers;
        this.nullObjectPattern = nullObjectPattern;
    }
    public FizzBuzz(){
        FizzPatternMatcher fizz = new FizzPatternMatcher();
        BuzzPatternMatcher buzz = new BuzzPatternMatcher();
        NullResponse nullResponse = new NullResponse();
        nullObjectPattern = nullResponse;
        patternMatchers = new ArrayList<>();
        patternMatchers.add(fizz);
        patternMatchers.add(buzz);
    }
    public String say(int number) {
        String strReturn = nullObjectPattern.generateRresponse();

        for (PatternMatcher patternMatcher : patternMatchers) {
            if (patternMatcher.matches(number)) strReturn += patternMatcher.generateRresponse();
        }

        if(strReturn.equals("")) strReturn += String.valueOf(number);

        return strReturn;
    }
}

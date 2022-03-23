package workshop.fizzBuzz;

public class BuzzPatternMatcher implements PatternMatcher {
    @Override
    public boolean matches(int number) {
        return number % 5 == 0;
    }

    @Override
    public String generateRresponse() {
        return "Buzz";
    }
}

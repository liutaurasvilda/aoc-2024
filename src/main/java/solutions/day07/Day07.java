package solutions.day07;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

final class Day07 {

    public static long calibrationResult(List<Equation> equations) {
        return equations.stream()
                .map(Day07::evaluate)
                .filter(Optional::isPresent)
                .mapToLong(Optional::get)
                .sum();
    }

    private static Optional<Long> evaluate(Equation equation) {
        var results = new ArrayList<Long>();
        var operatorsPermutations = permutations(List.of("+", "*"), equation.numbers().size() - 1);
        for (List<String> operators : operatorsPermutations) {
            var result = equation.numbers().getFirst();
            for (int j = 1; j < equation.numbers().size(); j++) {
                var operator = operators.get(j - 1);
                if (operator.equals("+")) {
                    result += equation.numbers().get(j);
                } else if (operator.equals("*")) {
                    result *= equation.numbers().get(j);
                }
            }
            results.add(result);
        }
        return results.stream().filter(e -> e == equation.testValue()).findFirst();
    }

    public static List<List<String>> permutations(List<String> operators, int length) {
        var result = new ArrayList<List<String>>();
        // TODO build permutations
        return result;
    }

    record Equation(long testValue, List<Long> numbers) {
    }
}

package solutions.day07;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

final class Day07 {

    public static long calibrationResult(List<Equation> equations, List<String> operators) {
        return equations.stream()
                .map(e -> evaluate(e, operators))
                .filter(Optional::isPresent)
                .mapToLong(Optional::get)
                .sum();
    }

    private static Optional<Long> evaluate(Equation equation, List<String> operators) {
        var results = new ArrayList<Long>();
        var operatorsPermutations = permutations(operators, equation.numbers().size() - 1);
        for (List<String> operatorsPermutation : operatorsPermutations) {
            var result = equation.numbers().getFirst();
            for (int i = 1; i < equation.numbers().size(); i++) {
                var operator = operatorsPermutation.get(i - 1);
                switch (operator) {
                    case "+" -> result += equation.numbers().get(i);
                    case "*" -> result *= equation.numbers().get(i);
                    case "||" -> {
                        String resultString = String.valueOf(result);
                        String numberString = String.valueOf(equation.numbers().get(i));
                        result = Long.parseLong(resultString + numberString);
                    }
                }
            }
            results.add(result);
        }
        return results.stream().filter(e -> e == equation.testValue()).findFirst();
    }

    public static List<List<String>> permutations(List<String> operators, int k) {
        var result = new ArrayList<List<String>>();
        permute(operators, k, new ArrayList<>(), result);
        return result;
    }

    private static void permute(List<String> operators, int k, List<String> permutation, List<List<String>> result) {
        if (k == 0) {
            result.add(new ArrayList<>(permutation));
            permutation.clear();
            return;
        }
        for (int i = 0; i < operators.size(); i++) {
            var newPermutation = new ArrayList<>(permutation);
            newPermutation.add(operators.get(i));
            permute(operators, k - 1, newPermutation, result);
        }
    }

    record Equation(long testValue, List<Long> numbers) {
    }
}

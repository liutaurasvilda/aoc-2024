package solutions.day03;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class Day03 {

    public static long evalMul(ProgramMemory programMemory) {
        Pattern mul = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");
        return programMemory.value().stream().map(mul::matcher)
                .flatMap(matched -> matched.results()
                        .map(MatchResult::group)
                        .map(e -> Integer.parseInt(e.substring(e.indexOf("(") + 1, e.indexOf(",")))
                                * Integer.parseInt(e.substring(e.indexOf(",") + 1, e.indexOf(")")))))
                .mapToLong(e -> e).sum();
    }

    public static long evalMul2(ProgramMemory programMemory) {
        Pattern pattern = Pattern.compile("(do\\(\\)|don't\\(\\)|mul\\(\\d{1,3},\\d{1,3}\\))");
        long result = 0L;
        var doInstruction = true;
        for (int i = 0; i < programMemory.value().size(); i++) {
            Matcher matcher = pattern.matcher(programMemory.value().get(i));
            while (matcher.find()) {
                switch (matcher.group()) {
                    case "do()" -> doInstruction = true;
                    case "don't()" -> doInstruction = false;
                    default -> {
                        if (doInstruction) {
                            var instruction = matcher.group();
                            long n1 = Integer.parseInt(instruction.substring(instruction.indexOf("(") + 1, instruction.indexOf(",")));
                            long n2 = Integer.parseInt(instruction.substring(instruction.indexOf(",") + 1, instruction.indexOf(")")));
                            result += n1 * n2;
                        }
                    }
                }
            }
        }
        return result;
    }

    record ProgramMemory(List<String> value) {
    }
}

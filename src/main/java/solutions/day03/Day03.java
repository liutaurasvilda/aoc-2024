package solutions.day03;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class Day03 {

    public static long evalMul(ProgramMemory programMemory) {
        Pattern mul = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");
        return programMemory.value().stream().map(mul::matcher)
                .flatMap(matched -> matched.results().map(MatchResult::group).map(e -> leftNumber(e) * rightNumber(e)))
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
                            result += leftNumber(matcher.group()) * rightNumber(matcher.group());
                        }
                    }
                }
            }
        }
        return result;
    }

    private static long leftNumber(String s) {
        return Long.parseLong(s.substring(s.indexOf("(") + 1, s.indexOf(",")));
    }

    private static long rightNumber(String s) {
        return Long.parseLong(s.substring(s.indexOf(",") + 1, s.indexOf(")")));
    }

    record ProgramMemory(List<String> value) {
    }
}

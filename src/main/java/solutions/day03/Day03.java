package solutions.day03;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class Day03 {

    public static long evalMul(ProgramMemory programMemory) {
        Pattern mul = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");
        return programMemory.values().stream().map(mul::matcher)
                .flatMap(Matcher::results)
                .map(MatchResult::group)
                .map(e -> leftNumber(e) * rightNumber(e))
                .mapToLong(e -> e).sum();
    }

    public static long evalMul2(ProgramMemory programMemory) {
        Pattern conditionalMul = Pattern.compile("(do\\(\\)|don't\\(\\)|mul\\(\\d{1,3},\\d{1,3}\\))");
        AtomicBoolean execute = new AtomicBoolean(true);
        return programMemory.values().stream().map(conditionalMul::matcher)
                .flatMap((Matcher::results))
                .map(MatchResult::group)
                .map(e -> {
                    if (e.equals("do()")) {
                        execute.set(true);
                    } else if (e.equals("don't()")) {
                        execute.set(false);
                    } else if (execute.get()) {
                        return leftNumber(e) * rightNumber(e);
                    }
                    return 0;
                })
                .mapToLong(Number::longValue)
                .sum();
    }

    private static long leftNumber(String s) {
        return Long.parseLong(s.substring(s.indexOf("(") + 1, s.indexOf(",")));
    }

    private static long rightNumber(String s) {
        return Long.parseLong(s.substring(s.indexOf(",") + 1, s.indexOf(")")));
    }

    record ProgramMemory(List<String> values) {
    }
}

package solutions.day03;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class Day03 {

    public static long evalMul(ProgramMemory programMemory) {
        Pattern pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");
        return programMemory.value().stream().map(e -> evaluateInstruction(pattern, e)).mapToLong(e -> e).sum();
    }

    private static long evaluateInstruction(Pattern pattern, String value) {
        Matcher matcher = pattern.matcher(value);
        long result = 0L;
        while (matcher.find()) {
            var instruction = matcher.group();
            long n1 = Integer.parseInt(instruction.substring(instruction.indexOf("(") + 1, instruction.indexOf(",")));
            long n2 = Integer.parseInt(instruction.substring(instruction.indexOf(",") + 1, instruction.indexOf(")")));
            result += n1 * n2;
        }
        return result;
    }

    record ProgramMemory(List<String> value) {
    }
}

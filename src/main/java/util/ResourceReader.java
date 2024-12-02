package util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ResourceReader {

    public enum Delimiter {
        COMMA(","), SPACE(" "), NO_SPACE("");

        private String value;

        Delimiter(String value) {
            this.value = value;
        }
    }

    public static List<String> stringLines(String resourceName) {
        return readResource(resourceName)
                .collect(Collectors.toList());
    }

    public static List<List<String>> stringLinesDelimited(String resourceName, Delimiter delimiter) {
        return readResource(resourceName)
                .map(e -> Arrays.stream(e.split(delimiter.value))
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    public static List<String> oneLineStringsDelimited(String resourceName, Delimiter delimiter) {
        return Arrays.stream(ResourceReader.stringLines(resourceName)
                        .getFirst().split(delimiter.value))
                .collect(Collectors.toList());
    }

    public static List<Integer> intLines(String resourceName) {
        return readResource(resourceName)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static List<List<Integer>> intLinesDelimited(String resourceName, Delimiter delimiter) {
        return readResource(resourceName)
                .map(e -> Arrays.stream(e.split(delimiter.value))
                        .map(Integer::valueOf).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    public static List<Integer> oneLineIntsDelimited(String resourceName, Delimiter delimiter) {
        return Arrays.stream(ResourceReader.stringLines(resourceName)
                        .getFirst().split(delimiter.value)).map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static Stream<String> readResource(String resourceName) {
        try {
            return Files.lines(Paths.get(ClassLoader.getSystemResource(resourceName).toURI()));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Resource cannot be read");
        }
    }
}
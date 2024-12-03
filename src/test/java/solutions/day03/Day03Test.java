package solutions.day03;

import org.junit.jupiter.api.Test;
import util.ResourceReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day03Test {

    @Test
    void part1() {
        assertEquals(187194524, Day03.evalMul(programMemory()));
    }

    @Test
    void part2() {

    }

    private Day03.ProgramMemory programMemory() {
        return new Day03.ProgramMemory(ResourceReader.stringLines("day03.txt"));
    }
}
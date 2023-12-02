package adavis100.day02;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day02Test {
    String lines = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\n" +
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue\n" +
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red\n" +
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red\n" +
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green";
    @Test
    void solvesPart1() {
        Day02 day02 = new Day02();
        assertThat(day02.part1(lines)).isEqualTo(8);
    }

    @Test
    void solvesPart2() {
        Day02 day02 = new Day02();
        assertThat(day02.part2(lines)).isEqualTo(2286);
    }
}
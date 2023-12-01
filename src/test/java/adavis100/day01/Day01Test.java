package adavis100.day01;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day01Test {
    String lines = "1abc2\n" +
            "pqr3stu8vwx\n" +
            "a1b2c3d4e5f\n" +
            "treb7uchet";

    String lines2 = "two1nine\n" +
            "eightwothree\n" +
            "abcone2threexyz\n" +
            "xtwone3four\n" +
            "4nineeightseven2\n" +
            "zoneight234\n" +
            "7pqrstsixteen";

    @Test
    void solvesPart1Example() {
        var day1 = new Day01();
        assertThat(day1.part1(lines)).isEqualTo(142);
    }

    @Test
    void parsesSpelledNumbers() {
        var day1 = new Day01();

        assertThat(day1.convertSpelledDigits("two1nine")).isEqualTo("2o19e");
        assertThat(day1.convertSpelledDigits("abcone2threexyz")).isEqualTo("abc1e23exyz");
        assertThat(day1.convertSpelledDigits("xtwone3four")).isEqualTo("x21e34");
        assertThat(day1.convertSpelledDigits("4nineeightseven2")).isEqualTo("49e8t7n2");
        assertThat(day1.convertSpelledDigits("zoneight234")).isEqualTo("z18t234");
        assertThat(day1.convertSpelledDigits("7pqrstsixteen")).isEqualTo("7pqrst6teen");
    }

    @Test
    void solvesPart2Example() {
        var day1 = new Day01();

        assertThat(day1.part2(lines2)).isEqualTo(281);
    }
}

package adavis100.day06;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day06Test {
    long[] exTimes = new long[]{7, 15, 30};
    long[] exDists = new long[]{9, 40, 200};

    @Test
    void solvesPart1Example() {
        Day06 day06 = new Day06(exTimes, exDists);
        assertThat(day06.getWins()).isEqualTo(288);
    }

    @Test
    void solvesPart2Example() {
        Day06 day06 = new Day06(new long[] {71530}, new long[]{940200});
        assertThat(day06.part2()).isEqualTo(71503);
    }
}
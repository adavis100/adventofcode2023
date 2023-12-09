package adavis100.day09;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day09Test {
    String in = "0 3 6 9 12 15\n" +
            "1 3 6 10 15 21\n" +
            "10 13 16 21 30 45";

    @Test
    void solvesExample1() {
        Day09 day09 = new Day09();
        assertThat(day09.part1(in)).isEqualTo(114);
    }

    @Test
    void getsNextVal() {
        Day09 day09 = new Day09();
        assertThat(day09.getNextVal(new int[] {10, 13, 16, 21, 30, 45})).isEqualTo(68);
    }

    @Test
    void getsPrevVal() {
        Day09 day09 = new Day09();
        assertThat(day09.getPrevVal(new int[] {10, 13, 16, 21, 30, 45})).isEqualTo(5);
    }

    @Test
    void solvesExample2() {
        Day09 day09 = new Day09();
        assertThat(day09.part2(in)).isEqualTo(2);
    }
}
package adavis100.day10;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day10Test {

    String lines  = "-L|F7\n" +
            "7S-7|\n" +
            "L|7||\n" +
            "-L-J|\n" +
            "L|-JF";

    @Test
    void solvesExample1() {
        Day10 day10 = new Day10(lines);
        assertThat(day10.getSteps()).isEqualTo(4);

    }

    @Test
    void getsStart() {
        Day10 day10 = new Day10(lines);
        assertThat(day10.getStartPos()).isEqualTo(new Day10.Coord(1, 1));
    }

    @Test
    void getsStartNeighbors() {
        Day10 day10 = new Day10(lines);
        assertThat(day10.getNeighbors(new Day10.Coord(1,1))).containsExactlyInAnyOrder(new Day10.Coord(2, 1), new Day10.Coord(1, 2));
    }
}
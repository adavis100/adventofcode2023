package adavis100.day11;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day11Test {
    String in = "...#......\n" +
            ".......#..\n" +
            "#.........\n" +
            "..........\n" +
            "......#...\n" +
            ".#........\n" +
            ".........#\n" +
            "..........\n" +
            ".......#..\n" +
            "#...#.....";
    @Test
    void expandsSpace() {
        Day11 day11 = new Day11(in, 2);
        assertThat(day11.grid.length).isEqualTo(10);
        assertThat(day11.grid[0].length).isEqualTo(10);
        assertThat(day11.galaxies.size()).isEqualTo(9);
    }

    @Test
    void getsShortestPathLengths() {
        Day11 day11 = new Day11(in, 2);
        assertThat(day11.addShortestPathLengths()).isEqualTo(374);
    }
}
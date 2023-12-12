package adavis100.day10;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day10Test {

    String lines  =
            "-L|F7\n" +
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
    void replacesStart() {
        Day10 day10 = new Day10(lines);
        assertThat(day10.grid[1][1]).isEqualTo('F');
    }

    @Test
    void getsStartNeighbors() {
        Day10 day10 = new Day10(lines);
        assertThat(day10.getNeighbors(new Day10.Coord(1,1))).containsExactlyInAnyOrder(new Day10.Coord(2, 1), new Day10.Coord(1, 2));
    }

    @Test
    void solvesPart2Example1() {
        String in = "...........\n" +
                ".S-------7.\n" +
                ".|F-----7|.\n" +
                ".||.....||.\n" +
                ".||.....||.\n" +
                ".|L-7.F-J|.\n" +
                ".|..|.|..|.\n" +
                ".L--J.L--J.\n" +
                "...........";

        Day10 day10 = new Day10(in);
        assertThat(day10.findEnclosedPoints()).isEqualTo(4);
    }

    @Test
    void solvesPart3Example2() {
        String in =".F----7F7F7F7F-7....\n" +
                ".|F--7||||||||FJ....\n" +
                ".||.FJ||||||||L7....\n" +
                "FJL7L7LJLJ||LJ.L-7..\n" +
                "L--J.L7...LJS7F-7L7.\n" +
                "....F-J..F7FJ|L7L7L7\n" +
                "....L7.F7||L7|.L7L7|\n" +
                ".....|FJLJ|FJ|F7|.LJ\n" +
                "....FJL-7.||.||||...\n" +
                "....L---J.LJ.LJLJ...";
        Day10 day10 = new Day10(in);
        assertThat(day10.findEnclosedPoints()).isEqualTo(8);
    }

    @Test
    void solvesPart2Example3() {
        String in = "FF7FSF7F7F7F7F7F---7\n" +
                "L|LJ||||||||||||F--J\n" +
                "FL-7LJLJ||||||LJL-77\n" +
                "F--JF--7||LJLJ7F7FJ-\n" +
                "L---JF-JLJ.||-FJLJJ7\n" +
                "|F|F-JF---7F7-L7L|7|\n" +
                "|FFJF7L7F-JF7|JL---7\n" +
                "7-L-JL7||F7|L7F-7F7|\n" +
                "L.L7LFJ|||||FJL7||LJ\n" +
                "L7JLJL-JLJLJL--JLJ.L";
        Day10 day10 = new Day10(in);
        assertThat(day10.findEnclosedPoints()).isEqualTo(10);
    }
}
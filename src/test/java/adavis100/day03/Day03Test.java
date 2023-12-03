package adavis100.day03;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day03Test {
    String in = "467..114..\n" +
            "...*......\n" +
            "..35..633.\n" +
            "......#...\n" +
            "617*......\n" +
            ".....+.58.\n" +
            "..592.....\n" +
            "......755.\n" +
            "...$.*....\n" +
            ".664.598..";

    @Test
    void solvesPart1Example() {
        Day03 day03 = new Day03();
        assertThat(day03.part1(in)).isEqualTo(4361);
    }

    @Test
    void solvesPart2Example() {
        Day03 day03 = new Day03();
        assertThat(day03.part2(in)).isEqualTo(467835);
    }

    @Test
    void getsNumsAdjacentToStar() {
        Day03 day03 = new Day03();
        var grid = new char[][]{"..123*456...".toCharArray(),
                "...234555...".toCharArray()}; // directly below
        assertThat(day03.getStarAdjacentNums(grid, 0, 5)).hasSize(3);
        assertThat(day03.getStarAdjacentNums(grid, 0, 5).get(0)).isEqualTo(123);
        assertThat(day03.getStarAdjacentNums(grid, 0, 5).get(1)).isEqualTo(456);
        assertThat(day03.getStarAdjacentNums(grid, 0, 5).get(2)).isEqualTo(234555);

        grid = new char[][]{"..123*456...".toCharArray(),
                "...23.555...".toCharArray()}; // two numbers below
        assertThat(day03.getStarAdjacentNums(grid, 0, 5)).hasSize(4);
        assertThat(day03.getStarAdjacentNums(grid, 0, 5).get(0)).isEqualTo(123);
        assertThat(day03.getStarAdjacentNums(grid, 0, 5).get(1)).isEqualTo(456);
        assertThat(day03.getStarAdjacentNums(grid, 0, 5).get(2)).isEqualTo(23);
        assertThat(day03.getStarAdjacentNums(grid, 0, 5).get(3)).isEqualTo(555);

        grid = new char[][]{"..123456...".toCharArray(),
                ".....*......".toCharArray()}; // directly above
        assertThat(day03.getStarAdjacentNums(grid, 1, 5)).hasSize(1);
        assertThat(day03.getStarAdjacentNums(grid, 1, 5).get(0)).isEqualTo(123456);

        grid = new char[][]{"..123.56...".toCharArray(),
                ".....*......".toCharArray()}; // directly above
        assertThat(day03.getStarAdjacentNums(grid, 1, 5)).hasSize(2);
        assertThat(day03.getStarAdjacentNums(grid, 1, 5).get(0)).isEqualTo(123);
        assertThat(day03.getStarAdjacentNums(grid, 1, 5).get(1)).isEqualTo(56);

        grid = new char[][]{"467..114..".toCharArray(),
                            "...*......".toCharArray(),
                            "..35..633.".toCharArray()};
        assertThat(day03.getStarAdjacentNums(grid, 1, 3)).hasSize(2);
        assertThat(day03.getStarAdjacentNums(grid, 1, 3).get(0)).isEqualTo(35);
        assertThat(day03.getStarAdjacentNums(grid, 1, 3).get(1)).isEqualTo(467);
    }
}
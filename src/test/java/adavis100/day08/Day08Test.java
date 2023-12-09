package adavis100.day08;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day08Test {
    @Test
    void solvesExample1() {
        Day08 day08 = new Day08("RL\n" +
                "\n" +
                "AAA = (BBB, CCC)\n" +
                "BBB = (DDD, EEE)\n" +
                "CCC = (ZZZ, GGG)\n" +
                "DDD = (DDD, DDD)\n" +
                "EEE = (EEE, EEE)\n" +
                "GGG = (GGG, GGG)\n" +
                "ZZZ = (ZZZ, ZZZ)");
        assertThat(day08.countMovesFrom("AAA", "ZZZ")).isEqualTo(2);
    }


    @Test
    void solvesExample2() {
        Day08 day08 = new Day08("LLR\n" +
                "\n" +
                "AAA = (BBB, BBB)\n" +
                "BBB = (AAA, ZZZ)\n" +
                "ZZZ = (ZZZ, ZZZ)");
        assertThat(day08.countMovesFrom("AAA", "ZZZ")).isEqualTo(6);
    }

    @Test
    void solvesPart2Example() {
        Day08 day08 = new Day08("LR\n" +
                "\n" +
                "11A = (11B, XXX)\n" +
                "11B = (XXX, 11Z)\n" +
                "11Z = (11B, XXX)\n" +
                "22A = (22B, XXX)\n" +
                "22B = (22C, 22C)\n" +
                "22C = (22Z, 22Z)\n" +
                "22Z = (22B, 22B)\n" +
                "XXX = (XXX, XXX)");
        assertThat(day08.part2()).isEqualTo(6);
    }
}
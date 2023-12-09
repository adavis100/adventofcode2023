package adavis100.day07;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day07Test {
    String in = "32T3K 765\n" +
            "T55J5 684\n" +
            "KK677 28\n" +
            "KTJJT 220\n" +
            "QQQJA 483";

    @Test
    void solvesExample1() {
        Day07 day07 = new Day07(false);
        assertThat(day07.calculateWinnings(in)).isEqualTo(6440);
    }

    @Test
    void ranksExampleCards() {
        Day07 day07 = new Day07(false);
        var hands = List.of("32T3K", "T55J5", "KK677", "KTJJT", "QQQJA");
        assertThat(day07.orderHands(hands)).isEqualTo(List.of("32T3K", "KTJJT", "KK677", "T55J5", "QQQJA"));
    }

    @Test
    void solvesExample2() {
        Day07 day07 = new Day07(true);
        assertThat(day07.calculateWinnings(in)).isEqualTo(5905);
    }

    @Test
    void ranksCardsAccordingToRules() {
        Day07 day07 = new Day07(true);
        assertThat(day07.orderHands(List.of("KKKKK", "AAAAA"))).isEqualTo(List.of("KKKKK", "AAAAA"));
        assertThat(day07.orderHands(List.of("KKKJK", "QQQQQ"))).isEqualTo(List.of("KKKJK", "QQQQQ"));
        assertThat(day07.orderHands(List.of("KKTJK", "QQKQQ"))).isEqualTo(List.of("KKTJK", "QQKQQ"));
        assertThat(day07.orderHands(List.of("KKTJK", "QKKQQ"))).isEqualTo(List.of("KKTJK", "QKKQQ"));
        assertThat(day07.orderHands(List.of("KQTJK", "QKKQA"))).isEqualTo(List.of("KQTJK", "QKKQA"));
    }

    @Test
    void findsCardsWithJoker() {
        Day07 day07 = new Day07(true);
        assertThat(day07.has2Pair("AAKQJ")).isTrue();
        assertThat(day07.hasNOfAKind("Q2KJJ", 4)).isFalse();
        assertThat(day07.hasNOfAKind("42434", 3)).isTrue();
        assertThat(day07.hasFullHouse("Q2Q2Q")).isTrue();
    }
}
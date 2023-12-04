package adavis100.day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day04 {
    public static void main(String[] args) throws IOException {
        Day04 day04 = new Day04();
        System.out.println("part 1: " + day04.part1(day04.loadInputFile()));
        System.out.println("part 2: " + day04.part2(day04.loadInputFile()));
    }

    String loadInputFile() throws IOException {
        return Files.readString(Path.of("src/main/resources/day04.txt"));
    }

    public int part1(String lines) {
        int total = 0;
        for (var card: lines.split("\\n")) {
            total += (int) Math.pow(2, getMatches(card) - 1);
        }
        return total;
    }

    public int part2(String in) {
        String[] cards = in.split("\\n");
        int[] matchCounts = new int[cards.length];
        Arrays.fill(matchCounts, 1);
        for (int i = 0; i < cards.length; i++) {
            var matches = getMatches(cards[i]);
            int copies = matchCounts[i];
            for (int j = i + 1; j < Math.min(i + matches + 1, cards.length); j++) {
                matchCounts[j] += copies;
            }
        }
        return IntStream.of(matchCounts).sum();
    }

    long getMatches(String card) {
        card = card.replaceAll("Card\\s+\\d+: ", "");
        var winningNums = Arrays.stream(card.split("\\|")[0].
                        trim().
                        split("\\s+")).
                map(Integer::parseInt).
                collect(Collectors.toSet());
        return Arrays.stream(card.split("\\|")[1].trim().split("\\s+")).
                map(Integer::parseInt).
                filter(winningNums::contains).
                count();
    }
}

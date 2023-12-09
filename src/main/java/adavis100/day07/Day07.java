package adavis100.day07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Day07 {

    private final HashMap<Character, Integer> ranks;
    private final boolean joker;

    public Day07(boolean withJoker) {
        joker = withJoker;
        ranks = new HashMap<>();

        ranks.put('A', 14);
        ranks.put('K', 13);
        ranks.put('Q', 12);
        ranks.put('J', 11);
        ranks.put('T', 10);
        ranks.put('9', 9);
        ranks.put('8', 8);
        ranks.put('7', 7);
        ranks.put('6', 6);
        ranks.put('5', 5);
        ranks.put('4', 4);
        ranks.put('3', 3);
        ranks.put('2', 2);
        if (withJoker) {
            ranks.put('J', 1);
        }
    }

    public static void main(String[] args) throws IOException {
        Day07 day07 = new Day07(false);
        System.out.println("part 1: " + day07.calculateWinnings(day07.loadInputFile()));
        day07 = new Day07(true);
        System.out.println("part 2: " + day07.calculateWinnings(day07.loadInputFile()));
    }

    String loadInputFile() throws IOException {
        return Files.readString(Path.of("src/main/resources/day07.txt"));
    }

    public int calculateWinnings(String lines) {
        var total = 0;
        List<String> hands = new ArrayList<>();
        var bids = new HashMap<String, Integer>();
        for (var line : lines.split("\n")) {
            var card = line.split(" ")[0];
            hands.add(card);
            bids.put(card, Integer.parseInt(line.split(" ")[1]));
        }
        hands = orderHands(hands);
        for (int i = 0; i < hands.size(); i++) {
            total += (i+1) * bids.get(hands.get(i));
        }
        return total;
    }

    public List<String> orderHands(List<String> hands) {
        return hands.stream().sorted(this::compareCards).collect(Collectors.toList());
    }

    private int compareCards(String c1, String c2) {
        if (hasNOfAKind(c1,5) && hasNOfAKind(c2,5)) {
            return cardWiseCmp(c1, c2);
        } else if (hasNOfAKind(c1,5)) {
            return 1;
        } else if (hasNOfAKind(c2, 5)) {
            return -1;
        } else if (hasNOfAKind(c1,4) && hasNOfAKind(c2,4)) {
            return cardWiseCmp(c1, c2);
        } else if (hasNOfAKind(c1,4)) {
            return 1;
        } else if (hasNOfAKind(c2, 4)) {
            return -1;
        } else if (hasFullHouse(c1) && hasFullHouse(c2)) {
            return cardWiseCmp(c1, c2);
        } else if (hasFullHouse(c1)) {
            return 1;
        } else if (hasFullHouse(c2)) {
            return -1;
        } else if (hasNOfAKind(c1,3) && hasNOfAKind(c2,3)) {
            return cardWiseCmp(c1, c2);
        } else if (hasNOfAKind(c1,3)) {
            return 1;
        } else if (hasNOfAKind(c2, 3)) {
            return -1;
        } else if (has2Pair(c1) && has2Pair(c2)) {
            return cardWiseCmp(c1, c2);
        } else if (has2Pair(c1)) {
            return 1;
        } else if (has2Pair(c2)) {
            return -1;
        } else if (hasNOfAKind(c1,2) && hasNOfAKind(c2,2)) {
            return cardWiseCmp(c1, c2);
        } else if (hasNOfAKind(c1,2)) {
            return 1;
        } else if (hasNOfAKind(c2, 2)) {
            return -1;
        } else {
            return cardWiseCmp(c1, c2);
        }
    }

    boolean has2Pair(String card) {
        var counts = getCounts(card);
        int pairs = 0;
        for (int i = 0; i < 15; i++) {
            if (counts[i] == 2) {
                pairs++;
            }
        }
        return pairs == 2 || (pairs == 1 && joker && counts[ranks.get('J')] == 1);
    }

    boolean hasFullHouse(String card) {
        var counts = getCounts(card);
        boolean has3 = false;
        int threePos = 0;
        for (int i = 0; i < 15; i++) {
            if (counts[i] == 3 || (joker && i != ranks.get('J') && counts[i] + counts[ranks.get('J')] == 3)) {
                threePos = i;
                has3 = true;
                break;
            }
        }
        if (has3) {
            for (int i = 0; i < 15; i++) {
                if (i != threePos && i != ranks.get('J') && counts[i] == 2) {
                    return true;
                }
            }
        }

        return false;
    }

    private int cardWiseCmp(String card1, String card2) {
        for (int i = 0; i < 5; i++) {
            char c1 = card1.charAt(i);
            char c2 = card2.charAt(i);
            if (c1 != c2) {
                return ranks.get(c1) - ranks.get(c2);
            }
        }
        return 0;
    }

    boolean hasNOfAKind(String card, int n) {
        var counts = getCounts(card);
        for (int i = 0; i < 15; i++) {
            if (counts[i] == n || (joker && i != ranks.get('J') && counts[i] + counts[ranks.get('J')] == n)) {
                return true;
            }
        }
        return false;
    }

    private int[] getCounts(String card) {
        var counts = new int[15];
        for (char c: card.toCharArray()) {
            counts[ranks.get(c)]++;
        }
        return counts;
    }
}

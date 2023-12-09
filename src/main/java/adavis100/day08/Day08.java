package adavis100.day08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day08 {

    class Node {
        String l;
        String r;

        public Node(String l, String r) {
            this.l = l;
            this.r = r;
        }
    }

    String moves;
    Map<String, Node> map = new HashMap<>();

    Day08(String in) {
        var lines = in.split("\\n");
        moves = lines[0];

        for (int i = 2; i < lines.length; i++) {
            var key = lines[i].split(" = ")[0];
            var neighbors = lines[i].split(" = ")[1].replaceAll("\\(", "").
                    replaceAll("\\)", "").split(", ");
            map.put(key, new Node(neighbors[0], neighbors[1]));
        }
    }

    public static void main(String[] args) throws IOException {
        Day08 day08 = new Day08(loadInputFile());
        System.out.println("part 1: " + day08.countMovesFrom("AAA", "ZZZ"));
        System.out.println("part 2: " + day08.part2());
    }

    static String loadInputFile() throws IOException {
        return Files.readString(Path.of("src/main/resources/day08.txt"));
    }

    public int countMovesFrom(String pos, String to) {
        var moveCount = 0;
        while (true) {
            for (char c : moves.toCharArray()) {
                var node = map.get(pos);
                if (c == 'L') {
                    pos = node.l;
                } else if (c == 'R') {
                    pos = node.r;
                }
                moveCount++;
                if (pos.endsWith(to)) {
                    return moveCount;
                }
            }
        }
    }

    public long part2() {
        List<String> positions = getStartPositions(map);
        int[] counts = new int[positions.size()];
        for (int i = 0; i < positions.size(); i++) {
            counts[i] = countMovesFrom(positions.get(i), "Z");
        }
        long moves = 1;
        for (int count : counts) {
            moves = lcm(moves, count);
        }
        return moves;
    }

    static long gcd(long a, long b)
    {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    static long lcm(long a, long b)
    {
        return (a / gcd(a, b)) * b;
    }

    private List<String> getStartPositions(Map<String, Node> map) {
        List<String> starts = new ArrayList<>();
        for (String key : map.keySet()) {
            if (key.endsWith("A")) {
                starts.add(key);
            }
        }
        return starts;
    }
}

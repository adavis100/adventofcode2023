package adavis100.day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Day02 {
    public static void main(String[] args) throws IOException {
        Day02 day02 = new Day02();
        System.out.println("part 1: " + day02.part1(day02.loadInputFile()));
        System.out.println("part 2: " + day02.part2(day02.loadInputFile()));
    }

    String loadInputFile() throws IOException {
        return Files.readString(Path.of("src/main/resources/day02.txt"));
    }

    public int part1(String lines) {
        var total = 0;
        for (var line : lines.split("\n")) {
            var id = parseId(line);

            var valid = true;
            for (String subGame : getSubGames(line)) {
                var count = getCount(subGame);
                if (count.get("red") > 12 || count.get("green") > 13 || count.get("blue") > 14) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                total += id;
            }
        }
        return total;
    }

    private Map<String, Integer> getCount(String s) {
        var map = new HashMap<String, Integer>();
        map.put("red", 0);
        map.put("green", 0);
        map.put("blue", 0);
        for (String colorCount : s.split(",")) {
            colorCount = colorCount.trim();
            int count = Integer.parseInt(colorCount.split(" ")[0]);
            String color = colorCount.split(" ")[1];
            map.put(color, count);
        }
        return map;
    }

    private int parseId(String s) {
        return Integer.parseInt(s.substring(0, s.indexOf(":")).replaceAll("Game ", ""));
    }

    private String[] getSubGames(String s) {
        return s.substring(s.indexOf(":") + 1).split(";");
    }

    public int part2(String lines) {
        var total = 0;
        for (var line : lines.split("\n")) {
            int minRed = 0;
            int minGreen = 0;
            int minBlue = 0;
            for (String subset : getSubGames(line)) {
                var count = getCount(subset);
                minRed = Math.max(minRed, count.get("red"));
                minGreen = Math.max(minGreen, count.get("green"));
                minBlue = Math.max(minBlue, count.get("blue"));
            }
            total += minRed * minGreen * minBlue;
        }
        return total;
    }
}

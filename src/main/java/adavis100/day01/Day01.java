package adavis100.day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Day01 {
    public static void main(String[] args) throws IOException {
        Day01 day01 = new Day01();
        System.out.println("part 1: " + day01.part1(day01.loadInputFile()));
        System.out.println("part 2: " + day01.part2(day01.loadInputFile()));
    }

    String loadInputFile() throws IOException {
        return Files.readString(Path.of("src/main/resources/day01.txt"));
    }

    public int part1(String lines) {
        var total = 0;
        for (var line: lines.split("\n")) {
            total += addFirstAndLastDigit(line);
        }
        return total;
    }

    private int addFirstAndLastDigit(String line) {
        int first, last;
        for (first = 0; !Character.isDigit(line.charAt(first));) {
            first++;
        }
        for (last = line.length() - 1; !Character.isDigit(line.charAt(last));) {
            last--;
        }
        String num = "" + line.charAt(first) + line.charAt(last);
        return Integer.parseInt(num);
    }

    public String convertSpelledDigits(String s) {
        return s.replaceAll("one", "onee").
                replaceAll("two", "twoo").
                replaceAll("three", "threee").
                replaceAll("five", "fivee").
                replaceAll("seven", "sevenn").
                replaceAll("eight", "eightt").
                replaceAll("nine", "ninee").
                replaceAll("one", "1").
                replaceAll("two","2").
                replaceAll("three", "3").
                replaceAll("four", "4").
                replaceAll("five", "5").
                replaceAll("six", "6").
                replaceAll("seven", "7").
                replaceAll("eight", "8").
                replaceAll("nine", "9");
    }

    public int part2(String lines) {
        return part1(convertSpelledDigits(lines));
    }
}

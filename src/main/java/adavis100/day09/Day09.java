package adavis100.day09;

import adavis100.day09.Day09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Day09 {
    public static void main(String[] args) throws IOException {
        Day09 day09 = new Day09();
        System.out.println("part 1: " + day09.part1(day09.loadInputFile()));
        System.out.println("part 2: " + day09.part2(day09.loadInputFile()));
    }

    String loadInputFile() throws IOException {
        return Files.readString(Path.of("src/main/resources/day09.txt"));
    }


    public int part1(String s) {
        int total = 0;
        for (String line : s.split("\\n")) {
            int [] history = Arrays.stream(line.split("\\s+")).map(Integer::parseInt).mapToInt(x -> x).toArray();
            total += getNextVal(history);
        }
        return total;
    }

    public int getNextVal(int []history) {
        int len = history.length;
        int[] lastVals = new int[len];
        int lastIndex = 0;
        while (!allZeros(history, len)) {
            lastVals[lastIndex++] = history[len- 1];
            for (int i = 0; i < len - 1; i++) {
                history[i] = history[i + 1] - history[i];
            }
            len--;
        }

        int nextVal = 0;
        for (int i = 0; i < lastIndex; i++) {
            nextVal += lastVals[i];
        }
        return nextVal;
    }

    private boolean allZeros(int[] history, int len) {
        return Arrays.stream(history).limit(len).allMatch(i -> i==0);
    }

    public int getPrevVal(int[] history) {
        int len = history.length;
        int[] firstVals = new int[len];
        int lastIndex = 0;
        while (!allZeros(history, len)) {
            firstVals[lastIndex++] = history[0];
            for (int i = 0; i < len - 1; i++) {
                history[i] = history[i + 1] - history[i];
            }
            len--;
        }

        int nextVal = 0;
        for (int i = lastIndex - 1; i >= 0; i--) {
            nextVal = firstVals[i] - nextVal;
        }
        return nextVal;
    }

    public int part2(String s) {
        int total = 0;
        for (String line : s.split("\\n")) {
            int [] history = Arrays.stream(line.split("\\s+")).map(Integer::parseInt).mapToInt(x -> x).toArray();
            total += getPrevVal(history);
        }
        return total;
    }
}

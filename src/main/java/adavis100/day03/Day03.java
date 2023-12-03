package adavis100.day03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day03 {
    public static void main(String[] args) throws IOException {
        Day03 day03 = new Day03();
        System.out.println("part 1: " + day03.part1(day03.loadInputFile()));
        System.out.println("part 2: " + day03.part2(day03.loadInputFile()));
    }

    String loadInputFile() throws IOException {
        return Files.readString(Path.of("src/main/resources/day03.txt"));
    }

    public int part1(String lines) {
        var grid = makeGrid(lines);
        var cleaned = removeNonPartNumbers(grid);
        return countNums(cleaned);
    }

    private char[][] removeNonPartNumbers(char[][] grid) {
        boolean inNum = false;
        boolean isPart = false;
        int numStart = 0;
        for (int i = 0; i < grid.length; i++) {
            char[] chars = grid[i];
            for (int j = 0; j < grid[i].length; j++) {
                if (Character.isDigit(chars[j]) && !inNum) {
                    inNum = true;
                    numStart = j;
                }
                if (Character.isDigit(chars[j]) && !isPart) {
                    isPart = isAdjacentToSymbol(grid, i, j);
                }
                if (inNum && !isPart && (j == grid[i].length - 1 || !Character.isDigit(chars[j + 1]))) {
                    eraseDigits(chars, numStart, j);
                    inNum = false;
                } else if (j == grid[i].length - 1 || !Character.isDigit(chars[j + 1])) {
                    inNum = false;
                    isPart = false;
                }
            }
            grid[i] = chars;
        }
        return grid;
    }

    private void eraseDigits(char[] chars, int start, int end) {
        for (int i = start; i <= end; i++) {
            chars[i] = '.';
        }
    }

    private boolean isAdjacentToSymbol(char[][] grid, int i, int j) {
        var adjacent = false;
        var cur = grid[i];
        adjacent = (j > 0 && isSymbol(cur[j - 1])) || ((j < (grid[i].length - 1)) && isSymbol(cur[j + 1]));
        if (i > 0 && i < grid.length - 1) { // middle
            var above = grid[i - 1];
            var below = grid[i + 1];
            adjacent = adjacent || ((j > 0 && isSymbol(above[j - 1])) || isSymbol(above[j]) || ((j < (grid[i].length - 1)) && isSymbol(above[j + 1])) ||
                    (j > 0 && isSymbol(below[j - 1])) || isSymbol(below[j]) || ((j < (grid[i].length - 1)) && isSymbol(below[j + 1])));
        } else if (i == 0) { // first row
            var below = grid[i + 1];
            adjacent = adjacent || (j > 0 && isSymbol(below[j - 1])) || isSymbol(below[j]) || ((j < (grid[i].length - 1)) && isSymbol(below[j + 1]));
        } else {  // last row
            var above = grid[i - 1];
            adjacent = adjacent || ((j > 0 && isSymbol(above[j - 1])) || isSymbol(above[j]) || ((j < (grid[i].length - 1)) && isSymbol(above[j + 1])));
        }
        return adjacent;
    }

    private boolean isSymbol(char c) {
        return !Character.isDigit(c) && c != '.';
    }

    private int countNums(char[][] cleaned) {
        int total = 0;
        for (char[] line : cleaned) {
            var nums = new String(line).replaceAll("\\D", " ").
                    trim().
                    replaceAll("\\s+", " ").
                    split(" ");
            if (nums.length > 0 && !nums[0].isEmpty()) {
                total += Arrays.stream(nums).
                        map(Integer::parseInt).
                        mapToInt(Integer::intValue).
                        sum();
            }
        }
        return total;
    }

    public int part2(String in) {
        int total = 0;
        var grid = makeGrid(in);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '*') {
                    var nums = getStarAdjacentNums(grid, i, j);
                    if (nums.size() == 2) {
                        total += nums.get(0) * nums.get(1);
                    }
                }
            }
        }
        return total;
    }

    private static char[][] makeGrid(String in) {
        var lines =  in.split("\n");
        char[][] grid = new char[lines.length][lines[0].length()];
        int i = 0;
        for (var line : lines) {
            grid[i++] = line.toCharArray();
        }
        return grid;
    }

    public List<Integer> getStarAdjacentNums(char[][] grid, int i, int j) {
        var nums = new ArrayList<Integer>();
        // is there number to left
        if (j > 0 && Character.isDigit(grid[i][j - 1])) {
            int k = j - 1;
            StringBuilder sb = new StringBuilder();
            while (k > 0 && Character.isDigit(grid[i][k])) {
                sb.append(grid[i][k]);
                k--;
            }
            nums.add(Integer.parseInt(sb.reverse().toString()));
        }
        // is there number to right
        if (j < grid[i].length - 1 && Character.isDigit(grid[i][j + 1])) {
            int k = j + 1;
            StringBuilder sb = new StringBuilder();
            while (k < grid[i].length && Character.isDigit(grid[i][k])) {
                sb.append(grid[i][k]);
                k++;
            }
            nums.add(Integer.parseInt(sb.toString()));
        }

        if (i < grid.length - 1) { // are there numbers below
            if (Character.isDigit(grid[i + 1][j])) { // directly below
                int start = j;
                while (start >= 0 && Character.isDigit(grid[i + 1][start])) {
                    start--;
                }
                start++;
                int end = j;
                while (end < grid[i + 1].length && Character.isDigit(grid[i + 1][end])) {
                    end++;
                }
                StringBuilder sb = new StringBuilder();
                for (int k = start; k < end; k++) {
                    sb.append(grid[i + 1][k]);
                }
                nums.add(Integer.parseInt(sb.toString()));
            } else {
                if (j > 0 && Character.isDigit(grid[i + 1][j - 1])) { // down and to the left
                    int start = j - 1;
                    while (start >= 0 && Character.isDigit(grid[i + 1][start])) {
                        start--;
                    }
                    start++;
                    StringBuilder sb = new StringBuilder();
                    for (int k = start; k < j; k++) {
                        sb.append(grid[i + 1][k]);
                    }
                    nums.add(Integer.parseInt(sb.toString()));
                }
                if (j < grid[i + 1].length - 1 && Character.isDigit(grid[i + 1][j + 1])) { // down and to the right
                    int start = j + 1;
                    int end = j + 1;
                    while (end < grid[i + 1].length - 1 && Character.isDigit(grid[i + 1][end])) {
                        end++;
                    }
                    StringBuilder sb = new StringBuilder();
                    for (int k = start; k < end; k++) {
                        sb.append(grid[i + 1][k]);
                    }
                    nums.add(Integer.parseInt(sb.toString()));
                }
            }
        }
        if (i > 0) { // are there numbers above
            if (Character.isDigit(grid[i - 1][j])) { // directly above
                int start = j;
                while (start >= 0 && Character.isDigit(grid[i - 1][start])) {
                    start--;
                }
                start++;
                int end = j;
                while (end < grid[i - 1].length && Character.isDigit(grid[i - 1][end])) {
                    end++;
                }
                StringBuilder sb = new StringBuilder();
                for (int k = start; k < end; k++) {
                    sb.append(grid[i - 1][k]);
                }
                nums.add(Integer.parseInt(sb.toString()));
            } else {
                if (j > 0 && Character.isDigit(grid[i - 1][j - 1])) { // up and to the left
                    int start = j - 1;
                    while (start >= 0 && Character.isDigit(grid[i - 1][start])) {
                        start--;
                    }
                    start++;
                    StringBuilder sb = new StringBuilder();
                    for (int k = start; k < j; k++) {
                        sb.append(grid[i - 1][k]);
                    }
                    nums.add(Integer.parseInt(sb.toString()));
                }
                if (j < grid[i - 1].length - 1 && Character.isDigit(grid[i - 1][j + 1])) { // up and to the right
                    int start = j + 1;
                    int end = j + 1;
                    while (end < grid[i - 1].length - 1 && Character.isDigit(grid[i - 1][end])) {
                        end++;
                    }
                    StringBuilder sb = new StringBuilder();
                    for (int k = start; k < end; k++) {
                        sb.append(grid[i - 1][k]);
                    }
                    nums.add(Integer.parseInt(sb.toString()));
                }
            }
        }

        return nums;
    }
}

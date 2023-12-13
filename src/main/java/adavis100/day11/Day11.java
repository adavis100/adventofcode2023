package adavis100.day11;

import adavis100.day11.Day11;
import adavis100.util.Coord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day11 {
    char[][] grid;
    List<Coord> galaxies = new ArrayList<>();
    int expansion;
    public Day11(String in, int expansion) {
        grid = loadGrid(in);
        grid = expandSpace(grid);
        galaxies = findGalaxies(grid);
        this.expansion = expansion;
    }


    private char[][] expandSpace(char[][] grid) {
        var emptyRows = getEmptyRows(grid);
        var emptyCols = getEmptyCols(grid);
        for (int row = 0; row < grid.length; row++) {
            if (emptyRows.contains(row)) {
                Arrays.fill(grid[row], '+');
            } else {
                for (int col = 0; col < grid[row].length; col++) {
                    if (emptyCols.contains(col)) {
                        grid[row][col] = '+';
                    }
                }
            }
        }
        return grid;
    }

    private Set<Integer> getEmptyRows(char[][] grid) {
        Set<Integer> emptyRows = new HashSet<>();
        for (int row = 0; row < grid.length; row++) {
            if (new String(grid[row]).chars().mapToObj(c->(char)c).allMatch(c -> c=='.')) {
                emptyRows.add(row);
            }
        }
        return emptyRows;
    }

    private Set<Integer> getEmptyCols(char[][] grid) {
        Set<Integer> emptyCols = new HashSet<>();
        for (int col = 0; col < grid[0].length; col++) {
            boolean allEmpty = true;
            for (int row = 0; row < grid.length; row++) {
                if (grid[row][col] != '.') {
                    allEmpty = false;
                    break;
                }
            }
            if (allEmpty) {
                emptyCols.add(col);
            }
        }
        return emptyCols;
    }

    private List<Coord> findGalaxies(char[][] grid) {
        List<Coord> galaxies = new ArrayList<>();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == '#') {
                    galaxies.add(new Coord(row, col));
                }
            }
        }
        return galaxies;
    }

    private char[][] loadGrid(String in) {
        String[] lines = in.split("\\n");
        char[][] grid = new char[lines.length][lines[0].length()];
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[0].length(); j++) {
                grid[i][j] = lines[i].charAt(j);
            }
        }
        return grid;
    }

    public static void main(String[] args) throws IOException {
        Day11 day11 = new Day11(loadInputFile(), 2);
        System.out.println("part 1: " + day11.addShortestPathLengths());
        day11 = new Day11(loadInputFile(), 1000000);
        System.out.println("part 2: " + day11.addShortestPathLengths());
    }

    public long  addShortestPathLengths() {
        long sum = 0;
        for (int i = 0; i < galaxies.size() - 1; i++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                sum += dist(galaxies.get(i), galaxies.get(j));
            }
        }
        return sum;
    }

    private int dist(Coord a, Coord b) {
        if (a.row > b.row) {
            Coord tmp = a;
            a = b;
            b = tmp;
        }
        int vertDist = 0;
        for (int i = a.row; i < b.row; i++) {
            if (grid[i][a.col] == '+') {
                vertDist += expansion;
            } else {
                vertDist++;
            }
        }
        if (a.col > b.col) {
            Coord tmp = a;
            a = b;
            b = tmp;
        }
        int horizDist = 0;
        for (int i = a.col; i < b.col; i++) {
            if (grid[a.row][i] == '+') {
                horizDist += expansion;
            } else {
                horizDist++;
            }
        }
        return vertDist + horizDist;
    }

    static String loadInputFile() throws IOException {
        return Files.readString(Path.of("src/main/resources/day11.txt"));
    }
}

package adavis100.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day10 {
    char[][] grid;

    public Day10(String in) {
        var lines = in.split("\\n");
        grid = new char[lines.length][lines[0].length()];
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length(); j++) {
                grid[i][j] = lines[i].charAt(j);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Day10 day10 = new Day10(loadInputFile());
        System.out.println("part 1: " + day10.getSteps());
//        System.out.println("part 2: " + day10.part2(day10.loadInputFile()));
    }

    public Coord getStartPos() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 'S') {
                    return new Coord(i, j);
                }
            }
        }
        return new Coord(0, 0);
    }

    public List<Coord> getNeighbors(Coord pos) {
        var neighbors = new ArrayList<Coord>();
        if (grid[pos.row][pos.col] == 'S') {
            getStartNeighbors(pos, neighbors);
        } else if (grid[pos.row][pos.col] == '|') {
            if (pos.row > 0) {
                neighbors.add(new Coord(pos.row - 1, pos.col));
            }
            if (pos.row < grid.length - 1) {
                 neighbors.add(new Coord(pos.row + 1, pos.col));
            }
        } else if (grid[pos.row][pos.col] == '-') {
            if (pos.col > 0) {
                neighbors.add(new Coord(pos.row, pos.col - 1));
            }
            if (pos.col < grid[0].length - 1) {
                neighbors.add(new Coord(pos.row, pos.col + 1));
            }
        } else if (grid[pos.row][pos.col] == 'L') {
            if (pos.row > 0) {
                neighbors.add(new Coord(pos.row - 1, pos.col));
            }
            if (pos.col < grid[0].length - 1) {
                neighbors.add(new Coord(pos.row, pos.col + 1));
            }
        } else if (grid[pos.row][pos.col] == 'J') {
            if (pos.row > 0) {
                neighbors.add(new Coord(pos.row - 1, pos.col));
            }
            if (pos.col > 0) {
                neighbors.add(new Coord(pos.row, pos.col - 1));
            }
        } else if (grid[pos.row][pos.col] == '7') {
            if (pos.col > 0) {
                neighbors.add(new Coord(pos.row, pos.col - 1));
            }
            if (pos.row < grid.length - 1) {
                neighbors.add(new Coord(pos.row + 1, pos.col));
            }
        } else if (grid[pos.row][pos.col] == 'F') {
            if (pos.col < grid[0].length - 1) {
                neighbors.add(new Coord(pos.row, pos.col + 1));
            }
            if (pos.row < grid.length - 1) {
                neighbors.add(new Coord(pos.row + 1, pos.col));
            }
        }
        return neighbors;
    }

    private void getStartNeighbors(Coord pos, ArrayList<Coord> neighbors) {
        // above
        if (grid[pos.row - 1][pos.col] == '|' || grid[pos.row - 1][pos.col] == 'F' || grid[pos.row - 1][pos.col] == '7') {
            neighbors.add(new Coord(pos.row - 1, pos.col));
        }

        // left
        if (grid[pos.row][pos.col - 1] == '-' || grid[pos.row][pos.col - 1] == 'L' || grid[pos.row][pos.col - 1] == 'F') {
            neighbors.add(new Coord(pos.row, pos.col - 1));
        }

        // right
        if (grid[pos.row][pos.col + 1] == '-' || grid[pos.row][pos.col + 1] == 'J' || grid[pos.row][pos.col + 1] == '7') {
            neighbors.add(new Coord(pos.row, pos.col + 1));
        }

        // down
        if (grid[pos.row + 1][pos.col] == '|' || grid[pos.row + 1][pos.col] == 'L' || grid[pos.row + 1][pos.col] == 'J') {
            neighbors.add(new Coord(pos.row + 1, pos.col));
        }
    }

    public int getSteps() {
        Coord start = getStartPos();
        Coord marker = new Coord(-1,-1);
        Queue<Coord> queue = new ArrayDeque<>();
        int steps = 0;
        Set<Coord> visited = new HashSet<>();
        queue.add(start);
        queue.add(marker);
        while (!queue.isEmpty()) {
            var cur = queue.poll();
            if (cur.equals(marker)) {
                steps++;
                if (!queue.isEmpty()) {
                    queue.add(marker);
                }
            } else {
                visited.add(cur);
                for (var neighbor: getNeighbors(cur)) {
                    if (!visited.contains(neighbor)) {
                        queue.add(neighbor);
                    }

                }
            }
        }

        return steps - 1;
    }

    static class Coord {
        int row;
        int col;

        public Coord(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coord coord = (Coord) o;
            return row == coord.row && col == coord.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

    static String loadInputFile() throws IOException {
        return Files.readString(Path.of("src/main/resources/day10.txt"));
    }
}

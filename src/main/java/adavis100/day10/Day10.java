package adavis100.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day10 {
    char[][] grid;
    Set<Coord> pathCoords = new HashSet<>();
    Coord startPos;

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

    public Day10(String in) {
        var lines = in.split("\\n");
        grid = new char[lines.length][lines[0].length()];
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length(); j++) {
                grid[i][j] = lines[i].charAt(j);
            }
        }

        startPos = getStartPos();
        replaceS();
        findPath();
    }

    private void replaceS() {
        if (startPos.row > 8 && startPos.row < grid.length - 1 &&
                (grid[startPos.row - 1][startPos.col] == '|' || grid[startPos.row - 1][startPos.col] == '7' || grid[startPos.row - 1][startPos.col] == 'F') &&
                (grid[startPos.row + 1][startPos.col] == '|' || grid[startPos.row + 1][startPos.col] == 'L' || grid[startPos.row + 1][startPos.col] == 'J')) {
            grid[startPos.row][startPos.col] = '|';
        } else if (startPos.col > 0 && startPos.col + 1 < grid[0].length - 1 &&
                (grid[startPos.row][startPos.col - 1] == '-' || grid[startPos.row][startPos.col - 1] == 'L' || grid[startPos.row][startPos.col - 1] == 'F') &&
                (grid[startPos.row][startPos.col + 1] == '-' || grid[startPos.row][startPos.col + 1] == 'J' || grid[startPos.row][startPos.col + 1] == '7')) {
            grid[startPos.row][startPos.col] = '-';
        } else if (startPos.row > 0 && startPos.col + 1 < grid[0].length - 1 &&
                (grid[startPos.row - 1][startPos.col] == '|' || grid[startPos.row - 1][startPos.col] == '7' || grid[startPos.row - 1][startPos.col] == 'F') &&
                (grid[startPos.row][startPos.col + 1] == '-' || grid[startPos.row][startPos.col + 1] == 'J' || grid[startPos.row][startPos.col + 1] == '7')) {
            grid[startPos.row][startPos.col] = 'L';
        } else if (startPos.row > 0 && startPos.col > 0 &&
                (grid[startPos.row - 1][startPos.col] == '|' || grid[startPos.row - 1][startPos.col] == '7' || grid[startPos.row - 1][startPos.col] == 'F') &&
                (grid[startPos.row][startPos.col - 1] == '-' || grid[startPos.row][startPos.col - 1] == 'L' || grid[startPos.row][startPos.col + 1] == 'F')) {
            grid[startPos.row][startPos.col] = 'J';
        } else if (startPos.row < grid.length - 1 && startPos.col > 0 &&
                (grid[startPos.row + 1][startPos.col] == '|' || grid[startPos.row + 1][startPos.col] == 'L' || grid[startPos.row + 1][startPos.col] == 'J') &&
                (grid[startPos.row][startPos.col - 1] == '-' || grid[startPos.row][startPos.col - 1] == 'L' || grid[startPos.row][startPos.col - 1] == 'F')) {
            grid[startPos.row][startPos.col] = '7';
        } else if (startPos.row < grid.length - 1 && startPos.col < grid[0].length - 1 &&
                (grid[startPos.row + 1][startPos.col] == '|' || grid[startPos.row + 1][startPos.col] == 'L' || grid[startPos.row + 1][startPos.col] == 'J') &&
                (grid[startPos.row][startPos.col + 1] == '-' || grid[startPos.row][startPos.col + 1] == 'J' || grid[startPos.row][startPos.col + 1] == '7')) {
            grid[startPos.row][startPos.col] = 'F';
        }
    }

    public static void main(String[] args) throws IOException {
        Day10 day10 = new Day10(loadInputFile());
        System.out.println("part 1: " + day10.getSteps());
        System.out.println("part 2: " + day10.findEnclosedPoints());
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
         if (grid[pos.row][pos.col] == '|') {
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

    public int getSteps() {
        return pathCoords.size() / 2;
    }

    static String loadInputFile() throws IOException {
        return Files.readString(Path.of("src/main/resources/day10.txt"));
    }

    private void findPath() {
        Queue<Coord> queue = new ArrayDeque<>();
        queue.add(startPos);
        pathCoords.add(startPos);

        while (!queue.isEmpty()) {
            var cur = queue.poll();
            List<Coord> neighbors;
            neighbors = getNeighbors(cur);
            pathCoords.add(cur);
            for (var neighbor : neighbors) {
                if (!pathCoords.contains(neighbor)) {
                    queue.add(neighbor);
                }
            }
        }
    }

    public int findEnclosedPoints() {
        int enclosed = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (!pathCoords.contains(new Coord(i, j)) && pathCrossings(i, j) % 2 != 0) {
                    enclosed++;
                    grid[i][j] = 'I';
                }
            }
        }
        return enclosed;
    }

    private int pathCrossings(int row, int col) {
        int crossings = 0;
        for (int i = 0; i < col; i++) {
            if (pathCoords.contains(new Coord(row, i))) {
                if (grid[row][i] == '|') {
                    crossings++;
                } else if (grid[row][i] == 'F') {
                    for (i++; i < col; i++) {
                        if (grid[row][i] == 'J') {
                            crossings++;
                            break;
                        } else if (grid[row][i] == '7') {
                            crossings+=2;
                            break;
                        }
                    }
                } else if(grid[row][i] == 'L') {
                    for (i++; i < col; i++) {
                        if (grid[row][i] == '7') {
                            crossings++;
                            break;
                        } else if (grid[row][i] == 'J') {
                            crossings += 2;
                            break;
                        }
                    }
                }
            }
        }
        return crossings;
    }
}

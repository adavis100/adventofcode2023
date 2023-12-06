package adavis100.day06;

import java.io.IOException;

public class Day06 {
    private final long[] times;
    private final long[] distances;

    public static void main(String[] args) throws IOException {
        long[] times = new long[] {35, 69, 68, 87};
        long[] dists = new long[] {213, 1168, 1086, 1248};
        Day06 day06 = new Day06(times, dists);
        System.out.println("part 1: " + day06.getWins());

        times = new long[] {35696887};
        dists = new long[]{213116810861248L};
        day06 = new Day06(times, dists);
        System.out.println("part 2: " + day06.getWins());
    }

    public Day06(long[] times, long[] distances) {
        this.times = times;
        this.distances = distances;
    }
    public int getWins() {
        int prod = 1;
        for (int i = 0; i < times.length; i++) {
            int wins = 0;
            for (int j = 0; j < times[i]; j++) {
                if (isWin(j, times[i] - j, distances[i])) {
                    wins++;
                }
            }
            prod *= wins;
        }
        return prod;
    }

    private boolean isWin(long speed, long time, long distance) {
        return speed * time > distance;
    }

    public int part2() {
        return getWins();
    }
}

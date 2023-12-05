package adavis100.day05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Day05 {

    static class mapping {
        List<mappingEntry> mappingEntries;

        public mapping() {
            this.mappingEntries = new ArrayList<>();
        }
    }

    static class mappingEntry {
        long source;
        long dest;
        long length;
    }

    List<Long> seeds;
    Map<String, mapping> mappings;

    public Day05(String in) {
        var lines = in.split("\\n");
        seeds = Arrays.stream(lines[0].replaceAll("seeds: ", "").split("\\s"))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        mappings = new HashMap<>();

        String mapKey = "";
        mapping m = new mapping();
        for (int i = 2; i < lines.length; i++) {
            if (lines[i].contains("map")) {
                mapKey = lines[i].replaceAll(" map:", "");
                m = new mapping();
            } else if (lines[i].isEmpty() || i == lines.length - 1) {
                m.mappingEntries.sort((e1, e2) -> Long.compare(e1.source, e2.source));
                mappings.put(mapKey, m);
            } else {
                var e = new mappingEntry();
                e.dest = Long.parseLong(lines[i].split(" ")[0]);
                e.source = Long.parseLong(lines[i].split(" ")[1]);
                e.length = Long.parseLong(lines[i].split(" ")[2]);
                m.mappingEntries.add(e);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Day05 day05 = new Day05(loadInputFile());
        System.out.println("part 1: " + day05.part1());
        System.out.println("part 2: " + day05.part2());
    }

    static String loadInputFile() throws IOException {
        return Files.readString(Path.of("src/main/resources/day05.txt"));
    }

    public long mapSeedToLocation(long s) {
        var keys = List.of("seed-to-soil", "soil-to-fertilizer", "fertilizer-to-water", "water-to-light",
                "light-to-temperature", "temperature-to-humidity", "humidity-to-location");
        long result = s;
        for (String key: keys) {
            result = getMappingValue(key, result);
        }
        return result;
    }

    long getMappingValue(String key, long value) {
        var mapping = mappings.get(key);
        for (var entry: mapping.mappingEntries) {
            if (value < entry.source) {
                return value;
            } else if (value < entry.source + entry.length) {
                return entry.dest + (value - entry.source);
            }
        }
        return value;
    }

    public long part1() {
        return seeds.stream().map(this::mapSeedToLocation).mapToLong(l -> l).min().orElse(0);
    }

    public long part2() {
        long min = Long.MAX_VALUE;
        for (int i = 0; i < seeds.size(); i+=2) {
            long start = seeds.get(i);
            long range = seeds.get(i + 1);
            for (long j = start; j < start + range; j++) {
                min = Math.min(min, mapSeedToLocation(j));
            }
            System.out.println(min);
        }
        return min;
    }
}

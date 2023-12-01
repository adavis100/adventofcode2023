package adavis100.day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day01 {
    public static void main(String[] args) throws IOException {
        Day01 day01 = new Day01();
        System.out.println(day01.loadInputFile());
    }

    String loadInputFile() throws IOException {
        var inStr = Files.readString(Path.of("src/main/resources/day01.txt"));
        return inStr;
    }
}

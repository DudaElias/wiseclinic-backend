package com.unicamp.wiseclinic.adapters.io;

import java.util.Objects;
import java.util.Scanner;

public class ClasspathUtils {

    public static String readFromClasspath(String path) {
        Scanner scanner = new Scanner(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(path)));
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine());
        }
        return sb.toString();
    }
}

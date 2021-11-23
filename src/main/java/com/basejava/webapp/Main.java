package com.basejava.webapp;

import java.io.File;
import java.util.Objects;

public class Main {

    static StringBuilder sep = new StringBuilder();

    public static void main(String[] args) {
        String path = "src/main/java/com/basejava/webapp";
        File file = new File(path);

        if (!file.exists()) {
            System.out.println("Директории не существует");
        } else if (!file.isDirectory()) {
            System.out.println("Не является папкой");
        } else {
            printFileName(file);
        }
    }

    static void printFileName(File dir) {
        for (File f : Objects.requireNonNull(dir.listFiles())) {
            print(f);
            if (f.isDirectory()) {
                sep.append("-");
                printFileName(f);
            }
        }
        //sep.setLength(sep.length() - 1);
        if (sep.length() > 0)
            sep.deleteCharAt(sep.length() - 1);
    }

    static void print(File f) {
        System.out.println(sep + f.getName());
    }
}

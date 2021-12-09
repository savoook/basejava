package com.basejava;

import java.io.File;
import java.util.Objects;

public class ListOfProjectFiles {

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
            System.out.println(sep + f.getName());
            if (f.isDirectory()) {
                sep.append("-");
                printFileName(f);
            }
        }
        if (sep.length() > 0)
            sep.deleteCharAt(sep.length() - 1);
    }
}

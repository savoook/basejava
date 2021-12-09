package com.basejava;

import com.basejava.model.Resume;
import com.basejava.storage.MapResumeStorage;
import com.basejava.storage.Storage;

/**
 * Test for your com.basejava.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    private static final Storage ARRAY_STORAGE = new MapResumeStorage();

    public static void main(String[] args) {
        final Resume r1 = new Resume("uuid2", "Person2");
        final Resume r2 = new Resume("uuid3", "Person3");
        final Resume r3 = new Resume("uuid5", "Person5");
        final Resume r4 = new Resume("uuid1", "Person1");
        final Resume r5 = new Resume("uuid6", "Person6");
        final Resume r6 = new Resume("uuid4", "Person4");
        final Resume r7 = new Resume("uuid2", "Person2");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r4);
        ARRAY_STORAGE.save(r5);
        ARRAY_STORAGE.save(r6);
        //ARRAY_STORAGE.save(r7);
        printAll();

        ARRAY_STORAGE.update(r4);
        printAll();

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Get r4: " + ARRAY_STORAGE.get(r4.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());
        //System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
        printAll();

        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());

        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}

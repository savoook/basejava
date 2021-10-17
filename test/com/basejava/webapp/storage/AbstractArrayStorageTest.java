package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractArrayStorageTest {

    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);

    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_EXIST = new Resume(UUID_3);
    private static final Resume RESUME_NOT_EXIST = new Resume(UUID_4);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void SetUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }



    @Test
    void size() throws Exception {
        assertEquals(3, storage.size());
    }

    @Test
    void clear() throws Exception {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    void getAll() throws Exception {
        Resume[] expected = storage.getAll();
        Resume[] actual = new Resume[]{RESUME_1, RESUME_2, RESUME_3};
        assertArrayEquals(expected, actual);
        assertEquals(3, expected.length);
    }

    @Test
    void get() throws Exception {
        Resume expected1 = storage.get(UUID_1);
        Resume expected2 = storage.get(UUID_2);
        Resume expected3 = storage.get(UUID_3);
        assertEquals(expected1, RESUME_1);
        assertEquals(expected2, RESUME_2);
        assertEquals(expected3, RESUME_3);
    }

    @Test()
    void getNotExist() throws Exception {
        assertThrows(NotExistStorageException.class, () -> {
            storage.get("dummy");
        });
    }

    @Test
    void update() throws Exception {
        storage.update(RESUME_EXIST);
        Resume[] expected = storage.getAll();
        Resume[] actual = new Resume[]{RESUME_1, RESUME_2, RESUME_EXIST};
        assertArrayEquals(expected, actual);
    }

    @Test
    void updateNotExist() throws Exception {
        assertThrows(NotExistStorageException.class, () -> {
            storage.update(RESUME_NOT_EXIST);
        });
    }

    @Test
    void save() throws Exception {
        storage.save(RESUME_NOT_EXIST);
        Resume[] expected = storage.getAll();
        Resume[] actual = new Resume[]{RESUME_1, RESUME_2, RESUME_3, RESUME_NOT_EXIST};
        assertEquals(4, storage.size());
        assertArrayEquals(expected, actual);
    }

    @Test
    void saveExist() throws Exception {
        assertThrows(ExistStorageException.class, () -> {
            storage.save(RESUME_EXIST);
        });
    }

    @Test
    void saveWithOverflow() throws Exception {
        //Arrays.fill(storage, 3, AbstractArrayStorage.STORAGE_LIMIT-1, new Resume());
        //Resume[] resumes = storage.getAll();
        //Arrays.fill(resumes, 3, AbstractArrayStorage.STORAGE_LIMIT-1, new Resume());
        for (int i = 3; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
            storage.save(new Resume());
        }
        assertThrows(StorageException.class, () -> {
            storage.save(RESUME_NOT_EXIST);
        });
    }

    @Test
    void delete() throws Exception {
        Resume[] resumes = storage.getAll();
        Resume resume = resumes[(new Random()).ints(0, storage.size() - 1).iterator().
                nextInt()];
        storage.delete(resume.getUuid());
        assertEquals(2, storage.size());
    }

    @Test
    void deleteNotExist() throws Exception {
        assertThrows(NotExistStorageException.class, () -> {
            storage.delete(RESUME_NOT_EXIST.getUuid());
        });
    }
}
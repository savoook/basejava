package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractStorageTest {

    protected final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final String FullName_1 = "Person1";
    private static final String FullName_2 = "Person2";
    private static final String FullName_3 = "Person3";
    private static final String FullName_4 = "Person4";

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    protected static final Resume RESUME_EXIST;
    protected static final Resume RESUME_NOT_EXIST;

    static {
        RESUME_1 = new Resume(UUID_1, FullName_1);
        RESUME_2 = new Resume(UUID_2,FullName_2);
        RESUME_3 = new Resume(UUID_3,FullName_3);
        RESUME_EXIST = new Resume(UUID_3,FullName_3);
        RESUME_NOT_EXIST = new Resume(UUID_4,FullName_4);
    }

    public AbstractStorageTest(Storage storage) {
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
        Resume[] actual = storage.getAllSorted().toArray(Resume[]::new);
        Resume[] expected = new Resume[]{RESUME_1, RESUME_2, RESUME_3};
        assertArrayEquals(expected, actual);
        assertEquals(3, storage.size());
    }

    @Test
    void get() throws Exception {
        Resume actual = storage.get(UUID_2);
        assertEquals(RESUME_2, actual);
    }

    @Test()
    void getNotExist() throws Exception {
        assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
    }

    @Test
    void update() throws Exception {
        storage.update(RESUME_EXIST);
        Resume[] actual = storage.getAllSorted().toArray(Resume[]::new);
        Resume[] expected = new Resume[]{RESUME_1, RESUME_2, RESUME_EXIST};
        assertArrayEquals(expected, actual);
    }

    @Test
    void updateNotExist() throws Exception {
        assertThrows(NotExistStorageException.class, () -> storage.update(RESUME_NOT_EXIST));
    }

    @Test
    void save() throws Exception {
        storage.save(RESUME_NOT_EXIST);
        Resume[] actual = storage.getAllSorted().toArray(Resume[]::new);
        Resume[] expected = new Resume[]{RESUME_1, RESUME_2, RESUME_3, RESUME_NOT_EXIST};
        assertArrayEquals(expected, actual);
        assertEquals(4, storage.size());
    }

    @Test
    void saveExist() throws Exception {
        assertThrows(ExistStorageException.class, () -> storage.save(RESUME_EXIST));
    }

    @Test
    void delete() throws Exception {
        storage.delete(UUID_1);
        List<Resume> actual = storage.getAllSorted();
        List<Resume> expected = new ArrayList<Resume>(Arrays.asList(RESUME_2, RESUME_3));
        assertEquals(expected, actual);
        assertEquals(2, storage.size());
    }

    @Test
    void deleteNotExist() throws Exception {
        assertThrows(NotExistStorageException.class, () -> storage.delete(UUID_4));
    }
}
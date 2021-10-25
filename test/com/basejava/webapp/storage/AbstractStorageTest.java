package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractStorageTest {

    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_EXIST;
    private static final Resume RESUME_NOT_EXIST;

    static {
        RESUME_1 = new Resume(UUID_1);
        RESUME_2 = new Resume(UUID_2);
        RESUME_3 = new Resume(UUID_3);
        RESUME_EXIST = new Resume(UUID_3);
        RESUME_NOT_EXIST = new Resume(UUID_4);
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
        Resume[] actual = storage.getAll();
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
        assertThrows(NotExistStorageException.class, () -> {
            storage.get("dummy");
        });
    }

    @Test
    void update() throws Exception {
        storage.update(RESUME_EXIST);
        Resume[] actual = storage.getAll();
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
        Resume[] actual = storage.getAll();
        Resume[] expected = new Resume[]{RESUME_1, RESUME_2, RESUME_3, RESUME_NOT_EXIST};
        assertEquals(4, storage.size());
        assertArrayEquals(expected, actual);
    }

    @Test
    void saveExist() throws Exception {
        assertThrows(ExistStorageException.class, () -> storage.save(RESUME_EXIST));
    }

    @Test
    void saveWithOverflow() throws Exception {
        //Arrays.fill(storage, 3, AbstractArrayStorage.STORAGE_LIMIT-1, new Resume());
        //Resume[] resumes = storage.getAll();
        //Arrays.fill(resumes, 3, AbstractArrayStorage.STORAGE_LIMIT-1, new Resume());
        try {
            for (int i = 3; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException se) {
            fail("overflow occurred ahead of time");
        }
        assertThrows(StorageException.class, () -> storage.save(RESUME_NOT_EXIST));
    }

    @Test
    void delete() throws Exception {
/*        Resume[] actual = storage.getAll();
        Resume resume = actual[(new Random()).ints(0, storage.size() - 1).iterator().
                nextInt()];
        storage.delete(resume.getUuid());*/
        storage.delete(UUID_1);
        Resume[] actual = storage.getAll();
        Resume[] expected = new Resume[]{RESUME_2, RESUME_3};
        assertArrayEquals(expected, actual);
        assertEquals(2, storage.size());
    }

    @Test
    void deleteNotExist() throws Exception {
        assertThrows(NotExistStorageException.class, () -> storage.delete(UUID_4));
    }
}
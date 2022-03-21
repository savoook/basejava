package com.basejava.storage;

import com.basejava.exception.StorageException;
import com.basejava.model.Resume;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static com.basejava.storage.TestData.*;

abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test
    void saveWithOverflow() throws Exception {
        for (int i = 3; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
            assertDoesNotThrow(() -> storage.save(new Resume("Test")), "overflow occurred ahead of time");
        }
        assertThrows(StorageException.class, () -> storage.save(resumeNotExist));
    }
}

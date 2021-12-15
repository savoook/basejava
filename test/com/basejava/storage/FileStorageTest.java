package com.basejava.storage;

import static org.junit.jupiter.api.Assertions.*;

class FileStorageTest extends AbstractStorageTest {

    public FileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new Serial1()));
    }
}
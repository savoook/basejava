package com.basejava.storage;

import static org.junit.jupiter.api.Assertions.*;

class PathStorageTest extends AbstractStorageTest {

    public PathStorageTest() {
        super(new PathStorage(STORAGE_PATH, new Serial1()));
    }
}
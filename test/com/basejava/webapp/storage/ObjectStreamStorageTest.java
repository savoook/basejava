package com.basejava.webapp.storage;

import static org.junit.jupiter.api.Assertions.*;

class ObjectStreamStorageTest extends AbstractStorageTest {

    public ObjectStreamStorageTest() {
        super(new ObjectStreamStorage(STORAGE_DIR));
    }
}
package com.basejava.storage;

import static org.junit.jupiter.api.Assertions.*;

class ObjectStreamStorageTest extends AbstractFileStorageTest{

    public ObjectStreamStorageTest() {
        super(new ObjectStreamStorage(STORAGE_DIR));
    }
}
package com.basejava.storage;

class ObjectStreamStorageTest extends AbstractStorageTest{

    public ObjectStreamStorageTest() {
        super(new ObjectStreamStorage(STORAGE_DIR));
    }
}
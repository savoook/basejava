package com.basejava.storage;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractFileStorageTest extends AbstractStorageTest{

    public AbstractFileStorageTest(Storage storage) {
        super(storage);
    }
}
package com.basejava.webapp.storage;

import java.util.LinkedList;

public class ArrayListStorage extends ListStorage {

    public ArrayListStorage() {
        super(new LinkedList<>());
    }
}

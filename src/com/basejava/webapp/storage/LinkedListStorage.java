package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;

public class ArrayListStorage extends ListStorage {

    public ArrayListStorage() {
        super(new ArrayList<Resume>());
    }
}

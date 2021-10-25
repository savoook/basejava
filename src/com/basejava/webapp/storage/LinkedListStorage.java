package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.LinkedList;

public class LinkedListStorage extends ListStorage {

    public LinkedListStorage() {
        super(new LinkedList<Resume>());
    }
}

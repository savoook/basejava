package com.basejava.util;

import com.basejava.model.Resume;
import com.basejava.model.Section;
import com.basejava.model.TextSection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import  static com.basejava.storage.TestData.*;

class JsonParserTest {

    @Test
    void testResume() {
        String json = JsonParser.write(resume1, Resume.class);
        System.out.println(json);
        Resume r = JsonParser.read(json, Resume.class);
        assertEquals(resume1, r);
    }

    @Test
    void write() {
        Section section1 = new TextSection("test");
        String json = JsonParser.write(section1, Section.class);
        System.out.println(json);
        Section section2 = JsonParser.read(json, Section.class);
        assertEquals(section1, section2);
    }
}
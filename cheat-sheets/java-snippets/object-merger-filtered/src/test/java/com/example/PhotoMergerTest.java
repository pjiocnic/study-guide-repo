package com.example;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class PhotoMergerTest {
    @Test
    public void testMergePhotos() {
        List<Item1> object1List = Arrays.asList(
                new Item1(1, "YYYYYY"),
                new Item1(2, "YYYYYY"),
                new Item1(1, "ZZZZZ")
        );

        List<Item2> object2List = Arrays.asList(
                new Item2(1)
        );

        PhotoMerger.mergePhotos(object1List, object2List);

        assertEquals(1, object2List.size());
        Item2 item = object2List.get(0);
        assertEquals(1, item.getId());
        assertTrue(item.getPhotos().contains("YYYYYY"));
        assertTrue(item.getPhotos().contains("ZZZZZ"));
        assertEquals(2, item.getPhotos().size());
    }
}

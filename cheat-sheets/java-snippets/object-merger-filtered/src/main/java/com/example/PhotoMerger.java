package com.example;

import java.util.*;
import java.util.stream.Collectors;

class Item1 {
    int id;
    String photo;

    public Item1(int id, String photo) {
        this.id = id;
        this.photo = photo;
    }

    public int getId() { return id; }
    public String getPhoto() { return photo; }
}

class Item2 {
    int id;
    List<String> photos = new ArrayList<>();

    public Item2(int id) {
        this.id = id;
    }

    public int getId() { return id; }
    public List<String> getPhotos() { return photos; }

    public void addPhoto(String photo) {
        if (!photos.contains(photo)) {
            photos.add(photo);
        }
    }
}

public class PhotoMerger {
    public static void mergePhotos(List<Item1> object1List, List<Item2> object2List) {
        Map<Integer, Item2> object2Map = object2List.stream()
                .collect(Collectors.toMap(Item2::getId, item -> item));

        for (Item1 item1 : object1List) {
            Item2 target = object2Map.get(item1.getId());
            if (target != null) {
                target.addPhoto(item1.getPhoto());
            }
        }
    }
}

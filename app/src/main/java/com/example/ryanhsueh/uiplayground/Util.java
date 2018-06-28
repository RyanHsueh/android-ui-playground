package com.example.ryanhsueh.uiplayground;

import com.example.ryanhsueh.uiplayground.data.Friend;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Util {

    public static List<Friend> initFriends() {
        List<Friend> friends = new ArrayList<>();
        for (int i=0 ; i<2 ; i++) {
            friends.add(new Friend("Arial", 1));
            friends.add(new Friend("Ryan", 15));
            friends.add(new Friend("Cindy", 16));
            friends.add(new Friend("David", 17));
            friends.add(new Friend("Tom", 14));
            friends.add(new Friend("Molly", 17));
            friends.add(new Friend("Eric", 18));
            friends.add(new Friend("Frank", 15));
            friends.add(new Friend("Hally", 14));
            friends.add(new Friend("Emily", 19));
            friends.add(new Friend("Sandy", 14));
        }

        return friends;
    }

    public static List<Friend> initFriendsWithRandomName(int repeat) {
        List<Friend> friends = new ArrayList<>();
        for (int i=0 ; i<repeat ; i++) {
            friends.add(new Friend(getRandomLengthName("Arial"), 1));
            friends.add(new Friend(getRandomLengthName("Ryan"), 15));
            friends.add(new Friend(getRandomLengthName("Cindy"), 16));
            friends.add(new Friend(getRandomLengthName("David"), 17));
            friends.add(new Friend(getRandomLengthName("Tom"), 14));
            friends.add(new Friend(getRandomLengthName("Molly"), 17));
            friends.add(new Friend(getRandomLengthName("Eric"), 18));
            friends.add(new Friend(getRandomLengthName("Frank"), 15));
            friends.add(new Friend(getRandomLengthName("Hally"), 14));
            friends.add(new Friend(getRandomLengthName("Emily"), 19));
            friends.add(new Friend(getRandomLengthName("Sandy"), 14));
        }

        return friends;
    }



    private static String getRandomLengthName(String name) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder sb = new StringBuilder();
        for (int i=0 ; i<length ; i++) {
            sb.append(name).append(" ");
        }

        return sb.toString();
    }

}

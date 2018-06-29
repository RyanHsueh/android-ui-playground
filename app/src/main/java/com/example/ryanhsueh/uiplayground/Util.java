package com.example.ryanhsueh.uiplayground;

import com.example.ryanhsueh.uiplayground.data.Friend;
import com.example.ryanhsueh.uiplayground.data.News;

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
            friends.add(new Friend(getRandomLengthString("Arial"), 1));
            friends.add(new Friend(getRandomLengthString("Ryan"), 15));
            friends.add(new Friend(getRandomLengthString("Cindy"), 16));
            friends.add(new Friend(getRandomLengthString("David"), 17));
            friends.add(new Friend(getRandomLengthString("Tom"), 14));
            friends.add(new Friend(getRandomLengthString("Molly"), 17));
            friends.add(new Friend(getRandomLengthString("Eric"), 18));
            friends.add(new Friend(getRandomLengthString("Frank"), 15));
            friends.add(new Friend(getRandomLengthString("Hally"), 14));
            friends.add(new Friend(getRandomLengthString("Emily"), 19));
            friends.add(new Friend(getRandomLengthString("Sandy"), 14));
        }

        return friends;
    }

    public static List<News> createNews() {
        List<News> newsList = new ArrayList<>();
        newsList.add(new News("News Title " + 1, getRandomLengthString(" News content " + 1)));
        newsList.add(new News("News Title " + 2, getRandomLengthString(" News content " + 2)));
        newsList.add(new News("News Title " + 3, getRandomLengthString(" News content " + 3)));
        newsList.add(new News("News Title " + 4, getRandomLengthString(" News content " + 4)));
        newsList.add(new News("News Title " + 5, getRandomLengthString(" News content " + 5)));
        newsList.add(new News("News Title " + 6, getRandomLengthString(" News content " + 6)));
        newsList.add(new News("News Title " + 7, getRandomLengthString(" News content " + 7)));
        newsList.add(new News("News Title " + 8, getRandomLengthString(" News content " + 8)));
        newsList.add(new News("News Title " + 9, getRandomLengthString(" News content " + 9)));
        newsList.add(new News("News Title " + 10, getRandomLengthString(" News content " + 10)));
        newsList.add(new News("News Title " + 11, getRandomLengthString(" News content " + 11)));
        newsList.add(new News("News Title " + 12, getRandomLengthString(" News content " + 12)));
        newsList.add(new News("News Title " + 13, getRandomLengthString(" News content " + 13)));
        newsList.add(new News("News Title " + 14, getRandomLengthString(" News content " + 14)));

        return newsList;
    }


    private static String getRandomLengthString(String content) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder sb = new StringBuilder();
        for (int i=0 ; i<length ; i++) {
            sb.append(content).append(" ");
        }

        return sb.toString();
    }

}

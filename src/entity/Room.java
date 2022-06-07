package entity;

import java.util.Vector;

public class Room {
    Vector<Member> member;
    String title;
    int roomId;
    int count=0;

    public Room(int roomId, String title, int count, Vector<Member> member) {
        this.roomId = roomId;
        this.member = member;
        this.title = title;
        this.count = count;
    }

    public Vector<Member> getMember() {
        return member;
    }

    public String getTitle() {
        return title;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getCount() {
        return count;
    }
}

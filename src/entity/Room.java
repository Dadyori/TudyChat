package entity;

import java.util.Vector;

public class Room {
    Vector<Member> member;
    String title;
    int roomId;
    int count=0;

    Room() {
        member = new Vector<>();
    }
}

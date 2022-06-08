package entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Getter
@Setter
public class Room {
    List<Member> member;
    String title;
    int roomId;
    int count;

    public Room() {
        this.roomId = 0;
        this.title = "";
        this.count = 0;
        this.member = new ArrayList<>();
    }

    public Room(List<Member> member, String title, int roomId, int count) {
        this.member = member;
        this.title = title;
        this.roomId = roomId;
        this.count = count;
    }
}

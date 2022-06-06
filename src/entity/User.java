package entity;

import lombok.Getter;

@Getter
public class User {
    private String id;
    private String password;
    private String name;
    private String email;
    private Boolean connect;
}

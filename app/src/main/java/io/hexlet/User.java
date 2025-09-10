package io.hexlet;

public class User {
    private Long id;
    private final String name;
    private final String phone;

    public User(String name, String phone) {
        super();
        this.name = name;
        this.phone = phone;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;}
}

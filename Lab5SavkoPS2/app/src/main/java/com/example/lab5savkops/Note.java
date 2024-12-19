package com.example.lab5savkops;

public class Note {
    private int id;
    private String description;
    private int number;

    public Note(int id, String description, int number) {
        this.id = id;
        this.description = description;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getNumber() {
        return number;
    }
}
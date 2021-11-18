package com.basejava.webapp.model;

public enum ContactType {
    PHONE("Телефон"),
    EMAIL("Email"),
    SKYPE("Skype"),
    LINKEDIN("LinkedIn"),
    GITHUB("GitHub"),
    STACKOVERFLOW("StackOverFlow"),
    HOMEPAGE("Homepage");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

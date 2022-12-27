package com.epam.esm.dao.query;

public class Queries {

    private Queries() {
    }

    public static final String CREATE_CERTIFICATE = "INSERT INTO gift_certificate" +
            "(name, description, price, duration, create_date, last_update_date) VALUES (?, ?, ?, ?, ?, ?)";

    public static final String CREATE_TAG = "INSERT INTO tags(name) VALUES (?)";
}

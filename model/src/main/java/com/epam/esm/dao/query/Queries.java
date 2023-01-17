package com.epam.esm.dao.query;

/**
 * Class that contains all queries.
 */
public class Queries {

    private Queries() {
    }

    public static final String CREATE_CERTIFICATE = "INSERT INTO gift_certificates" +
            "(name, description, price, duration, create_date, last_update_date) VALUES (?, ?, ?, ?, ?, ?)";

    public static final String CREATE_CERTIFICATE_TAG_REFERENCE = "INSERT INTO " +
            "gift_certificate_has_tag(gift_certificate_id, tag_id) VALUES (?, ?)";

    public static final String CREATE_TAG = "INSERT INTO tags(name) VALUES (?)";


}

package com.epam.esm.dao.query;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

import static com.epam.esm.constants.Constants.*;

/**
 * Utility class, designed to build a query to update database and retrieve info from database according to some filters.
 */
@Component
public class QueryBuilder {

    /**
     * Method to build an update query.
     *
     * @param updateFields Map with update fields.
     * @param tableName Name of table.
     * @return String of query for update.
     */
    public String createUpdateQuery(Map<String, String> updateFields, String tableName){
        StringJoiner joiner = new StringJoiner(",");
        for (Map.Entry<String, String> entry : updateFields.entrySet()) {
            if (entry.getValue() != null && !entry.getKey().equals("id")) {
                joiner.add(entry.getKey() + "=" + '\'' + entry.getValue() + '\'');
            }
        }
        return "UPDATE " + tableName + " SET " + joiner + " WHERE id=" + updateFields.get("id");
    }

    /**
     * Method to retrieve info from database according to some filters.
     *
     * @param fields Map with filters.
     * @param tableName Name of table.
     * @return String of query for select with filters.
     */
    public String createGetQuery(Map<String, String> fields, String tableName) {
        StringBuilder query = new StringBuilder("SELECT * FROM " + tableName);
        if (Objects.equals(tableName, "gift_certificates")) {
            query.append(" gc LEFT JOIN gift_certificate_has_tag gcht ON gc.id=gcht.gift_certificate_id LEFT JOIN tags t ON gcht.tag_id=t.id");
        }
        if (fields.get(TAG_NAME) != null && !fields.get(TAG_NAME).equals("")) {
            addParameter(query, "t.name", fields.get(TAG_NAME));
        }
        if (fields.get(PART_OF_TAG_NAME) != null && !fields.get(PART_OF_TAG_NAME).equals("")) {
            addPartParameter(query, "t.name", fields.get(PART_OF_TAG_NAME));
        }
        if (fields.get(PART_OF_NAME) != null && !fields.get(PART_OF_NAME).equals("")) {
            addPartParameter(query, "gc.name", fields.get(PART_OF_NAME));
        }
        if (fields.get(PART_OF_DESCRIPTION) != null && !fields.get(PART_OF_DESCRIPTION).equals("")) {
            addPartParameter(query, "gc.description", fields.get(PART_OF_DESCRIPTION));
        }
        if (fields.get(SORT_BY_NAME) != null && !fields.get(SORT_BY_NAME).equals("")) {
            addSortParameter(query, "gc.name", fields.get(SORT_BY_NAME));
        }
        if (fields.get(SORT_BY_CREATE_DATE) != null && !fields.get(SORT_BY_CREATE_DATE).equals("")) {
            addSortParameter(query, "gc.create_date", fields.get(SORT_BY_CREATE_DATE));
        }
        return query.toString();
    }

    private void addParameter(StringBuilder query, String partParameter, String value) {
        if (query.toString().contains("WHERE")) {
            query.append(" AND ");
        } else {
            query.append(" WHERE ");
        }
        query.append(partParameter).append("='").append(value).append('\'');
    }

    private void addPartParameter(StringBuilder query, String partParameter, String value) {
        if (query.toString().contains("WHERE")) {
            query.append(" AND ");
        } else {
            query.append(" WHERE ");
        }
        query.append(partParameter).append(" LIKE '%").append(value).append("%'");
    }

    private void addSortParameter(StringBuilder query, String sortParameter, String value) {
        if (query.toString().contains("ORDER BY")) {
            query.append(", ");
        } else {
            query.append(" ORDER BY ");
        }
        query.append(sortParameter).append(" ").append(value);
    }
}

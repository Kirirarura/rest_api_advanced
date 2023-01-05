package com.epam.esm.dao.query;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.StringJoiner;

@Component
public class QueryBuilder {

    public String createUpdateQuery(Map<String, String> updateFields, String tableName){
        StringJoiner joiner = new StringJoiner(",");
        for (Map.Entry<String, String> entry : updateFields.entrySet()) {
            if (entry.getValue() != null && !entry.getKey().equals("id")) {
                joiner.add(entry.getKey() + "=" + '\'' + entry.getValue() + '\'');
            }
        }
        return "UPDATE " + tableName + " SET " + joiner + " WHERE id=" + updateFields.get("id");
    }
}

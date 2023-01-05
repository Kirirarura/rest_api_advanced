package com.epam.esm.extractor;

import com.epam.esm.entity.GiftCertificate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GiftCertificateExtractor {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String PRICE = "price";
    private static final String DURATION = "duration";
    private static final String LAST_UPDATE_DATE = "last_update_date";


    public Map<String, String> extract(GiftCertificate item) {
        String name = item.getName();
        String description = item.getDescription();
        String priceString = String.valueOf(item.getPrice());
        if (priceString.equals("null")) {
            priceString = null;
        }
        String durationString = String.valueOf(item.getDuration());
        if (durationString.equals("0")) {
            durationString = null;
        }
        String lastUpdateDate = item.getLastUpdateDate();
        String idString = String.valueOf(item.getId());

        Map<String, String> map = new HashMap<>();
        map.put(NAME, name);
        map.put(DESCRIPTION, description);
        map.put(PRICE, priceString);
        map.put(DURATION, durationString);
        map.put(LAST_UPDATE_DATE, lastUpdateDate);
        map.put(ID, idString);
        return map;
    }
}

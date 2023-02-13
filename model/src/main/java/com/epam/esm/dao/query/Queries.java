package com.epam.esm.dao.query;

import com.epam.esm.entity.Order;
import lombok.experimental.UtilityClass;

/**
 * Class that contains all queries.
 */
@UtilityClass
public class Queries {

    public static final String FIND_MOST_WIDELY_USED_TAG_OF_USER_WITH_HIGHEST_COST_OF_ALL_ORDERS_QUERY ="SELECT t FROM "
            + "GiftCertificate g INNER JOIN g.tags t WHERE g.id IN (SELECT o.giftCertificate.id FROM Order o "
            + "WHERE o.user.id IN (SELECT o.user.id FROM Order o GROUP BY o.user.id ORDER BY SUM(o.price) DESC)) "
            + "GROUP BY t.id ORDER BY COUNT(t.id) DESC";


    public static final String FIND_BY_USER_ID_QUERY = "SELECT o FROM " + Order.class.getName()
            + " o WHERE o.user.id = :user_id";
}

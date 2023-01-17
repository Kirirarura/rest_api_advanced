package dao;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exceptions.DaoException;
import com.epam.esm.dao.config.DBTestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = DBTestConfig.class)
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class GiftCertificateDaoImplTest {


    private static final Tag TAG_1 = new Tag(1L, "tag1");
    private static final Tag TAG_2 = new Tag(2L, "tag2");

    private static final GiftCertificate GIFT_CERTIFICATE_1 = new GiftCertificate(1L, "giftCertificate1",
            "description1", new BigDecimal("10.10"), 10,
            "2022-12-29T06:12:15.156", "2022-12-29T06:12:15.156");
    private static final GiftCertificate GIFT_CERTIFICATE_2 = new GiftCertificate(2L, "giftCertificate2",
            "description2", new BigDecimal("20.10"), 20,
            "2022-12-29T06:12:15.156", "2022-12-29T06:12:15.156");
    private static final GiftCertificate GIFT_CERTIFICATE_3 = new GiftCertificate(3L, "giftCertificate3",
            "description3", new BigDecimal("30.10"), 30,
            "2022-12-29T06:12:15.156", "2022-12-29T06:12:15.156");

    private static final GiftCertificate GIFT_CERTIFICATE_TO_CREATE = new GiftCertificate(4L, "giftCertificate4",
            "description4", new BigDecimal("40.10"), 40,
            "2022-12-29T06:12:15.156", "2022-12-29T06:12:15.156");

    private static final GiftCertificate GIFT_CERTIFICATE_TO_UPDATE = new GiftCertificate(2L, "giftCertificate4",
            "New updated description", new BigDecimal("40.10"), 40,
            "2022-12-29T06:12:15.156", "2022-12-29T06:12:15.156");

    private static final List<Tag> TAGS = Arrays.asList(TAG_1, TAG_2);

    @Autowired
    private GiftCertificateDao giftCertificateDao;

    @Test
    void testGetById() throws DaoException {
        GiftCertificate actual = giftCertificateDao.getById(GIFT_CERTIFICATE_2.getId()).get();
        assertEquals(GIFT_CERTIFICATE_2, actual);
    }

    @Test
    void testGetAll() throws DaoException {
        List<GiftCertificate> actual = giftCertificateDao.getAll();

        List<GiftCertificate> expected = Arrays.asList(GIFT_CERTIFICATE_1, GIFT_CERTIFICATE_2, GIFT_CERTIFICATE_3);
        assertEquals(expected, actual);
    }

    @Test
    void testGetWithFilters() throws DaoException {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("tagName", TAG_2.getName());
        parameters.put("partOfName", null);
        parameters.put("partOfDescription", null);
        parameters.put("partOfTagName", null);
        parameters.put("sortByName", "DESC");
        parameters.put("sortByTagName", null);
        parameters.put("sortByCreateDate", null);

        List<GiftCertificate> actual = giftCertificateDao.getWithFilters(parameters);
        List<GiftCertificate> expected = Arrays.asList(GIFT_CERTIFICATE_2, GIFT_CERTIFICATE_1);

        assertEquals(expected, actual);
    }


    @Test
    void create() throws DaoException {
        giftCertificateDao.create(GIFT_CERTIFICATE_TO_CREATE, TAGS);

        Optional<GiftCertificate> result = giftCertificateDao.getById(GIFT_CERTIFICATE_TO_CREATE.getId());
        GiftCertificate actual = null;
        if (result.isPresent()){
            actual = result.get();
        }
        assertEquals(GIFT_CERTIFICATE_TO_CREATE, actual);
    }

    @Test
    void testFindByName() throws DaoException {
        Optional<GiftCertificate> result = giftCertificateDao.findByName(GIFT_CERTIFICATE_1.getName());
        GiftCertificate actual = null;
        if (result.isPresent()){
            actual = result.get();
        }
        assertEquals(GIFT_CERTIFICATE_1, actual);
    }

    @Test
    void update() throws DaoException {
        List<Tag> emptyList = new ArrayList<>();
        giftCertificateDao.update(GIFT_CERTIFICATE_TO_UPDATE, emptyList);

        Optional<GiftCertificate> result = giftCertificateDao.getById(GIFT_CERTIFICATE_2.getId());
        GiftCertificate actual = null;
        if (result.isPresent()){
            actual = result.get();
        }

        assertEquals(GIFT_CERTIFICATE_TO_UPDATE, actual);
    }
}

package dao;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.config.DBTestConfig;
import com.epam.esm.entity.Tag;
import com.epam.esm.exceptions.DaoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = DBTestConfig.class)
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class TagDaoImplTest {

    private static final Tag TAG_1 = new Tag(1L, "tag1");
    private static final Tag TAG_2 = new Tag(2L, "tag2");
    private static final Tag TAG_3 = new Tag(3L, "tag3");
    private static final Tag TAG_4 = new Tag(4L, "tag4");
    private static final Tag TAG_TO_CREATE = new Tag(5L, "tag5");

    @Autowired
    TagDao tagDao;

    @Test
    void testCreate() throws DaoException {
        tagDao.create(TAG_TO_CREATE);

        Optional<Tag> result = tagDao.getById(TAG_TO_CREATE.getId());
        Tag actual = null;
        if (result.isPresent()){
            actual = result.get();
        }
        assertEquals(TAG_TO_CREATE, actual);
    }

    @Test
    void testFindByName() throws DaoException {
        Optional<Tag> result = tagDao.findByName(TAG_1.getName());
        Tag actual = null;
        if (result.isPresent()){
            actual = result.get();
        }
        assertEquals(TAG_1, actual);
    }

    @Test
    void testGetAll() throws DaoException {
        List<Tag> actual = tagDao.getAll();
        List<Tag> expected = Arrays.asList(TAG_1, TAG_2, TAG_3, TAG_4);

        assertEquals(expected, actual);
    }

    @Test
    void testGetById() throws DaoException {
        Optional<Tag> result = tagDao.getById(TAG_3.getId());
        Tag actual = null;
        if (result.isPresent()){
            actual = result.get();
        }
        assertEquals(TAG_3, actual);
    }
}

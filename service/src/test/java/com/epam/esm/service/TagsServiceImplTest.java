package com.epam.esm.service;

import com.epam.esm.dao.impl.TagDaoImpl;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.DuplicateEntityException;
import com.epam.esm.exception.NoSuchEntityException;
import com.epam.esm.exceptions.DaoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TagsServiceImplTest {

    @Mock
    private TagDaoImpl tagDao = Mockito.mock(TagDaoImpl.class);

    @InjectMocks
    private TagsServiceImpl tagsService;

    private static final Tag TAG_1 = new Tag(1L, "tag1");
    private static final Tag TAG_2 = new Tag(2L, "tag2");
    private static final Tag TAG_3 = new Tag(3L, "tag3");


    @Test
    void testGetAll() throws DaoException {
        List<Tag> tags = Arrays.asList(TAG_1, TAG_2, TAG_3);
        when(tagDao.getAll()).thenReturn(tags);

        List<Tag> actual = tagsService.getAll();

        assertEquals(tags, actual);
    }

    @Test
    void testGetById() throws DaoException, NoSuchEntityException {
        when(tagDao.getById(TAG_1.getId())).thenReturn(Optional.of(TAG_1));

        Tag actual = tagsService.getById(TAG_1.getId());

        assertEquals(TAG_1, actual);
    }

    @Test
    void testDeleteById() throws DaoException, NoSuchEntityException {
        when(tagDao.deleteById(TAG_2.getId())).thenReturn(2L);
        when(tagDao.getById(TAG_2.getId())).thenReturn(Optional.of(TAG_2));

        Long actual = tagsService.deleteById(TAG_2.getId());
        Long expected = 2L;

        assertEquals(expected, actual);
    }

    private Method getCheckTagMethod() throws NoSuchMethodException {
        Method method = TagsServiceImpl.class.getDeclaredMethod(
                "checkTag", Tag.class);
        method.setAccessible(true);
        return method;
    }

    @Test
    void testCheckTag() throws DaoException {
        when(tagDao.findByName(TAG_3.getName())).thenReturn(Optional.of(TAG_3));

        assertEquals(DuplicateEntityException.class, assertThrows(
                InvocationTargetException.class, () -> getCheckTagMethod().invoke(
                        tagsService, TAG_3)).getCause().getClass());
    }
}
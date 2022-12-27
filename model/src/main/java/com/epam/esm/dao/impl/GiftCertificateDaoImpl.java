package com.epam.esm.dao.impl;

import com.epam.esm.dao.AbstractDao;
import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.query.Queries;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exceptions.DaoException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.epam.esm.exceptions.ExceptionDaoMessages.*;

@Component
public class GiftCertificateDaoImpl extends AbstractDao<GiftCertificate> implements GiftCertificateDao {

    private static final String TABLE_NAME = "gift_certificate";

    private static final RowMapper<GiftCertificate> ROW_MAPPER =
            (rs, rowNum) -> new GiftCertificate(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getBigDecimal("price"),
                    rs.getInt("duration"),
                    rs.getString("create_date"),
                    rs.getString("last_update_date"));

    private final JdbcTemplate jdbcTemplate;

    public GiftCertificateDaoImpl(JdbcTemplate jdbcTemplate) {
        super(ROW_MAPPER, TABLE_NAME, jdbcTemplate);
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(GiftCertificate giftCertificate) throws DaoException {
        try{
            jdbcTemplate.update(Queries.CREATE_CERTIFICATE, giftCertificate.getName(),
                    giftCertificate.getDescription(), giftCertificate.getPrice(),
                    giftCertificate.getDuration(), giftCertificate.getCreateDate(), giftCertificate.getLastUpdateDate());
        } catch (DataAccessException e){
            throw new DaoException(SAVING_ERROR);
        }

    }

    @Override
    public void createCertificateTagReference(long certificateId, long tagId) {

    }

    @Override
    public Optional<GiftCertificate> findByName(String name) {
        return findByColumn("name", name);
    }
}

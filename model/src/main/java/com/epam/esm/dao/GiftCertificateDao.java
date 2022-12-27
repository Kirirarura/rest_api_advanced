package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exceptions.DaoException;

import java.util.List;
import java.util.Optional;

public interface GiftCertificateDao {

    void create(GiftCertificate giftCertificate) throws DaoException;

    void createCertificateTagReference(long certificateId, long tagId);

    List<GiftCertificate> getAll();

    Optional<GiftCertificate> findByName(String name);

}

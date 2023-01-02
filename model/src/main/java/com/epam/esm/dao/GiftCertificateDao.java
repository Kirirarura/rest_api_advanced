package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exceptions.DaoException;

import java.util.List;
import java.util.Optional;

public interface GiftCertificateDao {

    void create(GiftCertificate giftCertificate) throws DaoException;

    void createCertificateTagReference(Long certificateId, Long tagId);

    List<GiftCertificate> getAll();
    Optional<GiftCertificate> findById(Long id);

    Optional<GiftCertificate> findByName(String name);
    void deleteById(Long id);

}

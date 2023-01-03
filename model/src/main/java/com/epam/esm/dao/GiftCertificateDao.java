package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exceptions.DaoException;

import java.util.List;
import java.util.Optional;

public interface GiftCertificateDao {

    void create(GiftCertificate giftCertificate, List<Tag> tags) throws DaoException;

    List<GiftCertificate> getAll() throws DaoException;
    Optional<GiftCertificate> findById(Long id) throws DaoException;
    Optional<GiftCertificate> findByName(String name) throws DaoException;
    void deleteById(Long id) throws DaoException;
}

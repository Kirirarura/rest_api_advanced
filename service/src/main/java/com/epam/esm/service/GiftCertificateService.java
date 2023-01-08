package com.epam.esm.service;


import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.DuplicateEntityException;
import com.epam.esm.exception.InvalidEntityException;
import com.epam.esm.exception.InvalidIdException;
import com.epam.esm.exception.NoSuchEntityException;
import com.epam.esm.exceptions.DaoException;


import java.util.List;
import java.util.Map;

/**
 * Business logic for Certificates
 */
public interface GiftCertificateService {

    Long create(GiftCertificateDto giftCertificateDto) throws DaoException, InvalidEntityException, DuplicateEntityException;
    List<GiftCertificate> getAll() throws DaoException;
    GiftCertificate getById(Long id) throws NoSuchEntityException, DaoException, InvalidIdException;
    Long deleteById(Long id) throws NoSuchEntityException, DaoException, InvalidIdException;
    Long update(Long id, GiftCertificateDto giftCertificateDto) throws DaoException, InvalidEntityException, InvalidIdException;
    List<GiftCertificate> doFilter(Map<String, String> map) throws DaoException;
}

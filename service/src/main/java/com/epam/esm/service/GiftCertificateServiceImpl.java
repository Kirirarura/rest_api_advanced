package com.epam.esm.service;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.DuplicateEntityException;
import com.epam.esm.exception.InvalidEntityException;
import com.epam.esm.exception.NoSuchEntityException;
import com.epam.esm.exceptions.DaoException;
import com.epam.esm.validators.GiftCertificateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private final GiftCertificateDao giftCertificateDao;
    private final TagDao tagDao;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDao giftCertificateDao, TagDao tagDao) {
        this.giftCertificateDao = giftCertificateDao;
        this.tagDao = tagDao;
    }

    public List<GiftCertificate> getAll() throws DaoException {
        return giftCertificateDao.getAll();
    }

    @Override
    public void deleteById(Long id) throws NoSuchEntityException, DaoException {
        Optional<GiftCertificate> certificateOptional = giftCertificateDao.findById(id);
        if (!certificateOptional.isPresent()) {
            throw new NoSuchEntityException("certificate.not.found");
        }
        giftCertificateDao.deleteById(id);
    }

    @Override
    public GiftCertificate getById(Long id) throws NoSuchEntityException, DaoException {
        Optional<GiftCertificate> optionalGiftCertificate = giftCertificateDao.findById(id);
        if (!optionalGiftCertificate.isPresent()){
            throw new NoSuchEntityException("certificate.not.found");
        }
        return optionalGiftCertificate.get();
    }


    @Override
    @Transactional(rollbackFor = DaoException.class)
    public Long create(GiftCertificateDto giftCertificateDto) throws DaoException, InvalidEntityException, DuplicateEntityException {

        GiftCertificate giftCertificate = giftCertificateDto.getGiftCertificate();
        checkCertificate(giftCertificate);
        giftCertificate.setCreateDate(String.valueOf(LocalDateTime.now()));
        giftCertificate.setLastUpdateDate(String.valueOf(LocalDateTime.now()));

        List<Tag> requestTags = giftCertificateDto.getCertificateTags();
        List<Tag> createdTags = tagDao.getAll();
        saveNewTags(requestTags, createdTags);
        giftCertificateDao.create(giftCertificate, requestTags);

        String certificateName = giftCertificate.getName();
        return giftCertificateDao.findByName(certificateName)
                .map(GiftCertificate::getId).orElse(-1L);
    }

    private void checkCertificate(GiftCertificate giftCertificate)
            throws InvalidEntityException, DuplicateEntityException, DaoException {

        if (!GiftCertificateValidator.isValid(giftCertificate)) {
            throw new InvalidEntityException("certificate.invalid");
        }

        String name = giftCertificate.getName();
        Optional<GiftCertificate> optionalName = giftCertificateDao.findByName(name);
        if (optionalName.isPresent()){
            throw new DuplicateEntityException("certificate.already.exist");
        }
    }

    private void saveNewTags(List<Tag> requestTags, List<Tag> createdTags) throws DaoException {
        if (requestTags == null) {
            return;
        }
        for (Tag requestTag : requestTags) {
            boolean isExist = false;
            for (Tag createdTag : createdTags) {
                if (Objects.equals(requestTag.getName(), createdTag.getName())) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                tagDao.create(requestTag);
            }
        }
    }

}

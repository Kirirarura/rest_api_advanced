package com.epam.esm.service;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.entity.GiftCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;


@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private final GiftCertificateDao giftCertificateDao;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDao giftCertificateDao) {
        this.giftCertificateDao = giftCertificateDao;
    }


    @Override
    @Transactional
    public long create(GiftCertificateDto giftCertificateDto) {
        GiftCertificate giftCertificate = giftCertificateDto.getGiftCertificate();

        LocalDateTime createDate = LocalDateTime.now();
        giftCertificate.setCreateDate(String.valueOf(createDate));
        giftCertificate.setLastUpdateDate(String.valueOf(createDate));
        giftCertificateDao.create(giftCertificate);
        String certificateName = giftCertificate.getName();

        long certificateId = giftCertificateDao.findByName(certificateName)
                .map(GiftCertificate::getId).orElse(-1L);
        return certificateId;
    }

    public List<GiftCertificate> getAll() {
        return giftCertificateDao.getAll();
    }

}

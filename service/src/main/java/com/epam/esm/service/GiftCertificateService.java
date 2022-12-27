package com.epam.esm.service;


import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.entity.GiftCertificate;

import java.util.List;

/**
 * Business logic for Certificates
 */
public interface GiftCertificateService {

    long create(GiftCertificateDto giftCertificateDto);
    List<GiftCertificate> getAll();
}

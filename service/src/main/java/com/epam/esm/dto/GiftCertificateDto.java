package com.epam.esm.dto;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents an object that carries data between processes.
 */
public class GiftCertificateDto {

    private GiftCertificate giftCertificate;

    private List<Tag> certificateTags = new ArrayList<>();

    @JsonCreator
    public GiftCertificateDto() {
    }

    public GiftCertificateDto(GiftCertificate giftCertificate, List<Tag> certificateTags) {
        this.giftCertificate = giftCertificate;
        this.certificateTags = certificateTags;
    }

    public GiftCertificate getGiftCertificate() {
        return giftCertificate;
    }

    public void setGiftCertificate(GiftCertificate giftCertificate) {
        this.giftCertificate = giftCertificate;
    }

    public List<Tag> getCertificateTags() {
        return certificateTags;
    }

    public void setCertificateTags(List<Tag> certificateTags) {
        this.certificateTags = certificateTags;
    }

    public void addTag(Tag tag) {
        this.certificateTags.add(tag);
    }

    @Override
    public String toString() {
        return "GiftCertificateDto{" +
                "giftCertificate=" + giftCertificate +
                ", certificateTags=" + certificateTags +
                '}';
    }
}

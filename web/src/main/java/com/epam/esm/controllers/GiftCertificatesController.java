package com.epam.esm.controllers;

import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.DuplicateEntityException;
import com.epam.esm.exception.InvalidEntityException;
import com.epam.esm.exception.NoSuchEntityException;
import com.epam.esm.exceptions.DaoException;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/certificates")
public class GiftCertificatesController {

    private final GiftCertificateService giftCertificateService;

    @Autowired
    public GiftCertificatesController(GiftCertificateService giftCertificateService) {
        this.giftCertificateService = giftCertificateService;
    }

    @GetMapping
    public List<GiftCertificate> getAll() {
        return giftCertificateService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GiftCertificate getById(@PathVariable("id") Long id) throws NoSuchEntityException {
        return giftCertificateService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity create(@RequestBody GiftCertificateDto giftCertificateDto) throws DaoException, DuplicateEntityException, InvalidEntityException {
        long id = giftCertificateService.create(giftCertificateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteGiftCertificate(@PathVariable("id") Long id) throws NoSuchEntityException {
        giftCertificateService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

}

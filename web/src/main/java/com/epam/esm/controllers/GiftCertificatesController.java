package com.epam.esm.controllers;

import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.DuplicateEntityException;
import com.epam.esm.exception.InvalidEntityException;
import com.epam.esm.exception.InvalidIdException;
import com.epam.esm.exception.NoSuchEntityException;
import com.epam.esm.exceptions.DaoException;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.epam.esm.constants.Constants.*;

@RestController
@RequestMapping("/certificates")
public class GiftCertificatesController {

    private final GiftCertificateService giftCertificateService;

    @Autowired
    public GiftCertificatesController(GiftCertificateService giftCertificateService) {
        this.giftCertificateService = giftCertificateService;
    }

    @GetMapping
    public List<GiftCertificate> getAll() throws DaoException {
        return giftCertificateService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GiftCertificate getById(@PathVariable("id") Long id) throws NoSuchEntityException, DaoException {
        return giftCertificateService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody GiftCertificateDto giftCertificateDto) throws DaoException, DuplicateEntityException, InvalidEntityException {
        Long id = giftCertificateService.create(giftCertificateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteGiftCertificate(@PathVariable("id") Long id) throws NoSuchEntityException, DaoException {
        giftCertificateService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Long> updateGiftCertificate(@PathVariable("id") Long id,
                                                      @RequestBody GiftCertificateDto giftCertificateDto)
            throws DaoException, InvalidEntityException, InvalidIdException {
        Long resultId = giftCertificateService.update(id, giftCertificateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultId);
    }

    @GetMapping(value = "/filter")
    public List<GiftCertificate> giftCertificatesByParameter(
            @RequestParam(name = "tagName") String tagName,
            @RequestParam(name = "partOfName") String partOfName,
            @RequestParam(name = "partOfDescription") String partOfDescription,
            @RequestParam(name = "partOfTagName") String partOfTagName,
            @RequestParam(name = "sortByName") String sortByName,
            @RequestParam(name = "sortByCreateDate") String sortByCreateDate,
            @RequestParam(name = "sortByTagName") String sortByTagName) throws DaoException {
        Map<String, String> allRequestParams = new HashMap<>();
        allRequestParams.put(TAG_NAME,tagName);
        allRequestParams.put(PART_OF_NAME, partOfName);
        allRequestParams.put(PART_OF_DESCRIPTION,partOfDescription);
        allRequestParams.put(PART_OF_TAG_NAME, partOfTagName);
        allRequestParams.put(SORT_BY_NAME,sortByName);
        allRequestParams.put(SORT_BY_CREATE_DATE, sortByCreateDate);
        allRequestParams.put(SORT_BY_TAG_NAME, sortByTagName);
        return giftCertificateService.doFilter(allRequestParams);
    }

}

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

/**
 * Controller responsible for all operations with gift certificates.
 */
@RestController
@RequestMapping("/certificates")
public class GiftCertificatesController {

    private final GiftCertificateService giftCertificateService;

    @Autowired
    public GiftCertificatesController(GiftCertificateService giftCertificateService) {
        this.giftCertificateService = giftCertificateService;
    }

    /**
     * Method for getting all gift certificates.
     *
     * @return List of gift certificates.
     * @throws DaoException An exception that thrown in case of data access errors.
     */
    @GetMapping
    public List<GiftCertificate> getAll() throws DaoException {
        return giftCertificateService.getAll();
    }

    /**
     * Method for getting gift certificate by id.
     *
     * @param id ID of gift certificate to be returned.
     * @return Gift certificate object.
     * @throws NoSuchEntityException An exception that thrown in case gift certificate with provided id not found.
     * @throws DaoException An exception that thrown in case of data access errors.
     * @throws InvalidIdException An exception that thrown in case provided ID is invalid.
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GiftCertificate getById(@PathVariable("id") Long id) throws NoSuchEntityException, DaoException, InvalidIdException {
        return giftCertificateService.getById(id);
    }

    /**
     * Method for creating gift certificate.
     *
     * @param giftCertificateDto DTO object that contains gift certificate and related tags.
     * @return Gift certificate object with provided ID.
     * @throws DaoException An exception that thrown in case of data access errors.
     * @throws DuplicateEntityException An exception that thrown in case gift certificate is already presented in DB.
     * @throws InvalidEntityException An exception that thrown in case provided gift certificate is invalid.
     */
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody GiftCertificateDto giftCertificateDto) throws DaoException, DuplicateEntityException, InvalidEntityException {
        Long id = giftCertificateService.create(giftCertificateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    /**
     * Method for deleting gift certificate.
     *
     * @param id ID of gift certificate to be deleted.
     * @return ID of deleted gift certificate.
     * @throws NoSuchEntityException An exception that thrown in case gift certificate with provided ID not found.
     * @throws DaoException An exception that thrown in case of data access errors.
     * @throws InvalidIdException An exception that thrown in case provided ID is invalid.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteGiftCertificate(@PathVariable("id") Long id) throws NoSuchEntityException, DaoException, InvalidIdException {
        giftCertificateService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    /**
     * Method for gift certificate updating.
     *
     * @param id ID of gift certificate to be updated.
     * @param giftCertificateDto DTO for gift certificate update.
     * @return ID of updated gift certificate.
     * @throws DaoException An exception that thrown in case of data access errors.
     * @throws InvalidEntityException An exception that thrown in case provided gift certificate is invalid.
     * @throws InvalidIdException An exception that thrown in case provided ID is invalid.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Long> updateGiftCertificate(@PathVariable("id") Long id,
                                                      @RequestBody GiftCertificateDto giftCertificateDto)
            throws DaoException, InvalidEntityException, InvalidIdException {
        Long resultId = giftCertificateService.update(id, giftCertificateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultId);
    }

    /**
     * Method for getting gift certificates according to search filters.
     *
     * @return List of gift certificates got with filters.
     * @throws DaoException An exception that thrown in case of data access errors.
     */
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

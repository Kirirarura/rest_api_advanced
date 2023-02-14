package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.*;
import com.epam.esm.request.GiftCertificateCreateRequest;
import com.epam.esm.request.GiftCertificatePriceUpdateRequest;
import com.epam.esm.request.GiftCertificateUpdateRequest;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.impl.util.PaginationHelper;
import com.epam.esm.validation.FilterParamsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static com.epam.esm.dao.query.FilterParams.*;



@Service
@RequiredArgsConstructor
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private final GiftCertificateDao giftCertificateDao;
    private final TagDao tagDao;

    private static Supplier<NoSuchEntityException> getNoSuchGiftCertificateException(
            Long id) {
        return () -> new NoSuchEntityException(ExceptionMessageKey.NO_ENTITY, id);
    }

    @Override
    public GiftCertificate getById(Long id) {
        return giftCertificateDao.findById(id).orElseThrow(getNoSuchGiftCertificateException(id));
    }


    @Override
    @Transactional
    public GiftCertificate create(GiftCertificateCreateRequest request) {
        String giftCertificateName = request.getName();
        boolean isGiftCertificateExist = giftCertificateDao.findByName(giftCertificateName).isPresent();
        if (isGiftCertificateExist) {
            throw new DuplicateEntityException(ExceptionMessageKey.GIFT_CERTIFICATE_EXIST);
        }

        List<Tag> tags = checkRequestedTags(request);

        GiftCertificate giftCertificate = new GiftCertificate(null, request.getName(),
                request.getDescription(), request.getPrice(), request.getDuration(), String.valueOf(Instant.now()),
                String.valueOf(Instant.now()), tags);

        return giftCertificateDao.insert(giftCertificate);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        giftCertificateDao.findById(id).orElseThrow(getNoSuchGiftCertificateException(id));
    }

    @Override
    @Transactional
    public GiftCertificate update(Long id, GiftCertificateUpdateRequest updateRequest) throws NoSuchEntityException {
        GiftCertificate giftCertificate = giftCertificateDao.findById(id)
                .orElseThrow(getNoSuchGiftCertificateException(id));

        handleUpdateRequest(giftCertificate, updateRequest);

        return giftCertificateDao.update(giftCertificate);
    }

    @Override
    @Transactional
    public GiftCertificate update(Long id, GiftCertificatePriceUpdateRequest updateRequest) {
        GiftCertificate giftCertificate = giftCertificateDao.findById(id)
                .orElseThrow(getNoSuchGiftCertificateException(id));

        giftCertificate.setPrice(updateRequest.getPrice());
        return giftCertificateDao.update(giftCertificate);
    }

    @Override
    public List<GiftCertificate> doFilter(MultiValueMap<String, String> requestParams, int page, int size) {
        ExceptionResult exceptionResult = new ExceptionResult();

        String name = getSingleRequestParameter(requestParams, NAME);
        if (name != null){
            FilterParamsValidator.validateGiftCertificateName(name, exceptionResult);
        }

        List<String> tagNames = requestParams.get(TAG_NAME);
        if (tagNames != null) {
            for (String tagName : tagNames) {
                FilterParamsValidator.validateTagName(tagName, exceptionResult);
            }
        }
        String sortNameType = getSingleRequestParameter(requestParams, SORT_BY_NAME);
        if (sortNameType != null) {
            FilterParamsValidator.validateSortType(sortNameType.toUpperCase(), exceptionResult);
        }
        String sortCreateDateType = getSingleRequestParameter(requestParams, SORT_BY_CREATE_DATE);
        if (sortCreateDateType != null) {
            FilterParamsValidator.validateSortType(sortCreateDateType.toUpperCase(), exceptionResult);
        }
        if (!exceptionResult.getExceptionMessages().isEmpty()) {
            throw new IncorrectParameterException(exceptionResult);
        }

        Pageable pageRequest = PaginationHelper.createPageRequest(page, size);
        return giftCertificateDao.findWithFilters(requestParams, pageRequest);
    }


    private List<Tag> checkRequestedTags(GiftCertificateCreateRequest request) {
        List<Tag> tagsToPersist = new ArrayList<>();
        List<Tag> requestedTags = request.getTags();
        removeDuplicateTags(requestedTags);
        if (requestedTags != null) {
            for (Tag tag : requestedTags) {
                Optional<Tag> tagOptional = tagDao.findByName(tag.getName());
                if (tagOptional.isPresent()) {
                    tagsToPersist.add(tagOptional.get());
                } else {
                    tagsToPersist.add(tag);
                }
            }
        }
        return tagsToPersist;
    }

    private void removeDuplicateTags(List<Tag> tags) {
        if (tags != null) {
            List<Tag> result = new ArrayList<>();
            for (Tag tag : tags) {
                if (!result.contains(tag)) {
                    result.add(tag);
                }
            }
        }
    }

    private void handleUpdateRequest(GiftCertificate giftCertificate, GiftCertificateUpdateRequest updateRequest) {

        if (updateRequest.isNamePresent()) {
            giftCertificate.setName(updateRequest.getName());
        }

        if (updateRequest.isDescriptionPresent()) {
            giftCertificate.setDescription(updateRequest.getDescription());
        }

        if (updateRequest.isDurationPresent()) {
            giftCertificate.setDuration(updateRequest.getDuration());
        }

        if (updateRequest.isPricePresent()) {
            giftCertificate.setPrice(updateRequest.getPrice());
        }

        if (updateRequest.isTagsPresent()) {
            giftCertificate.setTags(updateRequest.getTags());
        }
    }

    protected String getSingleRequestParameter(MultiValueMap<String, String> requestParams, String parameter) {
        if (requestParams.containsKey(parameter)) {
            return requestParams.get(parameter).get(0);
        } else {
            return null;
        }
    }
}

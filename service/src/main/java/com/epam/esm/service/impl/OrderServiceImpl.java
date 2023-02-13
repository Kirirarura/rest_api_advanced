package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.OrderDao;
import com.epam.esm.dao.UserDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.User;
import com.epam.esm.exception.*;
import com.epam.esm.request.OrderCreateRequest;
import com.epam.esm.service.OrderService;
import com.epam.esm.service.util.PaginationHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.function.Supplier;

import static com.epam.esm.exception.ExceptionMessageKey.BAD_USER_ID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final UserDao userDao;
    private final GiftCertificateDao giftCertificateDao;

    private static Supplier<NoSuchEntityException> getNoSuchOrderException(
            Long id) {
        return () -> new NoSuchEntityException(ExceptionMessageKey.NO_ENTITY, id);
    }
    private static Supplier<NoSuchEntityException> getNoSuchUserException(
            Long id) {
        return () -> new NoSuchEntityException(ExceptionMessageKey.NO_ENTITY, id);
    }
    private static Supplier<NoSuchEntityException> getNoSuchGiftCertificateException(
            Long id) {
        return () -> new NoSuchEntityException(ExceptionMessageKey.NO_ENTITY, id);
    }

    @Override
    public Order getById(Long id) {
        return orderDao.findById(id).orElseThrow(getNoSuchOrderException(id));
    }

    @Override
    public Order create(OrderCreateRequest request) {
        User user = userDao.findById(request.getUserId())
                .orElseThrow(getNoSuchUserException(request.getUserId()));

        GiftCertificate giftCertificate = giftCertificateDao.findById(request.getGiftCertificateId())
                .orElseThrow(getNoSuchGiftCertificateException(request.getGiftCertificateId()));

        Order order = new Order(null, giftCertificate.getPrice(), String.valueOf(Instant.now()), user, giftCertificate);
        return orderDao.insert(order);
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId, int page, int size) {
        if (userId < 1){
            ExceptionResult exceptionResult = new ExceptionResult();
            exceptionResult.addException(BAD_USER_ID, userId);
            throw new IncorrectParameterException(exceptionResult);
        }
        Pageable pageRequest = PaginationHelper.createPageRequest(page, size);
        return orderDao.findByUserId(userId, pageRequest);
    }
}

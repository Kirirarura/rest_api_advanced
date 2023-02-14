package com.epam.esm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "orders")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Audited
public class Order implements Serializable {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "purchase_date", nullable = false)
    private String purchaseDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "gift_certificate_id")
    private GiftCertificate giftCertificate;
}

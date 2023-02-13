package com.epam.esm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Entity that represents gift certificate.
 */
@Entity
@Table(name = "gift_certificates")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class GiftCertificate implements Serializable {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NonNull
    @Size(min = 4, max = 40, message = "Size of gift certificate name is invalid; min = 4, max = 40")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    @NonNull
    @Min(value = 1, message = "Price of gift certificate is invalid; min = 1")
    private BigDecimal price;

    @Column(name = "duration")
    @NonNull
    @Min(value = 1, message = "Duration of gift certificate is invalid; min = 1")
    private int duration;

    @Column(name = "create_date")
    private String createDate;

    @Column(name = "last_update_date")
    private String lastUpdateDate;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "gift_certificates_has_tags",
            joinColumns = {
                    @JoinColumn(
                            name = "gift_certificate_id",
                            referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "tag_id",
                            referencedColumnName = "id")
            }
    )
    private List<Tag> tags;
}

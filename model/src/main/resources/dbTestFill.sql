
INSERT INTO gift_certificates_test.tags (name)
VALUES ('tag1');

INSERT INTO gift_certificates_test.tags (name)
VALUES ('tag2');

INSERT INTO gift_certificates_test.tags (name)
VALUES ('tag3');

INSERT INTO gift_certificates_test.tags (name)
VALUES ('tag4');

INSERT INTO gift_certificates_test.gift_certificates (name, description, price, duration, create_date, last_update_date)
VALUES ('giftCertificate1', 'description1', 10.10, 10, '2022-12-29T06:12:15.156', '2022-12-29T06:12:15.156');

INSERT INTO gift_certificates_test.gift_certificates (name, description, price, duration, create_date, last_update_date)
VALUES ('giftCertificate2', 'description2', 20.10, 20, '2022-12-29T06:12:15.156', '2022-12-29T06:12:15.156');

INSERT INTO gift_certificates_test.gift_certificates (name, description, price, duration, create_date, last_update_date)
VALUES ('giftCertificate3', 'description3', 30.10, 30, '2022-12-29T06:12:15.156', '2022-12-29T06:12:15.156');




INSERT INTO gift_certificates_test.gift_certificate_has_tag (gift_certificate_id, tag_id)
VALUES (1, 2);

INSERT INTO gift_certificates_test.gift_certificate_has_tag (gift_certificate_id, tag_id)
VALUES (2, 2);
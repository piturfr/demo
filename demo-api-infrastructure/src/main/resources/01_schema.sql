CREATE TABLE BRAND (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(50) NOT NULL,
    DESCRIPTION VARCHAR(255)
);

CREATE TABLE PRODUCT (
    ID BIGINT PRIMARY KEY,
    NAME VARCHAR(100) NOT NULL,
    DESCRIPTION VARCHAR(255)
);

CREATE TABLE PRICE (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    BRAND_ID BIGINT,
    START_DATE TIMESTAMP NOT NULL,
    END_DATE TIMESTAMP,
    PRICE_LIST INT,
    PRODUCT_ID BIGINT,
    PRIORITY INT,
    PRICE DECIMAL(10, 2) NOT NULL,
    CURRENCY VARCHAR(3) NOT NULL,
    FOREIGN KEY (BRAND_ID) REFERENCES BRAND(ID),
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT(ID)
);

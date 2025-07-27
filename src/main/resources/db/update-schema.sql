CREATE SCHEMA user_details;

CREATE TABLE user_details."user"
(
    user_id   UUID         NOT NULL,
    name      VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email     VARCHAR(255) NOT NULL,
    password  VARCHAR(255) NOT NULL,
    role      role         NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (user_id)
);

ALTER TABLE user_details."user"
    ADD CONSTRAINT uc_user_email UNIQUE (email);

CREATE SCHEMA product_schema;

CREATE TABLE product_schema.product
(
    product_id              UUID            NOT NULL,
    name                    VARCHAR(255)    NOT NULL,
    price                   DECIMAL(12, 2)  NOT NULL,
    description             VARCHAR(255)    NOT NULL,
    brand_name              VARCHAR(255)    NOT NULL,
    available_quantity_left INTEGER         NOT NULL,
    category                productcategory NOT NULL,
    seller_user_id          UUID            NOT NULL,
    published_at_date       TIMESTAMP WITHOUT TIME ZONE,
    withdrew_at_date        TIMESTAMP WITHOUT TIME ZONE,
    published_status        BOOLEAN         NOT NULL,
    sold_number             INTEGER         NOT NULL,
    CONSTRAINT pk_product PRIMARY KEY (product_id)
);

ALTER TABLE product_schema.product
    ADD CONSTRAINT FK_PRODUCT_ON_SELLER_USER FOREIGN KEY (seller_user_id) REFERENCES user_details."user" (user_id);

DROP TABLE product_schema.product;
DROP TABLE user_details."user";

DROP SCHEMA user_details;
DROP SCHEMA product_schema;


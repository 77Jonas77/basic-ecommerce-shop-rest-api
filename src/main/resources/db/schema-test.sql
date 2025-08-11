DROP SCHEMA IF EXISTS user_details CASCADE;
DROP SCHEMA IF EXISTS product_schema CASCADE;

CREATE SCHEMA user_details;
CREATE SCHEMA product_schema;

CREATE TABLE user_details."user" (
                                     user_id   UUID         NOT NULL,
                                     name      VARCHAR(255) NOT NULL,
                                     last_name VARCHAR(255) NOT NULL,
                                     email     VARCHAR(255) NOT NULL,
                                     password  VARCHAR(255) NOT NULL,
                                     role      VARCHAR(50)  NOT NULL,
                                     CONSTRAINT pk_user PRIMARY KEY (user_id),
                                     CONSTRAINT uc_user_email UNIQUE (email)
);

CREATE TABLE product_schema.product (
                                        product_id              UUID            NOT NULL,
                                        name                    VARCHAR(255)    NOT NULL,
                                        price                   DECIMAL(12, 2)  NOT NULL,
                                        description             VARCHAR(255)    NOT NULL,
                                        brand_name              VARCHAR(255)    NOT NULL,
                                        available_quantity_left INTEGER         NOT NULL,
                                        category                VARCHAR(50)     NOT NULL,
                                        seller_user_id          UUID            NOT NULL,
                                        published_at_date       TIMESTAMP,
                                        withdrew_at_date        TIMESTAMP,
                                        published_status        BOOLEAN         NOT NULL,
                                        sold_number             INTEGER         NOT NULL,
                                        filename                VARCHAR(255),
                                        CONSTRAINT pk_product PRIMARY KEY (product_id),
                                        CONSTRAINT FK_PRODUCT_ON_SELLER_USER FOREIGN KEY (seller_user_id) REFERENCES user_details."user" (user_id)
);

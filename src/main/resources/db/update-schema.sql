CREATE SCHEMA user_details;

CREATE TABLE user_details."user"
(
    user_id   UUID         NOT NULL,
    name      VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email     VARCHAR(255) NOT NULL,
    password  VARCHAR(255) NOT NULL,
    role      VARCHAR(255) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (user_id)
);

ALTER TABLE user_details."user"
    ADD CONSTRAINT uc_user_email UNIQUE (email);
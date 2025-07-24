CREATE TABLE users
(
    user_id   UUID         NOT NULL,
    name      VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email     VARCHAR(255) NOT NULL,
    password  VARCHAR(255) NOT NULL,
    role      role         NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (user_id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

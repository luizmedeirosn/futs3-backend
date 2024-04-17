CREATE TABLE IF NOT EXISTS tb_user
(
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    username VARCHAR(255)                            NOT NULL,
    email    VARCHAR(255)                            NOT NULL,
    password TEXT                                    NOT NULL,
    roles    VARCHAR(255)                            NOT NULL,

    PRIMARY KEY (id),
    UNIQUE (username),
    UNIQUE (email)
);

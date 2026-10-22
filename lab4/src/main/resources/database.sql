DROP TABLE IF EXISTS deliveries CASCADE;
DROP TABLE IF EXISTS vehicles CASCADE;
DROP TABLE IF EXISTS customers CASCADE;
DROP TABLE IF EXISTS drivers CASCADE;
DROP TABLE IF EXISTS depots CASCADE;

CREATE TABLE depots (
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name        VARCHAR(100) NOT NULL UNIQUE,
    coordinates VARCHAR(50)  NOT NULL
);

CREATE TABLE drivers (
    id    BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name  VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    phone VARCHAR(30)  NOT NULL UNIQUE
);


CREATE TABLE vehicles (
    id        BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name      VARCHAR(100) NOT NULL UNIQUE,
    capacity  INTEGER      NOT NULL CHECK (capacity > 0),
    depot     BIGINT       NOT NULL REFERENCES depots (id),
    available BOOLEAN      NOT NULL DEFAULT TRUE,
    driver    BIGINT       UNIQUE REFERENCES drivers (id) ON DELETE SET NULL
);

CREATE TABLE customers (
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    email       VARCHAR(150) NOT NULL UNIQUE,
    phone       VARCHAR(30)  NOT NULL UNIQUE,
    address     VARCHAR(255) NOT NULL,
    coordinates VARCHAR(50)  NOT NULL
);

CREATE TABLE deliveries (
    id        BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    customer  BIGINT    NOT NULL REFERENCES customers (id) ON DELETE CASCADE,
    quantity  INTEGER   NOT NULL CHECK (quantity > 0),
    notes     TEXT,
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_vehicles_depot ON vehicles (depot);
CREATE INDEX idx_deliveries_customer ON deliveries (customer);

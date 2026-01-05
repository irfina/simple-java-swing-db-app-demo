/**
 * Author:  Hansen, Irfin
 * Created: Jan 5, 2026
 */

CREATE ROLE cafe_dba NOSUPERUSER LOGIN PASSWORD 'user123';

CREATE DATABASE cafe OWNER cafe_dba;

CREATE TABLE meal_category (
    id SERIAL,
    name VARCHAR(256) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name)
);

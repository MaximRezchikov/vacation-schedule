CREATE TABLE IF NOT EXISTS employee (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    birthday DATE NOT NULL,
    login VARCHAR(60) UNIQUE,
    password VARCHAR (255),
    personnel_number BIGINT NOT NULL,
    post VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    username VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS vacation (
    id int8 generated BY DEFAULT AS IDENTITY,
    employee_id BIGINT,
    end_vacation DATE,
    start_vacation DATE,
    PRIMARY KEY(id)
);

ALTER TABLE if EXISTS vacation ADD CONSTRAINT employee_vacation_fk FOREIGN KEY (employee_id) REFERENCES employee;
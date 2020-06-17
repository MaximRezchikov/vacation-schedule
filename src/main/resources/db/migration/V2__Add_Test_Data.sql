INSERT INTO employee
            (birthday, login, password, personnel_number, post, start_date, username)
VALUES ('01.06.1990',
        'admin',
        '$2y$12$sEkxMtOnoW4DjBDcQMmvau6V38.TVGBPbuLI.m7xyjbTMQ5Te2n4G',
        1,
        'admin',
        '20.05.2014',
        'Adminov Admin Adminovich'
);

INSERT INTO vacation
            (employee_id, start_vacation, end_vacation)
VALUES (1,
        '14.06.2020',
        '30.06.2020'
);

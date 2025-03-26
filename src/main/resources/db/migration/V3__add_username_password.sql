ALTER TABLE employee
    ADD password VARCHAR(255) NULL;

ALTER TABLE employee
    ADD username VARCHAR(255) NULL;

update employee
set username = 'john',
    password = '$2a$12$jdBTGfgoRFZDtsebOLqIKuxlNOmSqLreAWOqipogGh/sWpEBXbKEK'
where name = 'John';
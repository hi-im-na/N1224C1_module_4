set foreign_key_checks = 0;
truncate table employee;
truncate table department;
truncate table employee_role;
truncate table role;
set foreign_key_checks = 1;

insert into department (name)
values ('HR'),
       ('IT'),
       ('Finance');

insert into employee (id, name, dob, gender, salary, phone, department_id)
VALUES (uuid_to_bin('ac0ad5dd-956b-4e66-b528-e248c18e3b76'), 'John', '1990-01-01', 'MALE', 1000, '123456789', 1),
       (uuid_to_bin('e83eb8ec-4f8a-4211-ae48-1db59cc54859'), 'Jane', '1991-01-02', 'FEMALE', 2000, '987654321', 2),
       (uuid_to_bin('6b90a8ca-db3c-4275-a86c-201c07e76a03'), 'Tom', '1992-01-03', 'FEMALE', 3000, '123456789', 3);


update employee
set username = 'john',
    password = '$2a$12$jdBTGfgoRFZDtsebOLqIKuxlNOmSqLreAWOqipogGh/sWpEBXbKEK'
where name = 'John';

update employee
set username = 'jane',
    password = '$2a$12$jdBTGfgoRFZDtsebOLqIKuxlNOmSqLreAWOqipogGh/sWpEBXbKEK'
where name = 'Jane';

insert into role (name)
values ('ADMIN'),
       ('USER');

insert into employee_role (employee_id, role_id)
VALUES (uuid_to_bin('ac0ad5dd-956b-4e66-b528-e248c18e3b76'), 1),
       (uuid_to_bin('ac0ad5dd-956b-4e66-b528-e248c18e3b76'), 2),
       (uuid_to_bin('e83eb8ec-4f8a-4211-ae48-1db59cc54859'), 2),
       (uuid_to_bin('6b90a8ca-db3c-4275-a86c-201c07e76a03'), 2);


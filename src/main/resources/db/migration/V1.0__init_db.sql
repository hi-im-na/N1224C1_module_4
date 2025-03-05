create table department
(
    id   int auto_increment primary key,
    name varchar(255)
);

create table employee
(
    id            binary(16) primary key,
    name          varchar(255),
    dob           date,
    gender        enum ('MALE', 'FEMALE'),
    salary        decimal(18, 2),
    phone         varchar(20),
    department_id int,
    foreign key (department_id) references department (id)
);

insert into department (name)
values ('HR'),
       ('IT'),
       ('Finance');

insert into employee (id, name, dob, gender, salary, phone, department_id)
VALUES (unhex(replace(uuid(), '-', '')), 'John', '1990-01-01', 'MALE', 1000, '123456789', 1),
       (unhex(replace(uuid(), '-', '')), 'Jane', '1991-01-02', 'FEMALE', 2000, '987654321', 2),
       (unhex(replace(uuid(), '-', '')), 'Tom', '1992-01-03', 'FEMALE', 3000, '123456789', 3);

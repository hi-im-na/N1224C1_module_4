create table department
(
    id   int auto_increment primary key,
    name varchar(255)
);

create table employee
(
    id            binary(16) primary key default (uuid_to_bin(uuid())),
    name          varchar(255),
    dob           date,
    gender        enum ('MALE', 'FEMALE'),
    salary        decimal(18, 2),
    phone         varchar(20),
    department_id int,
    foreign key (department_id) references department (id)
);

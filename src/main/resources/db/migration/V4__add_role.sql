CREATE TABLE employee_role
(
    employee_id BINARY(16) NOT NULL,
    role_id     BIGINT     NOT NULL,
    CONSTRAINT pk_employee_role PRIMARY KEY (employee_id, role_id)
);

CREATE TABLE `role`
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255)          NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

ALTER TABLE employee_role
    ADD CONSTRAINT fk_emprol_on_employee FOREIGN KEY (employee_id) REFERENCES employee (id);

ALTER TABLE employee_role
    ADD CONSTRAINT fk_emprol_on_role FOREIGN KEY (role_id) REFERENCES `role` (id);

ALTER TABLE employee
    DROP COLUMN gender;

ALTER TABLE employee
    ADD gender VARCHAR(255) NULL;

ALTER TABLE employee
    MODIFY phone VARCHAR(255);

ALTER TABLE employee
    MODIFY salary DECIMAL;
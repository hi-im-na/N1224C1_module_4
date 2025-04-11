ALTER TABLE department
    ADD deleted BIT(1) NOT NULL DEFAULT 0;

ALTER TABLE employee
    ADD deleted BIT(1) NOT NULL DEFAULT 0;
ALTER TABLE department
    ADD deleted BIT(1) NULL default NULL;

UPDATE department
SET deleted = 0
WHERE deleted IS NULL;
ALTER TABLE department
    MODIFY deleted BIT(1) NOT NULL;

ALTER TABLE employee
    ADD deleted BIT(1) NULL;

UPDATE employee
SET deleted = 0
WHERE deleted IS NULL;
ALTER TABLE employee
    MODIFY deleted BIT(1) NOT NULL;
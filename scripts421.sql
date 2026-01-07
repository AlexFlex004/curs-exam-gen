///STUDENTS///
ALTER TABLE student
ADD CONSTRAINT chk_student_age
CHECK (age >= 11);


ALTER TABLE student
ALTER COLUMN name SET NOT NULL;


ALTER TABLE student
ADD CONSTRAINT uq_student_name
UNIQUE (name);


ALTER TABLE student
ALTER COLUMN age SET DEFAULT 11;


ALTER TABLE faculty
ADD CONSTRAINT uq_faculty_name_color
UNIQUE (name, color);


///CARS///
CREATE TABLE car (
    id SERIAL PRIMARY KEY,
    brand VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    price NUMERIC(12, 2) NOT NULL
);

///PEOPLE///
CREATE TABLE person (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INTEGER NOT NULL,
    has_driver_license BOOLEAN NOT NULL,
    car_id INTEGER NOT NULL,
    CONSTRAINT fk_person_car
        FOREIGN KEY (car_id)
        REFERENCES car(id)
);

///JOIN_REQUESTS///
SELECT s.name,
       s.age,
       f.name AS faculty_name
FROM student s
JOIN faculty f ON s.faculty_id = f.id
WHERE f.name = 'Hogwarts';

SELECT s.name,
       s.age
FROM student s
WHERE s.avatar IS NOT NULL;
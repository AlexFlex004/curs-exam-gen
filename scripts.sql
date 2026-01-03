-- Студенты от 10 до 20 лет
SELECT * FROM student
WHERE age BETWEEN 10 AND 20;

-- Только имена студентов
SELECT name FROM student;

-- Имя содержит букву "о"
SELECT * FROM student
WHERE name ILIKE '%о%';

-- Возраст меньше id
SELECT * FROM student
WHERE age < id;

-- Сортировка по возрасту
SELECT * FROM student
ORDER BY age;
use enroll;
INSERT INTO professor(id, name, phone_number, professor_status) VALUES (1, 'professor1', '010-1234-1234', 'WORK');
INSERT INTO department(id, division, name, fax_number, number) VALUES (1, 'IT', 'computer', '02-011-2222', '032-1234-1234');
INSERT INTO course(course_id, course_status, course_name, professor_id, score) VALUES (1, 'OPEN', 'course1', 1, 1);
INSERT INTO student(student_id, name, semester_max_score) VALUES (1, 'student_name1', 10);
INSERT INTO subject(id, is_pre_requisite, book_name, introduction, subject_name) VALUES(1, false, 'book', 'intro', 'book');
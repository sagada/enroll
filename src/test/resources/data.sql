use enroll;
INSERT INTO professor(id, name, phone_number, professor_status)
VALUES (1, 'professor1', '010-1234-1234', 'WORK');
INSERT INTO department(id, division, name, fax_number, number)
VALUES (1, 'IT', 'computer', '02-011-2222', '032-1234-1234');
INSERT INTO course(course_id, course_status, department_id, max_stu_cnt, course_name, professor_id)
VALUES (1, 'OPEN', 1, 10, 'course1', 1)
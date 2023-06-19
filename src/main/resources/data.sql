INSERT INTO department (id, division, name, number) VALUES (1, 'IT', 'computer0', '100');
INSERT INTO department (id, division, name, number) VALUES (2, 'IT', 'computer1', '200');
INSERT INTO student (id, academic_year, advisor_professor_id, available_score, department_id, email, name) VALUES (1, 1, 1, 14, 1, 'qwe@naver.com', 'qqwe');
INSERT INTO subject (id, is_pre_requisite, book_name, introduction, subject_name) VALUES (1, false, null, 'introduction', 'operation system');
INSERT INTO professor (id, department_id, email, name, professor_status) VALUES (1, 1, '"saga@akaa.com"', '"david"', 'WORK');
INSERT INTO course (course_id, final_term_date, mid_term_date, course_name, course_status, professor_id, course_score, subject_id) VALUES (1, '2023-06-15 00:38:19', '2023-06-15 00:38:29', 'qwe', 'OPEN', 1, 1, 1);

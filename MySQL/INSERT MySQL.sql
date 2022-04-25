INSERT INTO STUDENTS (id_student, student_name, student_lastname, student_address, student_telephone, student_email) VALUES (1,'MARTIN','PEREZ','C/ CALLE 123','976123456','martin@gmail.com');
INSERT INTO STUDENTS (id_student, student_name, student_lastname, student_address, student_telephone, student_email) VALUES (2,'LOLA','MARTINEZ','C/ CALLE 345','976123454','lola@gmail.com');
INSERT INTO STUDENTS (id_student, student_name, student_lastname, student_address, student_telephone, student_email) VALUES (3,'SARA','DOLORES','C/ CALLE 567','976123457','sara@gmail.com');
INSERT INTO STUDENTS (id_student, student_name, student_lastname, student_address, student_telephone, student_email) VALUES (4,'DAVID','ROMERO','C/ CALLE 789','976123458','david@gmail.com');

INSERT INTO USERS (id_user, username, password, role, user_firstname, user_lastname, user_telephone, user_email) VALUE (10000,'Susana1','1234','Admin','SUSANA','RODRIGUEZ','976666666','susana@gmail.com');
INSERT INTO USERS (id_user, username, password, role, user_firstname, user_lastname, user_telephone, user_email) VALUE (10001,'Pedro2','1234','Secretary','PEDRO','ARANDA','976666667','pedro@gmail.com');

INSERT INTO ENROLLS (id_enroll, enroll_price, enroll_payquotes, enroll_notes, id_subject, id_student) VALUES (7001,150,1,'aprobado',5001,1);
INSERT INTO ENROLLS (id_enroll, enroll_price, enroll_payquotes, enroll_notes, id_subject, id_student) VALUES (7002,150,1,'pendiente',5002,1);
INSERT INTO ENROLLS (id_enroll, enroll_price, enroll_payquotes, enroll_notes, id_subject, id_student) VALUES (7003,150,1,'suspendido',5001,2);
INSERT INTO ENROLLS (id_enroll, enroll_price, enroll_payquotes, enroll_notes, id_subject, id_student) VALUES (7004,150,1,'aprobado',5003,2);
INSERT INTO ENROLLS (id_enroll, enroll_price, enroll_payquotes, enroll_notes, id_subject, id_student) VALUES (7005,150,1,'pendiente',5001,3);
INSERT INTO ENROLLS (id_enroll, enroll_price, enroll_payquotes, enroll_notes, id_subject, id_student) VALUES (7006,150,1,'suspendido',5004,3);
INSERT INTO ENROLLS (id_enroll, enroll_price, enroll_payquotes, enroll_notes, id_subject, id_student) VALUES (7007,150,1,'aprobado',5002,4);
INSERT INTO ENROLLS (id_enroll, enroll_price, enroll_payquotes, enroll_notes, id_subject, id_student) VALUES (7008,150,1,'pendiente',5003,4);

INSERT INTO SUBJECTS (id_subject, subject_name, subject_vacancies, subject_teacher, subject_price) VALUES (5001,'MATEMATICAS',30,'CARLOS PEREZ',100);
INSERT INTO SUBJECTS (id_subject, subject_name, subject_vacancies, subject_teacher, subject_price) VALUES (5002,'FISICA',30,'PEDRO CASTAÑOS',100);
INSERT INTO SUBJECTS (id_subject, subject_name, subject_vacancies, subject_teacher, subject_price) VALUES (5003,'DIBUJO',30,'EVA PEREZ',100);
INSERT INTO SUBJECTS (id_subject, subject_name, subject_vacancies, subject_teacher, subject_price) VALUES (5004,'QUIMICA',30,'CARLOS PEREZ',100);

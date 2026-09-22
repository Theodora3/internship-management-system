SET foreign_key_checks = 0;

-- Μήνυμα καταγραφής για να δούμε αν το data.sql εκτελείται
SELECT 'data.sql εκτελείται!' AS message;

-- Εισαγωγή δεδομένων
INSERT INTO roles (id, name) VALUES (1, 'STUDENT');
INSERT INTO users (id, username, password, role_id) VALUES
(1, 'student1', '$2a$10$gQzDufXY2OEg1sOS0b.Cj.mczkPaF3TTRiWCMhKXeTRZY6w7.q1qO', 1);
INSERT INTO students (id, user_id, full_name, registration_number, preferred_location, has_applied) VALUES
(1, 1, 'Γιάννης Παπαδόπουλος', 'AM123456', 'Αθήνα', false);
INSERT INTO student_interests (student_id, interest) VALUES
(1, 'Τεχνητή Νοημοσύνη'), (1, 'Επεξεργασία Εικόνας');
INSERT INTO student_skills (student_id, skill) VALUES
(1, 'Java'), (1, 'SQL');
INSERT INTO student_logbook (student_id, log_entry) VALUES
(1, '1η μέρα: Γνωριμία με το περιβάλλον'), (1, '2η μέρα: Εκπαίδευση σε Spring Boot');

SET foreign_key_checks = 1;

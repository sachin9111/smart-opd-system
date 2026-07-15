CREATE DATABASE IF NOT EXISTS smart_opd_auth;

CREATE DATABASE IF NOT EXISTS smart_opd_user;

CREATE DATABASE IF NOT EXISTS smart_opd_doctor;

CREATE DATABASE IF NOT EXISTS smart_opd_patient;

CREATE DATABASE IF NOT EXISTS smart_opd_queue;

CREATE DATABASE IF NOT EXISTS smart_opd_appointment;


GRANT ALL PRIVILEGES ON smart_opd_auth.* TO 'smartopd'@'%';
GRANT ALL PRIVILEGES ON smart_opd_user.* TO 'smartopd'@'%';
GRANT ALL PRIVILEGES ON smart_opd_doctor.* TO 'smartopd'@'%';
GRANT ALL PRIVILEGES ON smart_opd_patient.* TO 'smartopd'@'%';
GRANT ALL PRIVILEGES ON smart_opd_queue.* TO 'smartopd'@'%';
GRANT ALL PRIVILEGES ON smart_opd_appointment.* TO 'smartopd'@'%';

FLUSH PRIVILEGES;
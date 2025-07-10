
-- sample_oracle.sql
CREATE TABLE PATIENTS (
  patient_id NUMBER PRIMARY KEY,
  name VARCHAR2(100),
  address VARCHAR2(200),
  dob DATE,
  photo BLOB,
  processed VARCHAR2(1) DEFAULT 'N'
);

INSERT INTO PATIENTS (patient_id, name, address, dob) VALUES
(1, 'John Doe', '123 Main Street, Cityville', TO_DATE('1985-01-01', 'YYYY-MM-DD'));

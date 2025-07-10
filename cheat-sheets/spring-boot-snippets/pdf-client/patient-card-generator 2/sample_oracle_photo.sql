
-- sample_oracle_photo.sql
-- create directory
CREATE OR REPLACE DIRECTORY MY_IMAGES_DIR AS '/tmp/images';
GRANT READ ON DIRECTORY MY_IMAGES_DIR TO YOURUSER;

-- load
DECLARE
   l_bfile BFILE;
   l_blob  BLOB;
BEGIN
   l_bfile := BFILENAME('MY_IMAGES_DIR', 'patient1.jpg');
   DBMS_LOB.OPEN(l_bfile, DBMS_LOB.LOB_READONLY);
   SELECT photo INTO l_blob FROM patients WHERE patient_id = 1 FOR UPDATE;
   DBMS_LOB.LoadFromFile(l_blob, l_bfile, DBMS_LOB.GETLENGTH(l_bfile));
   DBMS_LOB.CLOSE(l_bfile);
END;
/

-- Example Oracle schema for table A
CREATE TABLE A (
  A VARCHAR2(128) PRIMARY KEY,
  B VARCHAR2(128)
);

-- Optional index to accelerate lookups where B IS NULL
CREATE INDEX A_B_NULL_IDX ON A (B);


CREATE OR REPLACE TRIGGER trg_team_scores_seq
BEFORE INSERT ON team_scores
FOR EACH ROW
BEGIN
  IF :NEW.seq IS NULL THEN
    SELECT team_scores_seq.NEXTVAL INTO :NEW.seq FROM dual;
  END IF;
  :NEW.created_at := CURRENT_TIMESTAMP;
  :NEW.updated_at := CURRENT_TIMESTAMP;
END;

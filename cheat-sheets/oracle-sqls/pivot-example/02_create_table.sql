
CREATE TABLE team_scores (
  seq         NUMBER PRIMARY KEY,
  team        VARCHAR2(20),
  game        VARCHAR2(20),
  score       NUMBER,
  created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

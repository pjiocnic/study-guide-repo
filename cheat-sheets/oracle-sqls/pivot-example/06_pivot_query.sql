
SELECT *
FROM (
  SELECT team, game, score
  FROM team_scores
)
PIVOT (
  MAX(score)
  FOR game IN ('GAME1' AS GAME1, 'GAME2' AS GAME2, 'GAME3' AS GAME3, 'GAME4' AS GAME4)
)
ORDER BY team;

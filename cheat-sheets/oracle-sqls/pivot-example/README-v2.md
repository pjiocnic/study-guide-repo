
# Oracle Team Scores Pivot Setup (Enhanced)

This project demonstrates how to pivot Oracle SQL data **from rows to columns**, including:
- ✅ Sequence-based primary key
- ✅ Indexing and triggers
- ✅ Administrative columns (`created_at`, `updated_at`)
- ✅ **Formatted numbers with commas**
- ✅ **Percentage breakdowns per team**

---

## 📁 Files Included

| File Name                    | Purpose |
|------------------------------|---------|
| `01_create_sequence.sql`     | Create SEQ for primary key |
| `02_create_table.sql`        | Table creation script |
| `03_create_trigger.sql`      | Trigger for auto-filling SEQ and timestamps |
| `04_create_index.sql`        | Adds index for team/game lookup |
| `05_insert_data.sql`         | Sample data for 3 teams across games |
| `06_pivot_query.sql`         | Basic pivot with raw scores |
| `07_pivot_with_formatting.sql` | Enhanced pivot with number formatting and percentage breakdowns |

---

## 🛠️ How to Run

### In SQL Developer
1. Run scripts in order from `01_` to `07_`
2. Use `07_pivot_with_formatting.sql` to view the enhanced pivot with percentages

### In SQL\*Plus or SQLcl

```bash
sqlplus user/pass@db @01_create_sequence.sql
sqlplus user/pass@db @02_create_table.sql
sqlplus user/pass@db @03_create_trigger.sql
sqlplus user/pass@db @04_create_index.sql
sqlplus user/pass@db @05_insert_data.sql
sqlplus user/pass@db @07_pivot_with_formatting.sql
```

---

## 📊 Output Format

Each row in the pivot shows the team’s scores for GAME1–GAME4, total score, and the percentage contribution of each game.

Example:
```
TEAM  GAME1  GAME2  GAME3  GAME4  TOTAL  GAME1_PCT  GAME2_PCT ...
TEAM1  10     20     40     50     120    8.33%      16.67%     ...
```

---

© 2025 Oracle SQL Pivot Example | Enhanced by ChatGPT

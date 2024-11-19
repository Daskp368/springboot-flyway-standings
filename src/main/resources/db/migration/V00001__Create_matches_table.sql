CREATE TABLE IF NOT EXISTS matches (
    match_id SERIAL PRIMARY KEY,
    season VARCHAR(100),
    match_date VARCHAR(100),
    home_team_id VARCHAR(100),
    away_team_id VARCHAR(100),
    home_score VARCHAR(100),
    away_score VARCHAR(100)
);
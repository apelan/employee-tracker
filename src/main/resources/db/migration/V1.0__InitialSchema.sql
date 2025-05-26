CREATE TABLE "teams" (
    "id" SERIAL PRIMARY KEY,
    "name" VARCHAR(100) NOT NULL UNIQUE,
    "team_lead_id" BIGINT -- let's assume one lead per team
);

CREATE TABLE "employees" (
    "id" SERIAL PRIMARY KEY,
    "name" VARCHAR(100) NOT NULL,
    "team_id" BIGINT
);

ALTER TABLE "teams"
ADD CONSTRAINT "fk_teams_team_lead"
FOREIGN KEY ("team_lead_id")
REFERENCES "employees"("id");

ALTER TABLE "employees"
ADD CONSTRAINT "fk_employees_team"
FOREIGN KEY ("team_id")
REFERENCES "teams"("id")
ON DELETE SET NULL;
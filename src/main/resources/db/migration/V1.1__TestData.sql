INSERT INTO "teams" ("name")
VALUES ('Development');

INSERT INTO "employees" ("name", "team_id")
VALUES ('Mirko', (SELECT "id" FROM "teams" WHERE "name" = 'Development')),
('Predrag', (SELECT "id" FROM "teams" WHERE "name" = 'Development')),
('Petar', (SELECT "id" FROM "teams" WHERE "name" = 'Development')),
('Vojislav', (SELECT "id" FROM "teams" WHERE "name" = 'Development'));

UPDATE "teams"
SET "team_lead_id"=(SELECT "id" FROM "employees" WHERE "name" = 'Mirko')
WHERE "name" = 'Development';
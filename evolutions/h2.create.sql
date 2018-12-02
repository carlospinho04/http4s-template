CREATE TABLE IF NOT EXISTS "item" (
  "id"       BIGSERIAL PRIMARY KEY NOT NULL,
  "name"     TEXT                  NOT NULL,
  "price"    BIGINT                NOT NULL
);
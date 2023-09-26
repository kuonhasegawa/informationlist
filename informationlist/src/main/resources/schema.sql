CREATE TABLE IF NOT EXISTS tasklist(
    id VARCHAR(8)   PRIMARY KEY,
    information VARCHAR(5),
    task VARCHAR(256),
    deadline VARCHAR(20),
);
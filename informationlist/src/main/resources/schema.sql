CREATE TABLE IF NOT EXISTS tasklist(
    id VARCHAR(15)   PRIMARY KEY,
    information VARCHAR(5),
    task VARCHAR(256),
    content_id VARCHAR(5),
    deadline VARCHAR(20),
    member_id VARCHAR(8)
);
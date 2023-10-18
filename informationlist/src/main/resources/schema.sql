CREATE TABLE IF NOT EXISTS tasklist(
    id VARCHAR(15)   PRIMARY KEY,
    task VARCHAR(256),
    content_id VARCHAR(5),
    deadline VARCHAR(20),
    member_id VARCHAR(8)
);
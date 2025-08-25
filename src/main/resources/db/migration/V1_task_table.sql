--V1: initial schema for tasks
CREATE TABLE IF NOT EXISTS tasks (
     id BIGSERIAL PRIMARY KEY,
     title VARCHAR(255) NOT NULL,
    description TEXT,
    due_date DATE,
    status VARCHAR(50)
    );

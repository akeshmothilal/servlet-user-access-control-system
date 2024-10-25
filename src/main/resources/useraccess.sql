CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
    role TEXT CHECK (role IN ('Employee', 'Manager', 'Admin')) NOT NULL
);


select * from users;


INSERT INTO users (username, password, role) VALUES 
('peter', 'admin', 'Admin'),
('nick', 'manager', 'Manager');



CREATE TABLE software (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    description TEXT,
    access_levels TEXT NOT NULL
);


select * from software;

CREATE TABLE requests (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL REFERENCES users(id),
    software_id INT NOT NULL REFERENCES software(id), 
    access_type TEXT CHECK (access_type IN ('Read', 'Write', 'Admin')) NOT NULL,
    reason TEXT NOT NULL,
    status TEXT CHECK (status IN ('Pending', 'Approved', 'Rejected')) NOT NULL DEFAULT 'Pending'
);
select * from requests;



CREATE TABLE users(
    id uuid default gen_random_uuid(),
    email text not null unique,
    password text not null,
    role TEXT NOT NULL CHECK (role IN ('USER', 'ADMIN')),
    primary key (id)
)
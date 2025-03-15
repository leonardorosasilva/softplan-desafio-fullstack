CREATE TABLE users (
   id uuid DEFAULT gen_random_uuid(),
   login TEXT NOT NULL UNIQUE,
   password TEXT NOT NULL,
   role TEXT NOT NULL CHECK (role IN ('USER', 'ADMIN')),
   primary key (id)
);
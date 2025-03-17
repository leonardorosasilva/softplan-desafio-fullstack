CREATE TABLE process(
    id uuid default gen_random_uuid(),
    title text not null,
    description text,
    foreign key (user_id)  references  users(id) on delete cascade
)
create table users (
  id                    bigserial,
  username              varchar(30) not null unique,
  password              varchar(80) not null,
  email                 varchar(50) unique,
  primary key (id)
);

create table roles (
  id                    serial,
  name                  varchar(50) not null,
  primary key (id)
);

CREATE TABLE users_roles (
  user_id               bigint not null,
  role_id               int not null,
  primary key (user_id, role_id),
  foreign key (user_id) references users (id),
  foreign key (role_id) references roles (id)
);

insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN');


insert into users (username, password, email)
values
('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com'),
('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'admin@gmail.com');

insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 2);


CREATE TABLE task (
    id bigserial PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(50) NOT NULL CHECK (status IN ('Pending', 'In Progress', 'Completed')),
    priority VARCHAR(50) NOT NULL CHECK (priority IN ('High', 'Medium', 'Low')),
    author_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    assignee_id INT REFERENCES users(id) ON DELETE SET NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE comment (
    id SERIAL PRIMARY KEY,
    task_id INT NOT NULL REFERENCES task(id) ON DELETE CASCADE,
    author_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    text TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_task_author_id ON task(author_id);
CREATE INDEX idx_task_assignee_id ON task(assignee_id);
CREATE INDEX idx_comment_task_id ON comment(task_id);
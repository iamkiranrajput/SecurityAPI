Create table users(username varchar(50) not null, password varchar(100) not null );
CREATE TABLE authorities (
  username VARCHAR(255) NOT NULL,
  authority VARCHAR(255) NOT NULL,
  PRIMARY KEY (username, authority)  -- Define username and authority as the composite primary key
);
create unique index ix_auth_username on authorities (username, authority);

create table stud(id int);
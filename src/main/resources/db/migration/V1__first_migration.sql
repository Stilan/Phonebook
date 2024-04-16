create table PhoneNumber (
    id uuid not null,
    dateOfCreation date,
    dateOfDeletion date,
    isDeleted boolean not null,
    number varchar(255),
    user_id uuid,
    primary key (id)
                         );

create table users (
    id uuid not null,
    isDeleted boolean not null,
    name varchar(255),
    primary key (id)
                   );
alter table if exists PhoneNumber add constraint FK4bjp1f3y42c6xbcqf70dmhjoo foreign key (user_id) references users;

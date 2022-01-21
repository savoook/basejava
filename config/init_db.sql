create table if not exists resume
(
    uuid      char(36) not null
        constraint resume_pk
            primary key,
    full_name text
);

alter table resume
    owner to postgres;


create table contact
(
    id          serial,
    type        text     not null,
    value       text     not null,
    resume_uuid char(36) not null
        constraint resume_uuid
            references resume
            on delete cascade
);

create unique index contact_uuid_type_index
    on contact (resume_uuid, type);


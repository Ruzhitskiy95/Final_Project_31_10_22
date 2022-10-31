create table training_records_schema.medical_info
(
    id                serial,
    blood_type        integer,
    rh                varchar(2),
    creation_date     timestamp(6) not null,
    modification_date timestamp(6) not null,
    user_id           integer
        constraint medical_info_users_id_fk
            references training_records_schema.users
            on update cascade on delete cascade
);

create unique index medical_info_id_uindex
    on training_records_schema.medical_info (id);

alter table training_records_schema.medical_info
    add constraint medical_info_pk
        primary key (id);


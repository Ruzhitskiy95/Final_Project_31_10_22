create table if not exists users
(
    id                serial
        constraint users_pk
            primary key,
    user_name         varchar(50)  default 'User'::character varying    not null,
    sur_name          varchar(50)  default 'surname'::character varying not null,
    birth_date        timestamp(6),
    is_deleted        boolean      default false                        not null,
    user_login        varchar(100),
    user_password     varchar(200) default 'default_password'::character varying,
    creation_date     timestamp(6) default CURRENT_TIMESTAMP(6),
    modification_date timestamp(6) default CURRENT_TIMESTAMP(6)
);

alter table users
    owner to postgres;

create unique index if not exists users_id_uindex
    on users (id);

create table if not exists user_anthropometry
(
    id              serial
        constraint user_anthropometry_pk
            primary key,
    user_id         integer,
    date            timestamp(6) default CURRENT_TIMESTAMP(6),
    height          integer,
    weight          integer,
    biceps_size     integer,
    chest_size      integer,
    waist_size      integer,
    quadriceps_size integer,
    calf_size       integer
);

alter table user_anthropometry
    owner to postgres;

create unique index if not exists user_anthropometry_id_uindex
    on user_anthropometry (id);

create unique index if not exists user_anthropometry_id_uindex_2
    on user_anthropometry (id);

create table if not exists exercise_list
(
    id            serial
        constraint exercise_list_pk
            primary key,
    exercise_name varchar(100)
);

alter table exercise_list
    owner to postgres;

create unique index if not exists exercise_list_id_uindex
    on exercise_list (id);

create table if not exists training_program
(
    id             serial
        constraint training_program_pk
            primary key,
    training_name  varchar(50),
    exercise_one   integer
        constraint training_program_exercise_list_id_fk
            references exercise_list,
    exercise_two   integer
        constraint training_program_exercise_list_id_fk_2
            references exercise_list,
    exercise_three integer
        constraint training_program_exercise_list_id_fk_3
            references exercise_list,
    exercise_four  integer
        constraint training_program_exercise_list_id_fk_4
            references exercise_list
);

alter table training_program
    owner to postgres;

create unique index if not exists training_program_id_uindex
    on training_program (id);

create table if not exists training_journal
(
    id            serial
        constraint training_journal_pk
            primary key,
    training_data timestamp(6) default CURRENT_TIMESTAMP(6),
    user_id       integer                    not null,
    exercise_id   integer                    not null
        constraint training_journal_exercise_list_id_fk
            references exercise_list,
    sets          integer,
    reps          integer,
    weight        integer,
    is_deleted    boolean      default false not null,
    total_weight  integer generated always as (((sets * reps) * weight)) stored
);

alter table training_journal
    owner to postgres;

create unique index if not exists training_journal_id_uindex
    on training_journal (id);

create index if not exists training_journal_exercise_id_index
    on training_journal (exercise_id);

create table if not exists roles
(
    id                bigserial
        constraint roles_pk
            primary key,
    role_name         varchar(15) default 'USER'::character varying,
    creation_date     timestamp(6),
    modification_date timestamp(6)
);

alter table roles
    owner to postgres;

create unique index if not exists roles_id_uindex
    on roles (id);

create table if not exists l_role_user
(
    id      bigserial
        constraint l_role_user_pk
            primary key,
    user_id bigint not null
        constraint l_role_user_users_id_fk
            references users
            on update cascade on delete cascade,
    role_id bigint not null
        constraint l_role_user_roles_id_fk
            references roles
            on update cascade on delete cascade
);

alter table l_role_user
    owner to postgres;

create unique index  if not exists l_role_user_id_uindex
    on l_role_user (id);

create index if not exists l_role_user_user_id_role_id_index
    on l_role_user (user_id, role_id);


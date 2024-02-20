create table admins
(
    admin_id                bigint auto_increment
        primary key,
    created_date_time       datetime(6)  not null,
    last_modified_date_time datetime(6)  not null,
    encoded_password        varchar(255) not null,
    phone_number            varchar(255) not null,
    constraint admin_phone_number_index
        unique (phone_number)
);


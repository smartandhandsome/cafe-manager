create table product_categories
(
    created_date_time       datetime(6)  not null,
    last_modified_date_time datetime(6)  not null,
    product_category_id     bigint auto_increment
        primary key,
    name                    varchar(255) not null,
    constraint UK_fl075bwasjwsxybk4x174befx
        unique (name)
);


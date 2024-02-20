create table product_sizes
(
    extra_charge            int          not null,
    extra_cost              int          not null,
    created_date_time       datetime(6)  not null,
    last_modified_date_time datetime(6)  not null,
    product_info_id         bigint       null,
    product_size_id         bigint auto_increment
        primary key,
    name                    varchar(255) not null
);

create index product_size_product_info_id
    on product_sizes (product_info_id);


create table product_infos
(
    base_cost               int          not null,
    base_price              int          not null,
    created_date_time       datetime(6)  not null,
    last_modified_date_time datetime(6)  not null,
    product_category_id     bigint       not null,
    product_info_id         bigint auto_increment
        primary key,
    barcode                 varchar(255) not null,
    description             varchar(255) not null,
    expiration_duration     varchar(255) not null,
    name                    varchar(255) not null,
    name_chosung            varchar(255) not null,
    constraint product_Infos_barcode_index
        unique (barcode)
);

create index product_Infos_category_index
    on product_infos (product_category_id);

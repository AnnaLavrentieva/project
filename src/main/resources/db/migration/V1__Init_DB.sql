
create table invoices (
invoice_id varchar(255) not null,
invoice_date date,
invoice_number varchar(255),
invoice_type varchar(255),
sum float8,
primary key (invoice_id)
);

create table persons (
person_id varchar(255) not null,
password varchar(255) not null,
role varchar(255) not null,
primary key (person_id)
);

create table ware_groups (
group_id varchar(255) not null,
primary key (group_id)
);

create table ware_movement_records (
record_id varchar(255) not null,
record_date timestamp,
movement varchar(255),
person_id varchar(255),
ware_id varchar(255),
warehouse_id varchar(255),
primary key (record_id)
);

create table warehouses (
warehouse_id varchar(255) not null,
primary key (warehouse_id)
);

create table wares (
ware_type varchar(31) not null,
ware_id varchar(255) not null,
amount int4 not null,
deployment_date date,
ware_name varchar(255),
inventory_number varchar(255),
price float8,
production_year int4,
serial_number varchar(255),
person_id varchar(255),
group_id varchar(255),
warehouse_id varchar(255),
invoice_id varchar(255),
primary key (ware_id)
);
alter table if exists ware_movement_records add
constraint ware_movement_records_persons_fk
foreign key (person_id) references persons;

alter table if exists ware_movement_records
add constraint ware_movement_records_wares_fk
foreign key (ware_id) references wares;

alter table if exists ware_movement_records
add constraint ware_movement_records_warehouses_fk
foreign key (warehouse_id) references warehouses;

alter table if exists wares
add constraint wares_persons_fk
foreign key (person_id) references persons;

alter table if exists wares
add constraint wares_ware_groups_fk
foreign key (group_id) references ware_groups;

alter table if exists wares
add constraint wares_warehouses_fk
foreign key (warehouse_id) references warehouses;

alter table if exists wares
add constraint wares_invoices_fk
foreign key (invoice_id) references invoices;
create table if not exists type_train(
    id_type serial primary key,
    name_type varchar
);

create table if not exists stations(
    id_station serial primary key,
    name_station varchar
);

create table if not exists trains(
           id_train serial primary key,
           name_train varchar,
           type_train_id int references type_train(id_type) on delete cascade,
           departure_station_id int references stations(id_station) on delete cascade,
           arrival_station_id int references stations(id_station) on delete cascade,
           date_time_departure date,
           date_time_arrival date,
           available_ticket int,
           total_ticket int,
           price_ticket float
);

create table if not exists client_railway(
           id_client serial primary key,
           id_train int references trains(id_train) on delete cascade,
           name_client varchar,
           soname_client varchar,
           date_purchase date,
           phone_client varchar
);

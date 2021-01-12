INSERT INTO type_train
(id_type, name_type)
SELECT 1, 'Econom'
    WHERE
    NOT EXISTS (
        SELECT id_type FROM type_train WHERE id_type = 1
    );

INSERT INTO type_train
(id_type, name_type)
SELECT 2, 'Business'
    WHERE
    NOT EXISTS (
        SELECT id_type FROM type_train WHERE id_type = 2
    );

INSERT INTO type_train
(id_type, name_type)
SELECT 3, 'Coupe'
    WHERE
    NOT EXISTS (
        SELECT id_type FROM type_train WHERE id_type = 3
    );

INSERT INTO type_train
(id_type, name_type)
SELECT 4, 'Reserved seat'
    WHERE
    NOT EXISTS (
        SELECT id_type FROM type_train WHERE id_type = 4
    );

INSERT INTO stations
(id_station, name_station)
SELECT 1, 'Brest'
    WHERE
    NOT EXISTS (
        SELECT id_station FROM stations WHERE id_station = 1
    );

INSERT INTO stations
(id_station, name_station)
SELECT 2, 'Minsk'
    WHERE
    NOT EXISTS (
        SELECT id_station FROM stations WHERE id_station = 2
    );

INSERT INTO stations
(id_station, name_station)
SELECT 3, 'Grodno'
    WHERE
    NOT EXISTS (
        SELECT id_station FROM stations WHERE id_station = 3
    );

INSERT INTO stations
(id_station, name_station)
SELECT 4, 'Vitebsk'
    WHERE
    NOT EXISTS (
        SELECT id_station FROM stations WHERE id_station = 4
    );

INSERT INTO stations
(id_station, name_station)
SELECT 5, 'Mogilev'
    WHERE
    NOT EXISTS (
        SELECT id_station FROM stations WHERE id_station = 5
    );

INSERT INTO stations
(id_station, name_station)
SELECT 6, 'Gomel'
    WHERE
    NOT EXISTS (
        SELECT id_station FROM stations WHERE id_station = 6
    );


insert into trains(id_train, name_train, type_train_id, departure_station_id, arrival_station_id,
                   date_time_departure, date_time_arrival, available_ticket, total_ticket, price_ticket)
select 123,'TestTrain',1,1,2,'2020/09/12','2020/09/13',123,200,23.3
 where not exists (select id_train from trains where id_train = 123)
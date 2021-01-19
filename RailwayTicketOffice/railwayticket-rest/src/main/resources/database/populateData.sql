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


INSERT INTO trains(id_train, name_train, type_train_id, departure_station_id, arrival_station_id,
                   date_time_departure, date_time_arrival, available_ticket, total_ticket, price_ticket)
SELECT 23,'Katrins',2,1,2,'2021/01/28','2021/02/02',100,100,23.3
WHERE NOT EXISTS (SELECT id_train FROM trains WHERE id_train = 23);

INSERT INTO trains(id_train, name_train, type_train_id, departure_station_id, arrival_station_id,
                   date_time_departure, date_time_arrival, available_ticket, total_ticket, price_ticket)
SELECT 24,'Matrix',2,2,5,'2021/01/24','2021/01/26',50,50,30.3
    WHERE NOT EXISTS (SELECT id_train FROM trains WHERE id_train = 24);

INSERT INTO trains(id_train, name_train, type_train_id, departure_station_id, arrival_station_id,
                   date_time_departure, date_time_arrival, available_ticket, total_ticket, price_ticket)
SELECT 25,'Joris',2,5,1,'2021/02/15','2021/02/17',123,200,23.3
    WHERE NOT EXISTS (SELECT id_train FROM trains WHERE id_train = 25);

INSERT INTO trains(id_train, name_train, type_train_id, departure_station_id, arrival_station_id,
                   date_time_departure, date_time_arrival, available_ticket, total_ticket, price_ticket)
SELECT 26,'Zexis',3,6,3,'2021/03/21','2021/03/23',120,120,45.3
    WHERE NOT EXISTS (SELECT id_train FROM trains WHERE id_train = 26);

INSERT INTO trains(id_train, name_train, type_train_id, departure_station_id, arrival_station_id,
                   date_time_departure, date_time_arrival, available_ticket, total_ticket, price_ticket)
SELECT 27,'Astra',1,4,6,'2021/05/23','2021/05/25',123,200,23.3
    WHERE NOT EXISTS (SELECT id_train FROM trains WHERE id_train = 27);

INSERT INTO trains(id_train, name_train, type_train_id, departure_station_id, arrival_station_id,
                   date_time_departure, date_time_arrival, available_ticket, total_ticket, price_ticket)
SELECT 28,'Joris',2,3,4,'2021/01/23','2021/02/25',123,200,23.3
    WHERE NOT EXISTS (SELECT id_train FROM trains WHERE id_train = 28);
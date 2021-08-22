INSERT INTO trains(id_train, name_train, type_train_id, departure_station_id, arrival_station_id,
                   date_time_departure, date_time_arrival, available_ticket, total_ticket, price_ticket)
SELECT 12345,'TestTrain',1,1,2,'2020/09/12','2020/09/13',123,200,23.3
    WHERE NOT EXISTS (SELECT id_train FROM trains WHERE id_train = 12345);

INSERT INTO trains(id_train, name_train, type_train_id, departure_station_id, arrival_station_id,
                   date_time_departure, date_time_arrival, available_ticket, total_ticket, price_ticket)
SELECT 127,'TestTrain',1,1,2,'2020/09/12','2020/09/13',123,200,23.3
    WHERE NOT EXISTS (SELECT id_train FROM trains WHERE id_train = 127);

INSERT INTO trains(id_train, name_train, type_train_id, departure_station_id, arrival_station_id,
                   date_time_departure, date_time_arrival, available_ticket, total_ticket, price_ticket)
SELECT 123,'TestTrain',1,1,2,'2020/09/12','2020/09/13',123,200,23.3
WHERE NOT EXISTS (SELECT id_train FROM trains WHERE id_train = 123);

INSERT INTO trains(id_train, name_train, type_train_id, departure_station_id, arrival_station_id,
                   date_time_departure, date_time_arrival, available_ticket, total_ticket, price_ticket)
SELECT 124,'TestTrain',1,1,2,'2020/09/12','2020/09/13',123,200,23.3
    WHERE NOT EXISTS (SELECT id_train FROM trains WHERE id_train = 124);

INSERT INTO trains(id_train, name_train, type_train_id, departure_station_id, arrival_station_id,
                   date_time_departure, date_time_arrival, available_ticket, total_ticket, price_ticket)
SELECT 322,'TestTrain',1,1,2,'2020/09/12','2020/09/13',123,200,23.3
    WHERE NOT EXISTS (SELECT id_train FROM trains WHERE id_train = 322);

insert into client_railway(id_client, id_train, name_client, soname_client, date_purchase, phone_client)
SELECT 777,322,'DeleteUser','TestClient','2020/09/12','+375333231231' WHERE NOT EXISTS
(SELECT id_client FROM client_railway WHERE id_client = 777);

insert into client_railway(id_client, id_train, name_client, soname_client, date_purchase, phone_client)
SELECT 778,322,'DeleteUser','TestClient','2020/09/12','+375333231231' WHERE NOT EXISTS
(SELECT id_client FROM client_railway WHERE id_client = 778);

insert into client_railway(id_client, id_train, name_client, soname_client, date_purchase, phone_client)
SELECT 123,127,'TestClient','TestClient','2020/09/12','+375333231231' WHERE NOT EXISTS (SELECT id_client FROM client_railway WHERE id_client = 123);

insert into client_railway(id_client, id_train, name_client, soname_client, date_purchase, phone_client)
SELECT 321,322,'TestClient','TestClient','2020/09/12','+375333231231' WHERE NOT EXISTS (SELECT id_client FROM client_railway WHERE id_client = 321);

insert into client_railway(id_client, id_train, name_client, soname_client, date_purchase, phone_client)
SELECT 421,127,'TestClient','TestClient','2020/09/12','+375333231231' WHERE NOT EXISTS (SELECT id_client FROM client_railway WHERE id_client = 421);

insert into client_railway(id_client, id_train, name_client, soname_client, date_purchase, phone_client)
SELECT 521,127,'TestClient','TestClient','2020/09/12','+375333231231' WHERE NOT EXISTS (SELECT id_client FROM client_railway WHERE id_client = 521);
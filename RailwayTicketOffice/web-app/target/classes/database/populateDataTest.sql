INSERT INTO trains(id_train, name_train, type_train_id, departure_station_id, arrival_station_id,
                   date_time_departure, date_time_arrival, available_ticket, total_ticket, price_ticket)
SELECT 1234,'TestTrain',1,1,2,'2020/09/12','2020/09/13',123,200,23.3
    WHERE NOT EXISTS (SELECT id_train FROM trains WHERE id_train = 1234);

INSERT INTO client_railway(id_client, id_train, name_client, soname_client, date_purchase, phone_client)
SELECT 123,123,'TestClient','TestClient','2020/09/12','+375333231231' WHERE NOT EXISTS (SELECT id_client FROM client_railway WHERE id_client = 123);
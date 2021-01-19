package com.railwayticket.restclient.service;

import com.railwayticket.restclient.dao.dao_api.TrainDaoApi;
import com.railwayticket.restclient.domain.Stations;
import com.railwayticket.restclient.domain.Trains;
import com.railwayticket.restclient.service.service_api.TrainServiceApi;
import com.railwayticket.restclient.dao.dao_api.ClientDaoApi;
import com.railwayticket.restclient.service.exception.TrainServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TrainServiceApiApiImplementation implements TrainServiceApi {

    private final TransactionTemplate transactionTemplate;
    @Qualifier("TrainDaoApiImplementation")
    @Autowired
    private TrainDaoApi trainDaoApi;

    @Qualifier("ClientDaoApiImplementation")
    @Autowired
    private ClientDaoApi clientDaoApi;

    final static Logger logger = Logger.getLogger(TrainServiceApiApiImplementation.class);

    public TrainServiceApiApiImplementation(PlatformTransactionManager transactionManager) {
        this.transactionTemplate = new TransactionTemplate(transactionManager);
        this.transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_UNCOMMITTED);
        this.transactionTemplate.setTimeout(60);
    }

    @Override
    public boolean save(Trains trains) throws TrainServiceException{
        TransactionDefinition definition =
                new DefaultTransactionDefinition();
        TransactionStatus status = Objects.requireNonNull(transactionTemplate.getTransactionManager()).getTransaction(definition);
        try{
            if(trains!=null) {
                    logger.info("Save train successfully. Name: " + trains.getName_train() +
                            " station departure: " + trains.getDepartureStation().getNameStation() + " station arrival: "
                            + trains.getArrivalStation().getNameStation() + " Time: " + new Date().toString());
                    return trainDaoApi.save(trains);
            }else{
                logger.error("Train for save equal null" + " Time: " + new Date().toString());
                throw new TrainServiceException("Error train for save equal null");
            }
        }catch (TrainServiceException ex){
            transactionTemplate.getTransactionManager().rollback(status);
            logger.error("Save train unsuccessfully. Name: " + " Time: " + new Date().toString());
            return false;
        }finally {
            logger.info("Commit transaction with status: " + status + "Time: " + new Date().toString());
            transactionTemplate.getTransactionManager().commit(status);
        }
     }

    @Override
    public boolean update(Trains trains) throws TrainServiceException{
        TransactionDefinition definition =
                new DefaultTransactionDefinition();
        TransactionStatus status = Objects.requireNonNull(transactionTemplate.getTransactionManager()).getTransaction(definition);
        try{
            if(trains.getId_train()!=null) {
                logger.info("Update train successfully. Train: " + trains.toString() + " Time: " + new Date().toString());
                return trainDaoApi.update(trains);
            }else{
                logger.error("Id train equal null" + " Time: " + new Date().toString());
                throw new TrainServiceException("Id train equal null");
            }
        }catch (TrainServiceException ex){
            transactionTemplate.getTransactionManager().rollback(status);
            logger.error("Update train unsuccessfully. Train: "+ trains.toString() + " Time: " + new Date().toString());
            return false;
        }finally {
            logger.info("Commit transaction with status: " + status + "Time: " + new Date().toString());
            transactionTemplate.getTransactionManager().commit(status);
        }
     }

    @Override
    public boolean delete(Trains trains) throws TrainServiceException{
        TransactionDefinition definition =
                new DefaultTransactionDefinition();
        TransactionStatus status = Objects.requireNonNull(transactionTemplate.getTransactionManager()).getTransaction(definition);
        try{
            if(trains.getId_train()!=null) {
                logger.info("Delete train successfully. Train: " + trains.toString() + " Time: " + new Date().toString());
                trainDaoApi.GetAllClientTrain(trains.getId_train()).forEach(o1->clientDaoApi.delete(clientDaoApi.getOneById(o1.getId_client())));
                return trainDaoApi.delete(trains);
            }else{
                logger.error("Id train equal null" + " Time: " + new Date().toString());
                throw new TrainServiceException("Id equal null");
            }
        }catch (TrainServiceException ex){
            transactionTemplate.getTransactionManager().rollback(status);
            logger.error("Delete train unsuccessfully. Train: "+ trains.toString() + " Time: " + new Date().toString());
            return false;
        }finally {
            logger.info("Commit transaction with status: " + status + "Time: " + new Date().toString());
            transactionTemplate.getTransactionManager().commit(status);
        }
    }

    @Override
    public Trains getOneById(Long id) throws TrainServiceException{
        TransactionDefinition definition =
                new DefaultTransactionDefinition();
        TransactionStatus status = Objects.requireNonNull(transactionTemplate.getTransactionManager()).getTransaction(definition);
        try{
            if(id!=null) {
                try {
                    Trains get_train = trainDaoApi.getOneById(id);
                    logger.info("Get one train successfully. Id train: " + get_train.toString() + " Time:" + new Date().toString());
                    return get_train;
                }catch (NullPointerException e){
                    return null;
                }
            }else{
                logger.error("Id for get train equal null" + " Time: " + new Date().toString());
                throw new TrainServiceException("Id for get train equal null");
            }
        }catch (TrainServiceException ex){
            transactionTemplate.getTransactionManager().rollback(status);
            logger.error("Get one train unsuccessfully." + "result - null"  + " Time:" + new Date().toString());
            return null;
        }finally {
            logger.info("Commit transaction with status: " + status + "Time: " + new Date().toString());
            transactionTemplate.getTransactionManager().commit(status);
        }
     }

    @Override
    public List<Trains> FindAll() {
        TransactionDefinition definition =
                new DefaultTransactionDefinition();
        TransactionStatus status = Objects.requireNonNull(transactionTemplate.getTransactionManager()).getTransaction(definition);
        try{
            logger.info("Find all train successfully. size list: "+ 1 + " Time:" + new Date().toString());
            return trainDaoApi.FindAll();
        }catch (Exception ex){
            transactionTemplate.getTransactionManager().rollback(status);
            logger.error("Find all train unsuccessfully." + "result - null"  + " Time:" + new Date().toString());
            return null;
        }finally {
            logger.info("Commit transaction with status: " + status + "Time: " + new Date().toString());
            transactionTemplate.getTransactionManager().commit(status);
        }
    }

    @Override
    public List<Trains> FindAllByDateDepartureArrivalStations(java.sql.Date date_departure, java.sql.Date date_arrival, Stations departure, Stations arrival)throws TrainServiceException{
        TransactionDefinition definition =
                new DefaultTransactionDefinition();
        TransactionStatus status = Objects.requireNonNull(transactionTemplate.getTransactionManager()).getTransaction(definition);
        try{
            if(date_arrival!=null && date_departure!=null && !departure.equals(arrival)) {
                logger.info("Find all by date and stations train successfully. size list: " + trainDaoApi.FindAllByDateDepartureArrivalStations(date_departure,date_arrival,departure,arrival).size() + " Time:" + new Date().toString());
                return trainDaoApi.FindAllByDateDepartureArrivalStations(date_departure,date_arrival,departure,arrival);
            }else {
                logger.error("Date eqaul null and error in stations" + " Time: " + new Date().toString());
                throw new TrainServiceException("Date eqaul null and error in stations");
            }
        }catch (TrainServiceException ex){
            transactionTemplate.getTransactionManager().rollback(status);
            logger.error("Find all by date and stations  train unsuccessfully." + "result - null"  + " Time:" + new Date().toString());
            return null;
        }finally {
            logger.info("Commit transaction with status: " + status + "Time: " + new Date().toString());
            transactionTemplate.getTransactionManager().commit(status);
        }
     }
}

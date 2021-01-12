package com.railwayticket.service;

import com.railwayticket.domain.Stations;
import com.railwayticket.domain.Trains;
import com.railwayticket.service.exception.TrainServiceException;
import com.railwayticket.service.servic_api.TrainServiceApi;
import org.apache.log4j.Logger;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

public class TrainServiceApiApiImplementation implements TrainServiceApi {

    private final TransactionTemplate transactionTemplate;

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
                //Dao executed here
                logger.info("Save train successfully. Name: " + trains.getName_train() +
                        " station departure: " + trains.getDepartureStation().getNameStation() + " station arrival: "
                        + trains.getArrivalStation().getNameStation() + " Time: " + new Date().toString());
                return true;
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
                //Dao executed here
                logger.info("Update train successfully. Train: " + trains.toString() + " Time: " + new Date().toString());
                return true;
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
                return true;
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
                //Dao executed here
                logger.info("Get one train successfully. Id train: " + new Trains().toString() + " Time:" + new Date().toString());
                return new Trains();
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
    public Optional<Trains> FindAll() {
        TransactionDefinition definition =
                new DefaultTransactionDefinition();
        TransactionStatus status = Objects.requireNonNull(transactionTemplate.getTransactionManager()).getTransaction(definition);
        try{
            //Dao executed here
            logger.info("Find all train successfully. size list: "+ 1 + " Time:" + new Date().toString());
            return Optional.of(new Trains());
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
    public Optional<Trains> FindAllByDateDepartureArrivalStations(java.sql.Date date, Stations departure, Stations arrival)throws TrainServiceException{
        TransactionDefinition definition =
                new DefaultTransactionDefinition();
        TransactionStatus status = Objects.requireNonNull(transactionTemplate.getTransactionManager()).getTransaction(definition);
        try{
            if(date!=null && !departure.equals(arrival)) {
                //Dao executed here
                logger.info("Find all by date and stations train successfully. size list: " + 1 + " Time:" + new Date().toString());
                return Optional.of(new Trains());
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

package com.railwayticket.service;

import com.railwayticket.HomeController;
import com.railwayticket.domain.ClientRailway;
import com.railwayticket.domain.Trains;
import com.railwayticket.service.exception.ClientServiceException;
import com.railwayticket.service.exception.TrainServiceException;
import com.railwayticket.service.servic_api.ClientServiceApi;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

public class ClientServiceApiImplementation implements ClientServiceApi {

    private final TransactionTemplate transactionTemplate;

    final static Logger logger = Logger.getLogger(ClientServiceApiImplementation.class);

    public ClientServiceApiImplementation(PlatformTransactionManager transactionManager) {
        this.transactionTemplate = new TransactionTemplate(transactionManager);
        this.transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_UNCOMMITTED);
        this.transactionTemplate.setTimeout(60);
    }
    @Override
    public Optional<ClientRailway> FindByNameClient(String name_client) throws ClientServiceException {
        TransactionDefinition definition =
                new DefaultTransactionDefinition();
        TransactionStatus status = Objects.requireNonNull(transactionTemplate.getTransactionManager()).getTransaction(definition);
        try{
            if(name_client!=null) {
                //Dao executed here
                logger.info("Find client by name successfully. Name: " + name_client + " Time: " + new Date().toString());
                return Optional.of(new ClientRailway());
            }else{
                logger.error("Name for find client equal null" + " Time: " + new Date().toString());
                throw new ClientServiceException("Error name for find client equal null");
            }
        }catch (ClientServiceException ex){
            transactionTemplate.getTransactionManager().rollback(status);
            logger.error("Find client by name unsuccessfully. Name: null" + " Time: " + new Date().toString());
            return Optional.empty();
        }finally {
            logger.info("Commit transaction with status: " + status + "Time: " + new Date().toString());
            transactionTemplate.getTransactionManager().commit(status);
        }
     }

    @Override
    public boolean save(ClientRailway clientRailway) throws ClientServiceException {
        TransactionDefinition definition =
                new DefaultTransactionDefinition();
        TransactionStatus status = Objects.requireNonNull(transactionTemplate.getTransactionManager()).getTransaction(definition);
        try{
            if(clientRailway!=null) {
                //Dao executed here
                logger.info("Save client successfully. Client Name: " + clientRailway.getName_client()+ " id: "+clientRailway.getId_client() + "Time: " + new Date().toString());
                return true;
            }else{
                logger.error("Client for save equal null" + " Time: " + new Date().toString());
                throw new ClientServiceException("Error client for save equal null");
            }
        }catch (ClientServiceException ex){
            transactionTemplate.getTransactionManager().rollback(status);
            logger.error("Save client successfully. Client: null" + " Time: " + new Date().toString());
            return false;
        }finally {
            logger.info("Commit transaction with status: " + status + "Time: " + new Date().toString());
            transactionTemplate.getTransactionManager().commit(status);
        }
    }

    @Override
    public boolean update(ClientRailway clientRailway) throws ClientServiceException{
        TransactionDefinition definition =
                new DefaultTransactionDefinition();
        TransactionStatus status = Objects.requireNonNull(transactionTemplate.getTransactionManager()).getTransaction(definition);
        try{
            if(clientRailway.getId_client()!=null) {
                //Dao executed here
                logger.info("Update client successfully. Client Name: " + clientRailway.getName_client()+ " id: "+clientRailway.getId_client() + "Time: " + new Date().toString());
                return true;
            }else{
                logger.error("id Client for update equal null" + " Time: " + new Date().toString());
                throw new ClientServiceException("Error id client for update equal null");
            }
        }catch (ClientServiceException ex){
            transactionTemplate.getTransactionManager().rollback(status);
            logger.error("Update client unsuccessfully. id Client: null" + " Time: " + new Date().toString());
            return false;
        }finally {
            logger.info("Commit transaction with status: " + status + "Time: " + new Date().toString());
            transactionTemplate.getTransactionManager().commit(status);
        }
     }

    @Override
    public boolean delete(ClientRailway clientRailway) throws ClientServiceException{
        TransactionDefinition definition =
                new DefaultTransactionDefinition();
        TransactionStatus status = Objects.requireNonNull(transactionTemplate.getTransactionManager()).getTransaction(definition);
        try{
            if(clientRailway.getId_client()!=null) {
                //Dao executed here
                logger.info("Delete client successfully. Client Name: " + clientRailway.getName_client()+ " id: "+clientRailway.getId_client() + "Time: " + new Date().toString());
                return true;
            }else{
                logger.error("id Client for delete equal null" + " Time: " + new Date().toString());
                throw new ClientServiceException("Error id client for delete equal null");
            }
        }catch (ClientServiceException ex){
            transactionTemplate.getTransactionManager().rollback(status);
            logger.error("Delete client unsuccessfully. id Client: null" + " Time: " + new Date().toString());
            return false;
        }finally {
            logger.info("Commit transaction with status: " + status + "Time: " + new Date().toString());
            transactionTemplate.getTransactionManager().commit(status);
        }
    }

    @Override
    public ClientRailway getOneById(Long id) throws ClientServiceException{
        TransactionDefinition definition =
                new DefaultTransactionDefinition();
        TransactionStatus status = Objects.requireNonNull(transactionTemplate.getTransactionManager()).getTransaction(definition);
        try{
            if(id!=null) {
                //Dao executed here
                ClientRailway clientRailway = new ClientRailway();
                clientRailway.setName_client("Temp");
                clientRailway.setId_client(1L);
                logger.info("Get one client by id successfully. Client Name: " + clientRailway.getName_client()+ " id: "+clientRailway.getId_client() + "Time: " + new Date().toString());
                return clientRailway;
            }else{
                logger.error("id Client for get one equal null" + " Time: " + new Date().toString());
                throw new ClientServiceException("Error id client for get one by id equal null");
            }
        }catch (ClientServiceException ex){
            transactionTemplate.getTransactionManager().rollback(status);
            logger.error("Get one client by id unsuccessfully. id Client: null" + " Time: " + new Date().toString());
            return null;
        }finally {
            logger.info("Commit transaction with status: " + status + "Time: " + new Date().toString());
            transactionTemplate.getTransactionManager().commit(status);
        }
     }

    @Override
    public Optional<ClientRailway> FindAll(){
        TransactionDefinition definition =
                new DefaultTransactionDefinition();
        TransactionStatus status = Objects.requireNonNull(transactionTemplate.getTransactionManager()).getTransaction(definition);
        try{
            //Dao executed here
            logger.info("Find all client successfully. size list: "+ 1 + " Time:" + new Date().toString());
            return Optional.of(new ClientRailway());
        }catch (Exception ex){
            transactionTemplate.getTransactionManager().rollback(status);
            logger.error("Find all client unsuccessfully." + "result - null"  + " Time:" + new Date().toString());
            return null;
        }finally {
            logger.info("Commit transaction with status: " + status + "Time: " + new Date().toString());
            transactionTemplate.getTransactionManager().commit(status);
        }
    }
}

package com.railwayticket.restclient.service;

import com.railwayticket.restclient.dao.dao_api.ClientDaoApi;
import com.railwayticket.restclient.domain.ClientRailway;
import com.railwayticket.restclient.service.exception.ClientServiceException;
import com.railwayticket.restclient.service.service_api.ClientServiceApi;
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

public class ClientServiceApiImplementation implements ClientServiceApi {

    private final TransactionTemplate transactionTemplate;

    @Qualifier("ClientDaoApiImplementation")
    @Autowired
    private ClientDaoApi clientDao;

    final static Logger logger = Logger.getLogger(ClientServiceApiImplementation.class);

    public ClientServiceApiImplementation(PlatformTransactionManager transactionManager) {
        this.transactionTemplate = new TransactionTemplate(transactionManager);
        this.transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_UNCOMMITTED);
        this.transactionTemplate.setTimeout(60);
     }
    @Override
    public List<ClientRailway> FindByNameClient(String name_client) throws ClientServiceException {
        TransactionDefinition definition =
                new DefaultTransactionDefinition();
        TransactionStatus status = Objects.requireNonNull(transactionTemplate.getTransactionManager()).getTransaction(definition);
        try{
            if(name_client!=null) {
                logger.info("Find client by name successfully. Name: " + name_client + " Time: " + new Date().toString());
                return clientDao.FindByName(name_client);
            }else{
                logger.error("Name for find client equal null" + " Time: " + new Date().toString());
                throw new ClientServiceException("Error name for find client equal null");
            }
        }catch (ClientServiceException ex){
            transactionTemplate.getTransactionManager().rollback(status);
            logger.error("Find client by name unsuccessfully. Name: null" + " Time: " + new Date().toString());
            return null;
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
                logger.info("Save client successfully. Client Name: " + clientRailway.getName_client()+ " id: "+clientRailway.getId_client() + "Time: " + new Date().toString());
                return clientDao.save(clientRailway);
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
                logger.info("Update client successfully. Client Name: " + clientRailway.getName_client()+ " id: "+clientRailway.getId_client() + "Time: " + new Date().toString());
                return clientDao.update(clientRailway);
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
                logger.info("Delete client successfully. Client Name: " + clientRailway.getName_client()+ " id: "+clientRailway.getId_client() + "Time: " + new Date().toString());
                return clientDao.delete(clientRailway);
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
                try {
                    ClientRailway clientRailway = clientDao.getOneById(id);
                    logger.info("Get one client by id successfully. Client Name: " + clientRailway.getName_client() + " id: " + clientRailway.getId_client() + "Time: " + new Date().toString());
                    return clientRailway;
                }catch (NullPointerException nullPointerException){
                    return null;
                }
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
    public List<ClientRailway> FindAll(){
        TransactionDefinition definition =
                new DefaultTransactionDefinition();
        TransactionStatus status = Objects.requireNonNull(transactionTemplate.getTransactionManager()).getTransaction(definition);
        try{
            List<ClientRailway>clientRailways = clientDao.FindAll();
            logger.info("Find all client successfully. size list: "+ clientRailways.size() + " Time:" + new Date().toString());
            return clientRailways;
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

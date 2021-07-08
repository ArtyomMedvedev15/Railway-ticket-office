package com.railwayticket.dao;


import com.domain.ClientRailway;
import com.railwayticket.dao.mapper.ClientMapper;

import com.railwayticket.dao_api.ClientDaoApi;
import com.railwayticket.dao_api.sql_annotation.SqlQuery;
import com.railwayticket.dao_api.sql_annotation.SqlQueryImpl;
import org.apache.log4j.Logger;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

public class ClientDaoImplementation extends SqlQueryImpl implements ClientDaoApi {

    private JdbcTemplate databaseQuery;

    @SqlQuery(sqlfilename = "sql/clients/findallclient.sql")
    public String SQL_FINDALL_CLIENT;

    @SqlQuery(sqlfilename = "sql/clients/insertclient.sql")
    public String SQL_INSERT_CLIENT;

    @SqlQuery(sqlfilename = "sql/clients/updateclient.sql")
    public String SQL_UPDATE_CLIENT;

    @SqlQuery(sqlfilename = "sql/clients/deleteclient.sql")
    public String SQL_DELETE_CLIENT;

    @SqlQuery(sqlfilename = "sql/clients/getoneclient.sql")
    public String SQL_GETONE_CLIENT;

    @SqlQuery(sqlfilename = "sql/clients/findclientbyname.sql")
    public String SQL_FINDBYNAME_CLIENT;

    final static Logger logger = Logger.getLogger(ClientDaoImplementation.class);

    public ClientDaoImplementation(DataSource dataSource) {
        this.databaseQuery = new JdbcTemplate(dataSource);
    }

    public ClientDaoImplementation() {
    }

    @Override
    public boolean save(ClientRailway clientRailway) {
         logger.info("Save new client." + "Client name: " + clientRailway.getName_client() +  " Time: " +  new Date().toString());
        return databaseQuery.update(SQL_INSERT_CLIENT,clientRailway.getId_train(),clientRailway.getName_client(),clientRailway.getSoname_client(),clientRailway.getDate_purchase(),clientRailway.getPhone_client())>0;
    }

    @Override
    public boolean update(ClientRailway clientRailway) {
         logger.info("Update client info. " + " Client id: " + clientRailway.getId_client() + " Time: " +  new Date().toString());
        return databaseQuery.update(SQL_UPDATE_CLIENT,clientRailway.getId_train(),clientRailway.getName_client(),clientRailway.getSoname_client(),clientRailway.getDate_purchase(),clientRailway.getPhone_client(),clientRailway.getId_client())>0;
    }

    @Override
    public boolean delete(ClientRailway clientRailway) {
         logger.info("Delete client. " + clientRailway.toString() +" Time: " + new Date().toString());
        return databaseQuery.update(SQL_DELETE_CLIENT,clientRailway.getId_client())>0;
    }

    @Override
    public ClientRailway getOneById(Long id) {
        try {
             logger.info("Get one client. " + " id client: " + id + " Time: " + new Date().toString());
            return databaseQuery.queryForObject(SQL_GETONE_CLIENT, new ClientMapper(), id);
        }catch (EmptyResultDataAccessException resultDataAccessException){
            return null;
        }
    }

    @Override
    public List<ClientRailway> FindAll() {
        System.out.println("SQL" + SQL_FINDALL_CLIENT);
        logger.info("Find all." + " Size: " + databaseQuery.query(SQL_FINDALL_CLIENT,new ClientMapper()).size() + " Time: " + new Date().toString());
        return databaseQuery.query(SQL_FINDALL_CLIENT,new ClientMapper());
    }

    @Override
    public List<ClientRailway> FindByName(String name) {
        String patter_for_find = "%"+name+"%";
         logger.info("Find client by name. " + "Name: " + name + " Time: " + new Date().toString());
        return databaseQuery.query(SQL_FINDBYNAME_CLIENT,new ClientMapper(),patter_for_find);
    }
}

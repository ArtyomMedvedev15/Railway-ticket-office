package com.railwayticket.dao;

import com.railwayticket.dao.dao_api.ClientDaoApi;
import com.railwayticket.dao.mapper.ClientMapper;
import com.railwayticket.domain.ClientRailway;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

public class ClientDaoImplementation implements ClientDaoApi {

    private JdbcTemplate databaseQuery;

    final static Logger logger = Logger.getLogger(ClientDaoImplementation.class);

    public ClientDaoImplementation(DataSource dataSource) {
        this.databaseQuery = new JdbcTemplate(dataSource);
    }

    public ClientDaoImplementation() {
    }

    @Override
    public boolean save(ClientRailway clientRailway) {
        String sql_save_client = "insert into client_railway( id_train, name_client, soname_client, date_purchase, phone_client)" +
                "VALUES(?,?,?,?,?)";
        logger.info("Save new client." + "Client name: " + clientRailway.getName_client() +  " Time: " +  new java.util.Date().toString());
        return databaseQuery.update(sql_save_client,clientRailway.getId_train(),clientRailway.getName_client(),clientRailway.getSoname_client(),clientRailway.getDate_purchase(),clientRailway.getPhone_client())>0;
    }

    @Override
    public boolean update(ClientRailway clientRailway) {
        String sql_update_client = "UPDATE client_railway SET id_train=?,name_client=?,soname_client=?,date_purchase=?,phone_client=? WHERE id_client=?";
        logger.info("Update client info. " + " Client id: " + clientRailway.getId_client() + " Time: " +  new java.util.Date().toString());
        return databaseQuery.update(sql_update_client,clientRailway.getId_train(),clientRailway.getName_client(),clientRailway.getSoname_client(),clientRailway.getDate_purchase(),clientRailway.getPhone_client(),clientRailway.getId_client())>0;
    }

    @Override
    public boolean delete(ClientRailway clientRailway) {
        String sql_delete_client = "DELETE FROM client_railway WHERE id_client=?";
        logger.info("Delete client. " + clientRailway.toString() +" Time: " + new Date().toString());
        return databaseQuery.update(sql_delete_client,clientRailway.getId_client())>0;
    }

    @Override
    public ClientRailway getOneById(Long id) {
        String sql_get_one = "SELECT * FROM client_railway WHERE id_client=?";
        logger.info("Get one client. " + " id client: " + id + " Time: " + new Date().toString());
        return databaseQuery.queryForObject(sql_get_one,new ClientMapper(),id);
    }

    @Override
    public List<ClientRailway> FindAll() {
        String sql_find_all = "SELECT * FROM client_railway";
        logger.info("Find all." + " Size: " + databaseQuery.query(sql_find_all,new ClientMapper()).size() + " Time: " + new Date().toString());
        return databaseQuery.query(sql_find_all,new ClientMapper());
    }

    @Override
    public List<ClientRailway> FindByName(String name) {
        String patter_for_find = "%"+name+"%";
        String sql_find_by_name = "SELECT * FROM client_railway WHERE name_client LIKE ?";
        logger.info("Find client by name. " + "Name: " + name + " Time: " + new Date().toString());
        return databaseQuery.query(sql_find_by_name,new ClientMapper(),patter_for_find);
    }
}

package com.railwayticket.dao.mybatis;

import com.domain.ClientRailway;
import com.railwayticket.dao_api.ClientDaoApi;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ClientMyBatisDaoImplementation extends ClientDaoApi {

    @Insert("<script>" +
            "<if test='id_client==null'>"+
            "insert into client_railway( id_train, name_client, soname_client, date_purchase, phone_client)" +
            "VALUES(#{id_train},#{name_client},#{soname_client},#{date_purchase},#{phone_client})" +
            "</if>"+
            "<if test='id_client!=null'>"+
            "insert into client_railway(id_client,id_train, name_client, soname_client, date_purchase, phone_client)" +
            "VALUES(#{id_client},#{id_train},#{name_client},#{soname_client},#{date_purchase},#{phone_client})" +
            "</if>"+
            "</script>")
    @Override
    boolean save(ClientRailway clientRailway);

    @Update("UPDATE client_railway SET id_train=#{id_train},name_client=#{name_client},soname_client=#{soname_client},date_purchase=#{date_purchase},phone_client=#{phone_client} WHERE id_client=#{id_client}")
    @Override
    boolean update(ClientRailway clientRailway);

    @Delete("DELETE FROM client_railway WHERE id_client=#{id_client}")
    @Override
    boolean delete(ClientRailway clientRailway);

    @Select("SELECT * FROM client_railway WHERE id_client=#{id}")
    @Override
    ClientRailway getOneById(Long id);

    @Select("SELECT * FROM client_railway")
    @Override
    List<ClientRailway> FindAll();

    @Select("SELECT * FROM client_railway WHERE name_client LIKE CONCAT('%', #{name_client}, '%')")
    @Override
    List<ClientRailway> FindByName(String name_client);
}

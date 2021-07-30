package com.railwayticket.restclient.util.xml;

import com.railwayticket.restclient.domains.ClientRailway;
import com.railwayticket.restclient.util.dto.ClientsDto;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

public class ClientsXmlExporter {
    private ClientsDto clientRailwayList;

    public ClientsXmlExporter(ClientsDto clientRailwayList) {
        this.clientRailwayList = clientRailwayList;
    }

    public void ClientsXmlExporterFile(HttpServletResponse response) throws JAXBException, IOException {
         JAXBContext jaxbContext = JAXBContext.newInstance(ClientsDto.class);
         Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
         jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
         jaxbMarshaller.marshal(clientRailwayList, response.getOutputStream());
    }
}

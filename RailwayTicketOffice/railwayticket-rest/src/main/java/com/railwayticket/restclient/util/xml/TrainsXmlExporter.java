package com.railwayticket.restclient.util.xml;



import com.railwayticket.restclient.util.dto.TrainsDto;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.util.List;
import com.domain.*;

public class TrainsXmlExporter {
    private TrainsDto trainsList;

    public TrainsXmlExporter(TrainsDto trainsList) {
        this.trainsList = trainsList;
    }

    public void TrainsXmlExporterFile(HttpServletResponse response) throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(TrainsDto.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        jaxbMarshaller.marshal(trainsList, response.getOutputStream());
    }
}

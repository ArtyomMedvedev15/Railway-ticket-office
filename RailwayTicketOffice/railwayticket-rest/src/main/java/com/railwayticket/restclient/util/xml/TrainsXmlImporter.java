package com.railwayticket.restclient.util.xml;

import com.railwayticket.restclient.domains.Trains;
import com.railwayticket.restclient.util.ConvertDomain;
import com.railwayticket.restclient.util.dto.TrainsDto;
import org.springframework.web.multipart.MultipartFile;
 import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrainsXmlImporter {

    public List<com.railwayticket.restclient.domains.Trains>ImportTrainsFromXml(MultipartFile file) throws JAXBException, IOException {
        List<Trains>resultTrainsXml = new ArrayList<>();

        JAXBContext jaxbContext = JAXBContext.newInstance(TrainsDto.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        TrainsDto trainsListfromxml = (TrainsDto)jaxbUnmarshaller.unmarshal(file.getInputStream());

        for (com.domain.Trains trains : trainsListfromxml.getTrains()){
            Trains trainresult = ConvertDomain.convertDomainTrains(trains);
            resultTrainsXml.add(trainresult);
        }

        return resultTrainsXml;
    }
}

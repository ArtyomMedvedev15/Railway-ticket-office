package com.railwayticket.restclient.util.xml;

import com.railwayticket.restclient.domains.ClientRailway;
import com.railwayticket.restclient.domains.Trains;
import com.railwayticket.restclient.util.ConvertDomain;
import com.railwayticket.restclient.util.dto.ClientsDto;
import com.railwayticket.restclient.util.dto.TrainsDto;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ClientsXmlImporter {
    private File xmlFile;

    public List<com.railwayticket.restclient.domains.ClientRailway> ImportClientsFromXml(MultipartFile file) throws JAXBException, IOException {
        decompressTarGzipFile(convert(file).toPath());

        List<com.railwayticket.restclient.domains.ClientRailway>resultClientsXml = new ArrayList<>();

        JAXBContext jaxbContext = JAXBContext.newInstance(ClientsDto.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        ClientsDto clientsDtoList = (ClientsDto)jaxbUnmarshaller.unmarshal(xmlFile);

        for (com.domain.ClientRailway client : clientsDtoList.getClients()){
            com.railwayticket.restclient.domains.ClientRailway clientRailway = ConvertDomain.convertDomainClientRailway(client);
            resultClientsXml.add(clientRailway);
        }

        return resultClientsXml;
    }

    public void decompressTarGzipFile(Path source)
            throws IOException {

        if (Files.notExists(source)) {
            throw new IOException("File doesn't exists!");
        }

        try (InputStream fi = Files.newInputStream(source);
             BufferedInputStream bi = new BufferedInputStream(fi);
             TarArchiveInputStream ti = new TarArchiveInputStream(bi)) {
            xmlFile = new File(source.getFileName().toString());
            ArchiveEntry entry;
            while ((entry = ti.getNextEntry()) != null) {
                IOUtils.copy(ti,new FileOutputStream(xmlFile));
            }
        }
    }

    public File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}

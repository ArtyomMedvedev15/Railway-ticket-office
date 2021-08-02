package com.railwayticket.restclient.util.xml;

import com.railwayticket.restclient.domains.Trains;
import com.railwayticket.restclient.util.ConvertDomain;
import com.railwayticket.restclient.util.dto.TrainsDto;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
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
import java.util.zip.ZipInputStream;

import static org.springframework.util.FileCopyUtils.BUFFER_SIZE;

public class TrainsXmlImporter {
    private File xmlFile;

    public List<com.railwayticket.restclient.domains.Trains>ImportTrainsFromXml(MultipartFile file) throws JAXBException, IOException {
        decompressTarGzipFile(convert(file).toPath());

        List<Trains>resultTrainsXml = new ArrayList<>();

        JAXBContext jaxbContext = JAXBContext.newInstance(TrainsDto.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        TrainsDto trainsListfromxml = (TrainsDto)jaxbUnmarshaller.unmarshal(xmlFile);

        for (com.domain.Trains trains : trainsListfromxml.getTrains()){
             Trains trainresult = ConvertDomain.convertDomainTrains(trains);
            resultTrainsXml.add(trainresult);
        }

        return resultTrainsXml;
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

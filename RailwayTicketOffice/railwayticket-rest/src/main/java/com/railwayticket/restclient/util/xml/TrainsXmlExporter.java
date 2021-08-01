package com.railwayticket.restclient.util.xml;



import com.railwayticket.restclient.util.dto.TrainsDto;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import com.domain.*;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;

public class TrainsXmlExporter {
    private TrainsDto trainsList;

    public TrainsXmlExporter(TrainsDto trainsList) {
        this.trainsList = trainsList;
    }

    public void TrainsXmlExporterFile(HttpServletResponse response) throws JAXBException, IOException {
        String filenameXml = "trains.xml";
        File fileSave = new File(filenameXml);
        JAXBContext jaxbContext = JAXBContext.newInstance(TrainsDto.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        jaxbMarshaller.marshal(trainsList, fileSave);

        createTarGzipFiles(fileSave,response);
    }

    public static void createTarGzipFiles(File file, HttpServletResponse response)
            throws IOException {

        try (OutputStream fOut = response.getOutputStream();
             BufferedOutputStream buffOut = new BufferedOutputStream(fOut);
             GzipCompressorOutputStream gzOut = new GzipCompressorOutputStream(buffOut);
             TarArchiveOutputStream tOut = new TarArchiveOutputStream(gzOut)) {

            if (!Files.isRegularFile(file.toPath())) {
                throw new IOException("Support only file!");
            }

            TarArchiveEntry tarEntry = new TarArchiveEntry("trains");
            tarEntry.setSize(file.length());
            tOut.putArchiveEntry(tarEntry);

            // copy file to TarArchiveOutputStream
            Files.copy(file.toPath(), tOut);

            tOut.closeArchiveEntry();

            file.delete();

            tOut.finish();
        }
    }
}

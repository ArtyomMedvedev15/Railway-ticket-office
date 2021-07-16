package com.railwayticket.services_api;





import com.domain.ClientRailway;
import com.railwayticket.services_api.exception.ClientServiceException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ClientServiceApi extends BaseServiceApi<ClientRailway> {
    List<ClientRailway> FindByNameClient(String name_client) throws ClientServiceException;
    void ImportExcel(MultipartFile file);
}

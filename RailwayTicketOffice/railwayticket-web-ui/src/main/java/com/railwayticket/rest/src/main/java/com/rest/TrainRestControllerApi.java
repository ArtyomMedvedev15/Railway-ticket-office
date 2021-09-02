package com.rest;

import com.ApiClient;

import com.rest.domains.Trains;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2021-09-02T23:14:31.694+03:00")
@Component("com.rest.TrainRestControllerApi")
public class TrainRestControllerApi {
    private ApiClient apiClient;

    public TrainRestControllerApi() {
        this(new ApiClient());
    }

    @Autowired
    public TrainRestControllerApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Find all train.
     * Allows you to get all trains.
     * <p><b>200</b> - Successfully find all train.
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - List of train was empty
     * @return List&lt;Trains&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<Trains> allTrainUsingGET() throws RestClientException {
        Object postBody = null;
        
        String path = UriComponentsBuilder.fromPath("/api/train/allTrain").build().toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "application/json;charset=UTF-8"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<List<Trains>> returnType = new ParameterizedTypeReference<List<Trains>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Delete train.
     * Allows you to delete train by id.
     * <p><b>200</b> - OK
     * <p><b>204</b> - Successfully delete train.
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Error train for delete equal null
     * @param id ID value for delete train.
     * @return Trains
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Trains deleteTrainsUsingGET(Long id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteTrainsUsingGET");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = UriComponentsBuilder.fromPath("/api/train/deleteTrain/{id}").buildAndExpand(uriVariables).toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "application/json;charset=UTF-8"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<Trains> returnType = new ParameterizedTypeReference<Trains>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Find all train by dates and stations.
     * Allows you to get all train by dates and stations.
     * <p><b>200</b> - Successfully find all train by dates and stations.
     * <p><b>201</b> - Created
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - List of train was empty
     * @param arrivalDate Date when train arrival
     * @param arrivalStationFind The station where the train arrives
     * @param departureDate Date when train departure
     * @param departureStationFind The station where the train starts from
     * @return List&lt;Trains&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<Trains> findAllTrainsByDatesAndStationsUsingPOST(String arrivalDate, String arrivalStationFind, String departureDate, String departureStationFind) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'arrivalDate' is set
        if (arrivalDate == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'arrivalDate' when calling findAllTrainsByDatesAndStationsUsingPOST");
        }
        
        // verify the required parameter 'arrivalStationFind' is set
        if (arrivalStationFind == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'arrivalStationFind' when calling findAllTrainsByDatesAndStationsUsingPOST");
        }
        
        // verify the required parameter 'departureDate' is set
        if (departureDate == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'departureDate' when calling findAllTrainsByDatesAndStationsUsingPOST");
        }
        
        // verify the required parameter 'departureStationFind' is set
        if (departureStationFind == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'departureStationFind' when calling findAllTrainsByDatesAndStationsUsingPOST");
        }
        
        String path = UriComponentsBuilder.fromPath("/api/train/findtrainbydates").build().toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
        
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "arrival_date", arrivalDate));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "arrival_station_find", arrivalStationFind));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "departure_date", departureDate));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "departure_station_find", departureStationFind));

        final String[] accepts = { 
            "application/json;charset=UTF-8"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<List<Trains>> returnType = new ParameterizedTypeReference<List<Trains>>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Save new train.
     * Allows you to save new train to database.
     * <p><b>200</b> - OK
     * <p><b>201</b> - Successfully save train.
     * <p><b>400</b> - Error train for save equal null
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param trainSave Train model for save new train with full field.
     * @return Trains
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Trains saveTrainUsingPOST(Trains trainSave) throws RestClientException {
        Object postBody = trainSave;
        
        // verify the required parameter 'trainSave' is set
        if (trainSave == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'trainSave' when calling saveTrainUsingPOST");
        }
        
        String path = UriComponentsBuilder.fromPath("/api/train/saveTrain").build().toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "application/json;charset=UTF-8"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<Trains> returnType = new ParameterizedTypeReference<Trains>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Find train by id.
     * Allows you to get a single train by ID
     * <p><b>200</b> - Successfully get train by id.
     * <p><b>400</b> - Error id train equal null
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Train was not found by this id
     * @param id ID value for find train by id.
     * @return Trains
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Trains trainByIdUsingGET(Long id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling trainByIdUsingGET");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = UriComponentsBuilder.fromPath("/api/train/{id}").buildAndExpand(uriVariables).toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "application/json;charset=UTF-8"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<Trains> returnType = new ParameterizedTypeReference<Trains>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Update train.
     * Allows you to update train.
     * <p><b>200</b> - Successfully update train.
     * <p><b>201</b> - Created
     * <p><b>400</b> - Error train for update equal null
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param trainUpdate Train model for update train info with full field.
     * @return Trains
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Trains updateTrainUsingPOST(Trains trainUpdate) throws RestClientException {
        Object postBody = trainUpdate;
        
        // verify the required parameter 'trainUpdate' is set
        if (trainUpdate == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'trainUpdate' when calling updateTrainUsingPOST");
        }
        
        String path = UriComponentsBuilder.fromPath("/api/train/updateTrain").build().toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "application/json;charset=UTF-8"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<Trains> returnType = new ParameterizedTypeReference<Trains>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
}

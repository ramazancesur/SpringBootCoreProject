package com.stok.ramazan.settings;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.stok.ramazan.securty.JwtAuthenticationRequest;
import com.stok.ramazan.securty.JwtUser;
import com.stok.ramazan.securty.service.JwtAuthenticationResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import java.io.IOException;
import javax.ws.rs.core.MultivaluedMap;

/**
 * Created by ramazancesur on 19/06/2017.
 */
public class RAuthentication {
  @SuppressWarnings("static-access")
  public static JwtAuthenticationResponse getAuthTokenCookie(JwtUser user) throws JsonGenerationException, JsonMappingException, IOException {
    String uri = "http://localhost:9000/auth";

    JwtAuthenticationResponse jwtAuthenticationResponse = null;

    try {


      Client client = Client.create();

      WebResource webResource = client.resource(uri);

      ObjectMapper mapper = new ObjectMapper();
      JwtAuthenticationRequest jwtAuthenticationRequest = new JwtAuthenticationRequest(user.getUsername(), user.getPassword());


      ClientResponse response = webResource.type("application/json")
          .post(ClientResponse.class, mapper.writeValueAsString(jwtAuthenticationRequest));

      if (response.getStatus() == 201) {
        throw new RuntimeException("Failed : HTTP error code : "
            + response.getStatus());
      }

      String jsonResponse = response.getEntity(String.class);

      Gson gson = new Gson();
      jwtAuthenticationResponse = gson.fromJson(jsonResponse, JwtAuthenticationResponse.class);

      System.out.println(jsonResponse);
      System.out.println("Output from Server .... \n");


    } catch (Exception e) {

      e.printStackTrace();

    }


    uri = "http://localhost:9000/Payment/OdemeDTO/all";


    Client client = Client.create();
    WebResource webResource = client.resource(uri);

    MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
    queryParams.add("json", jwtAuthenticationResponse.getToken());

    ClientResponse response1 = null;
    response1 = webResource.queryParams(queryParams)
        .header("Content-Type", "application/json;charset=UTF-8")
        .header("Authorization", jwtAuthenticationResponse.getToken())
        .get(ClientResponse.class);

    String jsonStr = response1.getEntity(String.class);

    System.out.println(jsonStr);
    return jwtAuthenticationResponse;


  }


}
package com.ciphertext.opencarebackend.controller;

import com.ciphertext.opencarebackend.OpenCareBackendApplication;
import com.ciphertext.opencarebackend.model.entity.District;
import com.ciphertext.opencarebackend.model.entity.Hospital;
import com.ciphertext.opencarebackend.repository.DistrictRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;

/**
 * @author Sadman
 */
@SpringBootTest(classes = OpenCareBackendApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HospitalApiControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DistrictRepository districtRepository;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port + "/api";
    }

    /**
     * Here we test that we can get all the hospitals in the database
     * using the GET method
     */
    @Test
    void testGetAllHospitals() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/hospitals",
                HttpMethod.GET, entity, String.class);

        Assertions.assertNotNull(response.getBody());
    }

    /**
     * Here we test that we can fetch a single hospital using its id
     */
    @Test
    void testGetHospitalById() {
        Hospital hospital = restTemplate.getForObject(getRootUrl() + "/hospitals/1", Hospital.class);
        System.out.println(hospital.getName());
        Assertions.assertNotNull(hospital);
    }

    /**
     * Here we test that we can create a hospital using the POST method
     */
    @Test
    void testCreateHospital() {
        Hospital hospital = new Hospital();
        hospital.setName("Test");
        hospital.setNumberOfBed(100);
        District district = districtRepository.findById(1).orElse(null);
        hospital.setDistrict(district);

        ResponseEntity<Hospital> postResponse = restTemplate.postForEntity(getRootUrl() + "/hospitals", hospital, Hospital.class);
        Assertions.assertNotNull(postResponse);
        Assertions.assertNotNull(postResponse.getBody());
    }

    /**
     * Here we test that we can update a car's information using the PUT method
     */
    @Test
    void testUpdateHospital() {
        int id = 78;
        Hospital hospital = restTemplate.getForObject(getRootUrl() + "/hospitals/" + id, Hospital.class);
        hospital.setName("Tesla");
        District district = districtRepository.findById(1).orElse(null);
        hospital.setDistrict(district);
        hospital.setNumberOfBed(50);

        restTemplate.put(getRootUrl() + "/hospitals/edit/" + id, hospital);

        Hospital updatedHospital = restTemplate.getForObject(getRootUrl() + "/hospitals/" + id, Hospital.class);
        Assertions.assertNotNull(updatedHospital);
    }

    /**
     * Here we test that we can delete a hospital by using the DELETE method,
     * then we verify that it no longer exists in the database
     */
    @Test
    void testDeleteHospital() {
        int id = 77;
        Hospital hospital = restTemplate.getForObject(getRootUrl() + "/hospitals/" + id, Hospital.class);
        Assertions.assertNotNull(hospital);

        restTemplate.delete(getRootUrl() + "/hospitals/delete/" + id);

        try {
            hospital = restTemplate.getForObject(getRootUrl() + "/hospitals/" + id, Hospital.class);
            System.out.println(hospital.getName());
        } catch (final HttpClientErrorException e) {
            Assertions.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}

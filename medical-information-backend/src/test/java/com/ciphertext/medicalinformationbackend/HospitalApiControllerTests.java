package com.ciphertext.medicalinformationbackend;

import com.ciphertext.medicalinformationbackend.model.District;
import com.ciphertext.medicalinformationbackend.model.Hospital;
import com.ciphertext.medicalinformationbackend.repository.DistrictRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

/**
 * @author Sadman
 */
@SpringBootTest(classes = MedicalInformationBackendApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HospitalApiControllerTests {

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
    public void testGetAllHospitals() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/hospitals",
                HttpMethod.GET, entity, String.class);

        Assert.assertNotNull(response.getBody());
    }

    /**
     * Here we test that we can fetch a single hospital using its id
     */
    @Test
    public void testGetHospitalById() {
        Hospital hospital = restTemplate.getForObject(getRootUrl() + "/hospitals/1", Hospital.class);
        System.out.println(hospital.getName());
        Assert.assertNotNull(hospital);
    }

    /**
     * Here we test that we can create a hospital using the POST method
     */
    @Test
    public void testCreateHospital() {
        Hospital hospital = new Hospital();
        hospital.setName("Test");
        hospital.setNumberOfBed(100);
        District district = districtRepository.getById(1);
        hospital.setDistrict(district);

        ResponseEntity<Hospital> postResponse = restTemplate.postForEntity(getRootUrl() + "/hospitals", hospital, Hospital.class);
        Assert.assertNotNull(postResponse);
        Assert.assertNotNull(postResponse.getBody());
    }

    /**
     * Here we test that we can update a car's information using the PUT method
     */
    @Test
    public void testUpdateHospital() {
        int id = 78;
        Hospital hospital = restTemplate.getForObject(getRootUrl() + "/hospitals/" + id, Hospital.class);
        hospital.setName("Tesla");
        District district = districtRepository.getById(1);
        hospital.setDistrict(district);
        hospital.setNumberOfBed(50);

        restTemplate.put(getRootUrl() + "/hospitals/edit/" + id, hospital);

        Hospital updatedHospital = restTemplate.getForObject(getRootUrl() + "/hospitals/" + id, Hospital.class);
        Assert.assertNotNull(updatedHospital);
    }

    /**
     * Here we test that we can delete a hospital by using the DELETE method,
     * then we verify that it no longer exists in the database
     */
    @Test
    public void testDeleteHospital() {
        int id = 77;
        Hospital hospital = restTemplate.getForObject(getRootUrl() + "/hospitals/" + id, Hospital.class);
        Assert.assertNotNull(hospital);

        restTemplate.delete(getRootUrl() + "/hospitals/delete/" + id);

        try {
            hospital = restTemplate.getForObject(getRootUrl() + "/hospitals/" + id, Hospital.class);
            System.out.println(hospital.getName());
        } catch (final HttpClientErrorException e) {
            Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}

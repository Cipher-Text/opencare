package com.ciphertext.opencarebackend;

import com.ciphertext.opencarebackend.model.MedicalSpeciality;
import com.ciphertext.opencarebackend.repository.DistrictRepository;
import org.junit.Assert;
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
public class MedicalSpecialityApiControllerTests {

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
     * Here we test that we can get all the specialities in the database
     * using the GET method
     */
    @Test
    public void testGetAllSpecialities() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/specialities",
                HttpMethod.GET, entity, String.class);

        Assert.assertNotNull(response.getBody());
    }

    /**
     * Here we test that we can fetch a single speciality using its id
     */
    @Test
    public void testGetSpecialityById() {
        MedicalSpeciality medicalSpeciality = restTemplate.getForObject(getRootUrl() + "/specialities/1", MedicalSpeciality.class);
        System.out.println(medicalSpeciality.getName());
        Assert.assertNotNull(medicalSpeciality);
    }

    /**
     * Here we test that we can create a speciality using the POST method
     */
    @Test
    public void testCreateSpeciality() {
        MedicalSpeciality medicalSpeciality = new MedicalSpeciality();
        medicalSpeciality.setName("Test");
        medicalSpeciality.setDescription(null);

        ResponseEntity<MedicalSpeciality> postResponse = restTemplate.postForEntity(getRootUrl() + "/specialities", medicalSpeciality, MedicalSpeciality.class);
        Assert.assertNotNull(postResponse);
        Assert.assertNotNull(postResponse.getBody());
    }

    /**
     * Here we test that we can update a car's information using the PUT method
     */
    @Test
    public void testUpdateSpeciality() {
        int id = 21;
        MedicalSpeciality medicalSpeciality = restTemplate.getForObject(getRootUrl() + "/specialities/" + id, MedicalSpeciality.class);
        medicalSpeciality.setName("Tesla");
        medicalSpeciality.setDescription(null);

        restTemplate.put(getRootUrl() + "/specialities/edit/" + id, medicalSpeciality);

        MedicalSpeciality updatedMedicalSpeciality = restTemplate.getForObject(getRootUrl() + "/specialities/" + id, MedicalSpeciality.class);
        Assert.assertNotNull(updatedMedicalSpeciality);
    }

    /**
     * Here we test that we can delete a speciality by using the DELETE method,
     * then we verify that it no longer exists in the database
     */
    @Test
    public void testDeleteSpeciality() {
        int id = 21;
        MedicalSpeciality medicalSpeciality = restTemplate.getForObject(getRootUrl() + "/specialities/" + id, MedicalSpeciality.class);
        Assert.assertNotNull(medicalSpeciality);

        restTemplate.delete(getRootUrl() + "/specialities/delete/" + id);

        try {
            medicalSpeciality = restTemplate.getForObject(getRootUrl() + "/specialities/" + id, MedicalSpeciality.class);
            System.out.println(medicalSpeciality.getName());
        } catch (final HttpClientErrorException e) {
            Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}

package com.ciphertext.medicalinformationbackend;

import com.ciphertext.medicalinformationbackend.model.District;
import com.ciphertext.medicalinformationbackend.model.Speciality;
import com.ciphertext.medicalinformationbackend.repository.DistrictRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;

/**
 * @author Sadman
 */
@SpringBootTest(classes = MedicalInformationBackendApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpecialityApiControllerTests {

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
        Speciality speciality = restTemplate.getForObject(getRootUrl() + "/specialities/1", Speciality.class);
        System.out.println(speciality.getName());
        Assert.assertNotNull(speciality);
    }

    /**
     * Here we test that we can create a speciality using the POST method
     */
    @Test
    public void testCreateSpeciality() {
        Speciality speciality = new Speciality();
        speciality.setName("Test");
        speciality.setDescription(null);

        ResponseEntity<Speciality> postResponse = restTemplate.postForEntity(getRootUrl() + "/specialities", speciality, Speciality.class);
        Assert.assertNotNull(postResponse);
        Assert.assertNotNull(postResponse.getBody());
    }

    /**
     * Here we test that we can update a car's information using the PUT method
     */
    @Test
    public void testUpdateSpeciality() {
        int id = 21;
        Speciality speciality = restTemplate.getForObject(getRootUrl() + "/specialities/" + id, Speciality.class);
        speciality.setName("Tesla");
        speciality.setDescription(null);

        restTemplate.put(getRootUrl() + "/specialities/edit/" + id, speciality);

        Speciality updatedSpeciality = restTemplate.getForObject(getRootUrl() + "/specialities/" + id, Speciality.class);
        Assert.assertNotNull(updatedSpeciality);
    }

    /**
     * Here we test that we can delete a speciality by using the DELETE method,
     * then we verify that it no longer exists in the database
     */
    @Test
    public void testDeleteSpeciality() {
        int id = 21;
        Speciality speciality = restTemplate.getForObject(getRootUrl() + "/specialities/" + id, Speciality.class);
        Assert.assertNotNull(speciality);

        restTemplate.delete(getRootUrl() + "/specialities/delete/" + id);

        try {
            speciality = restTemplate.getForObject(getRootUrl() + "/specialities/" + id, Speciality.class);
            System.out.println(speciality.getName());
        } catch (final HttpClientErrorException e) {
            Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}

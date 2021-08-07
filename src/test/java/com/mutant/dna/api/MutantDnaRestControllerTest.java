package com.mutant.dna.api;

import com.google.gson.Gson;
import com.mutant.dna.constants.Constants;
import com.mutant.dna.dto.DnaReadDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author npavila
 */
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBootTest
@AutoConfigureMockMvc
public class MutantDnaRestControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    //@LocalServerPort
    //private int port;
    @Autowired
    Constants constants;

    //@Autowired
    //private TestRestTemplate restTemplate;
    private final DnaReadDto dnaReadDto = new DnaReadDto();

    /**
     * Test of mutant method, of class MutantDnaRestController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testMutant() throws Exception {
        dnaReadDto.setDna(constants.getDnaHuman().split(","));
        MvcResult mvcResult = mockMvc
                .perform(post("/isMutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(dnaReadDto)))
                .andExpect(status().is4xxClientError())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals("", response);
    }

    /**
     * Test of stats method, of class MutantDnaRestController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testStats() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get("/stats"))
                .andExpect(status().isInternalServerError())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals("Ha ocurrido un error interno, por favor intente más tarde", response);
        //Assertions.assertNotNull(response);
    }
    
}

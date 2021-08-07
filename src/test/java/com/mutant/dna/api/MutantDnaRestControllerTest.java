package com.mutant.dna.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mutant.dna.constants.Constants;
import com.mutant.dna.dto.DnaReadDto;
import com.mutant.dna.dto.StatusDto;
import com.mutant.dna.service.impl.MutantDnaService;
import java.nio.charset.Charset;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;
import static org.mockito.BDDMockito.given;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
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
// we test only the SimpleController 
@SpringBootTest
@AutoConfigureMockMvc
public class MutantDnaRestControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc1;
    
    @Autowired
    private MockMvc mockMvc2;

    @Autowired
    Constants constants;

    @MockBean
    MutantDnaService service;

    private DnaReadDto dnaReadDto = new DnaReadDto();
    private final StatusDto statusDto = new StatusDto();

    /**
     * Test of mutant method, of class MutantDnaRestController.
     *
     * @throws java.lang.Exception
     */
    @Test
    void testMutant() throws Exception {
        whenDnaReadIsHuman();
    }

    /**
     * Test of stats method, of class MutantDnaRestController.
     *
     * @throws java.lang.Exception
     */
    @Test
    void testStats() throws Exception {
        whenStatusOk();
        whenStatusException();
    }

    private void whenDnaReadIsHuman() throws Exception {
        dnaReadDto = new DnaReadDto();
        dnaReadDto.setDna(constants.getDnaHuman().split(","));
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(dnaReadDto);

        MvcResult mvcResult = mockMvc2
                .perform(post("/mutant")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(status().isForbidden())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals("{\"result\":\"Human.\"}", response);
    }

    private void whenStatusOk() throws Exception {
        statusDto.setCountHumanDna(100L);
        statusDto.setCountMutantDna(40L);
        statusDto.setRatio(0.4);

        given(service.getStats()).willReturn(statusDto);

        MvcResult mvcResult = mockMvc1
                .perform(get("/stats"))
                .andExpect(status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertNotNull(response);
    }

    private void whenStatusException() throws Exception {
        given(service.getStats()).willThrow(Exception.class);

        MvcResult mvcResult = mockMvc1
                .perform(get("/stats"))
                .andExpect(status().isInternalServerError())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals("Ha ocurrido un error interno, por favor intente más tarde", response);
    }

}

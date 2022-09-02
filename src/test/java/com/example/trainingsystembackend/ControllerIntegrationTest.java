package com.example.trainingsystembackend;


import com.jayway.jsonpath.JsonPath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    //api/v1/listBatch
    @Test
    public void getBatch1() throws Exception{
        MvcResult result =this.mockMvc.perform(post("/authenticate")
                        .content("{\"username\":\"admin\",\"password\":\"admin\"}")
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful()).andReturn();

        String token= JsonPath.read(result.getResponse().getContentAsString(), "$.token");

        this.mockMvc.perform(get("/api/v1/listBatch").header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$[0].batchName").value("fb06_java"));
    }
    @Test
    public void getBatch2() throws Exception{
        MvcResult result =this.mockMvc.perform(post("/authenticate")
                .content("{\"username\":\"admin\",\"password\":\"admin\"}")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful()).andReturn();

        String token= JsonPath.read(result.getResponse().getContentAsString(), "$.token");

        this.mockMvc.perform(get("/api/v1/listBatch").header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$[0].batchName").value("babbsbdef"));
    }
}

package co.za.tvictor.dirscanrest.controller;

import co.za.tvictor.dirscanrest.model.DirectoryItem;
import co.za.tvictor.dirscanrest.model.DirectoryList;
import co.za.tvictor.dirscanrest.service.DirectoryInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.awt.*;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DirectoryScanController.class, secure = false)
public class DirectoryScanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DirectoryInfoService dirInfoService;

    DirectoryList mockDirList = new DirectoryList("/home/user","http://localhost:8080/home/user","http://localhost:8080/home/");

        @Test
        public void listFilesInDirectoryTest() throws Exception{

            Mockito.when(
                    dirInfoService.generateDirectoryModel(Mockito.anyString())).thenReturn(mockDirList);

            RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/home/user")
                    .accept(MediaType.APPLICATION_JSON);

            MvcResult result = mockMvc.perform(requestBuilder).andReturn();

            String expected = "{\"currentDir\":\"/home/user\",\"_self\":\"http://localhost:8080/home/user\",\"_parent\":\"http://localhost:8080/home/\",\"directoryListing\":[]}";

            JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
        }
}

package com.urlshortener.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.urlshortener.app.model.Link;
import com.urlshortener.app.service.LinkService;
import com.urlshortener.app.service.dto.CreateLinkDTO;
import com.urlshortener.app.service.dto.LinkDTO;
import com.urlshortener.app.web.rest.LinkController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(LinkController.class)
public class LinkControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LinkService service;

    @Test
    public void getLinksShouldReturnLinksList() throws Exception {
        List<Link> links = Arrays.asList(
                createLink("short1", "long-url1"),
                createLink("short2", "long-url2")
        );
        when(service.findAll()).thenReturn(links);
        MvcResult mvcResult = this.mockMvc.perform(get("/api/links"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(mvcResult.getResponse().getContentType(), "application/json");
        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<List<LinkDTO>>(){}.getType();
        List<LinkDTO> result = gson.fromJson(mvcResult.getResponse().getContentAsString(), listType);
        assertNotNull(result);
        assertEquals(links.size(), result.size());
        assertEquals(links.get(0).getShortUrl(), result.get(0).getShortUrl());
        assertEquals(links.get(0).getUrl(), result.get(0).getUrl());
        assertEquals(links.get(1).getShortUrl(), result.get(1).getShortUrl());
        assertEquals(links.get(1).getUrl(), result.get(1).getUrl());
    }

    @Test
    public void generateShortLinkShouldSuccess() throws Exception {
        Gson gson = new GsonBuilder().create();
        String shortUrl = "short-url";
        String url = "long-url";
        CreateLinkDTO createLinkDTO = new CreateLinkDTO();
        createLinkDTO.setUrl(url);

        when(service.generateShortLink(url)).thenReturn(createLink(shortUrl, url));
        MvcResult mvcResult = this.mockMvc.perform(
                post("/api/links").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(createLinkDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        LinkDTO result = gson.fromJson(mvcResult.getResponse().getContentAsString(), LinkDTO.class);
        assertNotNull(result);
        assertEquals(shortUrl, result.getShortUrl());
        assertEquals(url, result.getUrl());
    }

    private Link createLink(String shortUrl, String url) {
        return new Link(
                UUID.randomUUID(),
                shortUrl,
                url,
                Timestamp.from(Instant.now()),
                Timestamp.from(Instant.now()),
                UUID.randomUUID()
        );
    }
}

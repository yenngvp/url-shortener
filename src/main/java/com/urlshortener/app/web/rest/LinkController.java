package com.urlshortener.app.web.rest;

import com.urlshortener.app.service.LinkService;
import com.urlshortener.app.service.dto.CreateLinkDTO;
import com.urlshortener.app.service.dto.LinkDTO;
import com.urlshortener.app.service.dto.RevertLinkDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LinkController {
    private final LinkService linkService;

    @Autowired
    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/links")
    public List<LinkDTO> getLinks() {
        return this.linkService.findAll()
                .stream()
                .map(LinkDTO::fromLink)
                .collect(Collectors.toList());
    }

    @PostMapping("/links")
    public LinkDTO generateShortLink(@RequestBody CreateLinkDTO createLinkDTO) {
        return LinkDTO.fromLink(linkService.generateShortLink(createLinkDTO.getUrl()));
    }

    @PostMapping("/links/revert")
    public LinkDTO revertShortLink(@RequestBody RevertLinkDTO revertLinkDTO) {
        return LinkDTO.fromLink(linkService.revertShortLink(revertLinkDTO.getShortUrl()));
    }

    @DeleteMapping("/links/{id}")
    public void deleteLink(@PathVariable String id) {
        this.linkService.deleteLink(UUID.fromString(id));
    }
}

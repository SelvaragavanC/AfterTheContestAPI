package com.selvaragavan.afterthecontestapi.authentication.controllers;

import com.selvaragavan.afterthecontestapi.authentication.Entities.Content;
import com.selvaragavan.afterthecontestapi.authentication.dto.ContentAddRequest;
import com.selvaragavan.afterthecontestapi.authentication.dto.ContentAddResponse;
import com.selvaragavan.afterthecontestapi.authentication.dto.ContentGetRequest;
import com.selvaragavan.afterthecontestapi.authentication.services.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/content")

public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping("/get")
    public List<Content> getContent(@ModelAttribute ContentGetRequest request) {
        return contentService.getAllContents(request.getForContentId());
    }

    @PostMapping("/post")
    public ContentAddResponse addContent(@RequestBody ContentAddRequest request) {
        return contentService.addContent(request);
    }
}

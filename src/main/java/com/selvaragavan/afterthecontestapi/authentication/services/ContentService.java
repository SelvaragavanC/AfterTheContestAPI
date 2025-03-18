package com.selvaragavan.afterthecontestapi.authentication.services;

import com.selvaragavan.afterthecontestapi.authentication.Entities.Content;
import com.selvaragavan.afterthecontestapi.authentication.dto.ContentAddRequest;
import com.selvaragavan.afterthecontestapi.authentication.dto.ContentAddResponse;
import com.selvaragavan.afterthecontestapi.authentication.repositories.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private CurrentUserService currentUserService;



    public List<Content> getAllContents(String contentId) {
        return contentRepository.findByForContentId(contentId);
    }

    public ContentAddResponse addContent(ContentAddRequest request){
        String username = currentUserService.getCurrentUserName();


        contentRepository.save(
                Content.builder()
                        .forContentId(request.getForContentId())
                        .text(request.getText())
                        .username(username)
                        .build()
        );

        return new ContentAddResponse("Added Successfully");
    }
}

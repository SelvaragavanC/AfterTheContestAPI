package com.selvaragavan.afterthecontestapi.authentication.dto;


import com.selvaragavan.afterthecontestapi.authentication.Entities.Content;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentGetResponse {
    List<Content> contents;
}

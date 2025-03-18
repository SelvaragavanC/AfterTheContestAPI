package com.selvaragavan.afterthecontestapi.authentication.dto;

import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentAddRequest {
    private String forContentId;
    private String text;
    private String title;
}

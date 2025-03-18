package com.selvaragavan.afterthecontestapi.authentication.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentGetRequest {
    String forContentId;
}

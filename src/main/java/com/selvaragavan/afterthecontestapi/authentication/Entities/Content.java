package com.selvaragavan.afterthecontestapi.authentication.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String contentId;

    String forContentId; // same is used for problem ID as well.
    // I did this to handle comments as well. When loading comments for a Content. I just want another content having contentId to the current Content.

    String username;

    String text;
    String title;
}

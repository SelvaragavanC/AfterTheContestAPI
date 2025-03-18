package com.selvaragavan.afterthecontestapi.authentication.repositories;

import com.selvaragavan.afterthecontestapi.authentication.Entities.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    public List<Content> findByForContentId(String id); // to pick solutions for a particular problem and also to pick comments and replies
}

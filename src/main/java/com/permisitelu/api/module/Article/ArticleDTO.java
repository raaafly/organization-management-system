package com.permisitelu.api.module.Article;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link Article} entity
 */
@Data
public class ArticleDTO implements Serializable {
    private Long id;
    private String title;
    private String content;
    private boolean isPublished;
    private String createdBy;
    private String updatedBy;
    private Date createdAt;
    private Date updatedAt;
}
package com.permisitelu.api.module.Article;

import com.permisitelu.api.module.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Articles")
public class Article extends BaseEntity implements Serializable {
    @Column(name = "article_title", nullable = false)
    private String title;

    @Column(name = "article_content")
    @Type(type = "org.hibernate.type.TextType")
    private String content;

    @Column(name = "is_published")
    private boolean isPublished;
}
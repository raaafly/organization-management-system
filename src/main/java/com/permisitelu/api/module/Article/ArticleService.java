package com.permisitelu.api.module.Article;

import java.util.List;

public interface ArticleService {
    List<ArticleDTO> getArticles();
    ArticleDTO getArticleById(Long id);
    ArticleDTO addArticle(ArticleDTO object);
    ArticleDTO updateArticleById(Long id, ArticleDTO object);
    void deleteArticleById(Long id);
}

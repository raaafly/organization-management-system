package com.permisitelu.api.module.Article;

import com.permisitelu.api.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ModelMapper mapper;
    private final ArticleRepository articleRepository;

    @Override
    public List<ArticleDTO> getArticles() {
        return articleRepository.findAll().stream()
                .map(article -> mapper.map(article, ArticleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ArticleDTO getArticleById(Long id) {
        Article article = findArticleId(id);
        return mapper.map(article, ArticleDTO.class);
    }

    @Override
    public ArticleDTO addArticle(ArticleDTO object) {
        return null;
    }

    @Override
    public ArticleDTO updateArticleById(Long id, ArticleDTO object) {
        return null;
    }

    @Override
    public void deleteArticleById(Long id) {

    }

    private Article findArticleId(Long id) {
        return articleRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Article id " + id + " doesn't exists!"));
    }
}

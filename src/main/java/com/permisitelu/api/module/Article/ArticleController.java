package com.permisitelu.api.module.Article;

import com.permisitelu.api.common.BaseController;
import com.permisitelu.api.common.ResponseMessage;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Articles")
@RestController
@RequestMapping(path = "/articles")
@RequiredArgsConstructor
public class ArticleController implements BaseController<ArticleDTO> {
    private final ArticleService service;

    @Override
    @GetMapping
    public ResponseEntity<ResponseMessage> getAll() {
        List<ArticleDTO> data = service.getArticles();
        ResponseMessage message = new ResponseMessage("Get All Articles", data, HttpStatus.OK);
        return new ResponseEntity<>(message,  HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseMessage> getById(@PathVariable("id") Long id) {
        ArticleDTO data = service.getArticleById(id);
        ResponseMessage message = new ResponseMessage("Get Article Id " + id + " Successfully!", data, HttpStatus.OK);
        return new ResponseEntity<>(message,  HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<ResponseMessage> create(@RequestBody @Valid ArticleDTO object) {
        return null;
    }

    @Override
    @PutMapping(path = "/{id}")
    public ResponseEntity<ResponseMessage> update(@PathVariable("id") Long id, @RequestBody @Valid ArticleDTO object) {
        return null;
    }

    @Override
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseMessage> delete(@PathVariable("id") Long id) {
        service.deleteArticleById(id);
        ResponseMessage message = new ResponseMessage("Delete Article Id " + id + " Successfully!", HttpStatus.OK);
        return new ResponseEntity<>(message,  HttpStatus.OK);
    }
}

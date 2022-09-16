package coen.cloud.computing.newsfeed.service;

import coen.cloud.computing.newsfeed.model.common.Article;

import java.util.List;

public interface ArticleListAggregator {

    void getArticlesAndPublish(String topicName);
}

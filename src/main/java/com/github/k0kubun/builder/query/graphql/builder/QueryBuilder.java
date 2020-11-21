package com.github.k0kubun.builder.query.graphql.builder;

import com.github.k0kubun.builder.query.graphql.model.GraphQLObject;

import java.util.Map;

public interface QueryBuilder
{

    QueryBuilderImpl field(String name);

    QueryBuilderImpl object(String name, GraphQLObject object);

    QueryBuilderImpl objects(String name, Integer first, GraphQLObject object);

    QueryBuilderImpl objects(String name, Integer first, String after, GraphQLObject object);

    QueryBuilderImpl object(String name, Map<String, Object> params, GraphQLObject object);

    String build();
}

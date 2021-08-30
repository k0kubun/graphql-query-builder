package com.github.k0kubun.builder.query.graphql.builder;

import com.github.k0kubun.builder.query.graphql.model.GraphQLObject;
import com.github.k0kubun.builder.query.graphql.model.GraphQLParam;

import java.util.Map;

public interface QueryBuilder
{

    QueryBuilder field(String name);

    QueryBuilder object(String name, GraphQLObject object);

    QueryBuilder objects(String name, Integer first, GraphQLObject object);

    QueryBuilder objects(String name, Integer first, String after, GraphQLObject object);

    QueryBuilder object(String name, Map<String, Object> params, GraphQLObject object);

    QueryBuilder object(String name, GraphQLParam params, GraphQLObject object);

    String build();
}

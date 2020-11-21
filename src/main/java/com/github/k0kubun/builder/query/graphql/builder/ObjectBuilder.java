package com.github.k0kubun.builder.query.graphql.builder;

import com.github.k0kubun.builder.query.graphql.model.GraphQLObject;

import java.util.Map;

public interface ObjectBuilder
{
    ObjectBuilder field(String name);

    ObjectBuilder object(String name, GraphQLObject object);

    ObjectBuilder object(String name, Map<String, Object> params, GraphQLObject object);

    ObjectBuilder objects(String name, Integer first, GraphQLObject object);

    ObjectBuilder objects(String name, Integer first, String after, GraphQLObject object);

    ObjectBuilder on(String name, GraphQLObject object);

    GraphQLObject build();
}

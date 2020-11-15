package com.github.k0kubun.builder.query.graphql.builder;

import com.github.k0kubun.builder.query.graphql.model.GraphQLObject;

import java.util.Map;

public interface ObjectBuilder {
     ObjectBuilderImpl field(String name);

    ObjectBuilderImpl object(String name, GraphQLObject object);

    ObjectBuilderImpl object(String name, Map<String, Object> params, GraphQLObject object);

    ObjectBuilderImpl objects(String name, Integer first, GraphQLObject object);

    ObjectBuilderImpl objects(String name, Integer first, String after, GraphQLObject object);

    ObjectBuilderImpl on(String name, GraphQLObject object);

    GraphQLObject build();
}

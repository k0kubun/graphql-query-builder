package com.github.k0kubun.builder.query.graphql.builder;

import com.github.k0kubun.builder.query.graphql.model.GraphQLField;
import com.github.k0kubun.builder.query.graphql.model.GraphQLObject;
import com.github.k0kubun.builder.query.graphql.model.StringField;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GraphQLObjectBuilder
{
    private final List<GraphQLField> fields;

    public GraphQLObjectBuilder()
    {
        fields = new ArrayList<>();
    }

    public GraphQLObjectBuilder field(String name)
    {
        fields.add(new StringField(name));
        return this;
    }

    public GraphQLObjectBuilder object(String name, GraphQLObject object)
    {
        object.setName(name);
        fields.add(object);
        return this;
    }

    public GraphQLObjectBuilder object(String name, Map<String, Object> params, GraphQLObject object)
    {
        object.setName(name);
        object.setParams(params);
        fields.add(object);
        return this;
    }

    public GraphQLObjectBuilder objects(String name, Integer first, GraphQLObject object)
    {
        object.setName(name);
        object.setParams(ImmutableMap.of("first", first));
        fields.add(object);
        return this;
    }

    public GraphQLObjectBuilder objects(String name, Integer first, String after, GraphQLObject object)
    {
        object.setName(name);
        object.setParams(ImmutableMap.of("first", first, "after", after));
        fields.add(object);
        return this;
    }

    public GraphQLObject build()
    {
        return new GraphQLObject(fields);
    }
}

package com.github.k0kubun.builder.query.graphql.builder;

import com.github.k0kubun.builder.query.graphql.model.GraphQLField;
import com.github.k0kubun.builder.query.graphql.model.GraphQLObject;
import com.github.k0kubun.builder.query.graphql.model.StringField;
import com.google.common.collect.ImmutableMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ObjectBuilder
{
    private final List<GraphQLField> fields;

    public ObjectBuilder()
    {
        fields = new ArrayList<>();
    }

    public ObjectBuilder field(String name)
    {
        fields.add(new StringField(name));
        return this;
    }

    public ObjectBuilder object(String name, GraphQLObject object)
    {
        object.setName(name);
        fields.add(object);
        return this;
    }

    public ObjectBuilder object(String name, Map<String, Object> params, GraphQLObject object)
    {
        object.setName(name);
        object.setParams(params);
        fields.add(object);
        return this;
    }

    public ObjectBuilder objects(String name, Integer first, GraphQLObject object)
    {
        object.setName(name);
        object.setParams(ImmutableMap.of("first", first));
        fields.add(object);
        return this;
    }

    public ObjectBuilder objects(String name, Integer first, String after, GraphQLObject object)
    {
        object.setName(name);
        object.setParams(ImmutableMap.of("first", first, "after", after));
        fields.add(object);
        return this;
    }

    public ObjectBuilder on(String name, GraphQLObject object)
    {
        object.setName("... on " + name);
        fields.add(object); // TODO: wrap some proper class
        return this;
    }

    public GraphQLObject build()
    {
        return new GraphQLObject(fields);
    }
}

package com.github.k0kubun.builder.query.graphql.builder;

import com.github.k0kubun.builder.query.graphql.model.GraphQLField;
import com.github.k0kubun.builder.query.graphql.model.GraphQLObject;
import com.github.k0kubun.builder.query.graphql.model.StringField;
import com.google.common.collect.ImmutableMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class ObjectBuilderImpl
        implements ObjectBuilder
{
    private final List<GraphQLField> fields;

    public ObjectBuilderImpl()
    {
        fields = new ArrayList<>();
    }

    public ObjectBuilderImpl field(String name)
    {
        fields.add(new StringField(name));
        return this;
    }

    public ObjectBuilderImpl object(String name, GraphQLObject object)
    {
        object.setName(name);
        fields.add(object);
        return this;
    }

    public ObjectBuilderImpl object(String name, Map<String, Object> params, GraphQLObject object)
    {
        object.setName(name);
        object.setParams(params);
        fields.add(object);
        return this;
    }

    public ObjectBuilderImpl objects(String name, Integer first, GraphQLObject object)
    {
        object.setName(name);
        object.setParams(ImmutableMap.of("first", first));
        fields.add(object);
        return this;
    }

    public ObjectBuilderImpl objects(String name, Integer first, String after, GraphQLObject object)
    {
        object.setName(name);
        object.setParams(ImmutableMap.of("first", first, "after", after));
        fields.add(object);
        return this;
    }

    public ObjectBuilderImpl on(String name, GraphQLObject object)
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

package com.github.k0kubun.builder.query.graphql.builder;

import com.github.k0kubun.builder.query.graphql.model.GraphQLField;
import com.github.k0kubun.builder.query.graphql.model.GraphQLObject;
import com.github.k0kubun.builder.query.graphql.model.StringField;
import com.google.common.collect.ImmutableMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GraphQLQueryBuilder
{
    private final List<GraphQLField> fields;

    public GraphQLQueryBuilder()
    {
        fields = new ArrayList<>();
    }

    public String build()
    {
        StringBuilder builder = new StringBuilder();
        for (GraphQLField field : fields) {
            builder.append(field.indentRender(0));
        }
        return builder.toString();
    }

    public GraphQLQueryBuilder field(String name)
    {
        fields.add(new StringField(name));
        return this;
    }

    public GraphQLQueryBuilder object(String name, GraphQLObject object)
    {
        object.setName(name);
        fields.add(object);
        return this;
    }

    public GraphQLQueryBuilder objects(String name, Integer first, GraphQLObject object)
    {
        object.setName(name);
        object.setParams(ImmutableMap.of("first", first));
        fields.add(object);
        return this;
    }

    public GraphQLQueryBuilder objects(String name, Integer first, String after, GraphQLObject object)
    {
        object.setName(name);
        object.setParams(ImmutableMap.of("first", first, "after", after));
        fields.add(object);
        return this;
    }

    public GraphQLQueryBuilder object(String name, Map<String, Object> params, GraphQLObject object)
    {
        object.setName(name);
        object.setParams(params);
        fields.add(object);
        return this;
    }
}

package com.github.k0kubun.builder.query.graphql.builder;

import com.github.k0kubun.builder.query.graphql.model.*;
import com.google.common.collect.ImmutableMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class QueryBuilderImpl
        implements QueryBuilder
{
    private final List<GraphQLField> fields;

    public QueryBuilderImpl()
    {
        fields = new ArrayList<>();
    }

    public QueryBuilderImpl field(String name)
    {
        fields.add(new StringField(name));
        return this;
    }

    public QueryBuilderImpl object(String name, GraphQLObject object)
    {
        object.setName(name);
        fields.add(object);
        return this;
    }

    public QueryBuilderImpl objects(String name, Integer first, GraphQLObject object)
    {
        object.setName(name);
        object.setParams(ImmutableMap.of("first", first));
        fields.add(object);
        return this;
    }

    public QueryBuilderImpl objects(String name, Integer first, String after, GraphQLObject object)
    {
        object.setName(name);
        object.setParams(ImmutableMap.of("first", first, "after", after));
        fields.add(object);
        return this;
    }

    public QueryBuilderImpl object(String name, Map<String, Object> params, GraphQLObject object)
    {
        object.setName(name);
        object.setParams(params);
        fields.add(object);
        return this;
    }

    @Override
    public QueryBuilder object(String name, GraphQLParam params, GraphQLObject object) {
        object.setName(name);
        object.setObjectParam(params);
        fields.add(object);
        return this;
    }

    public String build()
    {
        StringBuilder builder = new StringBuilder();
        for (GraphQLField field : fields) {
            builder.append(field.indentRender(0));
        }
        return builder.toString();
    }
}

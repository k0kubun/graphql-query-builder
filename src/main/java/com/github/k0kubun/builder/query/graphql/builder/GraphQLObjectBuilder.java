package com.github.k0kubun.builder.query.graphql.builder;

import com.github.k0kubun.builder.query.graphql.model.GraphQLField;
import com.github.k0kubun.builder.query.graphql.model.GraphQLObject;
import com.github.k0kubun.builder.query.graphql.model.StringField;
import java.util.ArrayList;
import java.util.List;

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

    public GraphQLObject build()
    {
        return new GraphQLObject(fields);
    }
}

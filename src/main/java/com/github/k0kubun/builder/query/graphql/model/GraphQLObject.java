package com.github.k0kubun.builder.query.graphql.model;

import java.util.List;

public class GraphQLObject implements GraphQLField
{
    private final List<GraphQLField> fields;

    public GraphQLObject(List<GraphQLField> fields)
    {
        this.fields = fields;
    }

    public List<GraphQLField> getFields()
    {
        return fields;
    }

    public String indentRender(int indent)
    {
        StringBuilder builder = new StringBuilder();
        for (GraphQLField field : fields) {
            builder.append(field.indentRender(indent + 1));
        }
        return builder.toString();
    }
}

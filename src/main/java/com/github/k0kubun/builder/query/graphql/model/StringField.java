package com.github.k0kubun.builder.query.graphql.model;

public class StringField implements GraphQLField
{
    private final String name;

    public StringField(String name)
    {
        this.name = name;
    }

    public String indentRender(int indent)
    {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < indent; i++)
        {
            builder.append("  ");
        }
        builder.append(name);
        builder.append("\n");
        return builder.toString();
    }
}

package com.github.k0kubun.builder.query.graphql.model;

import java.util.List;

public class GraphQLObject implements GraphQLField
{
    private String name;
    private final List<GraphQLField> fields;

    public GraphQLObject(List<GraphQLField> fields)
    {
        this.fields = fields;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<GraphQLField> getFields()
    {
        return fields;
    }

    public String indentRender(int indent)
    {
        StringBuilder builder = new StringBuilder();
        printIndent(builder, indent);
        builder.append(name);
        builder.append(" {\n");
        for (GraphQLField field : fields) {
            builder.append(field.indentRender(indent + 1));
        }
        printIndent(builder, indent);
        builder.append("}\n");
        return builder.toString();
    }

    private void printIndent(StringBuilder builder, int indent)
    {
        for (int i = 0; i < indent; i++) {
            builder.append("  ");
        }
    }
}

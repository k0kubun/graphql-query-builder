package com.github.k0kubun.builder.query.graphql.model;

import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.Map;

public class GraphQLObject
        implements GraphQLField
{
    private String name;
    private Map<String, Object> params;
    private final List<GraphQLField> fields;

    public GraphQLObject(List<GraphQLField> fields)
    {
        this.params = ImmutableMap.of();
        this.fields = fields;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setParams(Map<String, Object> params)
    {
        this.params = params;
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

        // Render params
        if (params.size() > 0) {
            builder.append("(");
            boolean first = true;
            for (Map.Entry<String, Object> param : params.entrySet()) {
                if (first) {
                    first = false;
                }
                else {
                    builder.append(" ");
                }

                builder.append(param.getKey());
                builder.append(":");
                Object value = param.getValue();
                if (value instanceof String) {
                    builder.append("\"");
                    builder.append(value.toString().replace("\"", "\\\""));
                    builder.append("\"");
                }
                else {
                    builder.append(value.toString());
                }
            }
            builder.append(")");
        }

        builder.append(" {\n");

        // Render children
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

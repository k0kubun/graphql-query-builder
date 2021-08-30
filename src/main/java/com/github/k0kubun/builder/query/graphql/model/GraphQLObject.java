package com.github.k0kubun.builder.query.graphql.model;

import com.google.common.collect.ImmutableMap;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class GraphQLObject
        implements GraphQLField
{
    private final List<GraphQLField> fields;
    private String name;
    private Map<String, Object> params;
    private GraphQLParam objectParam;

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

    public void setObjectParam(GraphQLParam objectParam) {
        this.objectParam = objectParam;
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
        if (objectParam != null) {
            renderComplexParams(builder);
        } else {
            if (params.size() > 0) {
                renderSingleParams(builder);
            }
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

    private void renderSingleParams(StringBuilder builder) {
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
                printString(builder, value);
            }
            else {
                builder.append(value.toString());
            }
        }
        builder.append(")");
    }

    private void renderComplexParams(StringBuilder builder) {
        builder.append("(");
        renderInternalObjectParam(builder, objectParam);
        builder.append(")");
    }

    private void renderObjectParam(StringBuilder builder, Object value) {
        builder.append("{");
        renderInternalObjectParam(builder, value);
        builder.append("}");
    }

    private void renderInternalObjectParam(StringBuilder builder, Object param) {
        Field[] fields = param.getClass().getDeclaredFields();
        boolean first = true;

        for (Field classField : fields)
        {
            classField.setAccessible(true);
            Object value;

            try {
                value = classField.get(param);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

            if (value == null) {
                continue;
            }

            if (first) {
                first = false;
            }
            else {
                builder.append(", ");
            }

            builder.append(classField.getName());
            builder.append(":");

            if (value instanceof String) {
                printString(builder, value);
            } else if (value instanceof Integer || value instanceof Long) {
                builder.append(value);
            } else if (value instanceof Boolean) {
                renderBooleanParam(builder, value);
            } else if (value instanceof Iterable) {
                renderListParam(builder, value);
            } else {
                renderObjectParam(builder, value);
            }
        }
    }
    private void renderBooleanParam(StringBuilder builder, Object param) {
        if (Boolean.TRUE.equals(param)) {
            builder.append("true");
        } else {
            builder.append("false");
        }
    }

    private void renderListParam(StringBuilder builder, Object param) {
        builder.append("[");
        Iterable list = (Iterable)param;
        boolean first = true;

        for (Object value: list) {
            if (first) {
                first = false;
            }
            else {
                builder.append(", ");
            }

            if (value instanceof String) {
                printString(builder, value);
            } else if (value instanceof Integer || value instanceof Long) {
                builder.append(value);
            } else if (value instanceof Boolean) {
                renderBooleanParam(builder, value);
            } else {
                throw new UnsupportedOperationException();
            }
        }

        builder.append("]");
    }

    private void printString(StringBuilder builder, Object value) {
        builder.append("\"");
        builder.append(value.toString().replace("\"", "\\\""));
        builder.append("\"");
    }

    private void printIndent(StringBuilder builder, int indent)
    {
        for (int i = 0; i < indent; i++) {
            builder.append("  ");
        }
    }
}

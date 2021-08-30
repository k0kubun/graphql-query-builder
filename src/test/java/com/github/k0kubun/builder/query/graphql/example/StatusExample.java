package com.github.k0kubun.builder.query.graphql.example;

import java.util.List;

public class StatusExample {
    private List<String> status;
    private Boolean enabled;
    private Boolean visible;

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public void setStatus(List<String> status) {
        this.status = status;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}

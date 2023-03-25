package com.ah.book.domain;

import com.ah.book.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Map;

public class Dict extends BaseEntity {
    private String value;
    private String label;
    private Map<String,String> raw;

    public Dict(String value, String label, Map<String, String> raw) {
        this.value = value;
        this.label = label;
        this.raw = raw;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Map<String, String> getRaw() {
        return raw;
    }

    public void setRaw(Map<String, String> raw) {
        this.raw = raw;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("value",getValue())
                .append("label",getLabel())
                .append("raw",getRaw())
                .toString();
    }
}

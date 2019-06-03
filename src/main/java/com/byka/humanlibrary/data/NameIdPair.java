package com.byka.humanlibrary.data;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class NameIdPair implements Comparable<NameIdPair> {
    private String name;
    private Long id;

    public NameIdPair() {
        this.name = "";
        this.id = -1L;
    }

    public NameIdPair(String name, Long id) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int compareTo(NameIdPair o) {
        int res = this.name.compareTo(o.name);
        if (res == 0) {
            res = this.id.compareTo(o.id);
        }

        return res;
    }
}

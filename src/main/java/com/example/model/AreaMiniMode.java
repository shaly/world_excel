package com.example.model;

import java.io.PrintStream;
import java.io.Serializable;
import java.security.PrivateKey;
import java.util.List;

/**
 * @author: Jane
 * @date: 2020-09-01 11:25
 * @description:
 */
public class AreaMiniMode implements Serializable {
    private Integer code;
    private String name;
    private List<AreaMiniMode> children;

    @Override
    public String toString() {
        return "AreaMiniMode{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", children=" + children +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AreaMiniMode> getChildren() {
        return children;
    }

    public void setChildren(List<AreaMiniMode> children) {
        this.children = children;
    }
}

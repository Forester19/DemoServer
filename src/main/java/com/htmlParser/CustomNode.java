package com.htmlParser;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
@Scope(value = "prototype")
public class CustomNode {
    private String title;
    private Map<String, String> attributes = new HashMap<>();
    private boolean isShortTag;
    private boolean isOpenTag;


    public CustomNode() {
    }

    public CustomNode(String title, Map<String, String> attributes, boolean isShortTag, boolean isOpenTag) {
        this.title = title;
        this.attributes = attributes;
        this.isShortTag = isShortTag;
        this.isOpenTag = isOpenTag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map<String, String> getAttributes() {
        return Collections.unmodifiableMap(attributes);
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public boolean isShortTag() {
        return isShortTag;
    }

    public void setShortTag(boolean shortTag) {
        isShortTag = shortTag;
    }

    public boolean isOpenTag() {
        return isOpenTag;
    }

    public void setOpenTag(boolean openTag) {
        isOpenTag = openTag;
    }

    @Override
    public String toString() {
        return "CustomNode{" +
                "title='" + title + '\'' +
                ", attributes=" + attributes +
                ", isShortTag=" + isShortTag +
                ", isOpenTag=" + isOpenTag +
                '}';
    }
}

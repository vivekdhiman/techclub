package com.contentful.demo.models.components;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomTabs {
    private String tabTitle;
    private String tabContent;
    private int orderNum;
    private String collapsedDefault;
}

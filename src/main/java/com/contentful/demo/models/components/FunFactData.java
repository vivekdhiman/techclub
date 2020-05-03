package com.contentful.demo.models.components;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FunFactData {
    private String iconClass;
    private String dataCount;
    private String bottomSubHeading;
    private String bottomHeading;
}

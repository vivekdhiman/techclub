package com.contentful.demo.models.components;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PortfolioWork {
    private String headingText;
    private String subHeadingText;
    private String imageUrl;
}

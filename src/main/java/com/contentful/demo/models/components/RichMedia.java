package com.contentful.demo.models.components;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RichMedia {
    private String heading;
    private String subHeading;
    private String imageUrl;
}

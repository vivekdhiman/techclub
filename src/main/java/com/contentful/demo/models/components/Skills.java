package com.contentful.demo.models.components;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Skills {
    private String skillName;
    private String skillDataPercentage;
    private String skillPercentageTitle;
    private String description;
}

package com.contentful.demo.models.components;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CapabilitiesShowcase {
    private String title;
    private List<Skills> skillsList;
}

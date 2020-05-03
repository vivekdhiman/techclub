package com.contentful.demo.models.components;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfileTile {
    private String fullName;
    private String designation;
    private String imageUrl;
}

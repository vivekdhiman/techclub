package com.contentful.demo.models.components;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ProfileModel {
    private String headingText;
    private String subHeadingText;
    List<ProfileTile> profileTileList;
}

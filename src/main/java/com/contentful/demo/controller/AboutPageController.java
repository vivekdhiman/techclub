package com.contentful.demo.controller;

import com.contentful.demo.DemoApplication;
import com.contentful.demo.constants.SystemConstants;
import com.contentful.demo.models.components.*;
import com.contentful.java.cda.CDAAsset;
import com.contentful.java.cda.CDAEntry;
import com.github.mjeanroy.springmvc.view.mustache.core.ModelAndMustacheView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class AboutPageController {

    @RequestMapping(value = "/about", method = GET)
    public ModelAndView about() {
        CDAEntry homePageEntry = DemoApplication.client
                .fetch(CDAEntry.class)
                .one(SystemConstants.ENTITY_TYPE_NAV);
        ModelAndMustacheView view = new ModelAndMustacheView("about");
        CDAAsset logoImage =  homePageEntry.getField("logo");

        CDAEntry aboutBanner = DemoApplication.client
                .fetch(CDAEntry.class)
                .one(SystemConstants.ABOUT_ENTITY_TYPE_BANNER);

        CDAEntry aboutCapabilities = DemoApplication.client
                .fetch(CDAEntry.class)
                .one(SystemConstants.ABOUT_ENTITY_TYPE_CAPABILITIES);

        CDAEntry footerEntry = DemoApplication.client
                .fetch(CDAEntry.class)
                .one(SystemConstants.ENTITY_TYPE_FOOTER);

        CDAEntry tabs = DemoApplication.client
                .fetch(CDAEntry.class)
                .one(SystemConstants.ABOUT_ENTITY_TYPE_TABS);

        CDAEntry profile = DemoApplication.client
                .fetch(CDAEntry.class)
                .one(SystemConstants.ABOUT_ENTITY_TYPE_PROFILE);



        CapabilitiesShowcase showcase = buildCapabilities(aboutCapabilities);
        List<CustomTabs> customTabs = getCustonTabList(tabs);

        ProfileModel profileModel = getProfileModel(profile);


        view.addObject("aboutBanner", aboutBanner);
        view.addObject("home", homePageEntry);
        view.addObject("logo", logoImage);
        view.addObject("footer", footerEntry);
        view.addObject("aboutCapabilities", showcase);
        view.addObject("customTabs", customTabs);
        view.addObject("profileModel", profileModel);



        return view;
    }

    private CapabilitiesShowcase buildCapabilities(final CDAEntry cdaEntry){
        final String title = cdaEntry.getField("columnOneTitle");
        final List<Skills> skillsList = new ArrayList<>();
        List<CDAEntry> cdaEntryList= cdaEntry.getField("refernceList");
        cdaEntryList.stream().forEach(cdaEntryObject -> {
               List colOneItemsList =  cdaEntryObject.getField("columnOneItems");
               List colTwoItemsList =  cdaEntryObject.getField("columnTwoItems");
               Skills skillOne = buildSkill(colOneItemsList);
               Skills skillTwo = buildSkill(colTwoItemsList);
               skillsList.add(skillOne);
               skillsList.add(skillTwo);
        });
        CapabilitiesShowcase capabilitiesShowcase = CapabilitiesShowcase.builder().title(title).skillsList(skillsList).build();
        return capabilitiesShowcase;
    }

    private Skills buildSkill(final List skillList) {
       return Skills.builder().skillName(skillList.get(0).toString())
               .description(skillList.get(3).toString())
               .skillPercentageTitle(skillList.get(2).toString())
               .skillDataPercentage(skillList.get(1).toString())
               .build();
    }

    private List<CustomTabs> getCustonTabList(final CDAEntry cdaEntry) {
        final List<String> tabTitles = cdaEntry.getField("columnOneItems");
        final List<String> tabContents = cdaEntry.getField("columnTwoItems");
        final List<CustomTabs> customTabsList = new ArrayList<>();

        Iterator<String> tabTitlesIterator = tabTitles.iterator();
        Iterator<String> tabContentIterator = tabContents.iterator();
        int index = 0;
        while(tabTitlesIterator.hasNext() && tabContentIterator.hasNext()) {
            index++;
            CustomTabs customTabs = CustomTabs.builder()
                    .tabTitle(tabTitlesIterator.next())
                    .tabContent(tabContentIterator.next())
                    .orderNum(index)
                    .build();
            if(index != 1) {
                customTabs.setCollapsedDefault("collapsed");
            }
            customTabsList.add(customTabs);
        }
        return customTabsList;

    }

    private ProfileModel getProfileModel(final CDAEntry profile) {
        final String mainTitle = profile.getField("mainTitle");
        final String description = profile.getField("description");
        final List<String> headings = profile.getField("heading");
        final List<String> subHeadings = profile.getField("subheading");
        final List<CDAAsset> images = profile.getField("images");
        final List<ProfileTile> profileTileList = new ArrayList<>();

        Iterator<String> headingIterator = headings.iterator();
        Iterator<String> subHeadingIterator = subHeadings.iterator();
        Iterator<CDAAsset> imageIterator = images.iterator();

        while(headingIterator.hasNext() && subHeadingIterator.hasNext() && imageIterator.hasNext()) {
            ProfileTile profileTile = ProfileTile.builder()
                    .fullName(headingIterator.next())
                    .designation(subHeadingIterator.next())
                    .imageUrl(imageIterator.next().url())
                    .build();
            profileTileList.add(profileTile);
        }
        return ProfileModel.builder().headingText(mainTitle).subHeadingText(description).profileTileList(profileTileList).build();




    }


}
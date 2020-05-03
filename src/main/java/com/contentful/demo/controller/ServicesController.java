package com.contentful.demo.controller;

import com.contentful.demo.DemoApplication;
import com.contentful.demo.constants.SystemConstants;
import com.contentful.demo.models.components.FunFactData;
import com.contentful.demo.models.components.ProfileTile;
import com.contentful.demo.models.components.RichMedia;
import com.contentful.java.cda.CDAAsset;
import com.contentful.java.cda.CDAEntry;
import com.github.mjeanroy.springmvc.view.mustache.core.ModelAndMustacheView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class ServicesController {

    @RequestMapping(value = "/services", method = GET)
    public ModelAndView services() {
        CDAEntry homePageEntry = DemoApplication.client
                .fetch(CDAEntry.class)
                .one(SystemConstants.ENTITY_TYPE_NAV);
        ModelAndMustacheView view = new ModelAndMustacheView("services");
        CDAAsset logoImage = homePageEntry.getField("logo");


        CDAEntry services = DemoApplication.client
                .fetch(CDAEntry.class)
                .one(SystemConstants.SERVICES_ENTITY_TYPE_BANNER);

        CDAEntry servicesOffered = DemoApplication.client
                .fetch(CDAEntry.class)
                .one(SystemConstants.SERVICES_ENTITY_TYPE_OFFERED);

        List<RichMedia> richMediaList = getServiceOffered(servicesOffered);

        CDAEntry funFactsEntity = DemoApplication.client
                .fetch(CDAEntry.class)
                .one(SystemConstants.SERVICES_ENTITY_TYPE_FUNFACTS);

        List<FunFactData> funFactDataList = getFunFactData(funFactsEntity);

        CDAEntry footerEntry = DemoApplication.client
                .fetch(CDAEntry.class)
                .one(SystemConstants.ENTITY_TYPE_FOOTER);


        view.addObject("services", services);
        view.addObject("home", homePageEntry);
        view.addObject("logo", logoImage);
        view.addObject("richMediaList", richMediaList);
        view.addObject("funFactDataList", funFactDataList);

        view.addObject("footer", footerEntry);



        return view;
    }

    private List<RichMedia>getServiceOffered(final CDAEntry servicesOffered) {
        final List<String> headings = servicesOffered.getField("heading");
        final List<String> subHeadings = servicesOffered.getField("subheading");
        final List<CDAAsset> images = servicesOffered.getField("images");
        final List<RichMedia> richMediaList = new ArrayList<>();

        Iterator<String> headingIterator = headings.iterator();
        Iterator<String> subHeadingIterator = subHeadings.iterator();
        Iterator<CDAAsset> imageIterator = images.iterator();

        while(headingIterator.hasNext() && subHeadingIterator.hasNext() && imageIterator.hasNext()) {
            RichMedia richMedia = RichMedia.builder()
                    .heading(headingIterator.next())
                    .subHeading(subHeadingIterator.next())
                    .imageUrl(imageIterator.next().url())
                    .build();
            richMediaList.add(richMedia);
        }
        return richMediaList;

    }

    private List<FunFactData> getFunFactData(final CDAEntry data) {
        final List<String> iconClasses = data.getField("columnOneItems");
        final List<String> dataSet = data.getField("columnTwoItems");
        final List<String> bottomSubHeading = data.getField("columnThreeItems");
        final List<String> bottomHeading = data.getField("columnsFourItems");

        Iterator<String> iconClassesIterator = iconClasses.iterator();
        Iterator<String> dataSetIterator = dataSet.iterator();
        Iterator<String> bottomSubHeadingIterator = bottomSubHeading.iterator();
        Iterator<String> bottomHeadingIterator = bottomHeading.iterator();

        List<FunFactData> funFactDataList = new ArrayList<>();

        while (iconClassesIterator.hasNext() && dataSetIterator.hasNext()
                && bottomSubHeadingIterator.hasNext() && bottomHeadingIterator.hasNext()) {
            FunFactData funFactData = FunFactData.builder().iconClass(iconClassesIterator.next())
                    .dataCount(dataSetIterator.next()).bottomSubHeading(bottomSubHeadingIterator.next())
                    .bottomHeading(bottomHeadingIterator.next()).build();
            funFactDataList.add(funFactData);
        }
        return funFactDataList;
    }
}

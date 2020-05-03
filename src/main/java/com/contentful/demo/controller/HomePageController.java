package com.contentful.demo.controller;

import com.contentful.demo.DemoApplication;
import com.contentful.demo.constants.SystemConstants;
import com.contentful.java.cda.CDAAsset;
import com.contentful.java.cda.CDAEntry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.mjeanroy.springmvc.view.mustache.core.ModelAndMustacheView;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class HomePageController {

    @RequestMapping(value = "/home", method = GET)
    public ModelAndView home() {
        CDAEntry homePageEntry = DemoApplication.client
                .fetch(CDAEntry.class)
                .one(SystemConstants.ENTITY_TYPE_NAV);
        ModelAndMustacheView view = new ModelAndMustacheView("home");
        CDAAsset logoImage =  homePageEntry.getField("logo");

        CDAEntry homapageBanner = DemoApplication.client
                .fetch(CDAEntry.class)
                .one(SystemConstants.ENTITY_TYPE_HOME_BANNER);

        CDAEntry ourStoryTextAndImage = DemoApplication.client
                .fetch(CDAEntry.class)
                .one(SystemConstants.ENTITY_TYPE_OUR_STORY);

        CDAEntry ctaBannerEntity = DemoApplication.client
                .fetch(CDAEntry.class)
                .one(SystemConstants.HOME_ENTITY_TYPE_CTA_BANNER);

        CDAEntry ourClientEntry = DemoApplication.client
                .fetch(CDAEntry.class)
                .one(SystemConstants.HOME_ENTITY_TYPE_OUR_CLIENTS);

        CDAEntry footerEntry = DemoApplication.client
                .fetch(CDAEntry.class)
                .one(SystemConstants.ENTITY_TYPE_FOOTER);



        view.addObject("homeBanner", homapageBanner);
        view.addObject("home", homePageEntry);
        view.addObject("logo", logoImage);
        view.addObject("ourStory", ourStoryTextAndImage);
        view.addObject("ctaBanner", ctaBannerEntity);
        view.addObject("ourClient", ourClientEntry);
        view.addObject("footer", footerEntry);




        return view;
    }
}
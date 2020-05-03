package com.contentful.demo.controller;

import com.contentful.demo.DemoApplication;
import com.contentful.demo.constants.SystemConstants;
import com.contentful.java.cda.CDAAsset;
import com.contentful.java.cda.CDAEntry;
import com.github.mjeanroy.springmvc.view.mustache.core.ModelAndMustacheView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class ContactUsController {
    @RequestMapping(value = "/contact", method = GET)
    public ModelAndView contact() {
        CDAEntry homePageEntry = DemoApplication.client
                .fetch(CDAEntry.class)
                .one(SystemConstants.ENTITY_TYPE_NAV);
        ModelAndMustacheView view = new ModelAndMustacheView("contact");
        CDAAsset logoImage =  homePageEntry.getField("logo");


        CDAEntry footerEntry = DemoApplication.client
                .fetch(CDAEntry.class)
                .one(SystemConstants.ENTITY_TYPE_FOOTER);

        view.addObject("home", homePageEntry);
        view.addObject("logo", logoImage);
        view.addObject("footer", footerEntry);
        return view;
    }

}

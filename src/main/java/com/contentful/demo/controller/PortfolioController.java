package com.contentful.demo.controller;

import com.contentful.demo.DemoApplication;
import com.contentful.demo.constants.SystemConstants;
import com.contentful.demo.models.components.PortfolioWork;
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
public class PortfolioController {

    @RequestMapping(value = "/portfolio", method = GET)
    public ModelAndView portfolio() {
        CDAEntry homePageEntry = DemoApplication.client
                .fetch(CDAEntry.class)
                .one(SystemConstants.ENTITY_TYPE_NAV);
        ModelAndMustacheView view = new ModelAndMustacheView("portfolio");
        CDAAsset logoImage = homePageEntry.getField("logo");
        CDAEntry portFolioBanner = DemoApplication.client
                .fetch(CDAEntry.class)
                .one(SystemConstants.PORTFOLIO_ENTITY_TYPE_BANNER);

        CDAEntry portFolioWork = DemoApplication.client
                .fetch(CDAEntry.class)
                .one(SystemConstants.PORTFOLIO_ENTITY_TYPE_WORK);
        List<PortfolioWork> portfolioWorkList = getWorkList(portFolioWork);

        CDAEntry footerEntry = DemoApplication.client
                .fetch(CDAEntry.class)
                .one(SystemConstants.ENTITY_TYPE_FOOTER);


        view.addObject("home", homePageEntry);
        view.addObject("logo", logoImage);
        view.addObject("portFolioBanner", portFolioBanner);
        view.addObject("portfolioWorkList", portfolioWorkList);
        view.addObject("footer", footerEntry);

        return view;
    }

    private List<PortfolioWork> getWorkList(final CDAEntry portFolioWork) {
        final List<String> headingList = portFolioWork.getField("heading");
        final List<String> subHeadingList = portFolioWork.getField("subheading");
        final List<CDAAsset> imagesList = portFolioWork.getField("images");

        final List<PortfolioWork> portfolioWorkList = new ArrayList<>();


        Iterator<String> headingIterator = headingList.iterator();
        Iterator<String> subHeadingIterator = subHeadingList.iterator();
        Iterator<CDAAsset> imageIterator = imagesList.iterator();

        while(headingIterator.hasNext() && subHeadingIterator.hasNext() && imageIterator.hasNext()) {
            PortfolioWork portfolioWork = PortfolioWork.builder()
                    .headingText(headingIterator.next())
                    .subHeadingText(subHeadingIterator.next())
                    .imageUrl(imageIterator.next().url())
                    .build();
            portfolioWorkList.add(portfolioWork);
        }
        return portfolioWorkList;
    }

}

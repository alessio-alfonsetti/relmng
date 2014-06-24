package adp.realmng.controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.theme.AbstractThemeResolver;

public class ThemeController extends AbstractThemeResolver {

	    @Override
	    public String resolveThemeName(HttpServletRequest arg0) {
	        return isNight() ? "dark" : "bright";
	    }

	    // implementation
	    private boolean isNight() {
	        return new Random().nextBoolean();
	    }

	    @Override
	    public void setThemeName(HttpServletRequest arg0, HttpServletResponse arg1,
	            String arg2) {
	    }	
}

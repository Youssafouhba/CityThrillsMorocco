package com.CityThrillsMorocco.config;

import com.CityThrillsMorocco.enumeration.ActivityCategories;
import org.springframework.core.convert.converter.Converter;

public class StringToActivityCategoriesConverter implements Converter<String, ActivityCategories> {

    @Override
    public ActivityCategories convert(String source) {
        return ActivityCategories.valueOf(source);
    }
}

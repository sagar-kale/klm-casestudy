package com.klm.travel_casestudy.domain;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class JacksonLocationsModule extends SimpleModule {

    @Override
    public void setupModule(Module.SetupContext context) {
        context.setMixInAnnotations(Location.class, LocationMixin.class);
        context.setMixInAnnotations(Coordinates.class, CoordinatesMixin.class);
    }

}
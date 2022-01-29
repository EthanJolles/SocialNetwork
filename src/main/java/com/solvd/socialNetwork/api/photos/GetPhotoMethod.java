package com.solvd.socialNetwork.api.photos;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

import java.util.Properties;

public class GetPhotoMethod extends AbstractApiMethodV2 {
    public GetPhotoMethod() {
        super(null, "api/photos/_get/rs.json", new Properties());
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
    }
}

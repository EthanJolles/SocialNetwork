package com.solvd.socialNetwork.api.photos;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

public class DeletePhotoMethod extends AbstractApiMethodV2 {
    public DeletePhotoMethod() {
        super("api/photos/_delete/rq.json", "api/photos/_delete/rs.json", "api/photos/photo.properties");
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
    }
}

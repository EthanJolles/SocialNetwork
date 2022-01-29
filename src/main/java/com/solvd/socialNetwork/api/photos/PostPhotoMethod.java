package com.solvd.socialNetwork.api.photos;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

public class PostPhotoMethod extends AbstractApiMethodV2 {
    public PostPhotoMethod() {
        super("api/photos/_post/rq.json", "api/photos/_post/rs.json","api/photos/photo.properties");
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
    }
}

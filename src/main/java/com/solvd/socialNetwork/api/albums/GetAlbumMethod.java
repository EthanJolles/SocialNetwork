package com.solvd.socialNetwork.api.albums;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

import java.util.Properties;

public class GetAlbumMethod extends AbstractApiMethodV2 {
    public GetAlbumMethod() {
        super(null, "api/albums/_get/rs.json", new Properties());
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
    }
}

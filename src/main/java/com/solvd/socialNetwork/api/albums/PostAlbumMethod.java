package com.solvd.socialNetwork.api.albums;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

public class PostAlbumMethod extends AbstractApiMethodV2 {
    public PostAlbumMethod() {
        super("api/albums/_post/rq.json", "api/albums/_post/rs.json", "api/albums/album.properties");
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
    }
}

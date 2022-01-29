package com.solvd.socialNetwork.api.albums;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

public class DeleteAlbumMethod extends AbstractApiMethodV2 {
        public DeleteAlbumMethod() {
            super("api/albums/_delete/rq.json", "api/albums/_delete/rs.json", "api/albums/album.properties");
            replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
        }
    }

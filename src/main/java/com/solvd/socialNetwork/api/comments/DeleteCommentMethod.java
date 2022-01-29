package com.solvd.socialNetwork.api.comments;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

public class DeleteCommentMethod extends AbstractApiMethodV2 {
    public DeleteCommentMethod() {
        super("api/comments/_delete/rq.json", "api/comments/_delete/rs.json", "api/comments/comment.properties");
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
    }
}

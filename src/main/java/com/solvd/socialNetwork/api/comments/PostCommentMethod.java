package com.solvd.socialNetwork.api.comments;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

public class PostCommentMethod extends AbstractApiMethodV2 {
    public PostCommentMethod() {
        super("api/comments/_post/rq.json", "api/comments/_post/rs.json","api/comments/comment.properties");
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
    }
}

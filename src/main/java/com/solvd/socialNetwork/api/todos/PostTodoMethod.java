package com.solvd.socialNetwork.api.todos;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

public class PostTodoMethod extends AbstractApiMethodV2 {
    public PostTodoMethod() {
        super("api/todos/_post/rq.json", "api/todos/_post/rs.json","api/todos/todo.properties");
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
    }
}

package com.solvd.socialNetwork.api.todos;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

public class DeleteTodoMethod extends AbstractApiMethodV2 {
    public DeleteTodoMethod() {
        super("api/todos/_delete/rq.json", "api/todos/_delete/rs.json", "api/todos/todo.properties");
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
    }
}

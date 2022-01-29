package com.solvd.socialNetwork;

import com.qaprosoft.apitools.validation.JsonCompareKeywords;
import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.solvd.socialNetwork.api.todos.DeleteTodoMethod;
import com.solvd.socialNetwork.api.todos.GetTodoMethod;
import com.solvd.socialNetwork.api.todos.PostTodoMethod;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

public class APITodoTest extends AbstractApiMethodV2 {

    @Test
    @MethodOwner(owner = "Ethan Jolles")
    public void testCreateTodo() {
        PostTodoMethod api = new PostTodoMethod();
        api.expectResponseStatus(HttpResponseStatusType.CREATED_201);
        api.callAPI();
        api.validateResponse();
    }

    @Test
    @MethodOwner(owner = "Ethan Jolles")
    public void testGetTodo() {
        GetTodoMethod api = new GetTodoMethod();
        api.expectResponseStatus(HttpResponseStatusType.OK_200);
        api.callAPI();
        api.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        api.validateResponseAgainstSchema("api/todos/_get/rs.schema");
    }

    @Test
    @MethodOwner(owner = "Ethan Jolles")
    public void testDeleteTodo() {
        DeleteTodoMethod api = new DeleteTodoMethod();
        api.expectResponseStatus(HttpResponseStatusType.OK_200);
        api.callAPI();
        api.validateResponse();
    }
}

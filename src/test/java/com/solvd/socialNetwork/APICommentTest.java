package com.solvd.socialNetwork;

import com.qaprosoft.apitools.validation.JsonCompareKeywords;
import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.solvd.socialNetwork.api.comments.DeleteCommentMethod;
import com.solvd.socialNetwork.api.comments.GetCommentMethod;
import com.solvd.socialNetwork.api.comments.PostCommentMethod;
import org.apache.log4j.Logger;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

public class APICommentTest implements IAbstractTest {

    private static final Logger LOGGER = Logger.getLogger(APICommentTest.class);

    @Test
    @MethodOwner(owner = "Ethan Jolles")
    public void testCreateComment() {
        PostCommentMethod api = new PostCommentMethod();
        api.expectResponseStatus(HttpResponseStatusType.CREATED_201);
        api.callAPI();
        api.validateResponse();
    }

    @Test
    @MethodOwner(owner = "Ethan Jolles")
    public void testGetComment() {
        GetCommentMethod api = new GetCommentMethod();
        api.expectResponseStatus(HttpResponseStatusType.OK_200);
        api.callAPI();
        api.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        api.validateResponseAgainstSchema("api/comments/_get/rs.schema");
    }

    @Test
    @MethodOwner(owner = "Ethan Jolles")
    public void testDeleteComment() {
        DeleteCommentMethod api = new DeleteCommentMethod();
        api.expectResponseStatus(HttpResponseStatusType.OK_200);
        api.callAPI();
        api.validateResponse();
    }
}

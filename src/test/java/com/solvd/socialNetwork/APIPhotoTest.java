package com.solvd.socialNetwork;

import com.qaprosoft.apitools.validation.JsonCompareKeywords;
import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.solvd.socialNetwork.api.photos.DeletePhotoMethod;
import com.solvd.socialNetwork.api.photos.GetPhotoMethod;
import com.solvd.socialNetwork.api.photos.PostPhotoMethod;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

public class APIPhotoTest implements IAbstractTest {

    @Test
    @MethodOwner(owner = "Ethan Jolles")
    public void testCreatePhoto() {
        PostPhotoMethod api = new PostPhotoMethod();
        api.expectResponseStatus(HttpResponseStatusType.CREATED_201);
        api.callAPI();
        api.validateResponse();
    }

    @Test
    @MethodOwner(owner = "Ethan Jolles")
    public void testGetPhoto() {
        GetPhotoMethod api = new GetPhotoMethod();
        api.expectResponseStatus(HttpResponseStatusType.OK_200);
        api.callAPI();
        api.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        api.validateResponseAgainstSchema("api/photos/_get/rs.schema");
    }

    @Test
    @MethodOwner(owner = "Ethan Jolles")
    public void testDeletePhoto() {
        DeletePhotoMethod api = new DeletePhotoMethod();
        api.expectResponseStatus(HttpResponseStatusType.OK_200);
        api.callAPI();
        api.validateResponse();
    }
}

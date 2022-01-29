package com.solvd.socialNetwork;

import com.azure.core.annotation.Delete;
import com.qaprosoft.apitools.validation.JsonCompareKeywords;
import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.solvd.socialNetwork.api.albums.DeleteAlbumMethod;
import com.solvd.socialNetwork.api.albums.GetAlbumMethod;
import com.solvd.socialNetwork.api.albums.PostAlbumMethod;
import org.apache.log4j.Logger;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

public class APIAlbumTest implements IAbstractTest {

    private static final Logger LOGGER = Logger.getLogger(APIAlbumTest.class);

    @Test
    @MethodOwner(owner = "Ethan Jolles")
    public void testCreateAlbum() throws Exception {
        PostAlbumMethod api = new PostAlbumMethod();
        api.expectResponseStatus(HttpResponseStatusType.CREATED_201);
        api.callAPI();
        api.validateResponse();
    }

    @Test
    @MethodOwner(owner = "Ethan Jolles")
    public void testGetAlbum() throws Exception {
        GetAlbumMethod api = new GetAlbumMethod();
        api.expectResponseStatus(HttpResponseStatusType.OK_200);
        api.callAPI();
        api.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        api.validateResponseAgainstSchema("api/albums/_get/rs.schema");
    }

    @Test
    @MethodOwner(owner = "Ethan Jolles")
    public void testDeleteAlbum() throws Exception {
        DeleteAlbumMethod api = new DeleteAlbumMethod();
    }
}

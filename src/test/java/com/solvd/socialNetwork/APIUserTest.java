package com.solvd.socialNetwork;

import com.qaprosoft.apitools.validation.JsonCompareKeywords;
import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.core.foundation.utils.tag.Priority;
import com.qaprosoft.carina.core.foundation.utils.tag.TestPriority;
import com.solvd.socialNetwork.api.users.DeleteUserMethod;
import com.solvd.socialNetwork.api.users.GetUserMethod;
import com.solvd.socialNetwork.api.users.PostUserMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

public class APIUserTest implements IAbstractTest {

    private static final Logger LOGGER = LogManager.getLogger(APIUserTest.class);

    @Test()
    @MethodOwner(owner = "Ethan")
    public void testCreateUser() throws Exception {
        LOGGER.info("Test Create User");
        setCases("4555,54545");
        PostUserMethod api = new PostUserMethod();
        api.expectResponseStatus(HttpResponseStatusType.CREATED_201);
        api.callAPI();
        api.validateResponse();
    }

    @Test()
    @MethodOwner(owner = "Ethan")
    public void testCreateUserMissingFields() throws Exception {
        LOGGER.info("Test Create User Missing Fields");
        PostUserMethod api = new PostUserMethod();
        api.removeProperty("name");
        api.removeProperty("address");
        api.expectResponseStatus(HttpResponseStatusType.CREATED_201);
        api.callAPI();
        api.validateResponse();
    }

    @Test()
    @MethodOwner(owner = "Ethan")
    public void testGetUser() throws Exception {
        LOGGER.info("Test Get User");
        GetUserMethod api = new GetUserMethod();
        api.expectResponseStatus(HttpResponseStatusType.OK_200);
        api.callAPI();
        api.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        api.validateResponseAgainstSchema("api/users/_get/rs.schema");
    }

    @Test()
    @MethodOwner(owner = "Ethan")
    @TestPriority(Priority.P1)
    public void testDeleteUsers() {
        DeleteUserMethod api = new DeleteUserMethod();
        api.expectResponseStatus(HttpResponseStatusType.OK_200);
        api.callAPI();
        api.validateResponse();
    }
}

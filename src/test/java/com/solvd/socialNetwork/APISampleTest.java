package com.solvd.socialNetwork;

import com.qaprosoft.apitools.validation.JsonCompareKeywords;
import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.core.foundation.utils.tag.Priority;
import com.qaprosoft.carina.core.foundation.utils.tag.TestPriority;
import com.solvd.socialNetwork.api.DeleteUserMethod;
import com.solvd.socialNetwork.api.GetUserMethods;
import com.solvd.socialNetwork.api.PostUserMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

public class APISampleTest implements IAbstractTest {

    private static final Logger LOGGER = LogManager.getLogger(APISampleTest.class);

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
        GetUserMethods getUserMethods = new GetUserMethods();
        getUserMethods.expectResponseStatus(HttpResponseStatusType.OK_200);
        getUserMethods.callAPI();
        getUserMethods.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        getUserMethods.validateResponseAgainstSchema("api/users/_get/rs.schema");
    }

    @Test()
    @MethodOwner(owner = "Ethan")
    @TestPriority(Priority.P1)
    public void testDeleteUsers() {
        DeleteUserMethod deleteUserMethod = new DeleteUserMethod();
        deleteUserMethod.expectResponseStatus(HttpResponseStatusType.OK_200);
        deleteUserMethod.callAPI();
        deleteUserMethod.validateResponse();
    }

}

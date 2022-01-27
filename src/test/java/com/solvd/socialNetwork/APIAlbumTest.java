package com.solvd.socialNetwork;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.solvd.socialNetwork.api.albums.PostAlbumMethod;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class APIAlbumTest implements IAbstractTest {

    private static final Logger LOGGER = Logger.getLogger(APIAlbumTest.class);

    @Test
    @MethodOwner(owner = "Ethan Jolles")
    public void testCreateAlbum() throws Exception {
        PostAlbumMethod api = new PostAlbumMethod();
    }
}

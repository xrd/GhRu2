package ghru.com.ghru2;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import java.util.Date;

import static java.lang.Integer.parseInt;
import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class GitHubHelperTest {
    @Test
    public void testClient() throws Exception {
        String login = "wovenmedia";
        String password = "Yerevan2011";
        String repoName = "wovenmedia.github.io";
        String randomString = String.valueOf( Double.valueOf( Math.random() * 10000000)).toString();
        String randomAndDate = randomString + " " + (new Date()).toString() ;
        GitHubHelper ghh = new GitHubHelper( login, password );
        assert( ghh.SaveFile( repoName, randomAndDate, "Just the body text" ) );

        String url = "http://wovenmedia.github.io";
        OkHttpClient ok = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = ok.newCall(request).execute();
        String body = response.body().string();
        boolean rv = body.contains( randomAndDate );
        assertTrue( "Body does not contain the expected random string and date (" + randomAndDate + ")", rv );
    }

}


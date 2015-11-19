package com.example.ghru;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.os.AsyncTask;
import org.eclipse.egit.github.core.service.UserService;
import org.eclipse.egit.github.core.User;
import java.io.IOException;

public class MainActivity extends Activity
{
    String username, password;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main); 

        Button login = (Button)findViewById( R.id.login ); 
        login.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    EditText utv = (EditText)findViewById( R.id.username ); 
                    EditText ptv = (EditText)findViewById( R.id.password );
                    username = (String)utv.getText().toString();
                    password = (String)ptv.getText().toString();

                    new LoginTask().execute(username, password);
                }
            });
    }

    private void loggedIn() {

        setContentView(R.layout.logged_in); 

        Button submit = (Button)findViewById( R.id.submit );
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                TextView status = (TextView) findViewById(R.id.login_status);
                status.setText("Logging in, please wait...");

                EditText post = (EditText) findViewById(R.id.post);
                String postContents = post.getText().toString();

                EditText repo = (EditText) findViewById(R.id.repository);
                String repoName = repo.getText().toString();

                EditText title = (EditText) findViewById(R.id.title);
                String titleText = title.getText().toString();

                doPost(repoName, titleText, postContents);
            }
        });
    }

    class LoginTask extends AsyncTask<String, Void, Boolean> {  
        @Override
            protected Boolean doInBackground(String... credentials) {
            boolean rv = false;
            UserService us = new UserService();
            us.getClient().setCredentials( credentials[0], credentials[1] ); 
            try {
                User user = us.getUser( credentials[0] ); 
                rv = null != user;
            }
            catch( IOException ioe ) {}
            return rv;
        }
        
        @Override
            protected void onPostExecute(Boolean result) {
            if( result ) {
                loggedIn(); 
            }
            else {
                TextView status = (TextView)findViewById( R.id.login_status );
                status.setText( "Invalid login, please check credentials" ); 
            }
        }
    }

    private void doPost( String repoName, String title, String post ) {
        new PostTask().execute( username, password, repoName, title, post );
    }

    class PostTask extends AsyncTask<String, Void, Boolean> {  

        @Override 
            protected Boolean doInBackground(String... information) {
            String login = information[0];
            String password = information[1];
            String repoName = information[2];
            String titleText = information[3];
            String postContents = information[4];

            Boolean rv = false;
            GitHubHelper ghh = new GitHubHelper( login, password );
            try {
                ghh.SaveFile(repoName, titleText, postContents, "GhRu Update");
                rv = true;
            }
            catch( IOException ioe) {
                Log.d(ioe.getStackTrace().toString(), "GhRu");
            }
            return rv;
        }
        
        @Override
            protected void onPostExecute(Boolean result) {
            TextView status = (TextView)findViewById( R.id.post_status );
            if( result ) {
                status.setText( "Successful jekyll post" );
            }
            else {
                status.setText( "Post failed." ); 
            }
        }
    }


    
}

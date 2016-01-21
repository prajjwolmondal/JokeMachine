package com.example.prajjwol.jokemachine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Prajjwol on 20/01/2016.
 */
public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboot);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                finish();
                return true;
            case R.id.action_submit:
                Intent startSubmitJoke = new Intent(this, SubmitJoke.class);
                startActivity(startSubmitJoke);
                return true;
            case R.id.action_contact:
                sendEmail();
                return true;
            case R.id.action_aboot:
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    public void sendEmail() {
        /* Create the Intent */
        final Intent emailIntent = new Intent(Intent.ACTION_SEND);

        /* Fill it with Data */
        emailIntent.setType("plain/text");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"prajj.mondal@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Contacting Supreme and Most Awesome Dev for JokeMachine");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear Awesome and Sexy Dev, \n \n \n Your Gorgeous Girlfriend, \n Annoying Ammi");


        /* Send it off to the Activity-Chooser */
        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }

}

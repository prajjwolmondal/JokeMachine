package com.example.prajjwol.jokemachine;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import com.parse.ParseObject;

/**
 * Created by Prajjwol on 16/12/2015.
 */
public class SubmitJoke extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit_joke);
    }

    public void submitJoke(View view){
        EditText first = (EditText) (findViewById(R.id.firstPart));
        EditText second = (EditText) (findViewById(R.id.secondPart));
        String f = String.valueOf(first.getText());
        String s = String.valueOf(second.getText());
        if (f.trim().equals("")){
            first.setError("Come on Ammi..");
        }
        else if (s.trim().equals("")){
            second.setError("Really Ammi...");
        }
        else {
            ParseObject d = new ParseObject("jokes");
            //d.put("Word","a");
            d.put("JokePart1",f);
            d.put("JokePart2",s);
            d.saveInBackground();
            finish();
        }
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
                return true;
            case R.id.action_contact:
                sendEmail();
                return true;
            case R.id.action_aboot:
                Intent startAboot = new Intent(this, About.class);
                startActivity(startAboot);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    public void sendEmail(){
        /* Create the Intent */
        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

        /* Fill it with Data */
        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"prajj.mondal@gmail.com"});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Contacting Dev for JokeMachine");

        /* Send it off to the Activity-Chooser */
        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Don't think you're funny enough?")
                    .setCancelable(false)
                    .setPositiveButton("I'm not funny :(", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            SubmitJoke.this.finish();
                        }
                    })
                    .setNegativeButton("Fuck you, I'm funny!", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
        return super.onKeyDown(keyCode, event);
    }

}

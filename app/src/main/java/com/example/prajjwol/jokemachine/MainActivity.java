package com.example.prajjwol.jokemachine;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.TextView;
import java.util.ArrayList;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.Parse;
import com.parse.ParseQuery;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Parse.initialize(this, "zFInIZhEujXzHdJQJVGLwIuRReS5IQTaVxgEdSnQ", "qtZs3raRUVNjJIGlJq6lS59Q7s3WNvgAkqNfhLSo");
        scheduleNotification(getNotification(generateNiceMessage(),"Compliment Time!"), getTime());
    }

//    public void onResume(){
//        super.onResume(); // Always call the superclass method first
//        scheduleNotification(getNotification("",welcomeMessage()), 3000);
//    }
//
//    public String welcomeMessage(){
//        String[] messages = {"Welcome back boss!", "It's good to see you back again!!","I'm here to please you ;-)","Welcome back to the app, Ammi","Thank god it's you, and not Prajjwol.","Hey baby, I'm digging that outfit ;)","You back to push my buttons?"};
//        int rand = returnRand(messages.length);
//        return messages[rand];
//    }

    public int getTime(){
        int[] possibleTimes = {14400000,10800000,7200000,3600000,2700000,1800000,900000,300000,60000,30000};
        return possibleTimes[returnRand(possibleTimes.length)];
    }

    private Notification getNotification(String content,String title) {
//        Notification.Builder builder = new Notification.Builder(this);
//        builder.setContentTitle(content);
//        builder.setSmallIcon(R.drawable.ic_launcher);
//        builder.setAutoCancel(true);
//        builder.setPriority(Notification.PRIORITY_LOW);
//        builder.setVibrate(new long[]{1500, 1000, 500}).build();
//        return builder.build();
//
        Notification.Builder builder =
                new Notification.Builder(this)
                        .setContentTitle(title)
                        .setContentText(content)
                        .setSmallIcon(R.drawable.notification_icon)
                        .setAutoCancel(true)
                        .setPriority(Notification.PRIORITY_LOW)
                        .setStyle(new Notification.BigTextStyle()
                                .bigText(content));

        builder.setVibrate(new long[] { 1000, 1000 }).build();
        builder.setLights(Color.RED, 2000, 5000);
        return builder.build();
    }

    private void scheduleNotification(Notification notification, int delay) {

        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

    public String generateNiceMessage(){
        String[] messages = {"Is eveybody from Kenya as awesome as you??","You're so cute that puppies and kittens send pictures of you to each other.\n","You're soo fucking beautiful!","You're just too insanely loveable!! <3","You are the best kisser in the world!","Hahaha gotcha :P","You're the strongest and kindest woman I know :)","Aaammmmiiiiiiiii","....Error...Can't process...how breathtakingly gorgeous you are...","Your hair makes you so look much more attractive and sexier.","You make that dress look sooo good","Steve Harvey was supposed to say your name, but he fucked up!","I'm positive that the girl who walked past you got jealous of how sexy you look","You're ammizing!","I Love You Sooooooo Much","Your smile brightens the world", "Is this getting annoying?","Damn girl!! You look stunning!","You smell amaaazing!","If looks could kill, you'd be a mass murderer","<Insert cheesy yet funny comment about how beautiful Ammi looks>","I'm betting that right now I'm thinking about how beautiful you are naked","You in that outfit, is the cure for depression","YOU have the CUTEST smile and laugh :D","Your eyes are BEA-UU-TIFUL. They look like caramel covered candy.","You're such an adorable and cute butt!","You're stunningly sexy as fuck!","Your reporting skills are so rad!","How much are you digging these?","You're one of the most interesting person I've ever known","I love thhudasasodnwsadasqw. Damn, looks like I had an Ammi stroke!","BOOOOOOOOOBSS"};
        int len = messages.length;
        int rand = returnRand(len);
        return messages[rand];
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
                return true;
            case R.id.action_submit:
                Intent startSubmitJoke = new Intent(this, SubmitJoke.class);
                startActivity(startSubmitJoke);
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

    public int returnRand(int max) {
//        Random rn = new Random();
//        int min = 0;
//        return rn.nextInt(max - min + 1) + min;
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=1; i<max; i++) {
            list.add(new Integer(i));
        }
        // Shuffling it three times. There's no mathematical or logical reason for shuffling it 3 times.
        Collections.shuffle(list);
        Collections.shuffle(list);
        Collections.shuffle(list);
        return list.get(0);

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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Had enough jokes? Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Press the other button!", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            MainActivity.this.finish();
                        }
                    })
                    .setNegativeButton("Press Me!", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
        return super.onKeyDown(keyCode, event);
    }

    public void createOneLiners(View view) {

        if (isNetworkAvailable()) {
            ParseObject testObject = new ParseObject("UserInfoLog");
            testObject.put("Button", "OneLiner");
            testObject.saveInBackground();
            ParseQuery<ParseObject> query = ParseQuery.getQuery("jokes");
            query.getInBackground("o28h07hB5h", new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject object, ParseException e) {
                    if (e == null) {
                        TextView f = (TextView) (findViewById(R.id.first));
                        TextView s = (TextView) (findViewById(R.id.second));
                        ArrayList<String> testStringArrayList = (ArrayList<String>) object.get("First");
                        int l = testStringArrayList.size();
                        int randomInt = returnRand(l);
                        String first = testStringArrayList.get(randomInt);
                        f.setText(first);
                        s.setText("");

                    }
                }
            });
        } else {
            String[] firstPart = {"I told them I wanted to be a comedian, and they laughed; then I became a comedian, no one's laughing now", "Procrastination: working tomorrow for a better today.", "No matter how good I get at tennis, I will never be better than a wall.", "I love the word frequently, and I try to use it as much as possible.", "You can't trust atoms, they make up everything.", "I don't mean to brag, but I'm 2 girls away from a threesome.", "I shouldn't have driven home last night... Especially since i walked to the bar..", "I want to die peacefully in my sleep like my grandfather, not screaming in terror like his passengers.", "If I ever saw an amputee being hanged, I'd just start calling out letters.", " I used to be indecisive but now I'm not sure.", "\"I'm sorry\" and \"I apologize\" mean the same thing unless you're at a funeral.", "Last night I was playing poker with Tarot cards, I got a full house and four people died.", "I Love You Gorgeous", "I don't hold grudges, my father did and I always hated him for it", "I saw a sign that said \"watch for children\" and I thought, \"That sounds like a fair trade", "I hate Russian dolls, they're so full of themselves.", "A recent survey showed that 6 out of 7 dwarfs are not happy.", "The easiest time to add insult to injury is when you’re signing somebody’s cast.", "Two antennas meet on a roof and fall in love; the wedding wasn't much but the reception was excellent.", "There are only two hard things in Computer Science: cache invalidation, naming things, and off-by-one errors."};
            int l = firstPart.length;
            int randomInt = returnRand(l);
            TextView first = (TextView) findViewById(R.id.first);
            TextView second = (TextView) findViewById(R.id.second);
            first.setText(firstPart[randomInt]);
            second.setText("");
        }
    }

    public void createJokes(View view) {
        if (isNetworkAvailable()) {
            ParseObject testObject = new ParseObject("UserInfoLog");
            testObject.put("Button", "TwoLiner");
            testObject.saveInBackground();
            ParseQuery<ParseObject> query = ParseQuery.getQuery("jokes");
            query.getInBackground("o28h07hB5h", new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject object, ParseException e) {
                    if (e == null) {
                        TextView f = (TextView) (findViewById(R.id.first));
                        TextView s = (TextView) (findViewById(R.id.second));
                        ArrayList<String> sec = (ArrayList<String>) object.get("Second");
                        ArrayList<String> third = (ArrayList<String>) object.get("Third");
                        int l = sec.size();
                        int randomInt = returnRand(l);
                        String first = sec.get(randomInt);
                        String second = third.get(randomInt);
                        f.setText(first);
                        s.setText(second);

                    }
                }
            });
        } else {
            String[] firstPart = {"One morning I shot an elephant in my pajamas.", "And The Lord said unto John, \"Come forth and receive eternal life.\"", "If you build a man a fire you keep him warm for a day.", "Give a man a fish, and feed him for a day. Don't teach a man to fish, feed yourself. He's a grown man, fishing's not that hard.", "I had a threesome last night.", "Having sex is like playing bridge.", "I was walking in the park and this guy waved at me. Then he said, 'I'm sorry, I thought you were someone else.'", "What's Al Qaeda's favorite football team?", "I wanted to buy a candleholder, but the store didn't have one.", "Is my girlfriend disappointed with our sex life?", "I've never killed a man, but I've read many an obituary with a great deal of satisfaction.", "All I've ever wanted was an honest week's pay for an honest day's work.", "Why don't blind people skydive?", "How do you spot a blind guy at a nude beach?", "A bear and a rabbit are taking a shit in the woods. The bear turns to the rabbit and asks, \"Do you have problems with shit sticking to your fur?\" And the rabbit replies, \"No, I don't.\" ", "My two lesbian neighbours got me a Rolex for my birthday. ", "What did the egg say to the boiling pot of water?", "A Drunk man walks into a bar, goes up to the bartender and shouts, \"I FUCKED YOUR MOM LAST NIGHT!\"", "Why does Santa have such a large sack?", "Why doesn't Santa have any of his own children?", "A logician's wife is having a baby. The doctor immediately hands the newborn to the dad. His wife asks impatiently: \"So, is it a boy or a girl\" ?", "I told my girlfriend she drew her eyebrows too high.", "Why are moon rocks better to eat than Earth rocks?", "I have an EpiPen.", "If I got $1 every time somebody called me a racist", "How many Germans does it take to change a light bulb?"};
            String[] secondPart = {"How he got into my pajamas I'll never know.", "But John came in fifth and won a toaster.", "If you set a man on fire you keep him warm for the rest of his life.", "- Ron Swanson.", "There were a couple of no-shows, but I enjoyed myself anyway.", "If you don't have a good partner, you'd better have a good hand.", "I said, 'I am.'", "The New York Jets.", "So I bought a cake.", "A tiny part of me says yes.", "- Mark Twain", "-Steve Martin", "It scares the hell out of the dog.", "Its not hard.", "So the bear wipes his ass with the rabbit.", " They misunderstood when I said \"I wanna watch.\"", "You expect for me to get hard? I just got laid!", "The bartender responds, \"Go home, Dad.\"", " Because he only comes once a year!", "Because he only comes once a year and it's down the chimney.", "The logician replies: \"yes\".", "She seemed surprised.", "Because, they're a little meteor.", "My friend gave it to me as he was dying. It seemed very important to him that I have it.", "black people would rob me", "One. They are efficient and don't have humour."};
            int l = firstPart.length;
            int randomInt = returnRand(l);
            TextView first = (TextView) findViewById(R.id.first);
            TextView second = (TextView) findViewById(R.id.second);
            first.setText(firstPart[randomInt]);
            second.setText(secondPart[randomInt]);
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
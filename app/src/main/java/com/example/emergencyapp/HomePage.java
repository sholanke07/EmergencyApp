package com.example.emergencyapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePage extends Activity implements Handler.Callback {
    Button fire, kid, medical, robbery;
    String NAME;
    TextView view, view1, view2;
    String locationAddress;
    long time = 3600000;
    Handler handler;
    Handler myhandler = new Handler();
    ImageView menubtn;
    BottomNavigationView bNv;
    private ActionBar toolbar;
    AppLocationService appLocationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        handler = new Handler(HomePage.this);
        menubtn = (ImageView) findViewById(R.id.menubtn);
        fire = (Button) findViewById(R.id.fire);
        medical = (Button) findViewById(R.id.Med);
        kid = (Button) findViewById(R.id.kid);
        robbery = (Button) findViewById(R.id.robbery);
        view1 = (TextView) findViewById(R.id.textView);
        bNv = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        appLocationService = new AppLocationService(HomePage.this);

        //toolbar = getSupportActionBar();
        bNv.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
       // toolbar.setTitle("EmergencyApp");


        menubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Menu();
            }
        });

        fire.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View arg0) {
                SharedPreferences sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
                NAME = sharedPreferences.getString("value", " ");

                Location location = appLocationService.getLocation(LocationManager.GPS_PROVIDER);
                if (location == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(HomePage.this);
                    builder.setTitle("Location Services Not Active");
                    builder.setMessage("Please enable Location Services and GPS");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Show location settings when the user acknowledges the alert dialog
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(intent);
                        }
                    });
                    Dialog alertDialog = builder.create();
                    alertDialog.setCanceledOnTouchOutside(false);
                    alertDialog.show();
                } else if (
                        (checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) || (isNetworkAvailable() != true) || (location == null)) {
                    Toast.makeText(getApplicationContext(), "Check Your Network or Location", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    locationAddress LocationAddress = new locationAddress();
                    LocationAddress.getAddressFromLocation(latitude, longitude,
                            getApplicationContext(), new GeocoderHandler());
                    Test();
                    smsFire();
                    SendMailFire();
                }
            }
        });
        medical.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View arg0) {
                SharedPreferences sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
                NAME = sharedPreferences.getString("value", " ");

                Location location = appLocationService.getLocation(LocationManager.GPS_PROVIDER);
                if (location == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(HomePage.this);
                    builder.setTitle("Location Services Not Active");
                    builder.setMessage("Please enable Location Services and GPS");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Show location settings when the user acknowledges the alert dialog
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(intent);
                        }
                    });
                    Dialog alertDialog = builder.create();
                    alertDialog.setCanceledOnTouchOutside(false);
                    alertDialog.show();
                }else if ((checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) || (isNetworkAvailable() != true) || (location == null)) {
                    Toast.makeText(getApplicationContext(), "Check Your Network or Location", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    locationAddress LocationAddress = new locationAddress();
                    LocationAddress.getAddressFromLocation(latitude, longitude,
                            getApplicationContext(), new GeocoderHandler());
                    Test();
                    smsMedical();
                    SendMailMedical();
                }
                // sendNotification();
            }
        });
        robbery.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View arg0) {
                SharedPreferences sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
                NAME = sharedPreferences.getString("value", " ");

                Location location = appLocationService.getLocation(LocationManager.GPS_PROVIDER);
                if (location == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(HomePage.this);
                    builder.setTitle("Location Services Not Active");
                    builder.setMessage("Please enable Location Services and GPS");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Show location settings when the user acknowledges the alert dialog
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(intent);
                        }
                    });
                    Dialog alertDialog = builder.create();
                    alertDialog.setCanceledOnTouchOutside(false);
                    alertDialog.show();
                }else if ((checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) || (isNetworkAvailable() != true) || (location == null)) {
                    Toast.makeText(getApplicationContext(), "Check Your Network or Location", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    locationAddress LocationAddress = new locationAddress();
                    LocationAddress.getAddressFromLocation(latitude, longitude,
                            getApplicationContext(), new GeocoderHandler());
                    Test();
                    smsRobbery();
                    SendMailRobbery();
                }
            }
        });
        kid.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View arg0) {
               /* if (isNetworkAvailable() != true) {
                    Toast.makeText(getApplicationContext(), "Please Enable Your Intent", Toast.LENGTH_SHORT).show();
                    return;
                }

                Location location = appLocationService.getLocation(LocationManager.GPS_PROVIDER);


                if (location != null) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    locationAddress locationAddress = new locationAddress();
                    locationAddress.getAddressFromLocation(latitude, longitude,
                            getApplicationContext(), new GeocoderHandler());
                } else {
                    Toast.makeText(MainActivity.this, "Please Enable GPS", Toast.LENGTH_SHORT).show();
                    return;
                }*/
                SharedPreferences sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
                NAME = sharedPreferences.getString("value", " ");

                Location location = appLocationService.getLocation(LocationManager.GPS_PROVIDER);
                if (location == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(HomePage.this);
                    builder.setTitle("Location Services Not Active");
                    builder.setMessage("Please enable Location Services and GPS");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Show location settings when the user acknowledges the alert dialog
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(intent);
                        }
                    });
                    Dialog alertDialog = builder.create();
                    alertDialog.setCanceledOnTouchOutside(false);
                    alertDialog.show();
                } else if ((checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) || (isNetworkAvailable() != true) || (location == null)) {
                    Toast.makeText(getApplicationContext(), "Check Your Network or Location", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    locationAddress LocationAddress = new locationAddress();
                    LocationAddress.getAddressFromLocation(latitude, longitude,
                            getApplicationContext(), new GeocoderHandler());
                        Test();
                        smsKid();
                        SendMailKid();
                    }
                /*Intent intent = new Intent(MainActivity.this, MyService.class);
                intent.putExtra("Lagos", "08023456281");
                intent.putExtra("Ogun", "08086101353");
                intent.putExtra("MyResult", myResult);
                startService(intent);


                PeriodicWorkRequest.Builder builder = new PeriodicWorkRequest.Builder(MyWorker.class, 1, TimeUnit.MINUTES);
                builder.setConstraints(Constraints.NONE);
                PeriodicWorkRequest workRequest = builder.build();

                if (mIsPeriodicWorkScheduled) {

                    UUID workId = workRequest.getId();

                    WorkManager.getInstance().cancelWorkById(workId);

                    view.setText("Schedule Periodic Work");

                    mIsPeriodicWorkScheduled = false;

                } else {

                    WorkManager.getInstance().enqueue(workRequest);

                    mIsPeriodicWorkScheduled = true;

                    view.setText("Cancel Periodic Work");

                }*/
            }
        });
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    @Override
    public boolean handleMessage(@NonNull Message msg) {
        return false;
    }

    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {

            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            view1.setText(locationAddress);
        }
    }
    private void Test() {
        Location location;
        AppLocationService appLocationService2 = new AppLocationService(HomePage.this);
        location = appLocationService2.getLocation(LocationManager.GPS_PROVIDER);
    }
    @Override
    protected void onPause() {
        // SendMail();
        super.onPause();
    }
    private void Menu() {
        PopupMenu p = new PopupMenu(HomePage.this, menubtn);
        p.getMenuInflater().inflate(R.menu.menu3, p.getMenu());
        p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.add:
                        Intent intent = new Intent(HomePage.this, AddFriends.class);
                        startActivity(intent);
                        return true;
                    case R.id.view:
                        Intent intent3 = new Intent(HomePage.this, RecyclerViewActivity.class);
                        startActivity(intent3);
                        return true;
                    case R.id.signout:
                        signout();
                        return true;
                    case R.id.about:
                        // Intent intent4 = new Intent(MainActivity2.this, AboutPolicy.class);
                        // startActivity(intent4);
                        return true;
                    default:
                        return true;
                }
            }
        });
        p.show();
    }
        private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.add:
                        Intent intent = new Intent(HomePage.this, Welcome_AddActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.view:
                        Intent intent3 = new Intent(HomePage.this, RecyclerViewActivity.class);
                        startActivity(intent3);
                        return true;
                    case R.id.setting:
                        //toolbar.setTitle("Settings");
                        // test.setText("Settings");
                        Toast.makeText(getApplicationContext(), "Person", Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        };

    private void signout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure you want to Signout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(HomePage.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        AlertDialog ad = builder.create();
        ad.show();
    }


    public void sendNotification() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.point)
                        .setContentTitle("EmergencyAlert")
                        .setContentText("EmergencyAlert From EssencePoint Tech:" + " " + NAME + " " + " needs your attention right now at " + locationAddress + " " + "location");

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(001, mBuilder.build());
    }
    private void SendMailFire() {
        BackgroundMail.newBuilder(HomePage.this)
                .withUsername("sholankedapo@gmail.com")
                .withPassword("dapo07@sholanke")
                .withMailto("lateefsholanke@yahoo.com")
                .withSubject("EmergencyAlert")
                .withBody("EmergencyAlert From EssencePoint Tech:" + " " + " There is a Fire Outbreak " + NAME + " " + "needs your attention right now at " + " " + locationAddress + " " + "location" + " " + "Please use the latitude and longitude to get the accurate location")
                .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getApplicationContext(), "Mail Sent", Toast.LENGTH_LONG).show();
                    }
                })
                .withOnFailCallback(new BackgroundMail.OnFailCallback() {
                    @Override
                    public void onFail() {
                        Toast.makeText(getApplicationContext(), "Mail Failed", Toast.LENGTH_LONG).show();
                    }
                })
                .send();
    }
    private void SendMailMedical() {
        BackgroundMail.newBuilder(HomePage.this)
                .withUsername("sholankedapo@gmail.com")
                .withPassword("dapo07@sholanke")
                .withMailto("lateefsholanke@yahoo.com")
                .withSubject("EmergencyAlert")
                .withBody("EmergencyAlert From EssencePoint Tech:" + " " + NAME + " " + "needs Medical attention right now at " + " " + locationAddress + " " + "location" + " " + "Please use the latitude and longitude to get the accurate location")
                .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getApplicationContext(), "Mail Sent", Toast.LENGTH_LONG).show();
                    }
                })
                .withOnFailCallback(new BackgroundMail.OnFailCallback() {
                    @Override
                    public void onFail() {
                        Toast.makeText(getApplicationContext(), "Mail Failed", Toast.LENGTH_LONG).show();
                    }
                })
                .send();
    }
    private void SendMailKid() {
        BackgroundMail.newBuilder(HomePage.this)
                .withUsername("sholankedapo@gmail.com")
                .withPassword("dapo07@sholanke")
                .withMailto("lateefsholanke@yahoo.com")
                .withSubject("EmergencyAlert")
                .withBody("EmergencyAlert From EssencePoint Tech:" + " " + "There is a Kidnapping case going on now " + NAME + " " + "needs your attention right now at " + " " + locationAddress + " " + "location" + " " + "Please use the latitude and longitude to get the accurate location")
                .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getApplicationContext(), "Mail Sent", Toast.LENGTH_LONG).show();
                    }
                })
                .withOnFailCallback(new BackgroundMail.OnFailCallback() {
                    @Override
                    public void onFail() {
                        Toast.makeText(getApplicationContext(), "Mail Failed", Toast.LENGTH_LONG).show();
                    }
                })
                .send();
    }

    private void SendMailRobbery() {
        BackgroundMail.newBuilder(HomePage.this)
                .withUsername("sholankedapo@gmail.com")
                .withPassword("dapo07@sholanke")
                .withMailto("lateefsholanke@yahoo.com")
                .withSubject("EmergencyAlert")
                .withBody("EmergencyAlert From EssencePoint Tech:" + " " + "There is a Robbery Case" + " " +  NAME + " " + "needs your attention right now at " + " " + locationAddress + " " + "location" + " " + "Please use the latitude and longitude to get the accurate location")
                .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getApplicationContext(), "Mail Sent", Toast.LENGTH_LONG).show();
                    }
                })
                .withOnFailCallback(new BackgroundMail.OnFailCallback() {
                    @Override
                    public void onFail() {
                        Toast.makeText(getApplicationContext(), "Mail Failed", Toast.LENGTH_LONG).show();
                    }
                })
                .send();
    }
    private void smsFire() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
        //Get the SmsManager instance and call the sendTextMessage method to send message
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("2347039126736", null, "EmergencyAlert From EssencePoint Tech:" + " " + "There is a Fire Outbreak " + " " + NAME + " " + " needs your attention right now at " + locationAddress + " " + "location", pi, null);
        Toast.makeText(getApplicationContext(), "SMS Sent", Toast.LENGTH_SHORT).show();
    }
    private void smsMedical() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
        //Get the SmsManager instance and call the sendTextMessage method to send message
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("2347039126736", null, "EmergencyAlert From EssencePoint Tech:" + " " + NAME + " " + " needs Medical attention right now at " + locationAddress + " " + "location", pi, null);
        Toast.makeText(getApplicationContext(), "SMS Sent", Toast.LENGTH_SHORT).show();
    }
    private void smsKid() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
        //Get the SmsManager instance and call the sendTextMessage method to send message
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("2347039126736", null, "EmergencyAlert From EssencePoint Tech:" + " " + "There is a Kidnapping case going on now " + " " + NAME + " " + " needs your attention right now at " + locationAddress + " " + "location", pi, null);
        Toast.makeText(getApplicationContext(), "SMS Sent", Toast.LENGTH_SHORT).show();
    }
    private void smsRobbery() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
        //Get the SmsManager instance and call the sendTextMessage method to send message
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("2347039126736", null, "EmergencyAlert From EssencePoint Tech:" + " " + "There is a Robbery Case" + " " + NAME + " " + " needs your attention right now at " + locationAddress + " " + "location", pi, null);
        Toast.makeText(getApplicationContext(), "SMS Sent", Toast.LENGTH_SHORT).show();
    }
    private void Timer() {
        Runnable runnableCode = new Runnable() {
            int num = 10;
            @Override
            public void run() {
                // Do something here on the main thread
                if (num > 0) {
                    Test();
                    sendNotification();
                    smsKid();
                    SendMailKid();

                    num--;
                    Log.d("Handlers", "Called on main thread");
                    // Repeat this the same runnable code block again another 2 seconds
                    // 'this' is referencing the Runnable object
                    myhandler.postDelayed(this, 300000);
                }
            }
        };
// Start the initial runnable task by posting through the handler
        myhandler.post(runnableCode);

        /*Timer timer = new Timer();
        TimerTask minTimer = new TimerTask() {
            @Override
            public void run() {
                Test();
                sendsms();
                SendMail();
            }
        };
        timer.schedule(minTimer, 01, 100000);*/
       /* CountDownTimer mytimer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long x = millisUntilFinished / 1000;
                Log.d("current value", String.valueOf(x));
                view.setText(String.valueOf(x));
                if ((x == 3504) || (x == 3212) || (x == 2920 ) || (x == 2628) || (x == 2336) || (x == 2044) || (x == 1752) || (x == 1460) || (x == 1168) || (x == 876) || (x == 584) || (x == 292 || (x == 0)))  {
                    Test();
                    sendsms();
                    SendMail();
                    sendNotification();

                }
                   // view.setText(String.valueOf(x));



            }

            @Override
            public void onFinish() {

               // for(int i=1; i<=10; i++) {


                    //Test();
                    //SendMail();
                    //sendsms();
                }

            //}

        }.start();

    }*/

    }
}
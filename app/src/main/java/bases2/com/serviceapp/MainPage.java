package bases2.com.serviceapp;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;


public class MainPage extends Activity {

    AlarmManager alarm;
    PendingIntent pintent;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        intent = new Intent(this, MyServices.class);
    }

    public void startProcess(View view)
    {
        System.out.println("Pasa por aqui en el onclick");
        startService(intent);

        Calendar cal = Calendar.getInstance();
        pintent = PendingIntent.getService(this, 0, intent, 0);

        alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        // Start service every hour
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),60000, pintent);
    }

    public void stopProcess(View view){
        alarm.cancel(pintent);
        stopService(intent);
    }
}

package bases2.com.serviceapp;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Admin on 24/04/2015.
 */
public class MyServices extends Service {

    String Url = "https://scontent-iad.xx.fbcdn.net/hphotos-xfp1/v/t1.0-9/1891201_721728077871576_2076851305_n.jpg?oh=81199ef055492442f17a02aec785f0c5&oe=55CDEC64";
    String ApiLink = "http://173.255.114.98/ClusterCassandra/api/insertData";
    long initialTime;
    long finalTime;
    double size;
    double latitude;
    double longitude;
    Bitmap bitmap;
    LocationManager manager;
    String locationProvider;
    Location location;
    int cont = 0;

    public class ImageUrl extends AsyncTask<Void, Void, Bitmap>
    {
        private String url;

        public ImageUrl(String url)
        {
            this.url = url;
        }

        @Override
        protected Bitmap doInBackground(Void... params)
        {
            try
            {
                URL imageURL = new URL(url);
                initialTime = System.nanoTime();
                bitmap = BitmapFactory.decodeStream(imageURL.openStream());
                finalTime = System.nanoTime();
                return bitmap;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result)
        {
            super.onPostExecute(result);
        }
    }

    @Override
    public IBinder onBind(Intent arg0){
        //TODO Auto-generated method stub
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        //this service will run until we stop it

        try {

            startDownload();

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Toast.makeText(this,"Service started", Toast.LENGTH_LONG).show();
        return START_STICKY;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Toast.makeText(this,"Service Stopped", Toast.LENGTH_LONG).show();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    protected double sizeOf(Bitmap data)
    {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR1)
        {
            return (data.getRowBytes() * data.getHeight());
        }
        else
        {
            return data.getByteCount();
        }
    }

    public void startDownload() throws ExecutionException, InterruptedException
    {
        bitmap = new ImageUrl(Url).execute().get();
        //Globals.getInstance(null).imageView.setImageBitmap(bitmap);
        double time = (finalTime - initialTime)/1e6/1000;
        size = sizeOf(bitmap);

        bitmap = null;
        manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationProvider = LocationManager.GPS_PROVIDER;
        location = manager.getLastKnownLocation(locationProvider);

        double speedView = (size/1024/1024) / time;

        if(location != null)
        {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
        ArrayList<NameValuePair> postData = new  ArrayList<>();

        postData.add(new BasicNameValuePair("TransaccionDataId",));
        postData.add(new BasicNameValuePair("NetworkName",));
        postData.add(new BasicNameValuePair("DownloadSpeed",));
        postData.add(new BasicNameValuePair("Latitude",));
        postData.add(new BasicNameValuePair("Longitude",));
        postData.add(new BasicNameValuePair("SessionNumber",));
        Log.e("A", "Transaccion realizada");
        Log.e("A", String.valueOf(++cont));
    }
}

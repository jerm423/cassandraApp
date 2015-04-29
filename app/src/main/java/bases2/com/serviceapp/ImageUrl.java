package bases2.com.serviceapp;

/**
 * Created by Admin on 26/04/2015.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import java.net.URL;

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
            Globals.getInstance(null).initialTime = System.nanoTime();
            Bitmap bitmap = BitmapFactory.decodeStream(imageURL.openStream());
            Globals.getInstance(null).finalTime = System.nanoTime();
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


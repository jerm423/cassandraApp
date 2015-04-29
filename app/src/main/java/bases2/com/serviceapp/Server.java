package bases2.com.serviceapp;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

/**
 * Created by Admin on 28/04/2015.
 */
public class Server extends AsyncTask<HttpPost, String, JSONArray>
{
    @Override
    protected void onPreExecute()
    {
    };

    @Override
    protected JSONArray doInBackground(HttpPost... post)
    {
        try
        {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(post[0]);
            String JsonString = EntityUtils.toString(response.getEntity());
            return new JSONArray(JsonString);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(JSONArray result)
    {
    }

    public static HttpPost getHttpPost(String pPhpFile)
    {
        return new HttpPost(pPhpFile);
    }
}

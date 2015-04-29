package bases2.com.serviceapp;

import android.app.Activity;
import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Admin on 24/04/2015.
 */
public class Globals{


    Uri notification;
    Ringtone r;
    String Url = "https://scontent-iad.xx.fbcdn.net/hphotos-xfp1/v/t1.0-9/1891201_721728077871576_2076851305_n.jpg?oh=81199ef055492442f17a02aec785f0c5&oe=55CDEC64";
    double size;
    long initialTime;
    long finalTime;
    Context context;

    private static Globals instance;

    public Globals(Context pContext){

        this.context = pContext;
    }


    public static Globals getInstance(Context context){
        if(instance == null){
            instance = new Globals(context);
        }
        return instance;
    }
}

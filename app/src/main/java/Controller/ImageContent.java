package Controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageContent extends AsyncTask<String, Void, Bitmap> {

    protected Bitmap myBitmap;


    @Override
    protected Bitmap doInBackground(String... urls) {
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            InputStream in = urlConnection.getInputStream();

            myBitmap = BitmapFactory.decodeStream(in);
            return myBitmap;

        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
}
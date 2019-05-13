package Controller;

import android.os.AsyncTask;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebContent extends AsyncTask<String, Void, String> {

//    protected String result;
//
//    public String getResult() {
//        return result;
//    }
//
//    public void setResult(String result) {
//       this.result = result;
//    }
//
//
//    @Override
//    protected String doInBackground(String... urls) {
//        String result = "";
//        URL url;
//        HttpURLConnection urlConnection = null;
//
//        try {
//            url = new URL(urls[0]);
//            urlConnection = (HttpURLConnection) url.openConnection();
//            InputStream in = urlConnection.getInputStream();
//            InputStreamReader reader = new InputStreamReader(in);
//            int data = reader.read();
//
//            while (data != -1) {
//                char current = (char) data;
//
//                result += current;
//
//                data = reader.read();
//            }
//
//            return result;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
//            return "Failed";
//        }
//    }
//}
//
//public class DownloadTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... urls) {

        String result = "";
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            int data = reader.read();

            while (data != -1) {
                char current = (char) data;

                result += current;

                data = reader.read();
            }

            return result;

        } catch (Exception e) {
            e.printStackTrace();

            return "Failed";
        }
    }
}
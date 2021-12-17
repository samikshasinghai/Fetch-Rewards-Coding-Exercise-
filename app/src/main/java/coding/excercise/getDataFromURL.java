package coding.excercise;

import android.os.AsyncTask;

import java.io.IOException;

import coding.excercise.MainActivity;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class getDataFromURL extends AsyncTask<Void, Void, String> {

    String apiResponse = null;

    @Override
    protected String doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();

        String url = "https://fetch-hiring.s3.amazonaws.com/hiring.json";
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = null;

        try
        {
            response = client.newCall(request).execute();
            apiResponse = response.body().string();


        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return apiResponse;

    }

    @Override
    protected void onPostExecute(String apiResponse) {

    }
}

package com.rizqiaryansa.app.randomnumbergenerator;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by RizqiAryansa on 11/22/2017.
 */

public class AsyncTaskActivity extends AsyncTask<Integer, Integer, Boolean> {

    private boolean isRunning;
    private boolean stop;
    private int seconds;
    private Context context;
    private onprogressUpdateListener listener;

    public AsyncTaskActivity(Context c) {
        context = c ;
    }

    public void setonprogressUpdateListener(onprogressUpdateListener l) {
        listener = l;
    }

    @Override
    protected void onPreExecute() {
//        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Integer... params) {
        stop = false;
        isRunning = true;
        seconds = params[0];
        while (seconds < 10 && isRunning == true && stop == false) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Log.e("AsyncRandomNumber", e.getMessage());
            }
            seconds++;
            if(seconds == 10) {
                seconds = 0;
            }
            this.publishProgress(seconds);
        }

        if (isRunning == true)
            return true;
        else
            return false;
    }

//    protected void myPauseTask() {
//        stop = true;
//    }

    @Override
    protected void onCancelled() {
        stop = true;
        //isRunning = true;
        Toast.makeText(context, "Random Generator Stopped!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(Boolean result) {
//        isRunning = false;
//        if(result == true) {
//            Toast.makeText(context, "Running 9 seconds timer complete!", Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        listener.progressUpdate(values[0]);
    }

    public boolean getIsRunning() {
        return isRunning;
    }

    public interface onprogressUpdateListener {
        public void progressUpdate(int i);
    }
}

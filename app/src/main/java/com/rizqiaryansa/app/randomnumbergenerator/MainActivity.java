package com.rizqiaryansa.app.randomnumbergenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rizqiaryansa.app.randomnumbergenerator.AsyncTaskActivity.onprogressUpdateListener;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AsyncTaskActivity timer;
    TextView tv_random_number;
    Button btn_start, btn_stop;
    private Thread bgThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_random_number = (TextView) findViewById(R.id.tv_random_generator);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_stop = (Button) findViewById(R.id.btn_stop);

        //initonclickListener();

        btn_start.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                startThread();
                break;
            case R.id.btn_stop:
                stopThread();
                break;
        }
    }

    private void startThread() {
        if (bgThread == null || bgThread.getState() == Thread.State.TERMINATED) {
            final Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        int i = 0;
                        while (i <= 0) {
                            final int random = (int) (Math.random() * 10);
                            Thread.sleep(500);
                            Log.e("random", random + "");
                            tv_random_number.post(new Runnable() {
                                @Override
                                public void run() {
                                    tv_random_number.setText(random + "");
                                }
                            });

                            i = 0;
                        }
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }

                }
            };

            bgThread = new Thread(runnable);
            bgThread.start();
        }
    }

    private void stopThread() {
        bgThread.interrupt();
    }

        //    private void startNumber() {
//        if (bgThread == null || bgThread.getState() == Thread.State.TERMINATED) {
//            Runnable runnable = new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        for (int i = 0; i <= 10; i++) {
//                            final int value = i;
//                            Thread.sleep(1000);
//
//                            tv_random_number.setText("" + value);
//                        }
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            };
//            bgThread = new Thread(runnable);
//            bgThread.start();
//        }
//    }

//    private void initonclickListener() {
//        View.OnClickListener click = new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                switch (view.getId()) {
//                    case R.id.btn_start:
//                        startThread();
//                        break;
//                    case R.id.btn_stop:
//                        stopThread();
//                        break;
//                }
//            }
//        };
//        findViewById(R.id.btn_start).setOnClickListener(click);
//        findViewById(R.id.btn_stop).setOnClickListener(click);
//
//    }
//
//    private void startThread() {
//        if (timer != null && timer.getIsRunning() == true) {
//            Toast.makeText(this, "Number Generator is Running", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        timer = new AsyncTaskActivity(this);
//        String generator_number = tv_random_number.getText().toString();
//        int random_number = Integer.valueOf(generator_number);
//        timer.execute(random_number);
//
//        timer.setonprogressUpdateListener(new onprogressUpdateListener() {
//            @Override
//            public void progressUpdate(int i) {
//                ((TextView) findViewById(R.id.tv_random_generator)).setText(""+i);
//            }
//        });
//    }
//
//    private void stopThread() {
//        if (timer == null || timer.getIsRunning() == false) {
//            Toast.makeText(this, "Number Generator is Stopped", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        timer.cancel(true);
//    }
        }

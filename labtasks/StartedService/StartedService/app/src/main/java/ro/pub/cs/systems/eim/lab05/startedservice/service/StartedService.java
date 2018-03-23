package ro.pub.cs.systems.eim.lab05.startedservice.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import ro.pub.cs.systems.eim.lab05.startedservice.general.Constants;

public class StartedService extends Service {
    private static Context context;

    private class ProcessingThread extends Thread {
        ProcessingThread() {

        }

        @Override
        public void run() {
            try {
                sendMessage(Constants.MESSAGE_STRING);
                Thread.sleep(Constants.SLEEP_TIME);
                sendMessage(Constants.MESSAGE_INTEGER);
                Thread.sleep(Constants.SLEEP_TIME);
                sendMessage(Constants.MESSAGE_ARRAY_LIST);
                Thread.sleep(Constants.SLEEP_TIME);

            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }

            Log.d(Constants.TAG, "Thread.run() was invoked, PID: " + android.os.Process.myPid() + " TID: " + android.os.Process.myTid());
        }

        public void sendMessage(int type) {
            Intent intent = new Intent();

            switch (type) {
                case Constants.MESSAGE_STRING:
                    intent.setAction(Constants.ACTION_STRING);
                    intent.putExtra(Constants.DATA, Constants.STRING_DATA);
                    break;
                case Constants.MESSAGE_INTEGER:
                    intent.setAction(Constants.ACTION_INTEGER);
                    intent.putExtra(Constants.DATA, Constants.INTEGER_DATA);
                    break;
                case Constants.MESSAGE_ARRAY_LIST:
                    intent.setAction(Constants.ACTION_ARRAY_LIST);
                    intent.putExtra(Constants.DATA, Constants.ARRAY_LIST_DATA);
                    break;
                default:
                    break;
            }

            context.sendBroadcast(intent);


        }
    }


    private ProcessingThread pt = null;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        Log.d(Constants.TAG, "onCreate() method was invoked");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(Constants.TAG, "onBind() method was invoked");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(Constants.TAG, "onUnbind() method was invoked");
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(Constants.TAG, "onRebind() method was invoked");
    }

    @Override
    public void onDestroy() {
        Log.d(Constants.TAG, "onDestroy() method was invoked");

        if (pt != null) {
            try {
                pt.join();
            } catch (Exception ex) {

            }
        }

        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(Constants.TAG, "onStartCommand() method was invoked");
        // TODO: exercise 5 - implement and start the ProcessingThread

        //if (pt == null) {
            pt = new ProcessingThread();

            pt.start();
        //}

        return START_REDELIVER_INTENT;
    }

}

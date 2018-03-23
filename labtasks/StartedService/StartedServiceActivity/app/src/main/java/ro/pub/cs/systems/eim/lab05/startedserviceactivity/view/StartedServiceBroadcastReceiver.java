package ro.pub.cs.systems.eim.lab05.startedserviceactivity.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import java.util.ArrayList;

import ro.pub.cs.systems.eim.lab05.startedserviceactivity.general.Constants;

public class StartedServiceBroadcastReceiver extends BroadcastReceiver {

    private TextView messageTextView;

    // TODO: exercise 9 - default constructor

    public StartedServiceBroadcastReceiver(TextView messageTextView) {
        this.messageTextView = messageTextView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: exercise 7 - get the action and the extra information from the intent
        // and set the text on the messageTextView

        String action = intent.getAction();

        if (messageTextView == null) {
            return;
        }

        switch (action) {
            case Constants.ACTION_STRING:
                String data = intent.getStringExtra(Constants.DATA);
                messageTextView.setText(messageTextView.getText().toString() + "\n" + data);
                break;
            case Constants.ACTION_INTEGER:
                int x = intent.getIntExtra(Constants.DATA, 0);
                messageTextView.setText(messageTextView.getText().toString() + "\n" + x);
                break;
            case Constants.ACTION_ARRAY_LIST:
                ArrayList<String> list = intent.getStringArrayListExtra(Constants.DATA);
                messageTextView.setText(messageTextView.getText().toString() + "\n" + list.toString());
                break;
        }


        // TODO: exercise 9 - restart the activity through an intent
        // if the messageTextView is not available
    }
}

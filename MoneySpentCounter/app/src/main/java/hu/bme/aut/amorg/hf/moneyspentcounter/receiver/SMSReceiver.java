package hu.bme.aut.amorg.hf.moneyspentcounter.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsMessage;
import android.widget.Toast;

import hu.bme.aut.amorg.hf.moneyspentcounter.CreateBoughtItemActivity;
import hu.bme.aut.amorg.hf.moneyspentcounter.DetailsActivity;
import hu.bme.aut.amorg.hf.moneyspentcounter.R;

public class SMSReceiver extends BroadcastReceiver {

    public static final String KEY_SPENT_MONEY = "KEY_SPENT_MONEY";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        Object[] pdus = (Object[]) extras.get("pdus");
        for (Object pdu : pdus) {
            SmsMessage msg = SmsMessage.createFromPdu((byte[]) pdu);
            String origin = msg.getOriginatingAddress();
            String body = msg.getMessageBody();
            if(DetailsActivity.getBankPhoneNumber().equals(origin)){
                buildNotification(context, body);
            }
        }
    }

    public void buildNotification(Context context, String message) {
        Intent intent = new Intent(context, CreateBoughtItemActivity.class);
        intent.putExtra(KEY_SPENT_MONEY, getSpentMoney(message));
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);
        Notification n = new Notification.Builder(context)
                .setContentTitle("SMS for your payment")
                .setContentText("Do you want to add to your list? " + "Price: " + getSpentMoney(message))
                .setSmallIcon(R.drawable.ic_local_atm_black_36dp)
                .setContentIntent(pIntent)
                .setAutoCancel(true).build();
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, n);
    }

    public int getSpentMoney(String message) {
        String[] msg = message.split(" ");
        int price = 0;
        for (String s: msg) {
            if(isNumeric(s)) {
                price = changePriceToInt(s);
                return price;
            }
        }
        return price;
    }

    public boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    public int changePriceToInt(String price){
        Double result;
        result = Double.parseDouble(price);
        if(price.contains("."))
            result*=1000;
        return result.intValue();
    }
}
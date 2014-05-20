package com.example.naviIT;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class RefreshReceiver extends BroadcastReceiver{
	public RefreshReceiver() {
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO: This method is called when the BroadcastReceiver is receiving
		// an Intent broadcast.
		
		Bundle b = intent.getExtras();
		String title = b.getString("title");
		String venue = b.getString("venue");
		String time = b.getString("time");
		
		/*String action = intent.getAction();

		  Log.i("Receiver", "Broadcast received: " + action);

		  if(action.equals("my.action.string")){
			  title = intent.getExtras().getString("title");
		  }
		*/
		Log.d("Message", "In refreshReceiver, title is "+title);
		
		PendingIntent pi = PendingIntent.getActivity(context, 0, new Intent(context,  EventsActivity.class), 0);
		NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
			.setAutoCancel(true)
			.setContentIntent(pi)
			.setContentText(venue + " " + time)
			.setContentTitle(title)
			.setSmallIcon(R.drawable.ic_launcher)
			.setTicker(title)
			.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
			//.setWhen(System.currentTimeMillis() - 5*60*1000);
			
		
			NotificationManager mgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
			Notification not = builder.build();
			mgr.notify(0, not);
	}


}

package ddwu.mobile.finalproject.ma02_20180977;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyBroadCastReceiverAlarm  extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {
//        Toast.makeText(context, "one time!", Toast.LENGTH_LONG).show();


        // Notification 출력
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, context.getString(R.string.CHANNEL_ID3))
                .setSmallIcon(R.mipmap.somsom) //알람 아이콘
                .setContentTitle("아프지마솜")
                .setContentText("설정한 시간이 되었습니다.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true); //알람 중요도

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(300, builder.build()); //알람 생성


    }
}

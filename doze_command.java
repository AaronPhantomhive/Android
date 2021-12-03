D:\Android\SDK\platform-tools

	<uses-permission android:name="android.permission.WAKE_LOCK"/>
	<uses-permission android:name="android.permission.USE_FULL_SCREEN_INTENT" />
	<uses-permission android:name="android.permission.DEVICE_POWER"
		tools:ignore="ProtectedPermissions" />

	<uses-permission android:name="android.permission.SCHEDULE_EXACT_ALARM" />

<!--	<uses-permission-->
<!--		android:name="android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS"/>-->





	public void becomeActiveLocked(String activeReason, int activeUid) {
		Lifecycle lifecycle = null;
		Lifecycle.State currentState = lifecycle.getCurrentState();

	}
	
	
	
	
	
	// whiteList version 1
	@SuppressLint("BatteryLife")
	public void whiteList(Context context){
		Intent intent = new Intent();
		String packageName = context.getPackageName();
		PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
		if (pm.isIgnoringBatteryOptimizations(packageName)) // if you want to disable doze mode for this package
			intent.setAction(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
		else { // if you want to enable doze mode
			intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
			intent.setData(Uri.parse("package:" + packageName));
		}
		context.startActivity(intent);
	}
	
	
// whiteList version 2 (success)
	private boolean isWhiteList() {
		boolean inWhiteList = false;
		PowerManager powerManager = (PowerManager) mainService.getSystemService(Context.POWER_SERVICE);
		if (powerManager != null) {
			inWhiteList = powerManager.isIgnoringBatteryOptimizations(mainActivity.getPackageName());
		}
		return inWhiteList;
	}

	@SuppressLint("BatteryLife")
	public void requestIgnoreBatteryOptimizations(Context context) {
		Intent intent = new Intent();
		PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
		// not in whiteList
		if (!powerManager.isIgnoringBatteryOptimizations(context.getPackageName())) {
			intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
			intent.setData(Uri.parse("package:" + context.getPackageName()));
			Log.d(AndroidConst.LOG_TAG, "2222222222222222222222222222222222");
		}
		context.startActivity(intent);
	}


	if(!isWhiteList()) {
		requestIgnoreBatteryOptimizations(mainActivity);
	}







		// fullScreenIntent
		Intent fullScreenIntent = new Intent(mainService, MainActivity.class);
		@SuppressLint("UnspecifiedImmutableFlag")
		PendingIntent fullScreenPendingIntent = PendingIntent.getActivity(mainService, 0,
				fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		NotificationCompat.Builder notificationBuilder =
				new NotificationCompat.Builder(mainService, "CHANNEL_ID")
						.setSmallIcon(R.drawable.mbiot_icon)
						.setContentTitle(mainActivity.getString(R.string.app_name))
						.setContentText("端末を強制的にWakeupさせました")
						.setPriority(NotificationCompat.PRIORITY_HIGH)
						.setCategory(NotificationCompat.CATEGORY_CALL)
						// Use a full-screen intent only for the highest-priority alerts where you
						// have an associated activity that you would like to launch after the user
						// interacts with the notification. Also, if your app targets Android 10
						// or higher, you need to request the USE_FULL_SCREEN_INTENT permission in
						// order for the platform to invoke this notification.
						.setFullScreenIntent(fullScreenPendingIntent, true);
		@SuppressLint("ServiceCast")
		NotificationManager manager = (NotificationManager) mainService.getSystemService(Service.POWER_SERVICE);
		manager.notify(NOTIFICATION_CLICK, notificationBuilder.build());


		// thread
		Handler handler = new Handler(Looper.getMainLooper());
		handler.post(new Runnable() {
			@Override
			public void run() {
				if() {
					try {
						
					}
				}
			}
		});

        // fullScreenIntent success in locationService.startLocationUpdates(mainService, location -> { }
		Intent fullScreenIntent = new Intent(mainService, MainActivity.class);
		PendingIntent fullScreenPendingIntent = PendingIntent.getActivity(mainService, 0,
				fullScreenIntent, PendingIntent.FLAG_IMMUTABLE);
		NotificationCompat.Builder notificationBuilder =
				new NotificationCompat.Builder(mainService, "CHANNEL_ID")
						.setSmallIcon(R.drawable.mbiot_icon)
						.setContentText("端末を強制的にWakeupさせました " + messageNotificationId)
						.setFullScreenIntent(fullScreenPendingIntent, true);
		NotificationManager manager = (NotificationManager) mainService.getSystemService(NOTIFICATION_SERVICE);
		manager.notify(messageNotificationId, notificationBuilder.build());
		
		// screen on 1
		PowerManager powerManager = (PowerManager) mainService.getSystemService(Context.POWER_SERVICE);
		@SuppressLint("InvalidWakeLockTag")
		PowerManager.WakeLock wakeLock = powerManager.newWakeLock(
				PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK, "MBWakeLock");
		wakeLock.acquire();
		
		// screen on 2
		mainActivity.setTurnScreenOn(true);
                            	mainActivity.setShowWhenLocked(true);
                            	KeyguardManager keyguardManager = (KeyguardManager) mainActivity.getSystemService(Context.KEYGUARD_SERVICE);
                            	keyguardManager.requestDismissKeyguard(mainActivity, null);
		
		// screen on 3
		mainActivity.getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
						WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
						WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
						WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);
						
		// screen on 4 (success)keyguardManager.requestDismissKeyguard && PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK  实现5分钟亮屏
		PowerManager powerManager = (PowerManager) mainService.getSystemService(Context.POWER_SERVICE);
		@SuppressLint("InvalidWakeLockTag")
		PowerManager.WakeLock wakeLock = powerManager.newWakeLock(
				PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK, "MBWakeLock");
		wakeLock.acquire(TimeUnit.SECONDS.toMillis(5));
	
		mainActivity.setTurnScreenOn(true);
		mainActivity.setShowWhenLocked(true);
		KeyguardManager keyguardManager = (KeyguardManager) mainActivity.getSystemService(Context.KEYGUARD_SERVICE);
		keyguardManager.requestDismissKeyguard(mainActivity, null);
//								KeyguardManager.KeyguardLock keyguardLock = keyguardManager.newKeyguardLock("unLock");
//								keyguardLock.disableKeyguard();
//
//							Handler handler = new Handler(Looper.getMainLooper());
//							handler.post(() -> mainActivity.getWindow().addFlags(
//									WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
//									WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
//									WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
//									WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON |
//									WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD)
//							);
		wakeLock.release();
		
		

		// screen off
		// 方法1 需要权限 x
		// <uses-permission android:name="android.permission.WRITE_SETTINGS" />
		// Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, 1000);
		// 方法2 没有效果
        // wakeLock.release()；
		// 方法3 报错No active admin owned by uid 10147 for policy #3 需要权限 x
		// DevicePolicyManager policyManager = (DevicePolicyManager) mainService.getSystemService(Context.DEVICE_POLICY_SERVICE);
		// policyManager.lockNow();


		// AlarmManager
		AlarmManager alarmManager = (AlarmManager) mainService.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(mainService, MainActivity.class);
		@SuppressLint("UnspecifiedImmutableFlag")
		PendingIntent pendingIntent = PendingIntent.getBroadcast(mainService,
				ALARM_REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.R) {
			
			// choose 1 from 3
			
			// alarmManager.setAlarmClock
			AlarmManager.AlarmClockInfo clockInfo = new AlarmManager.AlarmClockInfo(System.currentTimeMillis() + 3000, pendingIntent);
			alarmManager.setAlarmClock(clockInfo, pendingIntent);

			// alarmManager.setExactAndAllowWhileIdle
			alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC, System.currentTimeMillis() + 3000, pendingIntent);
			
			// alarmManager.set
			long triggerAtTime = SystemClock.elapsedRealtime();
			alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);
			// or
			alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 3000, pendingIntent);
			
			Log.i(AndroidConst.LOG_TAG, "Alarm Manager Starts");
		}

	
/**
System.currentTimeMillis()  系统时间，也就是日期时间，可以被系统设置修改，然后值就会发生跳变。

uptimeMillis 自开机后，经过的时间，不包括深度睡眠的时间

elapsedRealtime自开机后，经过的时间，包括深度睡眠的时间

*/
	
/**
AlarmManager.RTC，硬件闹钟，不唤醒手机（也可能是其它设备）休眠；当手机休眠时不发射闹钟。

AlarmManager.RTC_WAKEUP，硬件闹钟，表示闹钟在睡眠状态下会唤醒系统并执行提示功能，该状态下闹钟使用绝对时间。

AlarmManager.ELAPSED_REALTIME，真实时间流逝闹钟，不唤醒手机休眠；当手机休眠时不发射闹钟。

AlarmManager.ELAPSED_REALTIME_WAKEUP，真实时间流逝闹钟，表示闹钟在睡眠状态下会唤醒系统并执行提示功能，该状态下闹钟也使用相对时间。

*/



	private void setAlarm() {
		// 時間をセットする
		Calendar calendar = Calendar.getInstance();
		// Calendarを使って現在の時間をミリ秒で取得
		calendar.setTimeInMillis(System.currentTimeMillis());
		// 5秒後に設定
		calendar.add(Calendar.SECOND, 5);

		//明示的なBroadCast
		Intent intent = new Intent(
				mainService, MBIoTReceiver.class
		);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(
				mainService, 0, intent, PendingIntent.FLAG_IMMUTABLE
		);

		//アラームクロックインフォを作成してアラーム時間をセット
		AlarmManager.AlarmClockInfo clockInfo = new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), null);
		//アラームマネージャーにアラームクロックをセット
		((AlarmManager)mainService.getSystemService(Context.ALARM_SERVICE)).setAlarmClock(clockInfo, pendingIntent);
//		((AlarmManager) mainService.getSystemService(Context.ALARM_SERVICE)).setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), 5000, pendingIntent);
	}


// sensor

case Sensor.TYPE_PROXIMITY:
	PowerManager mPowerManager = (PowerManager) mainActivity.getSystemService(Context.POWER_SERVICE);
	@SuppressLint("InvalidWakeLockTag")
	PowerManager.WakeLock mWakeLock = mPowerManager.newWakeLock(
			PowerManager.PROXIMITY_SCREEN_OFF_WAKE_LOCK, "MBWakeSensor");
	if (event.values[0] == 0.0) {
		// screen off
		if (!mWakeLock.isHeld())
			mWakeLock.acquire(TimeUnit.SECONDS.toMillis(5));
	} else {
		// screen on
		if (mWakeLock.isHeld())
			mWakeLock.release();
	}
	break;





















package com.fourclue.android.iot.component;

import android.content.Context;

public class MessageNotification {
}
















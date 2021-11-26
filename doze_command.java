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


AlarmManager.RTC_WAKEUP，硬件闹钟，当闹钟发躰时唤醒手机休眠；

AlarmManager.ELAPSED_REALTIME，真实时间流逝闹钟，不唤醒手机休眠；当手机休眠时不发射闹钟。

AlarmManager.ELAPSED_REALTIME_WAKEUP，真实时间流逝闹钟，当闹钟发躰时唤醒手机休眠；

 

RTC闹钟和ELAPSED_REALTIME最大的差别就是前者可以通过修改手机时间触发闹钟事件，后者要通过真实时间的流逝，即使在休眠状态，时间也会被计算。

*/

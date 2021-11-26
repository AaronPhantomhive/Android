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

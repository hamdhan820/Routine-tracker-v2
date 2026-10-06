f = 'android/app/src/main/AndroidManifest.xml'
s = open(f).read()
perms = ''.join('<uses-permission android:name="android.permission.%s" />\n' % p for p in
  ['POST_NOTIFICATIONS', 'USE_FULL_SCREEN_INTENT', 'WAKE_LOCK', 'VIBRATE', 'RECEIVE_BOOT_COMPLETED'])
s = s.replace('<application', perms + '<application', 1)
s = s.replace('</application>', '<activity android:name=".AlarmActivity" android:exported="false" android:showWhenLocked="true" android:turnScreenOn="true" android:excludeFromRecents="true" android:launchMode="singleTask" />\n<receiver android:name=".AlarmReceiver" android:exported="false" />\n</application>')
open(f, 'w').write(s)

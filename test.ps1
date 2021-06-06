./gradlew assembleDebug

adb uninstall de.sixbits.salescompanion

adb install -g app/build/outputs/apk/debug/app-debug.apk

adb shell am instrument -w -r    -e debug false -e class 'de.sixbits.salescompanion.contacts.ContactServiceTest' de.sixbits.salescompanion.test/androidx.test.runner.AndroidJUnitRunner
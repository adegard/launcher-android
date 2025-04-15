I'll create an AppListScreen that displays all apps on the phone with similar styling to your launcher, and I'll also modify the MainActivity to serve as a home screen replacement launcher.


## How to Make Your App a Home Screen Replacement

To make your app function as a home screen replacement launcher, I've implemented the following essential components:

### 1. AndroidManifest.xml Configuration
The most important part is adding specific intent filters in your AndroidManifest.xml:

```xml
<intent-filter>
    <action android:name="android.intent.action.MAIN" />
    <category android:name="android.intent.category.HOME" />
    <category android:name="android.intent.category.DEFAULT" />
</intent-filter>
```

This tells Android that your app can handle the HOME action, which is triggered when the user presses the home button.

### 2. Activity Configuration
I've added several key attributes to your MainActivity:
- `android:launchMode="singleTask"` ensures only one instance of your launcher runs
- `android:stateNotNeeded="true"` prevents state restoration when the launcher restarts
- `android:exported="true"` makes your activity accessible to the system

### 3. Handling Home Button Presses
In MainActivity.kt, I've added an `onNewIntent()` method to handle when the home button is pressed while your app is already running.

### 4. App Selection Prompt
When your app is installed, pressing the home button will prompt the user to choose between your launcher and the system default launcher. They can select your app and choose "Always" to make it the permanent home screen launcher.

## New Functionality

### AppListScreen
I've created a new screen that:
- Shows a list of all installed apps on the device
- Maintains the same styling as your launcher screen
- Includes a back arrow to return to the main launcher
- Allows users to launch any app by tapping on it

### Navigation
I've implemented simple navigation between screens:
- The "More" button on the main launcher now takes users to the app list
- The back arrow in the app list returns to the main launcher

### Sorting and Display
The app list is:
- Sorted alphabetically by app name
- Displayed in a scrollable list with the same font family and style as your main launcher
- Includes an up arrow at the bottom (similar to your down arrow in the main screen)

This implementation provides a complete home screen replacement with access to all the device's apps while maintaining your specific design language.




```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.launcherapp">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.LauncherApp">

        <activity
            android:name=".MainActivity"
            android:exported="true"
            android:launchMode="singleTask"
            android:screenOrientation="portrait"
            android:stateNotNeeded="true">

            <!-- Main launcher intent filter -->
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>

            <!-- Home launcher intent filter -->
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.HOME" />
                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>

        </activity>
    </application>

</manifest>

```








Turning your Android app into a **homescreen (launcher) replacement** involves a few specific steps. You're working with Kotlin, Jetpack Compose, and Material 3, which is perfect—you'll just need to handle the system-level integration so Android recognizes your app as a launcher.

---

### 🛠️ Step 1: Modify the Manifest

Update your `AndroidManifest.xml` to declare your app as a launcher:

```xml
<activity
    android:name=".MainActivity"
    android:exported="true"
    android:launchMode="singleTask">

    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.HOME" />
        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
```

This makes your app appear in the launcher chooser dialog when setting a default launcher.

> 📌 **Note:** Do *not* remove the existing `LAUNCHER` category if you want the app to be launchable normally too, like during dev.

---

### 🧠 Step 2: Handle Back Button Behavior

In a launcher, you don't want the app to exit when the user hits back. You can override back behavior:

```kotlin
override fun onBackPressed() {
    // Do nothing or show an exit dialog
}
```

---

### 📦 Step 3: Prevent System Bars From Auto-Hiding (Optional)

Some launchers hide the navigation bar or status bar. You might want to use immersive mode if needed, but that’s not required.

---

### 🏡 Step 4: Test It

Once installed on a device, press the home button. Android should prompt you to choose a default launcher. Your app should appear as an option.

To test:

1. Install the app.
2. Press the home button.
3. Choose your app as the default launcher.

---

### 🔙 To Go Back to the Original Launcher

If you want to revert back to your regular launcher:

#### Option 1: From System Settings

1. Go to **Settings > Apps > Default apps**.
2. Tap **Home app**.
3. Select your original launcher (e.g., Pixel Launcher, One UI Home).

#### Option 2: Clear Defaults

1. Go to **Settings > Apps**.
2. Find your launcher app.
3. Tap **Open by default**.
4. Tap **Clear defaults**.

Then press home again, and the system will ask you to choose a launcher.

---

Want help designing your launcher UI in Compose too? Or do you have a specific idea in mind for how the homescreen should look/function?

# android-verify

> Synaps Android Verify SDK 

[![](https://jitpack.io/v/synaps-hub/verify-android.svg)](https://jitpack.io/#synaps-hub/verify-android)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

**Synaps is an all-in-one compliance platform**. It offers a simple, fast and secure way to meet compliance requirements at scale.

[Visit Synaps.io](https://synaps.io) | [Read the Synaps documentation](https://docs.synaps.io)

![enter image description here](https://storage.googleapis.com/synaps-docs-media/synaps-verify.png)

## Gradle Dependency
To get a Git project into your build:

**Step 1.** Add the JitPack repository to your build file

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

**Step 2.** Add the dependency

```gradle
dependencies {
  ...
  implementation 'com.github.synaps-io:android-verify:0.1.0'
}
```


---

## The Basics

**First,** you need to add `SynapsIndividualVerify` in your layout:

```xml
<io.synaps.SynapsIndividualVerify
    android:id="@+id/synaps"
    android:layout_width="match_parent"
    android:layout_height="554dp"
    android:layout_alignParentLeft="true"
    android:layout_alignParentTop="true"
    app:primary_color="#090909"
    app:secondary_color="#FB0101"/>
```

**Second,** you need to launch synaps by providing the `sessionid` of the user:

```kotlin
synaps = findViewById<SynapsIndividualVerify>(R.id.synaps);
 try {
    synaps!!.launch(SESSION_ID)
} catch (e: CameraAccessException) {
    // handle when user disable the camera permission
    ActivityCompat.requestPermissions(this,
        arrayOf<String>( Manifest.permission.CAMERA),
        SYNAPS_REQUEST_CAMERA_PERMISSION_CODE);
}
```

**Third**, you need to need to listen `setOnReadyListener` to listen when the page is fully loaded.

```kotlin
synaps.setOnReadyListener(OnReadyListener {
 
})
```

**Forth**, you need to need to listen `setOnFinishListener` to listen when the user finished verification.

```kotlin
synaps.setOnFinishListener(OnFinishListener {
   
})
```

### Attributes list

| Attribute name          | Attribute type                                                                                           | Default | Required | Description                                                                   |
| ------------------ | --------------------------------------------------------------------------------------------------- | ------- | -------- | ----------------------------------------------------------------------------- |
| `lang`      | `string`                                                                       | `'en'`  | N        | Event listener called on every open/close action                              |
| `tier`      | `int`                                                                       | `null`  | N        | Tier is a simply way to divide your workflow into small pieces. It is very useful when you offer different features based on the verification level of your customer.  [More info](https://docs.synaps.io/manager-1/apps/individual/tiers)                           |
| `primary_color`      | `color`                                                                     | `null`  | N        | You can set a primary color, it will create a verification flow tailored to your compliance needs and your brand. |
| `secondary_color`      | `color`                                                                     | `null`  | N        | You can set a secondary color, it will create a verification flow tailored to your compliance needs and your brand. |

## Examples

## License

Apache 2.0 © [Synaps](https://www.synaps.io/)
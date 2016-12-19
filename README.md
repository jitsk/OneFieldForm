#OneFieldForm
This is One field form for android. Can be used to sign up the user using name, email and password.
Min API Level:16

##Including in project

###In build.gradle
```
repositories {
    mavenCentral()
}

dependencies {
    compile 'com.github.jitsk:onefieldform:0.0.4'
    compile 'org.greenrobot:eventbus:3.0.0'
}

```
Note: If face any issue, download aar from <a href="https://oss.sonatype.org/service/local/repositories/releases/content/com/github/jitsk/onefieldform/0.0.4/onefieldform-0.0.4.aar">here</a>  and <a href="http://stackoverflow.com/questions/24506648/adding-local-aar-files-to-gradle-build-using-flatdirs-is-not-working">add as module.</a>

###Via xml

Use this in top level layout
```
xmlns:formbox="http://schemas.android.com/apk/res-auto"
```

Use like this in xml file
```
<com.android.onefieldform.FormBox
                android:id="@+id/formbox"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                formbox:backgroundColor="@android:color/black"
                formbox:borderColor="@android:color/white"
                formbox:textColor="@android:color/white"
                formbox:outerCircleBorderColor="@android:color/white"
                formbox:outerCircleBackgroundColor="@android:color/black"
                formbox:innerCircleBorderColor="@android:color/white"
                formbox:innerCircleBackgroundColor="@android:color/black"
                android:minHeight="80dp"
                android:minWidth="250dp" />
```
Note: Use 80dp height for better appearence. UI may get distorted at higher heights.

###Via Code
```
FormBox formbox = new public FormBox(Context context,
                   int backGroundColor,
                   int borderColor,
                   int textColor,
                   int outerCircleBorderColor,
                   int outerCircleBackgroundColor,
                   int innerCircleBorderColor,
                   int innerCircleBackgroundColor);
```
##Listening to events

Uses eventbus to listen to events. Refer to <a href="https://github.com/greenrobot/EventBus">EventBus</a> for more.

In your activity, use
```
    @Subscribe
    public void onNameReceived(NameReceived nameReceived) {
        //process name
        EventBus.getDefault().post(new NameVerified());
    }

    @Subscribe
    public void onEmailReceived(EmailReceived emailReceived) {
        //process email
        EventBus.getDefault().post(new EmailVerified());
    }

    @Subscribe
    public void onPasswordReceived(PasswordReceived passwordReceived) {
        //process passsword
        EventBus.getDefault().post(new PasswordVerified());
    }

    @Subscribe
    public void onNameError(NameError nameError) {
        Toast.makeText(getApplicationContext(),"Invalid username",Toast.LENGTH_SHORT).show();
    }

    @Subscribe
    public void onEmailError(EmailError emailError) {
        Toast.makeText(getApplicationContext(),"Invalid email",Toast.LENGTH_SHORT).show();
    }

    @Subscribe
    public void onPasswordError(PasswordError passwordError) {
        Toast.makeText(getApplicationContext(),"Invalid password",Toast.LENGTH_SHORT).show();
    }
        
  ```
##Regular Expressions Used for Validations
```
    USERNAME_PATTERN = "^[a-zA-Z0-9_-]{3,100}$"
    PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,100})"
    EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"

```

##Notes
1. Input is limited to 24 characters.


##Known Bugs
1. ~~Thickness of border increases during animation.~~

##Developed By
Jithin Sebastian

##License
```
Copyright [2016] [Jithin Sebastian]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

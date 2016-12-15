# OneFieldForm
This is One field form for android. Can be used to sign up the user using name, email and password.
#Including in project

#In build.gradle
```
repositories {
    mavenCentral()
}

dependencies {
    compile 'com.github.jitsk:onefieldform:0.0.1'
    compile 'org.greenrobot:eventbus:3.0.0'
}


```

#Via xml

Use this in xml file
```
<com.android.onefieldform.FormBox
                android:id="@+id/formbox"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:backgroundColor="@android:color/black"
                android:borderColor="@android:color/white"
                android:textColor="@android:color/white"
                android:minHeight="80dp"
                android:minWidth="250dp" />
```

#Via Code
```
FormBox formbox = new FormBox(Context context, int backGroundColor, int borderColor, int textColor);
```
#Listening to events

Uses eventbus to listen to events. Refer to <a href="https://github.com/greenrobot/EventBus">EventBus</a> for more.

In main activity,
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
#Regular Expressions Used for Validations
```
    USERNAME_PATTERN = "^[a-zA-Z0-9_-]{3,100}$"
    PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,100})"
    EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"

```

#Limitations
1. Currently color of inner circle cannot be changed in name and email fields.
2. Input is limited to 24 characters.


#Known Bugs
1. Thickness of border increases during animation.

#Developed By
Jithin Sebastian

#License
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

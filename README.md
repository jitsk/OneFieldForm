# OneFieldForm
This is One field form for android. Can be used to sign up the user using name, email and password.
#Including in project

#Via xml

Use this in xml file
```
<com.android.onefieldform.FormBox
                android:id="@+id/formbox"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:minHeight="80dp"
                android:minWidth="250dp" />
```

#Via Code
```
FormBox formbox = new FormBox(Context context, int backGroundColor, int borderColor, int textColor);
```
#Listening to events
```
formBox.setFormBoxListener(new FormBox.FormBoxListener() {
            @Override
            public void OnInitiated() {
            }

            @Override
            public void onNameEntered(String name) {
            }

            @Override
            public void onEmailEntered(String email) {
            }

            @Override
            public void onPasswordEntered(String password) {
            }

            @Override
            public void onNameError() {
            }

            @Override
            public void onEmailError() {
            }

            @Override
            public void onPasswordError() {
            }
        });
        
  ```
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

package com.android.onefieldform;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.onefieldform.Events.EmailError;
import com.android.onefieldform.Events.EmailReceived;
import com.android.onefieldform.Events.EmailVerified;
import com.android.onefieldform.Events.NameError;
import com.android.onefieldform.Events.NameReceived;
import com.android.onefieldform.Events.NameVerified;
import com.android.onefieldform.Events.PasswordError;
import com.android.onefieldform.Events.PasswordReceived;
import com.android.onefieldform.Events.PasswordVerified;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Framebox class to create the framebox
 * @author jithin
 * @version 1.0
 */

public class FormBox extends FrameLayout {


    private EditText nameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;

    private View nextButton;
    private Button signupButton;
    private View nameInput;
    private View emailInput;
    private View passwordInput;
    private View welcome;
    private TextView textViewName;
    private TextView textViewMail;
    private TextView textViewPass;
    private TextView welcomeTextView;
    private View signupView;

    private int backGroundColor;
    private int borderColor;
    private int textColor;
    private int outerCircleBorderColor;
    private int outerCircleBackgroundColor;
    private int innerCircleBorderColor;
    private int innerCircleBackgroundColor;

    public FormBox(Context context) {
        super(context);
        initAttributes(context, null);
        init();
    }

    public FormBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttributes(context, attrs);
        init();
    }

    public FormBox(Context context,
                   int backGroundColor,
                   int borderColor,
                   int textColor,
                   int outerCircleBorderColor,
                   int outerCircleBackgroundColor,
                   int innerCircleBorderColor,
                   int innerCircleBackgroundColor) {
        super(context);
        this.backGroundColor = backGroundColor; this.borderColor = borderColor; this.textColor = textColor;
        this.outerCircleBorderColor = outerCircleBorderColor;
        this.outerCircleBackgroundColor = outerCircleBackgroundColor;
        this.innerCircleBorderColor = innerCircleBorderColor;
        this.innerCircleBackgroundColor = innerCircleBackgroundColor;
        init();
    }

    private void initAttributes(Context context, AttributeSet attrs) {
        initDefault();
        if(attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FormField);
            final int N = a.getIndexCount();
            for (int i = 0; i < N; ++i) {
                int attr = a.getIndex(i);
                if (attr == R.styleable.FormField_backgroundColor) {
                    backGroundColor = a.getColor(attr, ResourcesCompat.getColor(getResources(), R.color.color_black, null));
                } else if (attr == R.styleable.FormField_borderColor) {
                    borderColor = a.getColor(attr, ResourcesCompat.getColor(getResources(), R.color.color_clouds, null));
                } else if (attr == R.styleable.FormField_textColor) {
                    textColor = a.getColor(attr, ResourcesCompat.getColor(getResources(), R.color.color_clouds, null));
                } else if (attr == R.styleable.FormField_innerCircleBorderColor) {
                    innerCircleBorderColor = a.getColor(attr, ResourcesCompat.getColor(getResources(), R.color.color_clouds, null));
                } else if (attr == R.styleable.FormField_innerCircleBackgroundColor) {
                    innerCircleBackgroundColor = a.getColor(attr, ResourcesCompat.getColor(getResources(), R.color.color_clouds, null));
                } else if (attr == R.styleable.FormField_outerCircleBorderColor) {
                    outerCircleBorderColor = a.getColor(attr, ResourcesCompat.getColor(getResources(), R.color.color_clouds, null));
                } else if (attr == R.styleable.FormField_outerCircleBackgroundColor) {
                    outerCircleBackgroundColor = a.getColor(attr, ResourcesCompat.getColor(getResources(), R.color.color_clouds, null));
                }
            }
            a.recycle();
        }
    }

    private void initDefault() {
        backGroundColor = ResourcesCompat.getColor(getResources(), R.color.color_black, null);
        borderColor = ResourcesCompat.getColor(getResources(), R.color.color_clouds, null);
        textColor = ResourcesCompat.getColor(getResources(), R.color.color_clouds, null);
        outerCircleBorderColor = ResourcesCompat.getColor(getResources(), R.color.color_clouds, null);
        outerCircleBackgroundColor = ResourcesCompat.getColor(getResources(), R.color.color_black, null);
        innerCircleBorderColor = ResourcesCompat.getColor(getResources(), R.color.color_clouds, null);
        innerCircleBackgroundColor = ResourcesCompat.getColor(getResources(), R.color.color_black, null);
    }

    private void createAndSetDrawable(int stroke) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(getResources().getDimension(R.dimen.radius));
        gradientDrawable.setColor(backGroundColor);
        gradientDrawable.setStroke(stroke, borderColor);
        this.setBackground(gradientDrawable);
    }

    private void init() {
        EventBus.getDefault().register(this);
        createAndSetDrawable((int) getResources().getDimension(R.dimen.stroke));
        signupView = inflate(getContext(),R.layout.signup,null);
        setAndAddView(signupView);
        signupButton = (Button) signupView.findViewById(R.id.sign_up);
        signupButton.setTextColor(textColor);
        signupButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                initNameItem();
            }
        });
    }


    private void initNameItem() {
        nameInput = inflate(getContext(),R.layout.nameitem,null);
        setAndAddView(nameInput);
        CircleImageView circleImage = (CircleImageView) this.findViewById(R.id.circle_image_name);
        circleImage.setVisibility(View.INVISIBLE);
        textViewName = (TextView) this.findViewById(R.id.name_text);
        textViewName.setTextColor(textColor);
        nextButton = this.findViewById(R.id.name_next);
        nameEditText = (EditText)this.findViewById(R.id.editText_name);
        nameEditText.setTextColor(textColor);
        createOuterCirclesName();
        addTextWatcher(nameEditText);
        nextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                scaleButtonView(nextButton);
                postDelayed(new Runnable()
                {
                    public void run()
                    {
                        if(validateName()) {
                            EventBus.getDefault().post(new NameReceived(nameEditText.getText().toString()));
                        } else {
                            EventBus.getDefault().post(new NameError());
                            shakeAnime();
                        }
                    }
                }, 300);
            }
        });
        crossfade(signupView, nameInput);
        postDelayed(new Runnable() {
            @Override
            public void run() {
                animateStart();
            }
        },200);
    }

    @Subscribe
    public void onNameVerified(NameVerified nameVerified) {
        initEmailItem();
    }

    private void initEmailItem() {
        final FrameLayout frameLayout = this;
        emailInput = inflate(getContext(),R.layout.emailitem,null);
        setAndAddView(emailInput);
        nextButton = frameLayout.findViewById(R.id.email_next);
        emailEditText = (EditText)frameLayout.findViewById(R.id.editText_email);
        textViewMail = (TextView) frameLayout.findViewById(R.id.mail_text);
        textViewMail.setTextColor(textColor);
        emailEditText.setTextColor(textColor);
        createOuterCirclesEmail();
        addTextWatcher(emailEditText);
        nextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                scaleButtonView(nextButton);
                postDelayed(new Runnable()
                {
                    public void run()
                    {
                        if(validateMail()) {
                            EventBus.getDefault().post(new EmailReceived(emailEditText.getText().toString()));
                        } else {
                            EventBus.getDefault().post(new EmailError());
                            shakeAnime();
                        }
                    }
                }, 300);
            }
        });
        emailEditText.setText(nameEditText.getText().toString());
        textViewName.setVisibility(View.INVISIBLE);
        nameEditText.setVisibility(View.INVISIBLE);
        textViewMail.setVisibility(View.INVISIBLE);
        emailEditText.setVisibility(View.INVISIBLE);
        nextButton.setVisibility(View.INVISIBLE);
        findViewById(R.id.frameLayout_name).setVisibility(View.INVISIBLE);
        findViewById(R.id.name_next).setVisibility(View.INVISIBLE);
        transAnime(R.id.circle_image_name,nameEditText.getWidth());
        postDelayed(new Runnable()
        {
            public void run()
            {
                frameLayout.removeView(nameInput);
                emailEditText.setText("");
                emailEditText.setVisibility(View.VISIBLE);
                textViewMail.setVisibility(View.VISIBLE);
                nextButton.setVisibility(View.VISIBLE);
            }
        }, 320);
    }

    @Subscribe
    public void onEmailVerified(EmailVerified emailVerified) {
        initPasswordItem();
    }

    private void initPasswordItem() {
        passwordInput = inflate(getContext(),R.layout.passworditem,null);
        setAndAddView(passwordInput);
        textViewPass = (TextView) this.findViewById(R.id.password_text);
        nextButton = this.findViewById(R.id.password_next);
        passwordEditText = (EditText)this.findViewById(R.id.editText_password);
        textViewPass.setTextColor(textColor);
        passwordEditText.setTextColor(textColor);
        final FrameLayout frameLayout = this;
        addTextWatcher(passwordEditText);
        nextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                scaleButtonView(nextButton);
                postDelayed(new Runnable()
                {
                    public void run()
                    {
                        if(validatePassword()) {
                            EventBus.getDefault().post(new PasswordReceived(passwordEditText.getText().toString()));
                        } else {
                            EventBus.getDefault().post(new PasswordError());
                            shakeAnime();
                        }
                    }
                }, 300);
            }
        });
        passwordEditText.setText(emailEditText.getText().toString());
        textViewMail.setVisibility(View.INVISIBLE);
        emailEditText.setVisibility(View.INVISIBLE);
        textViewPass.setVisibility(View.INVISIBLE);
        nextButton.setVisibility(View.INVISIBLE);
        passwordEditText.setVisibility(View.INVISIBLE);
        findViewById(R.id.email_next).setVisibility(INVISIBLE);
        findViewById(R.id.frameLayout_email).setVisibility(INVISIBLE);
        transAnime(R.id.circle_image_email, emailEditText.getWidth());
        postDelayed(new Runnable()
        {
            public void run()
            {
                frameLayout.removeView(emailInput);
                passwordEditText.setText("");
                nextButton.setVisibility(VISIBLE);
                passwordEditText.setVisibility(View.VISIBLE);
                textViewPass.setVisibility(View.VISIBLE);
            }
        }, 320);

    }

    @Subscribe
    public void onPasswordVerified(PasswordVerified passwordVerified) {
        welcome();
    }

    private void welcome() {
        welcome = inflate(getContext(),R.layout.welcome,null);
        setAndAddView(welcome);
        final FrameLayout fl = this;
        textViewPass.setVisibility(View.INVISIBLE);
        passwordEditText.setVisibility(View.INVISIBLE);
        welcome.setVisibility(View.INVISIBLE);
        welcomeTextView = (TextView) this.findViewById(R.id.welcome_text);
        welcomeTextView.setTextColor(textColor);
        nextButton.setVisibility(View.INVISIBLE);
        transAnime(R.id.circle_image_password, passwordEditText.getWidth());
        postDelayed(new Runnable()
        {
            public void run()
            {
                welcome.setVisibility(View.VISIBLE);
                crossfade(passwordInput, welcome);
                fl.removeView(passwordInput);
            }
        }, 320);
        postDelayed(new Runnable()
        {
            public void run()
            {
                    ValueAnimator animation = ValueAnimator.ofFloat(getResources().getDimension(R.dimen.stroke),getResources().getDimension(R.dimen.stroke_zero));
                    animation.setDuration(500);
                    animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float ds = (float) animation.getAnimatedValue();
                        createAndSetDrawable((int) ds);
                    }
                });
                animation.start();
            }
        }, 400);
    }

    private boolean validateName() {
        return nameEditText.getText().toString().matches(Constants.USERNAME_PATTERN);
    }

    private boolean validateMail() {
        return emailEditText.getText().toString().matches(Constants.EMAIL_PATTERN);
    }

    private boolean validatePassword() {
        return passwordEditText.getText().toString().matches(Constants.PASSWORD_PATTERN);
    }

    private void crossfade(final View view1, View view2) {

        view2.setAlpha(0f);
        view2.animate()
                .alpha(1f)
                .setDuration(400)
                .setListener(null);

        view1.animate()
                .alpha(0f)
                .setDuration(400)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view1.setVisibility(View.GONE);
                    }
                });
    }

    private void scaleButtonView(View v) {
        final Animation animScale = AnimationUtils.loadAnimation(getContext(), R.anim.scale);
        v.startAnimation(animScale);
    }


    private FrameLayout.LayoutParams getParams() {
        return new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
    }

    private int getGravity() {
        return Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
    }

    private void setAndAddView(View view) {
        FrameLayout.LayoutParams params = getParams();
        params.gravity = getGravity();
        view.setLayoutParams(params);
        this.addView(view);
    }

    private void addTextWatcher(EditText editText) {
        final FrameLayout frameLayout = this;
        final Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.rotate);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(i2 > 16) {
                    nextButton.startAnimation(animation);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                frameLayout.clearAnimation();
            }
        });
    }

    private void shakeAnime() {
        final FrameLayout frameLayout = this;
        final Animation animShake = AnimationUtils.loadAnimation(getContext(), R.anim.shake);
        frameLayout.startAnimation(animShake);
    }

    private void animateStart() {
        final Animation animSequence = AnimationUtils.loadAnimation(getContext(), R.anim.seq1);
        final CircleImageView circleImage = (CircleImageView) this.findViewById(R.id.circle_image_name);
        circleImage.setVisibility(View.VISIBLE);
        circleImage.startAnimation(animSequence);
    }

    private void transAnime(int id, int width) {
        final CircleImageView circleImage = (CircleImageView) this.findViewById(id);
        circleImage.animate().
                translationXBy(width + 185).
                setDuration(300).
                start();
    }


    private void createOuterCirclesName() {
        LayerDrawable layerDrawable = (LayerDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.layoutbackground, null);
        GradientDrawable frontCircle = (GradientDrawable)layerDrawable.getDrawable(0);
        GradientDrawable backCircle = (GradientDrawable)layerDrawable.getDrawable(1);
        frontCircle.setStroke((int) getResources().getDimension(R.dimen.outercircle_stroke), outerCircleBorderColor);
        backCircle.setStroke((int) getResources().getDimension(R.dimen.innercircle_stroke), innerCircleBorderColor);
        frontCircle.setColor(outerCircleBackgroundColor);
        backCircle.setColor(innerCircleBackgroundColor);
        Drawable[] drawables = {frontCircle,backCircle};
        LayerDrawable newLayerDrawable = new LayerDrawable(drawables);
        findViewById(R.id.frameLayout_name).setBackground(newLayerDrawable);
    }

    private void createOuterCirclesEmail() {
        GradientDrawable drawable = (GradientDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.layoutbackground_email, null);
        drawable.setStroke((int) getResources().getDimension(R.dimen.innercircle_stroke), innerCircleBorderColor);
        drawable.setColor(innerCircleBackgroundColor);
        findViewById(R.id.frameLayout_email).setBackground(drawable);
    }
}

package ru.sfedu.schedule.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ru.sfedu.schedule.R;
import ru.sfedu.schedule.network.App;
import ru.sfedu.schedule.network.Cookie;
import ru.sfedu.schedule.network.RequestSchedule.RequestSchedule;
import ru.sfedu.schedule.activities.requestActivity.RequestActivity;
import ru.sfedu.schedule.utils.Substitution;
import ru.sfedu.schedule.utils.Util;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private App app;
    private CompositeDisposable disposable;
    private Button buttonIdentify, buttonSkip, buttonRegisration;
    private TextView editLogin, textLoginHint, editPassword, textPasswordHint, textLoginSuffix;
    private Context thisContext;
    private boolean textLoginOK, textPasswordOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisContext = this;
        setContentView(R.layout.activity_identification);
        app = (App) getApplication();
        disposable = new CompositeDisposable();

        Substitution.init(getApplicationContext());

        Cookie cookie = (Cookie) Util.open(getApplicationContext(), Cookie.class.getSimpleName());
        if (cookie != null) {
            disposable.add(app.getNetworkService().getApi().checkUser(cookie.getUserId(), cookie.getHash())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe((error, throwable) -> {
                        if (throwable != null) {
                            Toast.makeText(thisContext, R.string.data_loading_error, Toast.LENGTH_SHORT).show();
                            init();
                        } else {
                            if (!error.equals("0")) {
                                Util.delete(getApplicationContext(), Cookie.class.getSimpleName());
                                if (error.equals("1"))
                                    Toast.makeText(thisContext, R.string.invalid_session, Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(thisContext, error, Toast.LENGTH_SHORT).show();
                                init();
                            }
                            else {
                                Intent intent = new Intent(thisContext, RequestActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    })
            );
        } else init();
    }

    private void init() {

        editLogin = findViewById(R.id.edit_login);
        textLoginHint = findViewById(R.id.text_login_hint);
        textLoginSuffix = findViewById(R.id.text_login_suffix);
        editPassword = findViewById(R.id.edit_password);
        textPasswordHint = findViewById(R.id.text_password_hint);
        buttonIdentify = findViewById(R.id.button_identify);
        buttonSkip = findViewById(R.id.button_skip);
        buttonRegisration = findViewById(R.id.button_registration);

        textLoginSuffix.setText(R.string.sfedu_ru);
        setButtonIdentify();
        setButtonSkip();
        setButtonRegistration();

        textLoginOK = false;
        textPasswordOK = false;

        editLogin.addTextChangedListener(new TextWatcher() {

            private final Runnable post = new Runnable() {
                @Override
                public void run() {
                    textLoginHint.setVisibility(View.INVISIBLE);
                }
            };

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String status = Util.checkText(thisContext, editLogin.getText().toString(), R.string.pattern_login, R.string.pattern_login_symbols, 3, 40);
                textLoginHint.removeCallbacks(post);
                textLoginHint.setText(status);
                if (!status.equals(getString(R.string.OK))) {
                    textLoginOK = false;
                    buttonIdentify.setEnabled(false);
                    textLoginHint.setTextColor(getColor(R.color.design_default_color_error));
                    editLogin.getBackground().setColorFilter(getColor(R.color.design_default_color_error), PorterDuff.Mode.SRC_ATOP);
                } else {
                    textLoginOK = true;
                    if (textPasswordOK = true) buttonIdentify.setEnabled(true);
                    textLoginHint.setTextColor(getColor(R.color.teal_200));
                    editLogin.getBackground().setColorFilter(getColor(R.color.teal_200), PorterDuff.Mode.SRC_ATOP);
                }
                textLoginHint.setVisibility(View.VISIBLE);
                textLoginHint.postDelayed(post, 3000);
            }
        });

        editPassword.addTextChangedListener(new TextWatcher() {

            private final Runnable post = new Runnable() {
                @Override
                public void run() {
                    textPasswordHint.setVisibility(View.INVISIBLE);
                }
            };

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String status = Util.checkText(thisContext, editPassword.getText().toString(), R.string.pattern_password, R.string.pattern_password_symbols, 8, 40);
                textPasswordHint.removeCallbacks(post);
                textPasswordHint.setText(status);
                if (!status.equals(getString(R.string.OK))) {
                    textPasswordOK = false;
                    buttonIdentify.setEnabled(false);
                    textPasswordHint.setTextColor(getColor(R.color.design_default_color_error));
                    editPassword.getBackground().setColorFilter(getColor(R.color.design_default_color_error), PorterDuff.Mode.SRC_ATOP);
                } else {
                    textPasswordOK = true;
                    if (textLoginOK = true) buttonIdentify.setEnabled(true);
                    textPasswordHint.setTextColor(getColor(R.color.teal_200));
                    editPassword.getBackground().setColorFilter(getColor(R.color.teal_200), PorterDuff.Mode.SRC_ATOP);
                }
                textPasswordHint.setVisibility(View.VISIBLE);
                textPasswordHint.postDelayed(post, 3000);
            }
        });
    }

    private void setButtonIdentify() {
        buttonIdentify.setEnabled(false);
        buttonIdentify.setOnClickListener(view -> disposable.add(app.getNetworkService().getApi()
                .identify(editLogin.getText().toString() + textLoginSuffix.getText().toString(), editPassword.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((cookie, throwable) -> {
                    if (throwable != null) {
                        Toast.makeText(thisContext, R.string.data_loading_error, Toast.LENGTH_SHORT).show();
                    } else {
                        String error = cookie.getError();
                        if (!error.equals("0"))
                            if (error.equals("1")) {
                                Toast.makeText(thisContext, R.string.wrong_data, Toast.LENGTH_SHORT).show();
                            } else
                                Toast.makeText(thisContext, error, Toast.LENGTH_SHORT).show();
                        else {
                            String hash = cookie.getHash();
                            Log.v("identify", "hash " + hash);
                            Util.save(getApplicationContext(), cookie);
                            Intent intent = new Intent(thisContext, RequestActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                })
        ));
    }

    private void setButtonSkip() {
        buttonSkip.setEnabled(true);
        buttonSkip.setOnClickListener(view -> {
            Intent intent = new Intent(thisContext, RequestActivity.class);
            intent.putExtra(RequestSchedule.class.getSimpleName(), (Bundle) null);
            startActivity(intent);
            finish();
        });
    }

    private void setButtonRegistration() {
        buttonRegisration.setEnabled(true);
        buttonRegisration.setOnClickListener(view -> {
            Intent intent = new Intent(thisContext, RegistrationActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}
package com.example.myapp3;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp3.Network.App;
import com.example.myapp3.Network.Cookie;
import com.example.myapp3.RequestActivity.RequestActivity;
import com.example.myapp3.Util.Util;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class RegistrationActivity extends AppCompatActivity {

    App app;
    CompositeDisposable disposable;
    Button buttonRegister, buttonSentMail, buttonConfirmMail;
    EditText editPassword, editPasswordAccept, editMail, editMailConfirm;
    TextView textPasswordHint, textPasswordAcceptHint,
            textMailHint, textMailConfirmHint, textMailSuffix;
    Context thisContext;
    boolean textPasswordOK, textPasswordAcceptOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        thisContext = this;
        app = (App) getApplication();
        disposable = new CompositeDisposable();
        init();
    }

    private void init() {
        editPassword = findViewById(R.id.edit_password);
        textPasswordHint = findViewById(R.id.text_password_hint);
        editPasswordAccept = findViewById(R.id.edit_password_accept);
        textPasswordAcceptHint = findViewById(R.id.text_password_accept_hint);
        editMail = findViewById(R.id.edit_mail);
        textMailHint = findViewById(R.id.text_mail_hint);
        textMailSuffix = findViewById(R.id.text_mail_suffix);
        textMailSuffix.setText(R.string.sfedu_ru);
        editMailConfirm = findViewById(R.id.edit_mail_confirm);
        textMailConfirmHint = findViewById(R.id.text_mail_confirm_hint);
        buttonSentMail = findViewById(R.id.button_sent_mail);
        buttonConfirmMail = findViewById(R.id.button_mail_confirm);
        buttonRegister = findViewById(R.id.button_register);

        textPasswordOK = false;
        textPasswordAcceptOK = false;

        editMail.addTextChangedListener(new TextWatcher() {

            private final Runnable post = new Runnable() {
                @Override
                public void run() {
                    textMailHint.setVisibility(View.INVISIBLE);
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
                String status = Util.checkText(thisContext, editMail.getText().toString(), R.string.pattern_login, R.string.pattern_login_symbols, 3, 40);
                textMailHint.removeCallbacks(post);
                textMailHint.setText(status);
                if (!status.equals(getString(R.string.OK))) {
                    buttonSentMail.setEnabled(false);
                    textMailHint.setTextColor(getColor(R.color.design_default_color_error));
                    editMail.getBackground().setColorFilter(getColor(R.color.design_default_color_error), PorterDuff.Mode.SRC_ATOP);
                } else {
                    buttonSentMail.setEnabled(true);
                    textMailHint.setTextColor(getColor(R.color.teal_200));
                    editMail.getBackground().setColorFilter(getColor(R.color.teal_200), PorterDuff.Mode.SRC_ATOP);
                }
                textMailHint.setVisibility(View.VISIBLE);
                textMailHint.postDelayed(post, 3000);
            }
        });

        editMailConfirm.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editMailConfirm.getText().length() != 4) {
                    buttonConfirmMail.setEnabled(false);
                } else {
                    buttonConfirmMail.setEnabled(true);
                    buttonConfirmMail.callOnClick();
                }
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
                String status = Util.checkText(thisContext, editPassword.getText().toString(), R.string.pattern_login, R.string.pattern_login_symbols, 8, 40);
                textPasswordHint.removeCallbacks(post);
                textPasswordHint.setText(status);
                if (!status.equals(getString(R.string.OK))) {
                    textPasswordOK = false;
                    buttonRegister.setEnabled(false);
                    textPasswordHint.setTextColor(getColor(R.color.design_default_color_error));
                    editPassword.getBackground().setColorFilter(getColor(R.color.design_default_color_error), PorterDuff.Mode.SRC_ATOP);
                } else {
                    textPasswordOK = true;
                    if (textPasswordAcceptOK)
                        buttonRegister.setEnabled(true);
                    textPasswordHint.setTextColor(getColor(R.color.teal_200));
                    editPassword.getBackground().setColorFilter(getColor(R.color.teal_200), PorterDuff.Mode.SRC_ATOP);
                    editPasswordAccept.setText(editPasswordAccept.getText());
                }
                textPasswordHint.setVisibility(View.VISIBLE);
                textPasswordHint.postDelayed(post, 3000);
            }
        });

        editPasswordAccept.addTextChangedListener(new TextWatcher() {

            private final Runnable post = new Runnable() {
                @Override
                public void run() {
                    textMailHint.setVisibility(View.INVISIBLE);
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
                textPasswordAcceptHint.removeCallbacks(post);
                if (!editPassword.getText().toString().contentEquals(editPasswordAccept.getText())) {
                    textPasswordAcceptOK = false;
                    buttonRegister.setEnabled(false);
                    textPasswordAcceptHint.setText(R.string.passwords_not_equals);
                    textPasswordAcceptHint.setTextColor(getColor(R.color.design_default_color_error));
                    editPasswordAccept.getBackground().setColorFilter(getColor(R.color.design_default_color_error), PorterDuff.Mode.SRC_ATOP);
                } else {
                    textPasswordAcceptOK = true;
                    if (textPasswordOK)
                        buttonRegister.setEnabled(true);
                    textPasswordAcceptHint.setText(R.string.OK);
                    textPasswordAcceptHint.setTextColor(getColor(R.color.teal_200));
                    editPasswordAccept.getBackground().setColorFilter(getColor(R.color.teal_200), PorterDuff.Mode.SRC_ATOP);
                }
                textPasswordAcceptHint.setVisibility(View.VISIBLE);
                textPasswordAcceptHint.postDelayed(post, 3000);
            }
        });

        setButtonConfirmMail();
        setButtonSentMail();
        setButtonRegister();
    }

    private void setButtonSentMail() {
        buttonSentMail.setEnabled(false);
        buttonSentMail.setOnClickListener(view -> disposable.add(app.getNetworkService().getApi()
                .setMail(editMail.getText().toString() + textMailSuffix.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response, throwable) -> {
                    if (throwable != null) {
                        Toast.makeText(thisContext, R.string.data_loading_error, Toast.LENGTH_SHORT).show();
                    } else {
                        if (!response.equals("0"))
                            if (response.equals("1"))
                                Toast.makeText(thisContext, R.string.mail_is_taken, Toast.LENGTH_SHORT).show();
                            else if (response.equals("2"))
                                Toast.makeText(thisContext, R.string.sending_message_error, Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(thisContext, response, Toast.LENGTH_SHORT).show();
                        else {
                            editMail.setEnabled(false);
                            buttonSentMail.setEnabled(false);
                            buttonSentMail.setVisibility(View.INVISIBLE);
                            editMailConfirm.setVisibility(View.VISIBLE);
                            buttonConfirmMail.setVisibility(View.VISIBLE);
                        }
                    }
                })
        ));
    }

    private void setButtonConfirmMail() {
        buttonConfirmMail.setEnabled(false);
        buttonConfirmMail.setOnClickListener(view -> disposable.add(app.getNetworkService().getApi()
                .confirmMail(editMail.getText().toString() + textMailSuffix.getText().toString(), editMailConfirm.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response, throwable) -> {
                    if (throwable != null) {
                        Toast.makeText(thisContext, R.string.data_loading_error, Toast.LENGTH_SHORT).show();
                    } else {
                        if (!response.equals("0"))
                            if (response.equals("1")) {
                                Toast.makeText(thisContext, getString(R.string.crush, getString(R.string.internal_error)), Toast.LENGTH_SHORT).show();
                                buttonConfirmMail.setEnabled(false);
                                buttonConfirmMail.setVisibility(View.INVISIBLE);
                                editMailConfirm.setText("");
                                editMailConfirm.setVisibility(View.INVISIBLE);
                                editMail.setEnabled(true);
                                buttonSentMail.setVisibility(View.VISIBLE);
                            } else if (response.equals("2")) {
                                Toast.makeText(thisContext, R.string.wrong_code, Toast.LENGTH_SHORT).show();
                                editMailConfirm.getBackground().setColorFilter(getColor(R.color.design_default_color_error), PorterDuff.Mode.SRC_ATOP);
                            } else
                                Toast.makeText(thisContext, response, Toast.LENGTH_SHORT).show();
                        else {
                            editMailConfirm.getBackground().setColorFilter(getColor(R.color.teal_200), PorterDuff.Mode.SRC_ATOP);
                            editMailConfirm.setEnabled(false);
                            buttonConfirmMail.setEnabled(false);
                            buttonConfirmMail.setVisibility(View.INVISIBLE);
                            editPassword.setVisibility(View.VISIBLE);
                            editPasswordAccept.setVisibility(View.VISIBLE);
                            buttonRegister.setVisibility(View.VISIBLE);
                        }
                    }
                })
        ));
    }

    private void setButtonRegister() {
        buttonRegister.setEnabled(false);
        buttonRegister.setOnClickListener(view -> disposable.add(app.getNetworkService().getApi()
                .register(editMail.getText().toString() + textMailSuffix.getText().toString(), editMailConfirm.getText().toString(), editPassword.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((cookie, throwable) -> {
                    if (throwable != null) {
                        Toast.makeText(thisContext, R.string.data_loading_error, Toast.LENGTH_SHORT).show();
                    } else {
                        String error = cookie.getError();
                        if (!error.equals("0"))
                            if (error.equals("1") || error.equals("2") || error.equals("3")) {
                                String prefix = getString(R.string.internal_error);
                                if (error.equals("3"))
                                    prefix = getString(R.string.mail_is_taken);
                                Toast.makeText(thisContext, getString(R.string.crush, prefix), Toast.LENGTH_SHORT).show();
                                buttonRegister.setEnabled(false);
                                buttonRegister.setVisibility(View.INVISIBLE);
                                editPassword.setText("");
                                editPassword.setVisibility(View.INVISIBLE);
                                editPasswordAccept.setText("");
                                editPasswordAccept.setVisibility(View.INVISIBLE);
                                buttonConfirmMail.setEnabled(false);
                                buttonConfirmMail.setVisibility(View.INVISIBLE);
                                editMailConfirm.setText("");
                                editMailConfirm.setVisibility(View.INVISIBLE);
                                editMail.setEnabled(true);
                                buttonSentMail.setVisibility(View.VISIBLE);
                            } else
                                Toast.makeText(thisContext, error, Toast.LENGTH_SHORT).show();
                        else {
                            String hash = cookie.getHash();
                            Log.v("register", "code: " + hash);
                            Util.save(getApplicationContext(), cookie);
                            Intent intent = new Intent(thisContext, RequestActivity.class);
                            startActivity(intent);
                        }
                    }
                })
        ));
    }

    @Override
    public void onBackPressed() {
        openQuitDialog();
    }

    private void openQuitDialog() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(thisContext);
        quitDialog.setTitle(R.string.registration_on_back);

        quitDialog.setPositiveButton(R.string.back_yes, (dialogInterface, i) -> {
            Util.delete(getApplicationContext(), Cookie.class.getSimpleName());
            Intent intent = new Intent(thisContext, MainActivity.class);
            startActivity(intent);
            finish();
        });

        quitDialog.setNegativeButton(R.string.back_no, (dialog, which) -> {
            // TODO Auto-generated method stub
        });

        quitDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}
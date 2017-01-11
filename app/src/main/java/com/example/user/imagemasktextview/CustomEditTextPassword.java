package com.example.user.imagemasktextview;

import android.content.Context;
import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.widget.EditText;


/**
 * Created by User on 3/15/2016.
 */

// Usage **  editText.addTextChangedListener(new CustomEditTextPassword(this, editText, start_length, password style));  **

public class CustomEditTextPassword implements TextWatcher {

    private Context context;
    private EditText editText;
    private int start_length;
    private int password_style;

    public static final int PASSWORD_MIDDLE = 1;
    public static final int PASSWORD_NORMAL = 2;
    public static final int PASSWORD_DASH = 3;

    Spannable.Factory spannableFactory;

    public CustomEditTextPassword(Context context, EditText editText, int start_length, int password_style){
        this.context = context;
        this.editText = editText;
        this.start_length = start_length;
        this.password_style = password_style;

        spannableFactory = Spannable.Factory
                .getInstance();
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText.removeTextChangedListener(this);
                if (s.length() >= start_length) {
                    switch (password_style) {
                        case PASSWORD_MIDDLE:
                            editText.setText(getTextToIconInMiddle(context, s));
                            break;
                        case PASSWORD_NORMAL:
                            editText.setText(getTextToIcon(context, s));
                            break;
                    }
                    if(count!=0){                               // != key delete
                        if (s.length() - 1 > start) {
                            editText.setSelection(start + 1);
                        } else {
                            editText.setSelection(s.length());
                        }
                    }else{                                      // = key delete
                        editText.setSelection(start);
                    }
                }
                editText.addTextChangedListener(this);

    }

    @Override
    public void afterTextChanged(Editable s) {}

    public Spannable getTextToIconInMiddle(Context context, CharSequence text) {
        int password_length = 4;
        StringBuilder stringBuilder = new StringBuilder(text);
        Spannable spannable = spannableFactory.newSpannable(stringBuilder);
        int index = text.length() - 1;
            for(int i = start_length; i<=index; i++){
                if(i<start_length+password_length){
                    spannable.setSpan(new ImageSpan(context, R.drawable.text_mask),
                            i, i + 1,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        return spannable;
    }

    public Spannable getTextToIcon(Context context, CharSequence text) {
        StringBuilder stringBuilder = new StringBuilder(text);
        Spannable spannable = spannableFactory.newSpannable(stringBuilder);
        int index = text.length() - 1;
        for(int i = start_length; i<=index; i++){
                spannable.setSpan(new ImageSpan(context, R.drawable.text_mask),
                        i, i + 1,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannable;
    }

    public Spannable getTextToIconWithDash(Context context, CharSequence text) {
        Spannable spannable = spannableFactory.newSpannable(text);
        return spannable;
    }

}

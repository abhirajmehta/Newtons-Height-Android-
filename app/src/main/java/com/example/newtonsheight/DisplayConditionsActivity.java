package com.example.newtonsheight;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class DisplayConditionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_conditions);

        setTitle("Conditions");

        int unicode = 0x2764;
        TextView txt = (TextView) findViewById(R.id.abhishek);
        String emoji = getEmojiByUnicode(unicode);
        String text = "Abhishek Mehta ";
        txt.setText(text + emoji);

    }

    public String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }
}

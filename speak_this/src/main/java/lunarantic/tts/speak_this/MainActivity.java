package lunarantic.tts.speak_this;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech tts;
    EditText textToSpeak;
    Button speakButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        speakButton = findViewById(R.id.button);
        textToSpeak = findViewById(R.id.editText);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == 0) {
                    Toast.makeText(getApplicationContext(), "System is ready",
                            Toast.LENGTH_SHORT);
                }
            }
        });

        tts.setLanguage(Locale.US);

        speakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tts.speak(textToSpeak.getText().toString(), TextToSpeech.QUEUE_FLUSH, null,
                        "lunarantic.tts");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        tts.shutdown();
    }
}

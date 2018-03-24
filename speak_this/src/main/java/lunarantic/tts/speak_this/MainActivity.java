package lunarantic.tts.speak_this;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {

    TextToSpeech tts;
    EditText textToSpeak;
    Button speakButton;
    Properties prop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        speakButton = findViewById(R.id.button);
        textToSpeak = findViewById(R.id.editText);

        prop = new Properties();

        try {
            //prop.load(new FileReader(new File("file:///assets/MessagesBundles_"+getApplicationContext().getResources().getConfiguration().locale.getLanguage()+".properties")));
            prop.load(getResources().getAssets().open("MessagesBundles_"+getApplicationContext().getResources().getConfiguration().locale.getLanguage()+".properties"));
        } catch (IOException e) {
            Log.e("main", e.getMessage());
            prop = null;
        }

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == 0) {
                    Toast.makeText(getApplicationContext(), "System is ready",
                            Toast.LENGTH_SHORT);
                    if (prop == null) {
                        textToSpeak.setText("You know New York, you need New York, you know you need unique New York");
                    } else {
                        textToSpeak.setText(prop.getProperty("You are ready to go"));
                    }
                }
            }
        });

        //tts.setLanguage(Locale.US);
        tts.setSpeechRate(0.75f);

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

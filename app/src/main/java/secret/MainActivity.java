package secret;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import atys.AtyLogin;
import atys.AtyTimeLine;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        //判断token标识以跳转不同界面
        String token = Config.getCachedToken(this);
        if (token != null) {
            Intent i = new Intent(this, AtyTimeLine.class);
            i.putExtra(Config.KEY_TOKEN, token);
            startActivity(i);
        } else {
            startActivity(new Intent(this, AtyLogin.class));
        }
        finish();
    }
}

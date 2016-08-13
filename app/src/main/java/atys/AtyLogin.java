package atys;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jikexueyuan.secret.R;

import net.GetCode;

/**
 * Created by QQQ on 2016/8/13.
 */
public class AtyLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_login);

        etPhone = (EditText) findViewById(R.id.et_phoneNum);

        findViewById(R.id.btn_getCode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(etPhone.getText())) {//电话号码为空
                    return;
                }

                final ProgressDialog pd = ProgressDialog.show(AtyLogin.this, getString(R.string.connecting), getString(R.string.waiting));

                new GetCode(etPhone.getText().toString(), new GetCode.SuccessCallBack() {
                    @Override
                    public void onSuccess() {
                        pd.dismiss();
                        Toast.makeText(AtyLogin.this, R.string.suc_to_get_code, Toast.LENGTH_SHORT).show();
                    }
                }, new GetCode.FailCallback() {
                    @Override
                    public void onFail() {
                        pd.dismiss();
                        Toast.makeText(AtyLogin.this, R.string.fail_to_get_code, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    private EditText etPhone = null;
}

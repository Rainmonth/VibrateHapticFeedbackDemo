package rainmonth.cn.vibratehapticfeedbackdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HapticFeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haptic_feedback);

        Button btnSystemFeedback = findViewById(R.id.btn_system_feedback);

        btnSystemFeedback.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(HapticFeedbackActivity.this, "长按点击", Toast.LENGTH_SHORT).show();
//                return false;// 返回false时，是没有长按震动反馈的
                return true;// 返回true时，有震动反馈
            }
        });

        Button btnUserFeedback = findViewById(R.id.btn_user_feedback);
        btnUserFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
            }
        });

        Button btnIgnoreViewSetting = findViewById(R.id.btn_ignore_view_setting);
        btnIgnoreViewSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS, HapticFeedbackConstants.FLAG_IGNORE_VIEW_SETTING);
            }
        });

        Button btnIgnoreGlobalSetting = findViewById(R.id.btn_ignore_global_setting);
        btnIgnoreGlobalSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
            }
        });
    }
}

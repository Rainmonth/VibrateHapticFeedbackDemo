package rainmonth.cn.vibratehapticfeedbackdemo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioAttributes;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class VibratorActivity extends AppCompatActivity {

    private Vibrator mVibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibrator);

        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        Button btnDefault = findViewById(R.id.btn_default);
        Button btnSpecial = findViewById(R.id.btn_special);
        Button btnCancel = findViewById(R.id.btn_cancel);

        btnDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCanVibrate()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        mVibrator.vibrate(VibrationEffect.createOneShot(2000, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
//                        mVibrator.vibrate(2000);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            mVibrator.vibrate(2000, new AudioAttributes.Builder().build());
                        } else {
                            mVibrator.vibrate(2000);
                        }
                    }
                }
            }
        });

        btnSpecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCanVibrate()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        mVibrator.vibrate(VibrationEffect.createWaveform(new long[]{500, 1000, 250, 2000}, 2));
                    } else {
                        mVibrator.vibrate(new long[]{500, 1000, 250, 2000}, 2);
                    }
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mVibrator) {
                    mVibrator.cancel();
                }
            }
        });

    }

    private boolean isCanVibrate() {
        // 是否开启震动权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.VIBRATE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "未获取到震动权限", Toast.LENGTH_LONG).show();
            return false;
        }

        if (mVibrator == null) {
            Toast.makeText(this, "mVibrator为空", Toast.LENGTH_LONG).show();
            return false;
        }
        if (!mVibrator.hasVibrator()) {
            Toast.makeText(this, "当前硬件不支持震动", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}

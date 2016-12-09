package projetomercadolivre.caioernandes.com.br.projetomercadolivre.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import projetomercadolivre.caioernandes.com.br.projetomercadolivre.R;

public class SplashActivity extends AppCompatActivity {
    public static final int TEMPO_SPLASH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent it = new Intent(SplashActivity.this, ProdutosActivity.class);
                startActivity(it);
                finish();
            }
        }, TEMPO_SPLASH);
    }
}

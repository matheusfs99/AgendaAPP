package br.com.matheusfarias.agendasqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickEntrar(View view){
        EditText usuario = findViewById(R.id.edtUsuario);
        EditText senha = findViewById(R.id.edtSenha);
        String usuarioStr = usuario.getText().toString();
        String senhaStr = senha.getText().toString();
        if (usuario != null && !usuarioStr.equals("") && senha != null && !senhaStr.equals("")){
            if (usuarioStr.equals("admin") && senhaStr.equals("admin")){
                Intent intent = new Intent(MainActivity.this, ConsultarActivity.class);
                startActivity(intent);
                finish();
            }else{
                usuario.setText("");
                senha.setText("");
                senha.clearFocus();
                Toast.makeText(getApplicationContext(), R.string.msg_senha_invalida, Toast.LENGTH_LONG).show();
            }
        }else{
            senha.clearFocus();
            Toast.makeText(getApplicationContext(), R.string.msg_camposobrigatorios, Toast.LENGTH_LONG).show();
        }


    }

}

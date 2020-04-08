package br.com.matheusfarias.agendasqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InserirActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir);

        Button salvar = findViewById(R.id.btnSalvar);
        Button cancelar = findViewById(R.id.btnCancelar);

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBController crud = new DBController(getBaseContext());
                EditText nome = findViewById(R.id.edtNome);
                EditText fone = findViewById(R.id.edtFone);
                String nomeStr = nome.getText().toString();
                String foneStr = fone.getText().toString();
                String msg;
                if (!nomeStr.equals("") && !foneStr.equals("")){
                    msg = crud.inserir(nomeStr, foneStr);
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(InserirActivity.this, ConsultarActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),
                            R.string.msg_camposobrigatorios, Toast.LENGTH_LONG).show();
                }
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

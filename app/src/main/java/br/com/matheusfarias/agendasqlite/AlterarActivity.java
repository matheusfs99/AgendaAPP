package br.com.matheusfarias.agendasqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AlterarActivity extends AppCompatActivity {

    private Cursor cursor;
    private DBController crud;
    private String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        codigo = this.getIntent().getStringExtra("codigo");
        crud = new DBController(getBaseContext());

        final EditText nome = findViewById(R.id.edtNome);
        final EditText fone = findViewById(R.id.edtFone);
        Button editar = findViewById(R.id.btnSalvar);
        Button excluir = findViewById(R.id.btnExcluir);

        cursor = crud.buscarPoId(Integer.parseInt(codigo));

        nome.setText(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
        fone.setText(cursor.getString(cursor.getColumnIndexOrThrow("fone")));

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crud.alterar(Integer.parseInt(codigo), nome.getText().toString(), fone.getText().toString());
                Toast.makeText(getApplicationContext(), R.string.msg_alterado, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AlterarActivity.this, ConsultarActivity.class);
                startActivity(intent);
                finish();
            }
        });

        excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crud.excluir(Integer.parseInt(codigo));
                Toast.makeText(getApplicationContext(), R.string.msg_excluido, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AlterarActivity.this, ConsultarActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}

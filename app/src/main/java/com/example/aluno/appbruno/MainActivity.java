package com.example.aluno.appbruno;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    Button login;
    EditText usuario;
    EditText password;
    TextView retorno;
    ArrayList<User> usuarios;
    SharedPreferences prefLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuarios = povoar();
        login = (Button) findViewById(R.id.login);
        usuario = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.password);
        retorno = (TextView) findViewById(R.id.retorno);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*int result = comparar(usuarios,usuario.getText().toString(),password.getText().toString());
                if(result == 1){
                    retorno.setText("Usuario e senha corretos");
                }else if(result == 2){
                    retorno.setText("Usuario Correto");
                }else if(result == 3){
                    retorno.setText("Senha Correta");
                }else
                    retorno.setText("Usuario não Registrado");*/
                String us = usuario.getText().toString();
                String pas =password.getText().toString();
                if(us.equals(lerLogin("login1")) && pas.equals(lerLogin("senha1"))){
                    openSite();
                }
                if(us.equals(lerLogin("login10")) && pas.equals(lerLogin("senha10"))){
                    openActivity(lerLogin("nome10"));
                }
            }
        });
    }
    public ArrayList<User> povoar(){
        ArrayList<User> usuarios = new ArrayList<User>();
        for(int i=0;i<11;i++){
            usuarios.add(new User("user"+i,"password"+i,"usuario"+i));
            salvarLoginESenha("user"+i,"password"+i,"usuario"+i,i);
        }
        return usuarios;
    }
    public int comparar(ArrayList<User> usuarios,String usuario,String password){
        int result = 0;
        int x;
        for(User us:usuarios){
            x = us.compare(usuario,password);
            if(x !=0){
                result = x;
            }
        }
        return result;
    }
    private void salvarLoginESenha(String usuario,String password,String name,int i) {
        int mode= MainActivity.MODE_PRIVATE;
        //Quem pode acessar?
        prefLogin=getSharedPreferences("prefLogin", mode);
        SharedPreferences.Editor editor= prefLogin.edit();
        editor.putString("login"+i, usuario);
        editor.putString("senha"+i,password);
        editor.putString("nome"+i,name);
        editor.commit();
    }
    private String lerLogin(String key) {
        int mode = MainActivity.MODE_PRIVATE;
        //Quem pode acessar?
        prefLogin = getSharedPreferences("prefLogin", mode);
        return prefLogin.getString(key,"não existe");
    }
    public void openSite(){
        Intent itent = new Intent(Intent.ACTION_VIEW);
        itent.setData(Uri.parse("http://portal.virtual.ufc.br/"));
        this.startActivity(itent);
    }
    public void openActivity(String name){
        Intent myIntent = new Intent(MainActivity.this, Main2Activity.class);
        myIntent.putExtra("key", name); //Optional parameters
        MainActivity.this.startActivity(myIntent);
    }
}

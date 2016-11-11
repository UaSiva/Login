package com.example.vijai.logvolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class Register extends AppCompatActivity {
    @BindView(R.id.bn_reg)
    Button reg_bn;
    @BindView(R.id.reg_name) EditText Name;
    @BindView(R.id.reg_email) EditText Email;
    @BindView(R.id.reg_user_name) EditText UserName;
    @BindView(R.id.reg_password) EditText Password;
    @BindView(R.id.reg_con_password) EditText ConPassword;
    String name,email,username,password,conpass;
    String reg_url="http://192.168.1.3/register.php";

    @OnClick(R.id.bn_reg)
    public void submit(){
        name=Name.getText().toString();
        email=Email.getText().toString();
        username=UserName.getText().toString();
        password=Password.getText().toString();
        conpass=ConPassword.getText().toString();

        if(name.equals("")||email.equals("")||username.equals("")||password.equals("")||conpass.equals("")){
            Toast.makeText(getApplicationContext(), "Fields cannot be empty",Toast.LENGTH_SHORT).show();

        }
        if(!password.equals(conpass)){
            Toast.makeText(getApplicationContext(),"Enter correct password",Toast.LENGTH_SHORT).show();

        }
        else{

            StringRequest stringRequest = new StringRequest(Request.Method.POST,reg_url,
                    new Response.Listener<String>(){
                        @Override
                        public void onResponse(String response){
                            try {

                                JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject= jsonArray.getJSONObject(0);
                                String code= jsonObject.getString("code");
                                String message=jsonObject.getString("message");

                                if(code.equals("reg_success")){
                                    Toast.makeText(getApplicationContext(),code,Toast.LENGTH_SHORT).show();

                                    finish();

                                }
                                if(code.equals("reg_failed")){
                                    Name.setText("");
                                    Email.setText("");
                                    UserName.setText("");
                                    Password.setText("");
                                    ConPassword.setText("");
                                    Toast.makeText(getApplicationContext(),code,Toast.LENGTH_SHORT).show();
                                    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();


                                }
                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                    },new Response.ErrorListener(){

                            @Override
                            public void onErrorResponse(VolleyError error){

                            }
            }){
                @Override
                protected Map<String,String> getParams(){
                    Map<String,String> params= new HashMap<String, String>();
                    params.put("name",name);
                    params.put("email",email);
                    params.put("user_name",username);
                    params.put("password",password);
                    return params;
                }
            };

            //MySingleton.getInstance(Register.this).addToRequestQueue(stringRequest);
            Volley.newRequestQueue(this).add(stringRequest);
        }


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        submit();


    }
}

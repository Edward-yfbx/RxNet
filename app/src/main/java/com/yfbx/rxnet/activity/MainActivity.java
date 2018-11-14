package com.yfbx.rxnet.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.yfbx.rxnet.R;
import com.yfbx.rxnet.bean.Student;
import com.yfbx.rxnet.bean.User;
import com.yfbx.rxnet.net.Api;
import com.yfbx.rxnet.net.MySubscriber;
import com.yfbx.rxnet.net.Net;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text);
    }


    public void login(View view) {
        Net.create(Api.class)
                .login("zhangsan", "123456")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<User>(this) {
                    @Override
                    public void onSuccess(int code, String msg, User user) {
                        String company = "姓名: " + user.getName();
                        textView.setText(company);
                    }
                });
    }


    public void getStudents(View view) {
        Net.create(Api.class)
                .getStudents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<List<Student>>(this) {
                    @Override
                    public void onSuccess(int code, String msg, List<Student> students) {
                        String size = "列表数量：" + students.size();
                        String company = "姓名: " + students.get(0).getName();
                        textView.setText("");
                        textView.append(size);
                        textView.append("\n");
                        textView.append(company);
                    }
                });

    }

}

package hbtbiz.hdoc.com.picandfond;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PicAndFondInterface{
    private Context context;//上下文
    private RecyclerView recyclerView;//图文混排列表
    private LinearLayoutManager mLayoutManager;////设置布局管理器
    private PicAndFondAdapter adapter;//适配器
    private TextView tvAdd;//添加
    List<PicAndFond> picAndFonds = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
       /* progressDialog = DialogUtil.createLoadingDialog(context,
                "正在处理请稍后...");*/
        initView();
        initData();
        initEvent();

    }

    private void initEvent() {
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PicAndFond picAndFond = new PicAndFond();
                picAndFond.setWenzi("测试"+0);
                picAndFond.setType(0);
                picAndFond.setPic("http://img.ivsky.com/img/bizhi/pre/201601/27/february_2016-001.jpg");
                picAndFonds.add(picAndFond);
                adapter = new PicAndFondAdapter(context,picAndFonds,MainActivity.this);
                recyclerView.setAdapter(adapter);
            }
        });

    }

    private void initData() {

        PicAndFond picAndFond2 = new PicAndFond();
        picAndFond2.setWenzi("测试2");
        picAndFond2.setType(1);
        picAndFonds.add(picAndFond2);


        /*PicAndFond picAndFond1 = new PicAndFond();
        picAndFond1.setWenzi("测试1");
        picAndFond1.setType(1);
        picAndFonds.add(picAndFond1);*/
        PicAndFond picAndFond = new PicAndFond();
        picAndFond.setWenzi("测试"+0);
        picAndFond.setType(0);
        picAndFond.setPic("http://img.ivsky.com/img/bizhi/pre/201601/27/february_2016-001.jpg");
        picAndFonds.add(picAndFond);

        adapter = new PicAndFondAdapter(context,picAndFonds,MainActivity.this);
        recyclerView.setAdapter(adapter);


        
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.line_recycler);
        mLayoutManager=new LinearLayoutManager(this);

        recyclerView.setLayoutManager(mLayoutManager);
        //这句就是添加我们自定义的分隔线
        recyclerView.addItemDecoration(new RecycleViewDivider(
                context, LinearLayoutManager.HORIZONTAL,1,getResources().getColor(R.color.dark_grey_ea)));
        tvAdd = (TextView) findViewById(R.id.tv_add);
        
    }


    @Override
    public void delete(int position, int type) {
        picAndFonds.remove(position);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void insert(int positon) {
        PicAndFond picAndFond2 = new PicAndFond();
        picAndFond2.setWenzi("测试"+positon+1);
        picAndFond2.setType(1);
        picAndFonds.add(positon+1,picAndFond2);
        adapter = new PicAndFondAdapter(context,picAndFonds,MainActivity.this);
        recyclerView.setAdapter(adapter);
    }
}



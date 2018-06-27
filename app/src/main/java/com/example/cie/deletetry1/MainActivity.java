package com.example.cie.deletetry1;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ListActivity {

    private Button mAddButton;
    private EditText grade, studentid, name;
    private Information[] mStudInfo = new Information[]{
            new Information("计算机151", 2014012543, "张宇"),
            new Information("计算机151", 2014012544, "刘德华"),
            new Information("计算机151", 2014012545, "张学友"),
    };
    private ListView listview;
    List<Map<String, Object>> mlistItems;
    Map<String, Object> mmap;
    //定义一个simpleAdapter，供列表项使用
    SimpleAdapter mSimpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(android.R.id.list);
        //为所有的listview的item注册contextMenu
        this.registerForContextMenu(listview);

        mlistItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < mStudInfo.length; i++) {
            mmap = new HashMap<String, Object>();
            mmap.put("grade", mStudInfo[i].getGrade());
            mmap.put("studentId", mStudInfo[i].getStudentId());
            mmap.put("name", mStudInfo[i].getName());
            mlistItems.add(mmap);
        }
        mSimpleAdapter = new SimpleAdapter(this, mlistItems, R.layout.item, new String[]{"grade", "studentId", "name"}, new int[]{R.id.grade, R.id.studentId, R.id.name});
        listview.setAdapter(mSimpleAdapter);

        //添加对象
        mAddButton = (Button) findViewById(R.id.button);
        grade = (EditText) findViewById(R.id.editText3);
        studentid = (EditText) findViewById(R.id.editText4);
        name = (EditText) findViewById(R.id.editText5);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mmap = new HashMap<String, Object>();
                mmap.put("grade", grade.getText());
                mmap.put("studentId", studentid.getText());
                mmap.put("name", name.getText());
                mlistItems.add(mmap);
                mSimpleAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("选择操作");
        menu.add(0, 1, Menu.NONE, "删除");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case 1:
                //删除
                int pos = (int) listview.getAdapter().getItemId(menuInfo.position);
                if (mlistItems.remove(pos) != null) {
                    System.out.println("success");
                } else {
                    System.out.println("failed");
                }
                mSimpleAdapter.notifyDataSetChanged();
                Toast.makeText(getBaseContext(), "删除此项", Toast.LENGTH_SHORT).show();
                break;
            default:
                //标记
                return super.onContextItemSelected(item);
        }
        return true;
    }

}

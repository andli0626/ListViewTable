package yyy.testdatabase;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.TextView;

public class TestDataBase extends Activity {
    DataBaseHelper helper;
    private TextView textView;
    private ListView listView;
    private GridView gridView;
    private Button button;
    private HorizontalScrollView layout;
    private ArrayList<String> list1;
    private ArrayList<ArrayList<String>> lists = new ArrayList<ArrayList<String>>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        listView = (ListView) findViewById(R.id.listview);
        button = (Button) findViewById(R.id.button);
        layout = (HorizontalScrollView) findViewById(R.id.layout);
        File file = getFilesDir();
        String path = file.getAbsolutePath()+"/trade.db";
        ArrayList<String> list = new ArrayList<String>();
        list.add("");
        list.add("流水号");
        list.add("交易金额");
        list.add("银行卡号");
        list.add("交易时间");
        list.add("交易状态");
        lists.add(list);
        helper = new DataBaseHelper(this, path, 1);
        final SQLiteDatabase db = helper.getReadableDatabase();
        db.execSQL("insert into trade values(null,?,?,?,?,?)",
        		new String[]{"123456","100","622712546985642","2009-12-21 20:21:12","成功"} );
        db.execSQL("insert into trade values(null,?,?,?,?,?)",
        		new String[]{"123456","100","622712546985642","2009-12-21 20:21:12","成功"} );
        db.execSQL("insert into trade values(null,?,?,?,?,?)",
        		new String[]{"123456","100","622712546985642","2009-12-21 20:21:12","成功"} );
        int i = 0;
        button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Cursor cursor = db.rawQuery("select * from trade", null);
		        while (cursor.moveToNext()) {
					//"create table trade(_id integer primary key autoincrement,serialNo,money,cardNum,date,status)";
		        	list1 = new ArrayList<String>();
		        	list1.add(cursor.getString(cursor.getColumnIndex("_id")));
		        	list1.add(cursor.getString(cursor.getColumnIndex("serialNo")));
		        	list1.add(cursor.getString(cursor.getColumnIndex("money")));
		        	list1.add(cursor.getString(cursor.getColumnIndex("cardNum")));
		        	list1.add(cursor.getString(cursor.getColumnIndex("date")));
		        	list1.add(cursor.getString(cursor.getColumnIndex("status")));
		        	
					lists.add(list1);
				}
		        cursor.close();
		        MyAdapter adapter = new MyAdapter(TestDataBase.this, lists);
		        listView.setAdapter(adapter);
		        layout.setVisibility(View.VISIBLE);
		        
			}
		});
    }
}






















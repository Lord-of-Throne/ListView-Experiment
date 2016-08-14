package com.example.li.listview_experiment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.mobeta.android.dslv.DragSortCursorAdapter;
import com.mobeta.android.dslv.DragSortListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class MainActivity extends AppCompatActivity {
    private JazzAdapter adapter;
    private List<Person> persons;
    private Button button;

    private DragSortListView.DropListener onDrop =
            new DragSortListView.DropListener() {
                @Override
                public void drop(int from, int to) {
                    Person item = adapter.getItem(from);

                    adapter.remove(item);
                    adapter.insert(item, to);
                }
            };

    private DragSortListView.RemoveListener onRemove =
            new DragSortListView.RemoveListener() {
                @Override
                public void remove(int which) {
                    adapter.remove(adapter.getItem(which));
                }
            };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DragSortListView listView = (DragSortListView) this.findViewById(R.id.listView);
        listView.setDropListener(onDrop);
        listView.setRemoveListener(onRemove);

        //获取到集合数据
        List<Person> persons = new ArrayList<>();
        for(int i=1; i<5; i++){
            Person person = new Person();
            person.name="name"+i;
            person.phone="phone"+i;
            person.amount="amount"+i;
            persons.add(person);
        }
        //创建SimpleAdapter适配器将数据绑定到item显示控件上
//        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.item,
//                new String[]{"name", "phone", "amount"}, new int[]{R.id.name, R.id.phone, R.id.amount});
        //实现列表的显示
        adapter = new JazzAdapter(persons);

        listView.setAdapter(adapter);
        //条目点击事件
//        listView.setOnItemClickListener(new ItemClickListener());

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                for(int i=1; i<adapter.getCount();i++){
//                    int[] orders = new int[4];
//                    int j = i-1;
//                    orders[i] = adapter.getPosition(adapter.getItem(i));
//                }

            }
        });
    }
//    //获取点击事件
//    private final class ItemClickListener implements AdapterView.OnItemClickListener {
//
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            ListView listView = (ListView) parent;
//            HashMap<String, Object> data = (HashMap<String, Object>) listView.getItemAtPosition(position);
//            String personid = data.get("id").toString();
//            Toast.makeText(getApplicationContext(), personid, Toast.LENGTH_SHORT).show();
//        }
//    }

    private class JazzAdapter extends ArrayAdapter<Person> {

        public JazzAdapter(List<Person> artists) {
            super(MainActivity.this, R.layout.item, artists);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            Person person = getItem(position);
            View v;
            ViewHolder holder;

            if (convertView == null) {
                v = LayoutInflater.from(getContext()).inflate(R.layout.item,null);
                holder = new ViewHolder();

                TextView tv = (TextView) v.findViewById(R.id.name);
                holder.name = tv;

                tv = (TextView) v.findViewById(R.id.phone);
                holder.phone = tv;

                tv = (TextView) v.findViewById(R.id.amount);
                holder.amount = tv;

                v.setTag(holder);
            } else {
                v = convertView;
                holder =(ViewHolder) v.getTag();
            }

            List<Person> personstemp = new ArrayList<>();
            personstemp = persons;
            int i =1;

            String name = getItem(position).name;
            String amount = getItem(position).amount;
            String phone = getItem(position).phone;

            holder.name.setText(name);
            holder.phone.setText(phone);
            holder.amount.setText(amount);
            return v;
        }
    }

    private class ViewHolder {
        public TextView name;
        public TextView phone;
        public TextView amount;
    }

    private class Person{
        public String getId() {
            return id;
        }
        private String id;

        public String getName() {
            return name;
        }

        private String name;

        public String getPhone() {
            return phone;
        }
        private String phone;

        public String getAmount() {
            return amount;
        }
        private String amount;
    }
}

package tchatha.tading;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity {
    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<Model> toDoItems = new ArrayList<Model>();
    ArrayList<String> listItems=new ArrayList<String>();
    CustomAdapter adapter2;
    EditText input;
    Button addBtn;
    String key = "key";
    ListView lv;
//    final MediaPlayer mp = MediaPlayer.create(this. R.raw.soho);

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<String> adapter;



    //RECORDING HOW MANY TIMES THE BUTTON HAS BEEN CLICKED
    int clickCounter=0;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
//        if(listItems != null){
//            for(int i = 0; i<listItems.size(); i++){
//                System.out.print(listItems.get(i));
//            }
//        }
        if (icicle != null) {
            ArrayList<String> values = icicle.getStringArrayList(key);
            listItems = values;
            for (int i = 0; i < listItems.size(); i++) {
                toDoItems.add(new Model(listItems.get(i), 0));
            }


            if (values != null) {
                adapter2 = new CustomAdapter(this, toDoItems);
                adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, values);
            }
        } else {
            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,
                    listItems);
            adapter2 = new CustomAdapter(this, toDoItems);

        }


        setContentView(R.layout.activity_main);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        input = (EditText) findViewById(R.id.input);
        addBtn = (Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        if(!(input.getText().toString().equals(""))) {
                            addItems(view, input.getText().toString());
                            InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            mgr.hideSoftInputFromWindow(input.getWindowToken(), 0);
                            input.setText("");
                        }
                    }
                }
        );

//        setListAdapter(adapter2);
        lv = (ListView) findViewById(R.id.list);

        lv.setAdapter(adapter2);
    }

    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
    public void addItems(View v, String text) {
        listItems.add(text);
        toDoItems.add(new Model(text, 0));
        adapter.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();
    }


    public void onSaveInstanceState(Bundle savedState){
        super.onSaveInstanceState(savedState);

        savedState.putStringArrayList(key, listItems);

    }
}
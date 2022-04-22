package com.example.dangbbang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Diabetes_level_MyRecyclerAdapter.Diabetes_level_MyRecyclerViewClickListener{

    ArrayList<Diabetes_level_ItemData> dataList = new ArrayList<>();
    Diabetes_level_MyRecyclerAdapter adapter = new Diabetes_level_MyRecyclerAdapter(dataList);

    private DrawerLayout drawerLayout;
    private View drawerView;
    TextView tv1, tv2;

    // 현재 날짜 표시
    long mNow = System.currentTimeMillis();
    Date mReDate = new Date(mNow);
    SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat tFormat = new SimpleDateFormat("HH:mm:ss");
    String formatDate = dFormat.format(mReDate);
    String formatTime = tFormat.format(mReDate);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnClickListener(this);




        /*
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerView = (View)findViewById(R.id.drawer);

        Button btn_open = (Button)findViewById(R.id.btn_open);
        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(drawerView);
            }
        });
        // 여기까지가 activity_main에서 '좌측네비' 버튼을 누르게되면 (id가 btn_open이니까)
        // 이 값이 눌렸을 때 네비게이션 메뉴가 열리는 것.

        // 위에 btn_open이 하나의 뭉탱이라 생각하고 아래에 비슷한거 하나 더 추가하기
        // 위에는 open버튼을 눌렀을 경우, 아래는 close버튼을 눌렀을 경우
        Button btn_close = (Button)findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
            }
        });


        drawerLayout.setDrawerListener(listener);
        drawerView.setOnTouchListener(new View.OnTouchListener() {  // 에러뜨면 alt+enter 눌러서 자동 import method
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;    // 기본적으로 false로 되어있는데 true로 바꾸기
            }
        });

         */

        // 현재 날짜 표시
        tv1 = (TextView) findViewById(R.id.show_date);
        tv1.setText(formatDate);

        tv2 = (TextView) findViewById(R.id.show_time);
        tv2.setText(formatTime);


    }

    /*
    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        // DrawerListener는 DrawerLayout을 왼쪽이나 오른쪽으로 슬라이드했을 때 이 곳에서 상태값을 받아온다.
        // 예를 들어 onDrawerSlide는 drawer을 슬라이드했을 때, onDrawerStateChanged는 상태가 바뀌었을 때 적용되는 것.
        // DrawerListener는 필수는 아니고 만들어놓으면 좋음.

        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };
     */





    // Diabates_level 관련 부분
    //리스트 클릭 이벤트
    @Override
    public void onItemClicked(int position) {
        // Toast.makeText(getApplicationContext(), ""+(position+1), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClicked(int position) {
        adapter.remove(position);
        Toast.makeText(getApplicationContext(),"리스트 삭제", Toast.LENGTH_SHORT).show();
    }



    // 다이얼로그를 사용해 list_insert.xml과 연결
    public void onMenuInsert(View view) {

        final View innerView = getLayoutInflater().inflate(R.layout.diabetes_level_list_insert, null);
        final Dialog mDialog = new Dialog(this);
        mDialog.setTitle("Title");
        mDialog.setContentView(innerView);
        mDialog.setCancelable(true);

        WindowManager.LayoutParams params = mDialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mDialog.getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

        final EditText editTime = mDialog.findViewById(R.id.dli_add_time);
        final EditText editBeforeN = mDialog.findViewById(R.id.dli_add_before_num);
        final EditText editAfterN = mDialog.findViewById(R.id.dli_add_after_num);
        Button btn_go = mDialog.findViewById(R.id.btn_go);
        Button btn_cancel = mDialog.findViewById(R.id.btn_cancel);

        //입력버튼을 누르면 실행되는 이벤트
        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myTime = editTime.getText().toString();
                String myBeforeN = editBeforeN.getText().toString();
                String myAfterN = editAfterN.getText().toString();

                dataList.add(new Diabetes_level_ItemData(myTime, myBeforeN, myAfterN));
                // Toast.makeText(getApplicationContext(), myTime, Toast.LENGTH_SHORT).show();
                mDialog.dismiss();
            }
        });

        // 취소를 누르면 다이얼로그 종료
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });

        mDialog.show();
        adapter.notifyDataSetChanged();     // 데이터 변경 후 adapter의 notifyDataSetChanged() 호출 필수
    }
}
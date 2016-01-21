package heshida.haihong.com.heshida.Comment;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import heshida.haihong.com.heshida.R;

public class CommentSchoolActivity extends Activity implements OnClickListener {
    public static CommentSchoolActivity self;
    TextView tv_expression, tv_send_msg;
    EditText et_id;
    // --表情--//
    int columns = 6, rows = 3, pageExpressionCount = 3 * 6 - 1;
    ViewPager vp_id;
    LinearLayout ll_expression;
    public LinearLayout ll_vp_selected_index;
    ArrayList<GridView> grids;
    MyPagerAdapter myPagerAdapter;
    public static List<Expression> expressionList = new ArrayList<Expression>();
    //--消息列表--//
    ChatMessageListAdapter chatMessageListAdapter;
    List<ChatMessage> l_msg = new ArrayList<ChatMessage>();
    ListView lv_id;
    Button mCommentBackButton;

    TextView tv_chat_title;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_comment_school);
        self = this;
        tv_expression = (TextView) findViewById(R.id.tv_expression);
        tv_expression.setOnClickListener(this);
        tv_send_msg = (TextView) findViewById(R.id.tv_send_msg);
        tv_send_msg.setOnClickListener(this);

        ll_vp_selected_index = (LinearLayout)findViewById(R.id.ll_vp_selected_index);
        ll_expression = (LinearLayout) findViewById(R.id.ll_expression);
        vp_id = (ViewPager)findViewById(R.id.vp_id);
        vp_id.setOnPageChangeListener(new MyOnPageChangeListener());

        et_id = (EditText)findViewById(R.id.et_id);
        lv_id = (ListView)findViewById(R.id.lv_id);

        tv_chat_title = (TextView)findViewById(R.id.tv_chat_title);
        mCommentBackButton = (Button) findViewById(R.id.comment_backbutton);
        initListener();
    }

    private void initListener() {
        mCommentBackButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(CommentSchoolActivity.self,"back",Toast.LENGTH_LONG).show();
                finish();
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        if (null == myPagerAdapter) {
            initExpression();
            List<List<Expression>> lists = initGridViewData();//填充GridView数据
            grids = new ArrayList<GridView>();
            int gv_padding_lr = (int) getResources().getDimension(R.dimen.chat_gv_padding_lr);
            int gv_padding_bt = (int) getResources().getDimension(R.dimen.chat_gv_padding_bt);
            int gv_spacing = (int) getResources().getDimension(R.dimen.chat_gv_spacing);
            int chat_dot_margin_lr = (int) getResources().getDimension(R.dimen.chat_dot_margin_lr);
            int chat_dot_wh = (int) getResources().getDimension(R.dimen.chat_dot_wh);
            for (int i = 0; i < lists.size(); i++) {
                List<Expression> l = lists.get(i);
                if (null != l) {
                    //--生成当前GridView--//
                    final GridView gv = new GridView(this);
                    gv.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
                    gv.setNumColumns(columns);
                    gv.setGravity(Gravity.CENTER);
                    gv.setPadding(gv_padding_lr, gv_padding_bt, gv_padding_lr,0);
                    gv.setHorizontalSpacing(gv_spacing);
                    gv.setVerticalSpacing(gv_spacing);
                    ExpressionImageAdapter expressionImageAdapter = new ExpressionImageAdapter(this, l);
                    gv.setAdapter(expressionImageAdapter);
                    //--点击列表事件处理--//
                    gv.setOnItemClickListener(new OnItemClickListener() {
                        /* (non-Javadoc)
                         * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
                         */
                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
                            Expression e = (Expression)gv.getItemAtPosition(arg2);
                            Log.v("_____________", "点击表情_"+e.code);
                            int index = et_id.getSelectionStart();
                            Editable edit = et_id.getEditableText();//获取EditText的文字
                            String content_all = edit.toString();
                            String content_forward = content_all.substring(0, index);
                            Log.v("_____________", "content_all = "+content_all+"|content_forward = " + content_forward+"|");
                            String reg = "\\[#[1-9][0-9]?\\]";
                            if(e.getDrableId()<0){//点击删除按钮
                                if(index>0){
                                    boolean delExpression = false;
                                    Pattern p  = Pattern.compile(reg);
                                    Matcher matcher = p.matcher(content_forward);
                                    //因为这里表情代码最长为5，所以这里减5
                                    boolean found = false;
                                    if(content_forward.length()>=4){//如果光标前字符少于4个，说明不可能为表情
                                        if(content_forward.length()==4){
                                            found = matcher.find(content_forward.length()-4);
                                        }else{
                                            found = matcher.find(content_forward.length()-5);
                                        }
                                        if(found){
                                            String flag = matcher.group();
                                            if(content_forward.substring(content_forward.length()-flag.length(), content_forward.length()).equals(flag)){
                                                delExpression = true;
                                                edit.delete(index-flag.length(), index);
                                            }
                                        }
                                    }
                                    if(!delExpression){
                                        edit.delete(index-1, index);
                                    }
                                }
                            }else{
                                ImageSpan imageSpan = new ImageSpan(BitmapFactory.decodeResource(getResources(), e.drableId));
                                SpannableString spannableString = new SpannableString(e.code);
                                spannableString.setSpan(imageSpan, 0, e.code.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                if(index <0 || index >= edit.length()){
                                    edit.append(spannableString);
                                }else{
                                    edit.insert(index, spannableString);
                                }
                            }
                        }
                    });
                    grids.add(gv);
                    //--生成索引图--//
                    ImageView iv = new ImageView(this);
                    android.widget.LinearLayout.LayoutParams lp = new android.widget.LinearLayout.LayoutParams(chat_dot_wh, chat_dot_wh);
                    lp.leftMargin = chat_dot_margin_lr;
                    lp.rightMargin = chat_dot_margin_lr;
                    iv.setLayoutParams(lp);
                    if (i == 0) {
                        iv.setBackgroundResource(R.drawable.page_focused);
                    } else {
                        iv.setBackgroundResource(R.drawable.page_unfocused);
                    }
                    ll_vp_selected_index.addView(iv);
                }
            }
            myPagerAdapter = new MyPagerAdapter(grids);
            vp_id.setAdapter(myPagerAdapter);
        }

        //tv_chat_title.setText(Html.fromHtml("<img src=\""+2130837523+"\" />", imageGetter_resource, null));
    }

    /**
     * 填充GridView所需要的数据
     */
    private List<List<Expression>> initGridViewData(){
        List<List<Expression>> lists = new ArrayList<List<Expression>>();
        List<Expression> list = null;
        for (int i = 0; i < expressionList.size(); i++) {
            if(i%pageExpressionCount==0){//一页数据已填充完成
                if(null!=list){
                    list.add(new Expression(-1, "backSpace"));//添加删除键
                    lists.add(list);
                }
                list = new ArrayList<Expression>();
            }
            list.add(expressionList.get(i));
            //最后一个表情，并且不是当前页最后一个表情时，后面添加删除键
            if(i>=expressionList.size()-1){
                list.add(new Expression(-1, "backSpace"));//添加删除键
                lists.add(list);
            }
        }
        return lists;
    }

    /**
     * 初始化表情信息
     */
    public static void initExpression() {
        Expression exp1 = new Expression(R.drawable.e_1, "[#1]");
        Expression exp2 = new Expression(R.drawable.e_2, "[#2]");
        Expression exp3 = new Expression(R.drawable.e_3, "[#3]");
        Expression exp4 = new Expression(R.drawable.e_4, "[#4]");
        Expression exp5 = new Expression(R.drawable.e_5, "[#5]");
        Expression exp6 = new Expression(R.drawable.e_6, "[#6]");
        Expression exp7 = new Expression(R.drawable.e_7, "[#7]");
        Expression exp8 = new Expression(R.drawable.e_8, "[#8]");
        Expression exp9 = new Expression(R.drawable.e_9, "[#9]");
        Expression exp10 = new Expression(R.drawable.e_10, "[#10]");

        Expression exp11 = new Expression(R.drawable.e_11, "[#11]");
        Expression exp12 = new Expression(R.drawable.e_12, "[#12]");
        Expression exp13 = new Expression(R.drawable.e_13, "[#13]");
        Expression exp14 = new Expression(R.drawable.e_14, "[#14]");
        Expression exp15 = new Expression(R.drawable.e_15, "[#15]");
        Expression exp16 = new Expression(R.drawable.e_16, "[#16]");
        Expression exp17 = new Expression(R.drawable.e_17, "[#17]");
        Expression exp18 = new Expression(R.drawable.e_18, "[#18]");
        Expression exp19 = new Expression(R.drawable.e_19, "[#19]");
        Expression exp20 = new Expression(R.drawable.e_20, "[#20]");

        Expression exp21 = new Expression(R.drawable.e_21, "[#21]");
        Expression exp22 = new Expression(R.drawable.e_22, "[#22]");
        Expression exp23 = new Expression(R.drawable.e_23, "[#23]");
        Expression exp24 = new Expression(R.drawable.e_24, "[#24]");
        Expression exp25 = new Expression(R.drawable.e_25, "[#25]");
        Expression exp26 = new Expression(R.drawable.e_26, "[#26]");
        Expression exp27 = new Expression(R.drawable.e_27, "[#27]");
        Expression exp28 = new Expression(R.drawable.e_28, "[#28]");
        Expression exp29 = new Expression(R.drawable.e_29, "[#29]");
        Expression exp30 = new Expression(R.drawable.e_30, "[#30]");

        Expression exp31 = new Expression(R.drawable.e_31, "[#31]");
        Expression exp32 = new Expression(R.drawable.e_32, "[#32]");
        Expression exp33 = new Expression(R.drawable.e_33, "[#33]");
        Expression exp34 = new Expression(R.drawable.e_34, "[#34]");
        Expression exp35 = new Expression(R.drawable.e_35, "[#35]");
        Expression exp36 = new Expression(R.drawable.e_36, "[#36]");
        Expression exp37 = new Expression(R.drawable.e_37, "[#37]");
        Expression exp38 = new Expression(R.drawable.e_38, "[#38]");
        Expression exp39 = new Expression(R.drawable.e_39, "[#39]");
        Expression exp40 = new Expression(R.drawable.e_40, "[#40]");

        Expression exp41 = new Expression(R.drawable.e_41, "[#41]");
        Expression exp42 = new Expression(R.drawable.e_42, "[#42]");
        Expression exp43 = new Expression(R.drawable.e_43, "[#43]");
        Expression exp44 = new Expression(R.drawable.e_44, "[#44]");
        Expression exp45 = new Expression(R.drawable.e_45, "[#45]");
        Expression exp46 = new Expression(R.drawable.e_46, "[#46]");
        Expression exp47 = new Expression(R.drawable.e_47, "[#47]");
        Expression exp48 = new Expression(R.drawable.e_48, "[#48]");
        Expression exp49 = new Expression(R.drawable.e_49, "[#49]");
        Expression exp50 = new Expression(R.drawable.e_50, "[#50]");

        Expression exp51 = new Expression(R.drawable.e_51, "[#51]");
        Expression exp52 = new Expression(R.drawable.e_52, "[#52]");
        Expression exp53 = new Expression(R.drawable.e_53, "[#53]");
        Expression exp54 = new Expression(R.drawable.e_54, "[#54]");
        Expression exp55 = new Expression(R.drawable.e_55, "[#55]");
        Expression exp56 = new Expression(R.drawable.e_56, "[#56]");
        Expression exp57 = new Expression(R.drawable.e_57, "[#57]");
        Expression exp58 = new Expression(R.drawable.e_58, "[#58]");
        Expression exp59 = new Expression(R.drawable.e_59, "[#59]");
        Expression exp60 = new Expression(R.drawable.e_60, "[#60]");

        Expression exp61 = new Expression(R.drawable.e_61, "[#61]");
        Expression exp62 = new Expression(R.drawable.e_62, "[#62]");
        Expression exp63 = new Expression(R.drawable.e_63, "[#63]");
        Expression exp64 = new Expression(R.drawable.e_64, "[#64]");

        expressionList.add(exp1);
        expressionList.add(exp2);
        expressionList.add(exp3);
        expressionList.add(exp4);
        expressionList.add(exp5);
        expressionList.add(exp6);
        expressionList.add(exp7);
        expressionList.add(exp8);
        expressionList.add(exp9);
        expressionList.add(exp10);

        expressionList.add(exp11);
        expressionList.add(exp12);
        expressionList.add(exp13);
        expressionList.add(exp14);
        expressionList.add(exp15);
        expressionList.add(exp16);
        expressionList.add(exp17);
        expressionList.add(exp18);
        expressionList.add(exp19);
        expressionList.add(exp20);

        expressionList.add(exp21);
        expressionList.add(exp22);
        expressionList.add(exp23);
        expressionList.add(exp24);
        expressionList.add(exp25);
        expressionList.add(exp26);
        expressionList.add(exp27);
        expressionList.add(exp28);
        expressionList.add(exp29);
        expressionList.add(exp30);

        expressionList.add(exp31);
        expressionList.add(exp32);
        expressionList.add(exp33);
        expressionList.add(exp34);
        expressionList.add(exp35);
        expressionList.add(exp36);
        expressionList.add(exp37);
        expressionList.add(exp38);
        expressionList.add(exp39);
        expressionList.add(exp40);

        expressionList.add(exp41);
        expressionList.add(exp42);
        expressionList.add(exp43);
        expressionList.add(exp44);
        expressionList.add(exp45);
        expressionList.add(exp46);
        expressionList.add(exp47);
        expressionList.add(exp48);
        expressionList.add(exp49);
        expressionList.add(exp50);

        expressionList.add(exp51);
        expressionList.add(exp52);
        expressionList.add(exp53);
        expressionList.add(exp54);
        expressionList.add(exp55);
        expressionList.add(exp56);
        expressionList.add(exp57);
        expressionList.add(exp58);
        expressionList.add(exp59);
        expressionList.add(exp60);

        expressionList.add(exp61);
        expressionList.add(exp62);
        expressionList.add(exp63);
        expressionList.add(exp64);
    }

    private void sendMsg(){
        ChatMessage cm = new ChatMessage();
        cm.chatMsg = et_id.getText().toString();
        cm.nickName = "nickName"+ l_msg.size()%2;
        cm.userID = l_msg.size()%2;
        Log.v("_____________", "msg="+cm.chatMsg+"|nickName="+cm.nickName+"|userID="+cm.userID);
        this.l_msg.add(cm);
        if(null==chatMessageListAdapter){
            chatMessageListAdapter = new ChatMessageListAdapter(l_msg, this);
            lv_id.setAdapter(chatMessageListAdapter);
        }else{
            chatMessageListAdapter.setL(l_msg);
            chatMessageListAdapter.notifyDataSetChanged();
        }
        et_id.setText("");
    }
    /**
     * 发送表情
     */
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.tv_expression:
                if (ll_expression.getVisibility() == View.GONE) {
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                    ll_expression.setVisibility(View.VISIBLE);
                } else {
                    ll_expression.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_send_msg:
                sendMsg();
                break;

        }
    }
}
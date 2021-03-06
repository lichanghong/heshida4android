package group.haihong.com.stu.Comment;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import group.haihong.com.stu.R;

/**
 * 聊天消息列表Adapter
 * @author daobo.yuan
 */
public class ChatMessageListAdapter extends BaseAdapter {
	Context context;
	List<ChatMessage> l;
	LayoutInflater lfInflater;
	int expression_wh = -1;
	public ChatMessageListAdapter(List<ChatMessage> l,Context context) {
		// TODO Auto-generated constructor stub
		this.l = l;
		this.context = context;
		lfInflater = LayoutInflater.from(this.context);
		expression_wh = (int)this.context.getResources().getDimension(R.dimen.chat_expression_wh);
	}
	
	public void setL(List<ChatMessage> l) {
		this.l = l;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return this.l.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return this.l.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	final Html.ImageGetter imageGetter_resource = new Html.ImageGetter() {
		public Drawable getDrawable(String source) {
			Drawable drawable = null;
			int rId = Integer.parseInt(source);
			drawable = context.getResources().getDrawable(rId);
			drawable.setBounds(0, 0, expression_wh,expression_wh);//设置显示的图像大小
			return drawable;
		};
	};
	public static String replaceSpaceToCode(String str){
		String rt = str.replace(" ", "&nbsp;");
		rt = rt.replace("\n", "<br/>");
		
		return rt;
	}
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ChatMessage chatMessage = l.get(position);
		ViewHolder holder= null;
		if(null==convertView){
			convertView = lfInflater.inflate(R.layout.chat_msg_listview_item, null);
			holder = new ViewHolder();
			holder.rl_msg_friend = (RelativeLayout)convertView.findViewById(R.id.rl_msg_friend);
			holder.rl_msg_mine = (RelativeLayout)convertView.findViewById(R.id.rl_msg_mine);
			holder.raiv_faceico_friend = (ImageView)convertView.findViewById(R.id.raiv_faceico_friend);
			holder.raiv_faceico_mine = (ImageView)convertView.findViewById(R.id.raiv_faceico_mine);
			holder.tv_msg_content_friend = (TextView)convertView.findViewById(R.id.tv_msg_content_friend);
			holder.tv_msg_content_mine = (TextView)convertView.findViewById(R.id.tv_msg_content_mine);
			holder.tv_mine_nickname = (TextView)convertView.findViewById(R.id.tv_mine_nickname);
			holder.tv_friend_nickname = (TextView)convertView.findViewById(R.id.tv_friend_nickname);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		
		String content = msgConvert(replaceSpaceToCode(chatMessage.chatMsg));
		Log.v("_____________", "3content = " + content);
		if(chatMessage.userID==0){
			holder.rl_msg_friend.setVisibility(View.GONE);
			holder.rl_msg_mine.setVisibility(View.VISIBLE);
			holder.raiv_faceico_mine.setBackgroundResource(R.drawable.male);
			holder.tv_msg_content_mine.setText(Html.fromHtml(content, imageGetter_resource, null));
			//holder.tv_msg_content_mine.setText(content);
			holder.tv_mine_nickname.setText(chatMessage.nickName);
		}else{
			holder.rl_msg_friend.setVisibility(View.VISIBLE);
			holder.rl_msg_mine.setVisibility(View.GONE);
			holder.raiv_faceico_friend.setBackgroundResource(R.drawable.female);
			holder.tv_msg_content_friend.setText(Html.fromHtml(content, imageGetter_resource, null));
			//holder.tv_msg_content_friend.setText(content);
			holder.tv_friend_nickname.setText(chatMessage.nickName);
		}
		return convertView;
	}
	
	private String msgConvert(String content){
		Log.v("_____________", "1content = " + content);
		for (int i = 0; i < CommentSchoolActivity.expressionList.size(); i++) {
			content = content.replace(CommentSchoolActivity.expressionList.get(i).code, "<img src=\""+CommentSchoolActivity.expressionList.get(i).drableId+"\" />");
		}
		Log.v("_____________", "2content = " + content);
		return content;
	}
	
	class ViewHolder{
		RelativeLayout rl_msg_mine,rl_msg_friend;//显示消息的外层布局
		ImageView raiv_faceico_mine,raiv_faceico_friend;//头像
		TextView tv_mine_nickname,tv_friend_nickname;//消息内容
		TextView tv_msg_content_mine,tv_msg_content_friend;
	}
}
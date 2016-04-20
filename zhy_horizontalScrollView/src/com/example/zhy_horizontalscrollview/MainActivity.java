package com.example.zhy_horizontalscrollview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zhy_horizontalscrollview.MyHorizontalScrollView.CurrentImageChangeListener;
import com.example.zhy_horizontalscrollview.MyHorizontalScrollView.OnItemClickListener;
import com.example.zhy_horizontalscrollview.MyHorizontalScrollView.OnLoadListener;

public class MainActivity extends Activity implements OnLoadListener
{

	private MyHorizontalScrollView mHorizontalScrollView;
	private HorizontalScrollViewAdapter mAdapter;
	private ImageView mImg;
	private List<Integer> mDatas = new ArrayList<Integer>(Arrays.asList(
			R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d,
			R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h,
			R.drawable.l));

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		mImg = (ImageView) findViewById(R.id.id_content);

		mHorizontalScrollView = (MyHorizontalScrollView) findViewById(R.id.id_horizontalScrollView);
		mHorizontalScrollView.setOnLoadListener(this);
		mAdapter = new HorizontalScrollViewAdapter(this, mDatas);
		//添加滚动回调
		mHorizontalScrollView
				.setCurrentImageChangeListener(new CurrentImageChangeListener()
				{
					@Override
					public void onCurrentImgChanged(int position,
							View viewIndicator)
					{
						mImg.setImageResource(mDatas.get(position));
						viewIndicator.setBackgroundColor(Color
								.parseColor("#AA024DA4"));
						if(position==mDatas.size()-4-1){
							Toast.makeText(MainActivity.this, "dd", 1).show();
						}
					}
				});
		//添加点击回调
		mHorizontalScrollView.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onClick(View view, int position)
			{
				mImg.setImageResource(mDatas.get(position));
				view.setBackgroundColor(Color.parseColor("#AA024DA4"));
			}
		});
		//设置适配器
		mHorizontalScrollView.initDatas(mAdapter);
	}

	@Override
	public void onLoad() {
		// TODO Auto-generated method stub
		loadData(MyHorizontalScrollView.LOAD);
		Toast.makeText(this, "d", 1).show();
	}
	
	public void loadData(final int what) {
		//上拉加载回调
		Message msg = handler.obtainMessage();
		msg.what = what;
		handler.sendMessage(msg);
	}
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			List<String> result = (List<String>) msg.obj;
			switch (msg.what) {
			case MyHorizontalScrollView.REFRESH:
				//mHorizontalScrollView.onRefreshComplete();
				//vouchingList.clear();
				//vouchingList.addAll(result);
				break;
			case MyHorizontalScrollView.LOAD:
				mHorizontalScrollView.onLoadComplete();
				//nowpage++;
				//loading.LoadMask("", activity, flMain);
				//获取
				/*tradeControl.getCardTradeList(nowpage,MemberInfoFragment.this.card.getCardNum(), new CardTradeCallBack() {
					
					@Override
					public void cardTradeList(int code, List<Trade> cardTradeList2,
							int allPage) {
						loading.removeMask();
						switch (code) {
						case 1:
							for(int a=0;a<cardTradeList2.size();a++){
								MemberInfoFragment.this.cardTradeList.add(cardTradeList2.get(a));
							}
							//tradeListAdapter=new TradeListAdapter(activity, MemberInfoFragment.this.cardTradeList,1);
							//cardTradeAutoListView.setAdapter(tradeListAdapter);
							tradeListAdapter.notifyDataSetChanged();
							break;

						default:
							break;
						}
						
						
					}
				});*/
				break;
			}
			/*lstv.setResultSize(result.size());
			adapter.notifyDataSetChanged();*/
		};
	};

}

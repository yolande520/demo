package com.bankManagementSystem.main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bankManagementSystem.util.BaseActivity;

/*
 用户登录进去的主界面                        
 */
public class FinanicalCalActivity extends BaseActivity {
	private int images[] = { R.drawable.deposit_calc, R.drawable.loan_calc,
			R.drawable.nationaldebt_calc, R.drawable.foreign_exchange_calc,
			R.drawable.mortgage };
	private String functions[] = { "存款计算器", "贷款计算器", "国债收益计算", "外汇兑换计算",
			"按揭贷款计算" };
	private GridView giGridView;
	private Button btn;
	private TextView tv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.uesrfunction);
		setupView();
		giGridView.setAdapter(new GridViewAdapter(this));

		giGridView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						Intent intent = new Intent();
						switch (position) {
						case 0:
							intent.setClass(FinanicalCalActivity.this,
									FinanicalLoanCalActivity.class);

							break;
						case 1:
							intent.setClass(FinanicalCalActivity.this,
									FinanicalDepositCalActivity.class);
							break;
						case 2:
							intent.setClass(FinanicalCalActivity.this,
									FinanicalGovernmentLoanCalActivity.class);
							break;
						case 3:
							intent.setClass(FinanicalCalActivity.this,
									FinanicalForeignCalActivity.class);
							break;
						case 4:
							intent.setClass(FinanicalCalActivity.this,
									FinanicalMortgageCalActivity.class);
							break;
						}
						FinanicalCalActivity.this.startActivity(intent);

					}
				});

		btn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent backIntent = new Intent();
				backIntent.setClass(FinanicalCalActivity.this,
						UserActivity.class);
				FinanicalCalActivity.this.startActivity(backIntent);

			}
		});

		tv.setText("理财计算器");

	}

	private void setupView() {
		giGridView = (GridView) findViewById(R.id.gGridView);
		btn = (Button) findViewById(R.id.btn_back);
		tv = (TextView) findViewById(R.id.tv_title);
	}

	static class ViewHolder {
		TextView tv;
		ImageView iv;

	}

	public class GridViewAdapter extends BaseAdapter {
		private LayoutInflater inflater;

		public GridViewAdapter(Context context) {
			inflater = LayoutInflater.from(context);

		}

		public int getCount() {

			return images.length;
		}

		public Object getItem(int position) {

			return images[position];
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		// 这个GridView有几项，就调用几次getView()..判断有几项，是通过getCount（）方法。。
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.gv_item, null);
				holder = new ViewHolder();
				holder.tv = (TextView) convertView
						.findViewById(R.id.gv_item_appname);
				holder.iv = (ImageView) convertView
						.findViewById(R.id.gv_item_icon);
				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.tv.setText(functions[position]);
			holder.iv.setImageResource(images[position]);
			return convertView;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.containermenu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.set:
			startActivity(new Intent(Settings.ACTION_SETTINGS));

			break;

		case R.id.exit:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("你确定要退出吗？");
			builder.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							FinanicalCalActivity.this.exit();
						}
					});
			builder.setNegativeButton("取消",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {

						}
					});
			builder.create().show();

			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
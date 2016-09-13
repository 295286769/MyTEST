package com.richfinancial.qiyifinance.activity.manage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.richfinancial.qiyifinance.utils.ContactUtils;
import com.richfinancial.qiyiplus.R;

import java.util.ArrayList;
import java.util.List;

public class SortAdapter extends BaseAdapter {
	private Context context;
	private List<ContactUtils.ContactInfo> persons;
	private LayoutInflater inflater;

	public SortAdapter(Context context, List<ContactUtils.ContactInfo> persons) {
		this.context = context;
		this.persons = persons;
		this.inflater = LayoutInflater.from(context);

	}

	@Override
	public int getCount() {
		return persons.size();
	}

	@Override
	public Object getItem(int position) {
		return persons.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewholder = null;
		final ContactUtils.ContactInfo person = persons.get(position);
		if (convertView == null) {
			viewholder = new ViewHolder();
			convertView = inflater.inflate(R.layout.item_contact, null);
			viewholder.tv_tag = (TextView) convertView
					.findViewById(R.id.txtv_item_tag);
			viewholder.ck_select = (CheckBox)convertView.findViewById(R.id.ck_select_contact);
			viewholder.tv_name = (TextView) convertView.findViewById(R.id.txtv_item_name);
			viewholder.tv_phone = (TextView) convertView.findViewById(R.id.txtv_item_phone);
			convertView.setTag(viewholder);
		} else {
			viewholder = (ViewHolder) convertView.getTag();
		}
		// 获取首字母的assii值
		int selection = person.getFirstPinYin().charAt(0);
		// 通过首字母的assii值来判断是否显示字母
		int positionForSelection = getPositionForSelection(selection);
		if (position == positionForSelection) {// 相等说明需要显示字母
			viewholder.tv_tag.setVisibility(View.VISIBLE);
			viewholder.tv_tag.setText(person.getFirstPinYin());
		} else {
			viewholder.tv_tag.setVisibility(View.GONE);

		}

		if(person.isSelect()){
			viewholder.ck_select.setChecked(true);
		} else {
			viewholder.ck_select.setChecked(false);
		}

		viewholder.tv_name.setText(person.getName());
		viewholder.tv_phone.setText(person.getPhone());
		convertView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(person.isSelect()){
					person.setSelect(false);
				} else {
					person.setSelect(true);
				}
				notifyDataSetChanged();
			}
		});
		return convertView;
	}

	public List<ContactUtils.ContactInfo> getSelectedContactList(){
		List<ContactUtils.ContactInfo> list = new ArrayList<>();
		if(null!=persons && persons.size() > 0){
			for(int i = 0; i < persons.size(); i++){
				ContactUtils.ContactInfo contactInfo = persons.get(i);
				if(contactInfo.isSelect()){
					list.add(contactInfo);
				}
			}
		}
		return list;
	}

	public int getPositionForSelection(int selection) {
		for (int i = 0; i < persons.size(); i++) {
			String Fpinyin = persons.get(i).getFirstPinYin();
			char first = Fpinyin.toUpperCase().charAt(0);
			if (first == selection) {
				return i;
			}
		}
		return -1;

	}

	class ViewHolder {
		private TextView tv_tag;
		private CheckBox ck_select;
		private TextView tv_name;
		private TextView tv_phone;
	}

}

package com.richfinancial.qiyifinance.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContactUtils {

	/**
	 * 获取联系人数据
	 * 
	 * @param context
	 * @param phoneNums(已经存在的联系人电话)
	 * @return
	 */
	public List<ContactInfo> getAllContacts(Context context , List<String> phoneNums) {
		List<ContactInfo> list = new ArrayList<ContactInfo>();
		// 获取解析者
		ContentResolver resolver = context.getContentResolver();
		// 访问地址
		Uri raw_contacts = Uri.parse("content://com.android.contacts/raw_contacts");
		Uri data = Uri.parse("content://com.android.contacts/data");
		// 查询语句
		// select contact_id from raw_contacts;//1 2 3 4
		// select mimetype,data1 from view_data where raw_contact_id=3;
		// Cursor cursor=resolver.query(访问地址, 返回字段 null代表全部, where 语句, 参数, 排序)
		Cursor cursor = resolver.query(raw_contacts, new String[] { "contact_id" }, null, null, null);

		while (cursor.moveToNext()) {
			// getColumnIndex根据名称查列号
			String id = cursor.getString(cursor.getColumnIndex("contact_id"));
			// 创建实例
			ContactInfo info = new ContactInfo();
			info.setId(id);
			Cursor item = resolver.query(data, new String[] { "mimetype", "data1" }, "raw_contact_id=?", new String[] { id }, null);

			while (item.moveToNext()) {
				String mimetype = item.getString(item.getColumnIndex("mimetype"));
				String data1 = item.getString(item.getColumnIndex("data1"));
				if ("vnd.android.cursor.item/name".equals(mimetype)) {
					info.setName(data1);
				} else if ("vnd.android.cursor.item/phone_v2".equals(mimetype)) {
					info.setPhone(data1);
				}
			}
			item.close();
			// 添加集合(去掉名字为空的信息)
			if(!TextUtils.isEmpty(info.getName())){
				if(null != phoneNums && phoneNums.size() > 0){
					for(String phone : phoneNums){
						if(!phone.equals(info.getPhone())){
							list.add(info);
						}
					}
				} else {
					list.add(info);
				}
			}
		}

		cursor.close();
		return list;
	}

	public class ContactInfo implements Serializable{

		private String Id;
		private String Phone;
		private String Name;
		private String PinYin;
		private String FirstPinYin;
		private boolean isSelect = false;

		public ContactInfo(){
		}

		public String getId() {
			return Id;
		}

		public void setId(String id) {
			Id = id;
		}

		public String getPhone() {
			return Phone;
		}

		public void setPhone(String phone) {
			Phone = phone;
		}

		public String getName() {
			return Name;
		}

		public void setName(String name) {
			Name = name;
		}

		public String getPinYin() {
			return PinYin;
		}

		public void setPinYin(String pinYin) {
			PinYin = pinYin;
		}

		public String getFirstPinYin() {
			return FirstPinYin;
		}

		public void setFirstPinYin(String firstPinYin) {
			FirstPinYin = firstPinYin;
		}

		public boolean isSelect() {
			return isSelect;
		}

		public void setSelect(boolean select) {
			isSelect = select;
		}
	}

}
package com.richfinancial.qiyifinance.utils;

import android.text.TextUtils;

import java.util.Comparator;

public class PinyinComparator implements Comparator<ContactUtils.ContactInfo> {

	@Override
	public int compare(ContactUtils.ContactInfo lhs, ContactUtils.ContactInfo rhs) {
		// TODO Auto-generated method stub
		return sort(lhs, rhs);
	}

	private int sort(ContactUtils.ContactInfo lhs, ContactUtils.ContactInfo rhs) {
		// 获取ascii值
		if(!TextUtils.isEmpty(lhs.getName())){
			return -1;
		}
		int lhs_ascii = lhs.getFirstPinYin().toUpperCase().charAt(0);
		int rhs_ascii = rhs.getFirstPinYin().toUpperCase().charAt(0);
		// 判断若不是字母，则排在字母之后
		if (lhs_ascii < 65 || lhs_ascii > 90)
			return -1;
		else if (rhs_ascii < 65 || rhs_ascii > 90)
			return 1;
		else
			return lhs.getPinYin().compareTo(rhs.getPinYin());
	}

}


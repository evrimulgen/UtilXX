package com.xuexiang.view.expandableview.entity;


/**
 * @author yangyu
 *	���������������ڲ���������Ʊ����ͼ�꣩
 */
public class IconItem {
	//����ͼƬ����
	public int mDrawableId;
	//�����ı�����
	public CharSequence mTitle;
	
	public IconItem(int drawableId, CharSequence title){
		this.mDrawableId = drawableId;
		this.mTitle = title;
	}
	
	public IconItem(int drawableId){
		this.mDrawableId = drawableId;
	}
	
	public IconItem(CharSequence title){
		this.mTitle = title;
	}
	
}

// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   JsonResult.java

package bean;

/**
 * ����Json���������̳и���
 *
 */
public class JsonResult
{

	public static final int CODE_ERROR = 1;
	public static final int CODE_NOT_LOGIN = 2;
	public static final int CODE_SUCCESS = 0;
	private int Code;//0:����ɹ�  1������ʧ��
	private String Info;//������Ϣ

	public JsonResult()
	{
		Code=0;
		Info="success";
	}

	public JsonResult(int i)
	{
		Code = 0;
		Info = "success";
		Code = i;
	}

	public JsonResult error(String s)
	{
		Code = 1;
		Info = s;
		return this;
	}

	public int getCode()
	{
		return Code;
	}

	public String getInfo()
	{
		return Info;
	}

	public JsonResult setCode(int i)
	{
		Code = i;
		return this;
	}

	public JsonResult setInfo(String s)
	{
		Info = s;
		return this;
	}

	public JsonResult success(String s)
	{
		Code = 0;
		Info = s;
		return this;
	}
}

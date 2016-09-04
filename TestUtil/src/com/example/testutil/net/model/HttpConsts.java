package com.example.testutil.net.model;

import com.xuexiang.app.BaseApplication;

/**
 * ������
 *
 */
public class HttpConsts {
	
	public static final String TEST_URL = "http://www.weather.com.cn/adat/sk/101010100.html";	
	/** �û���Ϣ�������ĵ�ַ*/
	public static String app_user_url = BaseApplication.app_url + "helper/User";
	/** ���ŷ�����Ϣ���������ַ*/
	public static String app_visitservice_url = BaseApplication.app_url + "helper/VisitService";
	public static String visitservice_pic_bath_url  = BaseApplication.app_url + "helper/User/VisitService/";
	
	public static String student_url = BaseApplication.app_url + "jsonweb/SendStudentInfoServlet";
	
	public static String UPLPAD_URL = BaseApplication.app_url + "UploadFileServer/UploadFileServlet";
	public static String APK_UPDATE_URL = BaseApplication.app_url + "helper/APK/1.3/helper.apk";
	public static void setAppUrl(String url) {
		BaseApplication.app_url = url;
		app_user_url = BaseApplication.app_url + "helper/User";
		app_visitservice_url = BaseApplication.app_url + "helper/VisitService";
		visitservice_pic_bath_url  = BaseApplication.app_url + "helper/User/VisitService/";
		student_url = BaseApplication.app_url + "jsonweb/SendStudentInfoServlet";
		UPLPAD_URL = BaseApplication.app_url + "UploadFileServer/UploadFileServlet";
		APK_UPDATE_URL = BaseApplication.app_url + "helper/APK/1.3/helper.apk";
	}
	
	public static final class User{
		public static final String REGISTER = "Register";
		public static final String CHECKUSER = "CheckUser";
		public static final String LOGINCHECK = "LoginCheck";
		public static final String GETUSERINFOBYLOGINNAME = "GetUserInfoByLoginname";
		public static final String UPDATENICKNAME = "UpdateNickName";
		public static final String UPDATESEX = "UpdateSex";
		public static final String UPDATESIGNATURE = "UpdateSignature";
		public static final String UPDATEPHONE = "UpdatePhone";
		public static final String UPDATEPASSWORD = "UpdatePassword";
		
	}
	
	public static final class Address{
		public static final String GETALLADDRESS = "GetAllAddress";
		public static final String ADD = "Add";
		public static final String DELETE = "Delete";
		public static final String UPDATE = "Update";
		public static final String UPDATE_ISDEFAULT = "UpdateIsdefault";
		
	}
	
	public static final class App{
		public static final String GETNEWAPPINFO = "getNewAppInfo";
		public static final String ISNEEDUPDATE = "isNeedUpdate";		
	}
	
	public static final class FeedBack {
		public static final String DELETE = "Delete";
		public static final String GET_OWN_FEEDBACKINFO_BY_PAGE = "GetOwnFeedBackInfoByPage";
		public static final String GET_ALL_FEEDBACKINFO_BY_PAGE = "GetAllFeedBackInfoByPage";
		public static final String ADD = "Add";;

	}
	
	public static final class Qiangyu{
		public static final String ADD = "Add";	
		public static final String DELETE = "Delete";
		public static final String GET_ALL_QIANGYU_BY_PAGE = "GetAllQiangYuByPage";					
		public static final String GET_OWN_QIANGYU_BY_PAGE = "GetOwnQiangYuByPage";			
		public static final String ONCLICK_LOVE = "onClickLove";	
	}
	
	public static final class VisitService{
		public static final String ADD = "Add";	
		public static final String DELETE = "Delete";	
		public static final String GET_ALL_VISITINFO_BY_PAGE = "GetAllVisitInfoByPage";					
		public static final String GET_OWN_VISITINFO_BY_PAGE = "GetOwnVisitInfoByPage";		
		public static final String GET_TYPE_VISITINFO_BY_PAGE = "GetTypeVisitInfoByPage";
		public static final String ONCLICK_LOVE = "onClickLove";	
	}
	
	public static final class Repair{
		public static final String ADD = "Add";	
		public static final String DELETE = "Delete";	
		public static final String GET_ALL_REPAIRINFO_BY_PAGE = "GetAllRepairInfoByPage";					
		public static final String GET_OWN_REPAIRINFO_BY_PAGE = "GetOwnRepairInfoByPage";		
		public static final String GET_TYPE_REPAIRINFO_BY_PAGE = "GetTypeRepairInfoByPage";
	}
	
	public static final class CommentNet{
		public static final String GETALLCOMMENT = "GetAllComment";	
		public static final String ADD = "Add";	
		public static final String DELETE = "Delete";
	}
	
	public static final class FoodShopService{		
		public static final String REGISTER = "Register";	
		public static final String DELETE = "Delete";
		public static final String GET_FOODSHOP_BY_ID = "GetFoodShopById";	
		public static final String GET_FOODSHOP_BY_TYPE_PAGE = "GetFoodShopByTypeAndPage";	
		public static final String GET_FOODSHOP_BY_PAGE = "GetFoodShopByPage";	
		public static final String GET_FOODSHOP_BY_TYPE = "GetFoodShopByType";	
		public static final String GET_FOODSHOP_BY_CITY_PAGE = "GetFoodShopByCityAndPage";	
		public static final String GET_FOODSHOP_BY_CITY_TYPE_PAGE = "GetFoodShopByCityAndTypeAndPage";
		public static final String GET_FOODSHOP_BY_CITY_TYPE = "GetFoodShopByCityAndType";
		public static final String GET_TYPEFOODSHOP_BY_COMPLEX_CONDITION = "GetTypeFoodShopByComplexCondition";		
	}
	
	public static final class FoodService{		
		public static final String ADD = "Add";	
		public static final String DELETE = "Delete";
		public static final String GET_FOODINFO_BY_ID = "GetFoodInfoById";	
		public static final String GET_FOODINFO_BY_SHOPID = "GetFoodInfoByShopId";	
		public static final String GET_FOODTYPE_BY_SHOPID = "GetFoodTypeByShopId";			
	}
	
	
	
	/**
	 * �����㲥��action
	 */
	public static final class Brodcast_Action{
		public static final String ACTION_POLL_SERVICE="com.bmob.helpertest.poll.action.MESSAGE";
	}
	
	public static final class key {
		public static final String EXTRA_POLL_MESSAGE_STRING="com.bmob.helpertest.poll.message";
		public static final String EXTRA_PICTURE="com.bmob.helpertest.db.Picture";
		public static final String EXTRA_QIANGYU="com.bmob.helpertest.adapter.AIContentAdapter.QIANGYU";
		public static final String EXTRA_USERINFO="com.bmob.helpertest.entity.UserInfo";
		public static final String EXTRA_VISITSERVICE="com.bmob.helpertest.entity.VisitInfo";
		public static final String EXTRA_FOODSHOP="com.bmob.helpertest.entity.FoodShop";
		public static final String EXTRA_BIG_PICTURE_URL = "com.bmob.helpertest.entity.BigPictureUrl";
		public static final String EXTRA_ORDERLOCAL="com.bmob.helpertest.db.OrderLocal";
	}
	
	
	public static final class Result{
		public static final String ERROR_ACCESS_SERVER="com.xuexiang.util.consts.Error.ERROR_ACCESS_SERVER";
		public static final String ERROR_READ_DATA="com.xuexiang.util.consts.Error.ERROR_READ_DATA";
		public static final String ERROR_ACCESS_TIMEOUT="com.xuexiang.util.consts.Error.ERROR_ACCESS_TIMEOUT";
		public static final String ERROR_ACCESS_OUT_OF_MEMORY="com.xuexiang.util.consts.Error.ERROR_OUT_OF_MEMORY";
		public static final String ERROR_REQUEST_SERVER_FAIL="ERROR_REQUEST_SERVER_FAIL";//����ʧ��
		public static final String ERROR_ADDRESS_FORMAT="ERROR_ADDRESS_FORMAT";
		public static final String ERROR_RECEIVE_EXCEPTION="com.xuexiang.util.consts.Error.ERROR_RECEIVE_EXCEPTION";
		
		public static final String REQUEST_SERVER_SUCCESS="REQUEST_SERVER_SUCCESS";
		public static final String FILE_NOT_FOUND_ERROR="FILE_NOT_FOUND_ERROR";
		public static final String UPLOAD_SUCCESS="UPLOAD_SUCCESS";
		public static final String UPLOAD_FAILED="UPLOAD_FAILED";
		public static final String FILE_NOT_FOUND="FILE_NOT_FOUND";
		
	}
	
	/**
	 * Handle��Ϣ��what
	 * 
	 */
	public static final class What{
		public static final int ACCESS_SERVER__FAILED=20001;
		public static final int ALREADY_CAPTURE_REQUEST_FALL=20002;
		public static final int REQUEST_TIMEOUT=20003;
		public static final int REQUEST_OUT_OF_MEMORY=20004;
		public static final int REQUEST_EXCEPTION = 20035;
		public static final int REQUEST_SUCCESS = 20050;
		public static final int REQUEST_SUCCESS_MODE = 20051;
		public static final int REQUEST_FAIL = 20052;
		public static final int CODE_CHECK = 20053;
		
		
		
	}
	
	/**
	 * fragment ID
	 * 
	 */
	public static final class fragment{
		public static final int HOME_F = 0;
		public static final int DISCOVER_F = 1;
		public static final int VISIT_F = 2;
		public static final int CART_F = 3;
		public static final int USER_F = 4;
		
	}
	
	/**
	 * �������� 
	 */
	public static final class ShopType{
		public static final String REPAIR= "repair";		
		public static final String DECORATION = "decoration";
		public static final String HOMEMAKING = "homemaking";
		public static final String FOODS = "foods";
		public static final String VISIT = "visit";		
	}
	
	/**
	 * ���ŷ������� 
	 */
	public static final class VisitType{
		/** �ҽ�*/
		public static final String PRIVATE_TEACHER= "privateteacher";	
		/** ����*/
		public static final String MANICURE = "manicure";
		/** ��Ħ*/
		public static final String MASSAGE = "massage";
		/** ���*/
		public static final String COOK = "cook";
		/** ϴ��*/
		public static final String LAUNDRY = "laundry";		
		/** ��������*/
		public static final String KEEP_CAR = "keep_car";		
		/** ����*/
		public static final String HEALTHY = "healthy";		
		/** ����*/
		public static final String OTHER = "other";		
	}
	
	/**
	 * ��ʳ��������
	 */
	public static final class FoodShopType{
		/** ��ʳ*/
		public static final String DELICIOUS_FOOD= "deliciousfood";	
		/** ����*/
		public static final String SUPERMARKET = "supermarket";
		/** ��������*/
		public static final String FRESH_FRUITS = "freshfruits";
		/** �����Ʒ*/
		public static final String DESSERT_DRINK = "Dessertdrink";
		/** ����Ʒ��*/
		public static final String CHAIN_BRAND = "Chainbrand";		
		/** �־�ר��*/
		public static final String SPECIAL_DELIVERY = "Specialdelivery";		
		/** �ʻ�*/
		public static final String FLOWER = "flower";		
		/** ��ҩ����*/
		public static final String SEND_MEDICINE = "sendmedicine";
	}
	
	/**
	 * ά������ 
	 */
	public static final class RepairType{
		/** ˮ����*/
		public static final String PLUMBER= "plumber";	
		/** ľ����*/
		public static final String WOODWORKER = "woodworker";
		/** ������*/
		public static final String MASON = "mason";
		/** ������*/
		public static final String PAINTER = "painter";
	}
	
	/**
	 * ��������
	 */
	public static final class BankType{
		/** �й���������*/
		public static final String CCB= "ccb";	
		/** �й�ũҵ����*/
		public static final String ABC = "abc";
		/** �й���������*/
		public static final String ICBC = "icbc";
		/** �й�����*/
		public static final String BOC = "boc";
		/** �й���������*/
		public static final String CMBC = "cmbc";		
		/** ��������*/
		public static final String CMB = "cmb";		
		/** ��ҵ���� */
		public static final String CIB = "cib";		
		/** ��ͨ����*/
		public static final String BCM = "bcm";		
		/**�й��������*/
		public static final String CEB = "ceb";	
		/**�㶫��չ����*/
		public static final String GDB = "gdb";	
	}
	
	
	public static final class ShopList {		
		// shoplist����������
	    public static final String[] SHOPLIST_TYPE = { "ȫ��Ʒ��", "��ʳ", "����",
					"��������", "�����Ʒ", "����Ʒ��", "�־�ר��", "�ʻ�", "��ҩ����"}; 
	    
	    // shoplist����������
	 	public static final String[] SHOPLIST_CONDITION = { "�ۺ�����", "�������", "�ٶ����",
	 			"�������", "���ͼ����", "���ͷ����" };	 	
	    // shoplist����������
	 	public static final String[] SHOPLIST_CONDITION_HTTP = { "", "salednum", "delivertime",
	 	 			"starnum", "startingprice", "deliverprice" };
	 	
	    // shoplist�д�������ɸѡ
	    public static final String[] SHOPLIST_PROMOTION = { "ɸѡ", "�׵�����", "�����Ż�", "�ۿ��Ż�",
					"��������ȯ", "��ǰ�µ��Ż�", "�����", "�������ͷ�" };	 
	    // shoplist�д�������ɸѡ
	    public static final String[] SHOPLIST_PROMOTION_HTTP = { "", "firstorder", "fullreduction", "discount",
					"vouchers", "preorder", "fullofgifts", "freedistribution" };	 
	}

	
	
}

package camlebell.com.request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import camlebell.com.Utils.Constants;
import camlebell.com.model.DishInfo;
import camlebell.com.model.KitchenStatusInfo;
import camlebell.com.model.ResultInfo;
import camlebell.com.model.WeekDishInfo;


/**
 * 一周菜单接口
 * 
 * @author sunyan
 * 
 */
public class WeekDishRequest extends BaseRequest {

	private String treeGradeId;//学校id，目前固定为5，赤壁路(山西路)小学

	public WeekDishRequest() {

	}

	public WeekDishRequest(String treeGradeId) {
		this.treeGradeId = treeGradeId;
	}

	@Override
	protected String initService() {
		return "";
	}

	@Override
	protected Object initContent() throws JSONException {
		JSONObject request = new JSONObject();
		try {
			request.put("treeGradeId", treeGradeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return request;
	}

	public String getUrl() {
		return Constants.BASE_URL+Constants.REQUEST_NAME.WEEK_MENU;
	}

	@Override
	public String toString() {
		try {
			return initContent().toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return super.toString();
	}

	/**
	 * 获取数据
	 * 
	 * @return
	 */
	public ResultInfo<WeekDishInfo> getWeekDishList() {
		ResultInfo<WeekDishInfo> result = new ResultInfo<WeekDishInfo>();

		boolean isSuccess = super.success();
		result.setSuccess(isSuccess);
		if (!isSuccess) {
			result.setrTimeOut(super.isTimeOut());
			result.setFailInfo(super.getMessage());
			return result;
		}
		JSONArray data = getDataArray();
		if (data == null || data.length() == 0) {
			return result;
		}
		List<WeekDishInfo> list = new ArrayList<WeekDishInfo>();

		for (int i = 0; i < data.length(); i++) {
			JSONObject jo = data.optJSONObject(i);
			WeekDishInfo info = new WeekDishInfo(jo);
			if (info != null) {
				list.add(info);
			}
		}
		result.setListInfo(list);

		return result;
	}

}

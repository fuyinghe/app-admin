package com.hrbwmxx.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.axis.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrbwmxx.framework.fileUpload.service.FileUploadService;
import com.hrbwmxx.framework.job.clean.CleanDataJob;
import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.framework.util.ExceptionUtil;
import com.hrbwmxx.hrbu.log.model.Log;
import com.hrbwmxx.system.dao.IAppManageMapper;
import com.hrbwmxx.system.dao.IAppTypeMapper;
import com.hrbwmxx.system.vo.AppManageCustom;
import com.hrbwmxx.system.vo.AppManageVo;
import com.hrbwmxx.system.vo.AppTypeCustom;
import com.hrbwmxx.system.vo.AppTypeVo;

@Service
public class AppManageServiceImpl implements IAppManageService {

	// APP的MAPPER
	@Autowired
	private IAppManageMapper manageMapper;
	// 类型的MAPPER
	@Autowired
	private IAppTypeMapper typeMapper;
	@Autowired
	private ExceptionUtil exceptionUtil;
	@Autowired
	private FileUploadService fileUploadService;
	@Autowired
	private CleanDataJob cleanDataJob;

	/**
	 * 查询
	 * <p>
	 * Title: queryAppListPage
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param page
	 * @param obj
	 * @return
	 * @see com.hrbwmxx.system.service.IAppManageService#queryAppListPage(com.hrbwmxx.framework.model.Page,
	 *      com.hrbwmxx.system.model.AppManage)
	 */
	@Override
	public Result queryAppListPage(Page page, AppManageCustom obj) {
		// TODO Auto-generated method stub
		AppManageVo vo = new AppManageVo();
		try {

			List<AppManageCustom> list = manageMapper.queryAppListPage(obj, page);

			int totalCount = manageMapper.queryAppListPageCount(obj, page);
			vo.setTotalCount(totalCount);
			vo.setRows(list);

		} catch (Exception e) {
			e.printStackTrace();
			vo.setErrcode("500");
			vo.setErrmsg("查询失败！");
			String classname = new Exception().getStackTrace()[1].getClassName(); // 获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); // 获取调用者的方法名
			Log rz = exceptionUtil.buildRZ(vo.getErrcode(),
					vo.getErrmsg() + "发生在" + classname + "的" + method_name + "方法上");
			exceptionUtil.addLog(rz);
		}
		return vo;
	}

	/**
	 * 单条
	 * <p>
	 * Title: queryAppValueOne
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param obj
	 * @return
	 * @see com.hrbwmxx.system.service.IAppManageService#queryAppValueOne(com.hrbwmxx.system.model.AppManage)
	 */
	@Override
	public Result queryAppValueOne(AppManageCustom obj) {
		// TODO Auto-generated method stub
		AppManageVo vo = new AppManageVo();
		try {
			AppManageCustom custom = manageMapper.queryAppValueOne(obj);
			// 判断存入那种图片
			System.out.println("type" + custom.getUrltype());
			if (custom.getUrltype().equals("2"))// 网络图片
			{
				// 读取图片信息
				Map<String, String> map_request = new HashMap<String, String>();
				if (custom.getAttachId() != null) {
					map_request.put("attachId", custom.getAttachId());
					List<Map<String, String>> list_file_pic = fileUploadService.queryAttrList(map_request);
					custom.setList_file_pic(list_file_pic);
				}
			}
			vo.setCustom(custom);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			vo.setErrcode("500");
			vo.setErrmsg("查询失败！");
			String classname = new Exception().getStackTrace()[1].getClassName(); // 获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); // 获取调用者的方法名
			Log rz = exceptionUtil.buildRZ(vo.getErrcode(),
					vo.getErrmsg() + "发生在" + classname + "的" + method_name + "方法上");
			exceptionUtil.addLog(rz);
		}
		return vo;
	}

	/**
	 * 保存
	 * <p>
	 * Title: saveAppValue
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param obj
	 * @return
	 * @see com.hrbwmxx.system.service.IAppManageService#saveAppValue(com.hrbwmxx.system.model.AppManage)
	 */
	@Override
	public Result saveAppValue(AppManageCustom obj) {
		// TODO Auto-generated method stub
		AppManageVo vo = new AppManageVo();
		try {
			obj.setId(UUID.randomUUID().toString().replace("-", ""));
			obj.setOmit("1");
			if (obj.getIspay().equals("1") && !StringUtils.isEmpty(obj.getPaytype())) {
				obj.setLink("app-api/bmjf/pay?paytype=" + obj.getPaytype());
			}
			// 判断存入那种图片
			System.out.println("kkk" + obj.getAttachId() + "type" + obj.getUrltype());
			if (obj.getUrltype().equals("2"))// 网络图片
			{
				// 根据ATTACHID查询到网络路径
				Map<String, String> map_request = new HashMap<String, String>();
				if (obj.getAttachId() != null) {
					map_request.put("attachId", obj.getAttachId());
					List<Map<String, String>> list_file_attachment = fileUploadService.queryAttrList(map_request);
					obj.setIcon(list_file_attachment.get(0).get("downloadUrl"));
				}
			}
			// 更新文件表的文件state为1
			fileUploadService.updateFileStateByIds(obj.getAttachId(), "1");
			manageMapper.saveAppValue(obj);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			vo.setErrcode("500");
			vo.setErrmsg("操作失败！");
			String classname = new Exception().getStackTrace()[1].getClassName(); // 获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); // 获取调用者的方法名
			Log rz = exceptionUtil.buildRZ(vo.getErrcode(),
					vo.getErrmsg() + "发生在" + classname + "的" + method_name + "方法上");
			exceptionUtil.addLog(rz);
		}
		return vo;
	}

	/**
	 * 修改
	 * <p>
	 * Title: updateAppValue
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param obj
	 * @return
	 * @see com.hrbwmxx.system.service.IAppManageService#updateAppValue(com.hrbwmxx.system.model.AppManage)
	 */
	@Override
	public Result updateAppValue(AppManageCustom obj) {
		// TODO Auto-generated method stub
		AppManageVo vo = new AppManageVo();
		try {
			if (obj.getIspay() != null && !"".equals(obj.getIspay())) {
				if (obj.getIspay().equals("1") && !StringUtils.isEmpty(obj.getPaytype())) {
					obj.setLink("app-api/bmjf/pay?paytype=" + obj.getPaytype());
				}
			}
			if (obj.getUrltype() != null && !"".equals(obj.getUrltype())) {
				if (obj.getUrltype().equals("2"))// 网络图片
				{

					// 根据ATTACHID查询到网络路径
					Map<String, String> map_request = new HashMap<String, String>();
					if (obj.getAttachId() != null) {
						// 删除原来有的图片
						// 找到原来的
						AppManageCustom app = new AppManageCustom();
						app.setId(obj.getId());
						AppManageCustom objold = manageMapper.queryAppValueOne(app);
						if (objold.getAttachId() != null && objold.getAttachId().equals("")) {
							cleanDataJob.deletePicOrAttachmentfile(objold.getAttachId());
						}
						map_request.put("attachId", obj.getAttachId());
						List<Map<String, String>> list_file_attachment = fileUploadService.queryAttrList(map_request);
						obj.setIcon(list_file_attachment.get(0).get("downloadUrl"));
					}
				}
			}
			// 更新文件表的文件state为1
			fileUploadService.updateFileStateByIds(obj.getAttachId(), "1");
			manageMapper.updateAppValue(obj);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			vo.setErrcode("500");
			vo.setErrmsg("操作失败！");
			String classname = new Exception().getStackTrace()[1].getClassName(); // 获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); // 获取调用者的方法名
			Log rz = exceptionUtil.buildRZ(vo.getErrcode(),
					vo.getErrmsg() + "发生在" + classname + "的" + method_name + "方法上");
			exceptionUtil.addLog(rz);
		}
		return vo;
	}

	/**
	 * 删除
	 * <p>
	 * Title: deleteAppValue
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param obj
	 * @return
	 * @see com.hrbwmxx.system.service.IAppManageService#deleteAppValue(com.hrbwmxx.system.model.AppManage)
	 */
	@Override
	public Result deleteAppValue(AppManageCustom obj) {
		// TODO Auto-generated method stub
		AppManageVo vo = new AppManageVo();
		try {
			manageMapper.deleteAppValue(obj.getId());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			vo.setErrcode("500");
			vo.setErrmsg("操作失败！");
			String classname = new Exception().getStackTrace()[1].getClassName(); // 获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); // 获取调用者的方法名
			Log rz = exceptionUtil.buildRZ(vo.getErrcode(),
					vo.getErrmsg() + "发生在" + classname + "的" + method_name + "方法上");
			exceptionUtil.addLog(rz);
		}
		return vo;
	}

	// 类型查询
	@Override
	public Result queryAppTypeListPage(Page page, AppTypeCustom obj) {
		// TODO Auto-generated method stub
		AppTypeVo result = new AppTypeVo();
		try {
			List<AppTypeCustom> typeCustoms = typeMapper.queryAppTypeListPage(page, obj);
			int totalCount = typeMapper.queryAppTypeListPageCount(obj, page);
			result.setTotalCount(totalCount);
			result.setRows(typeCustoms);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setErrcode("500");
			result.setErrmsg("操作失败！");
			String classname = new Exception().getStackTrace()[1].getClassName(); // 获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); // 获取调用者的方法名
			Log rz = exceptionUtil.buildRZ(result.getErrcode(),
					result.getErrmsg() + "发生在" + classname + "的" + method_name + "方法上");
			exceptionUtil.addLog(rz);
		}
		return result;
	}

	// 类型单条查询
	@Override
	public Result queryAppTypeValueOne(AppTypeCustom obj) {
		// TODO Auto-generated method stub
		AppTypeVo result = new AppTypeVo();
		try {
			AppTypeCustom custom = typeMapper.queryAppTypeValueOne(obj);
			result.setCustom(custom);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setErrcode("500");
			result.setErrmsg("操作失败！");
			String classname = new Exception().getStackTrace()[1].getClassName(); // 获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); // 获取调用者的方法名
			Log rz = exceptionUtil.buildRZ(result.getErrcode(),
					result.getErrmsg() + "发生在" + classname + "的" + method_name + "方法上");
			exceptionUtil.addLog(rz);
		}
		return result;
	}

	// 类型添加
	@Override
	public Result saveAppTypeValue(AppTypeCustom obj) {
		// TODO Auto-generated method stub
		AppTypeVo result = new AppTypeVo();
		try {

			obj.setId(UUID.randomUUID().toString().replace("-", ""));

			typeMapper.saveAppTypeValue(obj);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setErrcode("500");
			result.setErrmsg("操作失败！");
			String classname = new Exception().getStackTrace()[1].getClassName(); // 获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); // 获取调用者的方法名
			Log rz = exceptionUtil.buildRZ(result.getErrcode(),
					result.getErrmsg() + "发生在" + classname + "的" + method_name + "方法上");
			exceptionUtil.addLog(rz);
		}
		return result;
	}

	// 类型修改
	@Override
	public Result updateAppTypeValue(AppTypeCustom obj) {
		// TODO Auto-generated method stub
		AppTypeVo result = new AppTypeVo();
		try {

			typeMapper.updateAppTypeValue(obj);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setErrcode("500");
			result.setErrmsg("操作失败！");
			String classname = new Exception().getStackTrace()[1].getClassName(); // 获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); // 获取调用者的方法名
			Log rz = exceptionUtil.buildRZ(result.getErrcode(),
					result.getErrmsg() + "发生在" + classname + "的" + method_name + "方法上");
			exceptionUtil.addLog(rz);
		}
		return result;
	}

	// 类型删除
	@Override
	public Result deleteAppTypeValue(AppTypeCustom obj) {
		// TODO Auto-generated method stub
		AppTypeVo result = new AppTypeVo();
		try {

			typeMapper.deleteAppTypeValue(obj.getId());

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setErrcode("500");
			result.setErrmsg("操作失败！");
			String classname = new Exception().getStackTrace()[1].getClassName(); // 获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); // 获取调用者的方法名
			Log rz = exceptionUtil.buildRZ(result.getErrcode(),
					result.getErrmsg() + "发生在" + classname + "的" + method_name + "方法上");
			exceptionUtil.addLog(rz);
		}
		return result;
	}

	// 保存城市位置
	@Override
	public Result saveCityCodeForApp(AppManageCustom obj) {
		// TODO Auto-generated method stub
		AppManageVo vo = new AppManageVo();
		AppManageCustom appManageCustom = new AppManageCustom();
		try {
			appManageCustom.setId(obj.getAppId());
			appManageCustom.setCitycode(obj.getCityId());
			manageMapper.updateAppValue(appManageCustom);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			vo.setErrcode("500");
			vo.setErrmsg("操作失败！");
			String classname = new Exception().getStackTrace()[1].getClassName(); // 获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); // 获取调用者的方法名
			Log rz = exceptionUtil.buildRZ(vo.getErrcode(),
					vo.getErrmsg() + "发生在" + classname + "的" + method_name + "方法上");
			exceptionUtil.addLog(rz);
		}
		return vo;
	}

	// 删除城市权限
	@Override
	public Result deleteCityCodeForApp(AppManageCustom obj) {
		AppManageVo vo = new AppManageVo();
		try {
			manageMapper.updateAppCityValue(obj);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			vo.setErrcode("500");
			vo.setErrmsg("操作失败！");
			String classname = new Exception().getStackTrace()[1].getClassName(); // 获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); // 获取调用者的方法名
			Log rz = exceptionUtil.buildRZ(vo.getErrcode(),
					vo.getErrmsg() + "发生在" + classname + "的" + method_name + "方法上");
			exceptionUtil.addLog(rz);
		}
		return vo;
	}
}

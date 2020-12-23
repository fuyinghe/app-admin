package com.webmos.framework.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.webmos.framework.vo.ModelMeteCustom;
import com.webmos.framework.model.AdvancedSearch;
import com.webmos.framework.model.FormModel;
import com.webmos.framework.model.ImpModel;
import com.webmos.framework.model.Model;
import com.webmos.framework.model.ModelTable;
import com.webmos.framework.model.VerifyEntity;

public interface ModelMapper {
	
	//返回模块的总体模型结构
	List<ModelMeteCustom> queryModelMeteList(@Param("moid") String moid);
	
	//返回模块中未隐藏的列
	String queryNotHiddenlColumns(@Param("moid")String moid);
	//返回模块中全部列(暂未使用)
	String queryAllColumns(@Param("moid")String moid);
	//返回模块中全部列(暂未使用)
	String queryFormColumns(@Param("moid")String moid);
	
	
	//返回模块中高级查询模型（数据查询组合高级查询条件使用）
	List<AdvancedSearch> queryAdvancedSearchMode(@Param("moid")String moid);
	
	//查询模块中表单显示的模型（from表单数据新增修改显示处理使用）
	List<FormModel> queryFormMode(@Param("moid")String moid);
	
	//返回模块配置信息
	ModelTable queryModeInfo(@Param("moid")String moid);
	
	//返回模块数据校验模型配置信息
	List<VerifyEntity> queryVerifyEntityModel(@Param("moid")String moid);
	
}

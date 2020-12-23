package com.hrbwmxx.framework.dao.sql;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import com.hrbwmxx.framework.dao.annotations.Column;
import com.hrbwmxx.framework.dao.annotations.DeleteRelatedTables;
import com.hrbwmxx.framework.dao.annotations.PrimaryKey;
import com.hrbwmxx.framework.dao.annotations.Table;
import com.hrbwmxx.framework.exception.AnnotationException;
import com.hrbwmxx.framework.model.LogicalDeleteAble;
import com.hrbwmxx.framework.model.TimestampAwareBean;

public class SQLBuilder {

	// private static final String nullString = "NULL";
	private static final String PARAMETER_TOKEN = "#";

	private static final Map<String, SqlText> sqlStatamentCache = new HashMap<String, SqlText>();

	private static TypeHandlerRegistry typeHandlerRegistry = new TypeHandlerRegistry();

	private static final String SELECT_STATAMENT = ".SELECT";
	private static final String EXIST_STATAMENT = ".EXIST";
	private static final String INSERT_STATAMENT = ".INSERT";
	private static final String UPDATE_STATAMENT = ".UPDATE";
	private static final String DELETE_LG_STATAMENT = ".DELETE_LG_STATAMENT";
	private static final String DELETE_PH_STATAMENT = ".DELETE_PH_STATAMENT";
	
	private static final String SELECT_LAST_INSERT_ID_SQL = "SELECT LAST_INSERT_ID()";

	// private static final String DELETE_RELATED_STATAMENTS =
	// ".DELETE_RELATED_STATAMENTS";

	private static void register(String id, SqlText sqlText) {
		sqlStatamentCache.put(id, sqlText);
	}

	private static SqlText getCachedSqlText(String id) {
		return sqlStatamentCache.get(id);
	}

	public static SqlText parsePKExistSQL(Object entity) {

		Class clazz = entity.getClass();
		String statamentId = clazz.getName() + EXIST_STATAMENT;
		SqlText sqlText = getCachedSqlText(statamentId);
		if (sqlText != null) {
			return sqlText;
		}
		StringBuffer sql = new StringBuffer();

		StringBuffer sqlColumns = new StringBuffer();
		sqlColumns.append("COUNT(1)");
		String tableName = getAnnonatedTableName(clazz);

		StringBuffer sqlConditions = new StringBuffer();
		sqlConditions.append(getPkCondition(entity));

		sql.append("SELECT ");
		sql.append(sqlColumns);
		sql.append(" FROM ");
		sql.append(tableName);
		sql.append(" T ");
		sql.append(sqlConditions);

		InlineParameterMapParser parser = new InlineParameterMapParser();
		sqlText = parser.parseInlineParameterMap(typeHandlerRegistry, sql
				.toString(), clazz);
		register(statamentId, sqlText);
		return sqlText;
	}

	public static SqlText parseSelectSQL(Object entity) {

		// SELECT_STATAMENT
		Class clazz = entity.getClass();
		String statamentId = clazz.getName() + SELECT_STATAMENT;
		SqlText sqlText = getCachedSqlText(statamentId);
		if (sqlText != null) {
			return sqlText;
		}

		StringBuffer sql = new StringBuffer();

		StringBuffer sqlColumns = new StringBuffer();
		StringBuffer sqlConditions = new StringBuffer();

		String tableName = getAnnonatedTableName(clazz);

		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];

			if (field.isAnnotationPresent(Column.class)) {
				String name = getColumnName(field);
				if (sqlColumns.length() > 0) {
					sqlColumns.append(",");
				}
				sqlColumns.append(name);
				sqlColumns.append(" AS ");
				sqlColumns.append(getBeanPropertyName(field));
			}
		}

		sqlConditions.append(getPkCondition(entity));

		 if (entity instanceof TimestampAwareBean) {
			if (sqlColumns.length() > 0) {
				sqlColumns.append(",");
			}

			sqlColumns.append("created_timestamp AS createdTimestamp,");
			sqlColumns.append("created_by AS createdBy,");
			sqlColumns.append("modified_timestamp AS modifiedTimestamp,");
			sqlColumns.append("modified_by AS modifiedBy,");
			sqlColumns.append("rec_active_flag AS recActiveFlag");
		}

		sql.append("SELECT ");
		sql.append(sqlColumns);
		sql.append(" FROM ");
		sql.append(tableName);
		sql.append(" T ");
		sql.append(sqlConditions);

		InlineParameterMapParser parser = new InlineParameterMapParser();
		sqlText = parser.parseInlineParameterMap(typeHandlerRegistry, sql
				.toString(), clazz);
		register(statamentId, sqlText);
		return sqlText;
	}

	
	public static boolean isAutoIncrement(Object entity) {
		Class clazz = entity.getClass();
		Field[] fields = clazz.getDeclaredFields();

		for (int i = 0, max = fields.length; i < max; i++) {
			Field field = fields[i];
			if (field.isAnnotationPresent(PrimaryKey.class)) {
				PrimaryKey pk = field.getAnnotation(PrimaryKey.class);
				if (pk.autoIncrement()) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static void setAutoIncrementFieldValue(Object entity, Integer lastInsertId) {
		Class clazz = entity.getClass();
		Field[] fields = clazz.getDeclaredFields();
		
		for (int i = 0, max = fields.length; i < max; i++) {
			Field field = fields[i];
			if (field.isAnnotationPresent(PrimaryKey.class)) {
				PrimaryKey pk = field.getAnnotation(PrimaryKey.class);
				if (pk.autoIncrement()) {
					field.setAccessible(true);
					try {
						field.set(entity, lastInsertId);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public static SqlText parseSelectLastInsertIdSQL() {
		InlineParameterMapParser parser = new InlineParameterMapParser();
		SqlText sqlText = new SqlText();
		sqlText.setText(SELECT_LAST_INSERT_ID_SQL);
		return sqlText;
	}
	
	public static SqlText parseInsertSQL(Object entity)
			throws AnnotationException {

		Class clazz = entity.getClass();
		String statamentId = clazz.getName() + INSERT_STATAMENT;
		SqlText sqlText = getCachedSqlText(statamentId);
		if (sqlText != null) {
			return sqlText;
		}

		StringBuilder sql = new StringBuilder();
		StringBuilder sqlColumns = new StringBuilder();
		StringBuilder sqlValues = new StringBuilder();

		String tableName = getAnnonatedTableName(clazz);

		Field[] fields = clazz.getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];

			if (field.isAnnotationPresent(Column.class)) {
				Column c = field.getAnnotation(Column.class);
				
				if (field.isAnnotationPresent(PrimaryKey.class)) {
					PrimaryKey pk = field.getAnnotation(PrimaryKey.class);
					if (pk.autoIncrement()) {
						continue;
					}
				}
				
				if (c.insertable()) {
					
					String name = getColumnName(field);
					if (sqlColumns.length() > 0) {
						sqlColumns.append(",");
						sqlValues.append(",");
					}
					sqlColumns.append(name);
					sqlValues.append(PARAMETER_TOKEN);
					sqlValues.append(getBeanPropertyName(field));
					sqlValues.append(PARAMETER_TOKEN);
				}
			}
		}
		
		if (entity instanceof TimestampAwareBean) {
			if (sqlColumns.length() > 0) {
				sqlColumns.append(",");
				sqlValues.append(",");
			}
			sqlColumns.append("created_timestamp");
			sqlValues.append("current_timestamp()");
			sqlColumns.append(",created_by");
			sqlValues.append(",");
			sqlValues.append(PARAMETER_TOKEN);
			sqlValues.append(TimestampAwareBean.KEY_CREATED_BY);
			sqlValues.append(PARAMETER_TOKEN);
			sqlColumns.append(",rec_active_flag");
			sqlValues.append(",");
			sqlValues.append("'Y'");
		}
		if (sqlColumns.length() == 0) {
			throw new AnnotationException(
					"There is no column to be inser inton table[ " + tableName
							+ "].");
		} else {
			sql.append("INSERT INTO ");
			sql.append(tableName);
			sql.append(" (");
			sql.append(sqlColumns);
			sql.append(") VALUES (");
			sql.append(sqlValues);
			sql.append(") ");
		}

		InlineParameterMapParser parser = new InlineParameterMapParser();
		sqlText = parser.parseInlineParameterMap(typeHandlerRegistry, sql
				.toString(), clazz);
		register(statamentId, sqlText);
		return sqlText;
	}

	public static SqlText parseUpdateSQL(Object entity)
			throws AnnotationException {

		Class clazz = entity.getClass();
		String statamentId = clazz.getName() + UPDATE_STATAMENT;

		SqlText sqlText = getCachedSqlText(statamentId);
		if (sqlText != null) {
			return sqlText;
		}

		StringBuffer sql = new StringBuffer();
		StringBuffer sqlValues = new StringBuffer();
		StringBuffer sqlConditions = new StringBuffer();

		String tableName = getAnnonatedTableName(clazz);

		sqlConditions.append(getPkCondition(entity));
		Map<String, String> updateKey = getUpdateKeyFileds(entity);
		for (Iterator i = updateKey.keySet().iterator(); i.hasNext();) {
			String key = (String) i.next();
			String value = updateKey.get(key);
			if (sqlConditions.length() == 0) {
				sqlConditions.append(" WHERE ");
			} else {
				sqlConditions.append(" AND ");
			}
			sqlConditions.append(key);
			sqlConditions.append("=");
			sqlConditions.append(PARAMETER_TOKEN);
			sqlConditions.append(value);
			sqlConditions.append(PARAMETER_TOKEN);
		}

		Map<String, String> updateFields = getUpdateableFileds(entity);

		for (Iterator i = updateFields.keySet().iterator(); i.hasNext();) {
			String key = (String) i.next();
			String value = updateFields.get(key);
			if (sqlValues.length() > 0) {
				sqlValues.append(",");
			}
			sqlValues.append(key);
			sqlValues.append("=");
			sqlValues.append(PARAMETER_TOKEN);
			sqlValues.append(value);
			sqlValues.append(PARAMETER_TOKEN);
		}

		 if (entity instanceof TimestampAwareBean) {
			if (sqlValues.length() > 0) {
				sqlValues.append(",");
			}
			sqlValues.append("modified_timestamp=");
			sqlValues.append("current_timestamp()");
			sqlValues.append(",modified_by=");
			sqlValues.append(PARAMETER_TOKEN);
			sqlValues.append(TimestampAwareBean.KEY_MODIFIED_BY);
			sqlValues.append(PARAMETER_TOKEN);
			sqlValues.append(",rec_active_flag=");
			sqlValues.append(PARAMETER_TOKEN);
			sqlValues.append(TimestampAwareBean.KEY_REC_ACTIVE_FLAG);
			sqlValues.append(PARAMETER_TOKEN);
		}

		if (sqlValues.length() == 0) {
			throw new AnnotationException(
					"There is no column to be inser inton table[ " + tableName
							+ "].");
		} else {
			sql.append("UPDATE ");
			sql.append(tableName);
			sql.append(" SET ");
			sql.append(sqlValues);
			sql.append(sqlConditions);
		}

		InlineParameterMapParser parser = new InlineParameterMapParser();
		sqlText = parser.parseInlineParameterMap(typeHandlerRegistry, sql
				.toString(), clazz);
		register(statamentId, sqlText);
		return sqlText;
	}

	public static SqlText parsePartlyUpdateSQL(Object entity,
			boolean fullField, String mask) throws AnnotationException {

		if (mask == null || mask.isEmpty()) {
			return null;
		}
		String[] maskFields = mask.split(",");

		StringBuffer sql = new StringBuffer();
		StringBuffer sqlValues = new StringBuffer();
		StringBuffer sqlConditions = new StringBuffer();

		Class clazz = entity.getClass();
		String tableName = getAnnonatedTableName(clazz);

		sqlConditions.append(getPkCondition(entity));
		Map<String, String> updateKey = getUpdateKeyFileds(entity);
		for (Iterator i = updateKey.keySet().iterator(); i.hasNext();) {
			String key = (String) i.next();
			String value = updateKey.get(key);
			if (sqlConditions.length() == 0) {
				sqlConditions.append(" WHERE ");
			} else {
				sqlConditions.append(" AND ");
			}
			sqlConditions.append(key);
			sqlConditions.append("=");
			sqlConditions.append(PARAMETER_TOKEN);
			sqlConditions.append(value);
			sqlConditions.append(PARAMETER_TOKEN);
		}

		Map<String, String> updateFields = getUpdateableFileds(entity,
				maskFields);

		for (Iterator i = updateFields.keySet().iterator(); i.hasNext();) {
			String key = (String) i.next();
			String value = updateFields.get(key);
			if (sqlValues.length() > 0) {
				sqlValues.append(",");
			}
			sqlValues.append(key);
			sqlValues.append("=");
			sqlValues.append(PARAMETER_TOKEN);
			sqlValues.append(value);
			sqlValues.append(PARAMETER_TOKEN);
		}

		// if (entity instanceof TimestampAwareBean) {
		// // TimestampAwareBean timeatampaware = (TimestampAwareBean) entity;
		// if (sqlConditions.length() == 0) {
		// sqlConditions.append(" WHERE ");
		// } else {
		// sqlConditions.append(" AND ");
		// }
		// sqlConditions.append("UPDATE_SEQ=");
		// sqlConditions.append(PARAMETER_TOKEN);
		// sqlConditions.append(TimestampAwareBean.KEY_UPDATE_SEQ);
		// sqlConditions.append(PARAMETER_TOKEN);
		//
		// if (sqlValues.length() > 0) {
		// sqlValues.append(",");
		// }
		//
		// sqlValues.append("ID_PRG=");
		// sqlValues.append(PARAMETER_TOKEN);
		// sqlValues.append(TimestampAwareBean.KEY_ID_PRG);
		// sqlValues.append(PARAMETER_TOKEN);
		// sqlValues.append(",UPDATE_DT=");
		// sqlValues.append(PARAMETER_TOKEN);
		// sqlValues.append(TimestampAwareBean.KEY_UPDATE_DT);
		// sqlValues.append(PARAMETER_TOKEN);
		// sqlValues.append(",UPDATE_PC=");
		// sqlValues.append(PARAMETER_TOKEN);
		// sqlValues.append(TimestampAwareBean.KEY_UPDATE_PC);
		// sqlValues.append(PARAMETER_TOKEN);
		// sqlValues.append(",UPDATE_USER=");
		// sqlValues.append(PARAMETER_TOKEN);
		// sqlValues.append(TimestampAwareBean.KEY_UPDATE_USER);
		// sqlValues.append(PARAMETER_TOKEN);
		// sqlValues.append(",UPDATE_SEQ=NVL(UPDATE_SEQ,0)+1");
		//
		// }

		if (sqlValues.length() == 0) {
			throw new AnnotationException(
					"There is no column to be inser inton table[ " + tableName
							+ "].");
		} else {
			sql.append("UPDATE ");
			sql.append(tableName);
			sql.append(" SET ");
			sql.append(sqlValues);
			sql.append(sqlConditions);
		}

		InlineParameterMapParser parser = new InlineParameterMapParser();
		SqlText sqlText = parser.parseInlineParameterMap(typeHandlerRegistry,
				sql.toString(), clazz);
		return sqlText;
	}

	public static SqlText parseLogicalDeleteSQL(Object entity) {

		Class clazz = entity.getClass();
		String statamentId = clazz.getName() + DELETE_LG_STATAMENT;
		SqlText sqlText = getCachedSqlText(statamentId);
		if (sqlText != null) {
			return sqlText;
		}

		String tableName = getAnnonatedTableName(clazz);
		if (entity instanceof LogicalDeleteAble) {

			StringBuilder sql = new StringBuilder();
			StringBuilder sqlConditions = new StringBuilder();
			sqlConditions.append(getPkCondition(entity));
			
			if (sqlConditions.length() == 0) {
				throw new RuntimeException(
						"Can not delete table without any condition: table[ "
								+ tableName + "].");
			} else {
				sql.append("UPDATE ");
				sql.append(tableName);
				sql.append(" SET rec_active_flag = 'N'");
				sql.append(sqlConditions);
			}

			InlineParameterMapParser parser = new InlineParameterMapParser();
			sqlText = parser.parseInlineParameterMap(typeHandlerRegistry, sql
					.toString(), clazz);
			register(statamentId, sqlText);
			return sqlText;
		} else {
			throw new RuntimeException(
					"Not a logical deleteable Table: table[ " + tableName
							+ "].");
		}
	}

	public static SqlText parseDeleteSQL(Object entity) {

		Class clazz = entity.getClass();
		String statamentId = clazz.getName() + DELETE_PH_STATAMENT;
		SqlText sqlText = getCachedSqlText(statamentId);
		if (sqlText != null) {
			return sqlText;
		}

		StringBuilder sql = new StringBuilder();
		StringBuilder sqlConditions = new StringBuilder();

		String tableName = getAnnonatedTableName(clazz);
		sqlConditions.append(getPkCondition(entity));

		if (sqlConditions.length() == 0) {
			throw new RuntimeException(
					"Can not delete table without any condition: table[ "
							+ tableName + "].");
		} else {
			sql.append("DELETE FROM ");
			sql.append(tableName);
			sql.append(sqlConditions);
		}

		InlineParameterMapParser parser = new InlineParameterMapParser();
		sqlText = parser.parseInlineParameterMap(typeHandlerRegistry, sql
				.toString(), clazz);
		register(statamentId, sqlText);
		return sqlText;
	}

	public static boolean isAnnonatedDeleteRelatedTables(Object entity) {
		Class clazz = entity.getClass();
		return clazz.isAnnotationPresent(DeleteRelatedTables.class);
	}
	
	public static SqlText[] parseDeleteRelatedTablesSQL(Object entity) {

		Class clazz = entity.getClass();
		// String statamentId = clazz.getName() + DELETE_RELATED_STATAMENTS;
		// SqlText sqlText = getCachedSqlText(statamentId);
		// if (sqlText != null) {
		// return sqlText;
		// }

		SqlText[] sqlTexts = {};

		StringBuilder sqlConditions = new StringBuilder();
		String[] tableNames = getAnnonatedDeleteRelatedTableNames(clazz);
		// todo: 不能用entity的主键id
		sqlConditions.append(getPkCondition(entity));

		if (sqlConditions.length() == 0) {
			throw new RuntimeException(
					"Can not delete table without any condition: table[ "
							+ clazz + "].");
		} else {

			sqlTexts = new SqlText[tableNames.length];
			InlineParameterMapParser parser = new InlineParameterMapParser();

			for (int i = 0; i < tableNames.length; i++) {
				StringBuilder sql = new StringBuilder();
				sql.append("DELETE FROM ");
				sql.append(tableNames[i]);
				sql.append(sqlConditions);

				sqlTexts[i] = parser.parseInlineParameterMap(
						typeHandlerRegistry, sql.toString(), clazz);

			}
		}
		// register(statamentId, sqlText);
		return sqlTexts;
	}

	private static String[] getAnnonatedDeleteRelatedTableNames(Class clazz)
			throws AnnotationException {

		String[] tableNames;
		if (!clazz.isAnnotationPresent(DeleteRelatedTables.class)) {
			throw new AnnotationException("Class:[" + clazz.getName()
					+ "] has no multi language table annotation.");
		}

		DeleteRelatedTables tables = (DeleteRelatedTables) clazz
				.getAnnotation(DeleteRelatedTables.class);
		tableNames = tables.value().trim().split(",");
		if (tableNames.length == 0) {
			throw new AnnotationException("Class:[" + clazz.getName()
					+ "] has no multi language table name.");
		}
		return tableNames;
	}

	public static String getAnnonatedTableName(Class<?> clazz)
			throws AnnotationException {

		String tableName;
		
		
		if (!clazz.isAnnotationPresent(Table.class) && clazz.getSuperclass() != null) {
			return getAnnonatedTableName(clazz.getSuperclass());
		}

		Table table = (Table) clazz.getAnnotation(Table.class);
		if (table.value() == null || table.value().trim().isEmpty()) {
			throw new AnnotationException("Class:[" + clazz.getName()
					+ "] has no table name.");
		}
		tableName = table.value().trim();
		return tableName;
	}

	private static String getBeanPropertyName(Field field) {
		String name = null;
		if (field.isAnnotationPresent(Column.class)) {
			Column c = field.getAnnotation(Column.class);
			name = c.propertyName();
			if (name.isEmpty()) {
				name = field.getName();
			}
		} else {
			name = field.getName();
		}
		return name;
	}

	private static String getColumnName(Field field) {
		String name = null;
		if (field.isAnnotationPresent(Column.class)) {
			Column c = field.getAnnotation(Column.class);
			name = c.mappedName().toLowerCase().trim();
			if (name.isEmpty()) {
				name = field.getName().toLowerCase();
			}
		}
		return name;
	}

	private static StringBuilder getPkCondition(Class clazz, StringBuilder sqlConditions) {
		if (clazz == null) {
			return sqlConditions;
		}

		Field[] fields = clazz.getDeclaredFields();
		
		for (int i = 0, max = fields.length; i < max; i++) {
			Field field = fields[i];
			if (field.isAnnotationPresent(PrimaryKey.class)) {
				Column c = field.getAnnotation(Column.class);
				String name = c.mappedName();
				String valueTokenName = getBeanPropertyName(field);
				
				if (sqlConditions.length() == 0) {
					sqlConditions.append(" WHERE ");
				} else {
					sqlConditions.append(" AND ");
				}
				sqlConditions.append(name);
				sqlConditions.append(" = ");
				sqlConditions.append(PARAMETER_TOKEN);
				sqlConditions.append(valueTokenName);
				sqlConditions.append(PARAMETER_TOKEN);
			}
		}
		
		Class<?> superClass = clazz.getSuperclass();
		
		return getPkCondition(superClass, sqlConditions);
	}
	
	private static String getPkCondition(Object entity) {

		StringBuilder sqlConditions = new StringBuilder();
		Class clazz = entity.getClass();
		
		sqlConditions = getPkCondition(clazz, sqlConditions);
		
		return sqlConditions.toString();
		
	}

	private static List<String> getPKFileds(Object entity) {
		List<String> list = new ArrayList<String>();
		Class clazz = entity.getClass();
		Field[] fields = clazz.getDeclaredFields();

		for (int i = 0, max = fields.length; i < max; i++) {
			Field field = fields[i];
			if (field.isAnnotationPresent(PrimaryKey.class)) {
				// Column c = field.getAnnotation(Column.class);
				list.add(getColumnName(field));
			}
		}
		return list;
	}

	private static Map<String, String> getUpdateableFileds(Object entity) {
		return getUpdateableFileds(entity, null);
	}

	private static Map<String, String> getUpdateableFileds(Object entity,
			String[] maskFields) {
		Map<String, String> map = new HashMap<String, String>();
		Class clazz = entity.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0, max = fields.length; i < max; i++) {
			Field field = fields[i];
			if (field.isAnnotationPresent(Column.class)) {
				Column c = field.getAnnotation(Column.class);
				if (c.updateable()) {
					String name = getColumnName(field);

					if (maskFields == null
							|| (maskFields != null && ArrayUtils.contains(
									maskFields, name))) {
						String value = getBeanPropertyName(field);
						map.put(name, value);
					}
				}
			}
		}
		return map;
	}

	public static void setStatementParameters(PreparedStatement ps,
			ParameterMapping[] parameterMappings, Object[] parameters)
			throws SQLException {

		if (parameterMappings != null) {
			for (int i = 0; i < parameterMappings.length; i++) {
				ParameterMapping mapping = parameterMappings[i];
				setParameter(ps, mapping, parameters, i);
			}
		}
	}

	public static Object[] getParameterData(
			ParameterMapping[] parameterMappings, Object parameterObject) {
		Object[] parmeters = new Object[parameterMappings.length];
		MetaObject metaObject = MetaObject.forObject(parameterObject, null, null, null);
		for (int i = 0; i < parameterMappings.length; i++) {
			parmeters[i] = metaObject.getValue(parameterMappings[i]
					.getPropertyName());
		}
		return parmeters;
	}

	protected static void setParameter(PreparedStatement ps,
			ParameterMapping mapping, Object[] parameters, int i)
			throws SQLException {
		Object value = parameters[i];

		// TypeHandler
		if (value != null) {
			Class<?> clazz = value.getClass();
			TypeHandler typeHaddler = typeHandlerRegistry.getTypeHandler(clazz);
			typeHaddler.setParameter(ps, i + 1, value, mapping.getJdbcType());
		} else {
			JdbcType jdbcType = mapping.getJdbcType();
			if (jdbcType != null) {
				ps.setNull(i + 1, jdbcType.TYPE_CODE);
			} else {
				ps.setNull(i + 1, Types.OTHER);
			}
		}
	}

	private static Map<String, String> getUpdateKeyFileds(Object entity) {

		Map<String, String> map = new HashMap<String, String>();
		Class clazz = entity.getClass();
		Field[] fields = clazz.getDeclaredFields();

		for (int i = 0, max = fields.length; i < max; i++) {
			Field field = fields[i];
			if (field.isAnnotationPresent(Column.class)) {
				Column c = field.getAnnotation(Column.class);
				if (c.updateKey()) {
					String key = getColumnName(field);
					String value = getBeanPropertyName(field);
					map.put(key, value);
				}
			}
		}
		return map;
	}

	public static void main(String[] args) {
//		FoodSrvc entity = new FoodSrvc();
//		SqlText sql = parseSelectSQL(entity);
//		System.out.println(sql.getText());
//		Object[] parameters = SQLBuilder.getParameterData(sql
//				.getParameterMappings(), entity);
	}

}

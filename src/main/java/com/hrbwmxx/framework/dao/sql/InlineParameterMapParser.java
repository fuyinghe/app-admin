package com.hrbwmxx.framework.dao.sql;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.ibatis.type.TypeHandlerRegistry;

public class InlineParameterMapParser {

	// private static final Probe PROBE = ProbeFactory.getProbe();
	private static final String PARAMETER_TOKEN = "#";

	// private static final String PARAM_DELIM = ":";

	// public SqlText parseInlineParameterMap(typeHandlerRegistry
	// typeHandlerRegistry, String sqlStatement) {
	// return parseInlineParameterMap(typeHandlerRegistry, sqlStatement, null);
	// }

	public SqlText parseInlineParameterMap( TypeHandlerRegistry typeHandlerRegistry, String sqlStatement,
			Class parameterClass) {
		String newSql = sqlStatement;

		List mappingList = new ArrayList();

		StringTokenizer parser = new StringTokenizer(sqlStatement,
				PARAMETER_TOKEN, true);
		StringBuffer newSqlBuffer = new StringBuffer();

		String token = null;
		String lastToken = null;
		while (parser.hasMoreTokens()) {
			token = parser.nextToken();
			if (PARAMETER_TOKEN.equals(lastToken)) {
				if (PARAMETER_TOKEN.equals(token)) {
					newSqlBuffer.append(PARAMETER_TOKEN);
					token = null;
				} else {
					ParameterMapping mapping = null;
					mapping = parseMapping(token, parameterClass,
							typeHandlerRegistry);
					mappingList.add(mapping);
					newSqlBuffer.append("?");
					token = parser.nextToken();
					if (!PARAMETER_TOKEN.equals(token)) {
						throw new RuntimeException(
								"Unterminated inline parameter in mapped statement ("
										+ "statement.getId()" + ").");
					}
					token = null;
				}
			} else {
				if (!PARAMETER_TOKEN.equals(token)) {
					newSqlBuffer.append(token);
				}
			}

			lastToken = token;
		}

		newSql = newSqlBuffer.toString();

		ParameterMapping[] mappingArray = (ParameterMapping[]) mappingList
				.toArray(new ParameterMapping[mappingList.size()]);

		SqlText sqlText = new SqlText();
		sqlText.setText(newSql);
		sqlText.setParameterMappings(mappingArray);
		return sqlText;
	}

	private ParameterMapping parseMapping(String token, Class parameterClass,
			TypeHandlerRegistry typeHandlerRegistry) {
		ParameterMapping mapping = new ParameterMapping();
		mapping.setPropertyName(token);
		return mapping;
	}
	
	public static void main (String[] args){
		
	}
}

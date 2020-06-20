package typechange;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
//BaseTypeHandler<Boolean>  <>��дҪת����java����
public class BooleanAndIntConverter extends BaseTypeHandler<Boolean>{

	//java���봫�ݵ����ݿ⣨boolean����Ϊnumber��
	/*
	 * ps:����ʱ��PreparedStatement����
	 * i:PreparedStatement��������Ĳ���λ��
	 * parameterҪת����javaֵ
	 * jdbcType:jdbc���������ݿ���������number
	 */
	public void setNonNullParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType)
			throws SQLException {
		if(parameter){
			//�����true�����ı�����ֵ��Ϊ1,��֪��ʲô���;�setObject
			ps.setInt(i,1);
		}else{
			//�����false�����ı�����ֵ��Ϊ0
			ps.setInt(i,0);
		}
		
		
	}
    
	
	//ǰ����get����sqlȡ��Java
	
	@Override
	public Boolean getNullableResult(ResultSet rs, String columnName) throws SQLException {
		int sexNum = rs.getInt(columnName);//columnIndex���е��У�����������ֵ  �൱��rs.getInt("stuNo")ͨ��������
	/*	if(sexNum == 1) {
		   return true;
		}else {
			return 
		}*/
		return sexNum == 1?true:false;
	}
    //CallableStatement�洢����
	public Boolean getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		int sexNum = cs.getInt(columnIndex);//ͨ���洢������
		return sexNum == 1?true:false;
	}



	@Override
	public Boolean getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		int sexNum = rs.getInt(columnIndex);//rs.getInt(1)ͨ���±���
		return sexNum == 1?true:false;
		
	}

	
	

	
	

}

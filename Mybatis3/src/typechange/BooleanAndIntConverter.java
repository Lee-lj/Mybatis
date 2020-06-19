package typechange;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
//BaseTypeHandler<Boolean>  <>中写要转换的java类型
public class BooleanAndIntConverter extends BaseTypeHandler<Boolean>{

	//java代码传递到数据库（boolean）传为number类
	/*
	 * ps:操作时的PreparedStatement对象
	 * i:PreparedStatement对象操作的参数位置
	 * parameter要转换的java值
	 * jdbcType:jdbc操作的数据库类型例如number
	 */
	public void setNonNullParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType)
			throws SQLException {
		if(parameter){
			//如果是true，将改变量的值变为1,不知道什么类型就setObject
			ps.setInt(i,1);
		}else{
			//如果是false，将改变量的值变为0
			ps.setInt(i,0);
		}
		
		
	}
    
	
	//前三种get，从sql取到Java
	
	@Override
	public Boolean getNullableResult(ResultSet rs, String columnName) throws SQLException {
		int sexNum = rs.getInt(columnName);//columnIndex表中的列，根据列名拿值  相当于rs.getInt("stuNo")通过列名拿
	/*	if(sexNum == 1) {
		   return true;
		}else {
			return 
		}*/
		return sexNum == 1?true:false;
	}
    //CallableStatement存储过程
	public Boolean getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		int sexNum = cs.getInt(columnIndex);//通过存储过程拿
		return sexNum == 1?true:false;
	}



	@Override
	public Boolean getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		int sexNum = rs.getInt(columnIndex);//rs.getInt(1)通过下标拿
		return sexNum == 1?true:false;
		
	}

	
	

	
	

}

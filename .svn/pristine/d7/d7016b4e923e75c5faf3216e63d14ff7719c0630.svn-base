package org.jeecgframework.dialog;

import org.hibernate.dialect.MySQLDialect;

/**
 * 重新定义方言
 * @author liye
 *
 */
public class MyDialect extends MySQLDialect {

	public MyDialect(){
		super();
		//函数名必须是小写，试验大写出错
		//SQLFunctionTemplate函数第一个参数是函数的输出类型，varchar2对应new StringType()    number对应new IntegerType()
		//?1代表第一个参数,?2代表第二个参数 
		//this.registerFunction("query_leave_status", (SQLFunction) new SQLFunctionTemplate(new StringType(), "query_leave_status(?1,?2)"));
	}
}

package mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import thirdclass.Address;
import thirdclass.Grade;
import thirdclass.Student;
import thirdclass.StudentClass;
import thirdclass.StudentWithCard;

//操作mybatis的接口
public interface StudentMapper {
	/*1.方法名和mapper.xml中的标签id值相同
	 * 2.方法的输入参数和mapper中的parameterType类型一致
	 * 3.方法的输出参数和mapper.xml中的resultType一致
	 * <select id="selectByNo" resultType="thirdclass.Student" parameterType="int">等于Student selectByNo(int stuNo);
	 * 接口名的全类名放入persionMapper中的namespace，这样接口就可以自动关联PersionMapper.xml 
	 */
 //接口类中的方法默认都是public abstract，所以可写可不写public abstract Student selectByNo(int stuNo);
	//返回值+sql语句的id+(输入类型),没有返回值就是void
	//无论返回值是一个还是多个，student List<student>,在mapper.xml中的resultType只写一个
	//通过session对象获取接口session.getMapper(接口.class)，再调用该接口的方法，程序会自动执行该方法对应的sql语句
	Student selectByNo(int stuNo); //查找一个
	List<Student> selectAll();//查找全部
	void deleteByNo(int stuNo);//删除
	void insertStudent(Student student);//插入
	void updateByNo(Student student);
	Student selectOne(int stuNo);
	void insertWithConverter(Student student);
	List<Student> selectStudentOrderByName();//查询所有学生，按人名字静态排序
	List<Student> selectStudentOrderByColumn(String column);//查询所有学生，按某种属性动态排序
	List<Student> selectStudentBystuNameOrstuAge(Student student);//根据学号和姓名查学生
	List<Student> selectStudentBystuNameOrstuAgeWithHashMap(HashMap<String,Object> map);//String , Object
	List<Student> selectStudentByAddress(Address address);//根据address查询学生，输入是地址
	List<Student> selectStudentByAddress2(Student address);//根据address查询学生,输入是级联属性（学生关联的address）
	int selectStudentCount();
	void querystudentByGrade(Map<String,Object> params);//调用存储过程，查找某个年级的总人数
	HashMap<String,Object> selectStudentOutHashMap();//输出为hashmap类型
	List<HashMap<String,Object>> selectAllStudentHashMap();//使用hashmap输出多个学生的多个对象
	Student selectWithSqlTag(Student student);//带sql标签的动态sql查询
	List<Student> selectWithForeach(Grade grade);//一次查询多个学生,属性方式
	List<Student> selectWithArray(int[] Nos);//一次查询多个学生,数组方式
	List<Student> selectWithList(List<Integer> Nos);//一次查询多个学生,集合方式
	List<Student> selectWithTargetList(Student[] students);//一次查询多个学生,对象数组方式
	StudentWithCard selectConnection(int stuNo);//一对一关联查询,业务扩展方式 
	Student selectConnectionWithresultMap(int stuNo);//利用mybatis提供的resultmap实现一对一关联查询 
	StudentClass selectOneToMany(int classId);//一对多关联查询
	List<Student> selectWithLazyload();//带延迟加载的一对一，查询全部学生并根据需要查询对应的学生证信息
	List<StudentClass> selectOneToManyWithLazyLoad();//带延迟加载的一对多，先查班级然后查班级中的学生
}
	
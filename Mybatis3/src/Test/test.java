package Test;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mapper.StudentMapper;
import thirdclass.Address;
import thirdclass.Grade;
import thirdclass.Student;
import thirdclass.StudentCard;
import thirdclass.StudentClass;
import thirdclass.StudentWithCard;

public class test {
	
	public static void selectStudentOne() throws IOException {
		Reader reader = Resources.getResourceAsReader("config.xml");
		SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
		SqlSession session=sessionFactory.openSession();
		//		String statement="thirdclass.PersonMapper.insertStudent";//statement注明要执行的sql ,用下面的StudentMapper来代替statement
		StudentMapper studentMapper=session.getMapper(StudentMapper.class);
		Student student=studentMapper.selectByNo(226);//接口中的方法
		System.out.println(student.getStuNo());	
		session.close();
		//查询第二次验证二级缓存
		/*SqlSession session1=sessionFactory.openSession();
		StudentMapper studentMapper1=session1.getMapper(StudentMapper.class);
		Student student1=studentMapper.selectByNo(226);
		System.out.println(student1.getStuNo());	
		session1.close();*/
	}
	
	//使用转换器的查询，数据库中的O,1在java中返回true，false，表示男女
	public static void selectOne() throws IOException {
		Reader reader = Resources.getResourceAsReader("config.xml");
		SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
		SqlSession session=sessionFactory.openSession();
		//		String statement="thirdclass.PersonMapper.insertStudent";//statement注明要执行的sql ,用下面的StudentMapper来代替statement
		StudentMapper studentMapper=session.getMapper(StudentMapper.class);
		Student student=studentMapper.selectOne(226);//接口中的方法
		System.out.println(student);	
		session.close();
	}
	//查询全部学生，根据人名字来静态排序
	public static void selectStudentOrderByName() throws IOException {
		Reader reader = Resources.getResourceAsReader("config.xml");
		SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
		SqlSession session=sessionFactory.openSession();
		//		String statement="thirdclass.PersonMapper.insertStudent";//statement注明要执行的sql ,用下面的StudentMapper来代替statement
		StudentMapper studentMapper=session.getMapper(StudentMapper.class);
		List<Student> student=studentMapper.selectStudentOrderByName();//接口中的方法
		System.out.println(student);	
		session.close();
	}
	//查询全部学生，根据人名字来动态排序
	public static void selectStudentOrderByColumn() throws IOException {
		Reader reader = Resources.getResourceAsReader("config.xml");
		SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
		SqlSession session=sessionFactory.openSession();
		//		String statement="thirdclass.PersonMapper.insertStudent";//statement注明要执行的sql ,用下面的StudentMapper来代替statement
		StudentMapper studentMapper=session.getMapper(StudentMapper.class);
		List<Student> student=studentMapper.selectStudentOrderByColumn("stuNo");//接口中的方法
		System.out.println(student);	
		session.close();
	}
	//根据学号或者姓名查询学生
	public static void selectStudentBystuNameOrstuAge() throws IOException {
		Reader reader = Resources.getResourceAsReader("config.xml");
		SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
		SqlSession session=sessionFactory.openSession();
		//		String statement="thirdclass.PersonMapper.insertStudent";//statement注明要执行的sql ,用下面的StudentMapper来代替statement
		StudentMapper studentMapper=session.getMapper(StudentMapper.class);
		Student student=new Student();
		student.setStuAge(22);
		student.setStuName("%l%");
		List<Student> students=studentMapper.selectStudentBystuNameOrstuAge(student);//接口中的方法
		System.out.println(students);	
		session.close();
	}
	
	//根据学号或者姓名查询学生,使用hashMap（输入参数是hashmap）
	public static void selectStudentBystuNameOrstuAgeWithHashMap() throws IOException {
		Reader reader = Resources.getResourceAsReader("config.xml");
		SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
		SqlSession session=sessionFactory.openSession();
		StudentMapper studentMapper=session.getMapper(StudentMapper.class);
		HashMap<String,Object> studentMap=new HashMap<>();
		studentMap.put("stuName", "lj"); 
		studentMap.put("stuAge", 2);
		
		List<Student> students=studentMapper.selectStudentBystuNameOrstuAgeWithHashMap (studentMap);//接口中的方法
		System.out.println(students);	
		session.close();
	}
	
	    //查询学生总数
		public static void selectStudentCount() throws IOException {
			Reader reader = Resources.getResourceAsReader("config.xml");
			SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
			SqlSession session=sessionFactory.openSession();
			StudentMapper studentMapper=session.getMapper(StudentMapper.class);
			int count=studentMapper.selectStudentCount();
			System.out.println(count);	
			session.close();
		}
		
		//根据地址查询学生（嵌套查询），输入是学生
		public static void selectStudentByAddress() throws IOException {
			Reader reader = Resources.getResourceAsReader("config.xml");
			SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
			SqlSession session=sessionFactory.openSession();
			//		String statement="thirdclass.PersonMapper.insertStudent";//statement注明要执行的sql ,用下面的StudentMapper来代替statement
			StudentMapper studentMapper=session.getMapper(StudentMapper.class);
			Address address=new Address();
			address.setHomeaddress("xa");
			address.setSchooladdress("la");
			List<Student> students=studentMapper.selectStudentByAddress(address);//接口中的方法
			System.out.println(students);	
			session.close();
		}
	
	//调用存储过程，查找某个年级的总人数
		public static void querystudentByGrade() throws IOException {
			Reader reader = Resources.getResourceAsReader("config.xml");
			SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
			SqlSession session=sessionFactory.openSession();
			//		String statement="thirdclass.PersonMapper.insertStudent";//statement注明要执行的sql ,用下面的StudentMapper来代替statement
			StudentMapper studentMapper=session.getMapper(StudentMapper.class);
			Map<String,Object> studentMap=new HashMap<>();//用map给存储过程输入参数
			
			studentMap.put("grade", "6");//给存储过程的输入grade输入6
			//返回值count不能直接调用int count=studentMapper.querystudentByGrade(studentMap);
			studentMapper.querystudentByGrade(studentMap);
			//获取存储过程的输出参数
			Object count = studentMap.get("scount");
			System.out.println(count);	
			session.close();
		}
		
		//输出为hashmap类型
        public static void selectStudentOutHashMap() throws IOException {
			Reader reader = Resources.getResourceAsReader("config.xml");
			SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
			SqlSession session=sessionFactory.openSession();
	        StudentMapper studentMapper=session.getMapper(StudentMapper.class);
	        HashMap<String, Object> student=studentMapper.selectStudentOutHashMap();
			System.out.println(student);
			session.close();
		}
		
        //使用hashmap输出多个学生的多个对象
        public static void selectAllStudentHashMap() throws IOException {
			Reader reader = Resources.getResourceAsReader("config.xml");
			SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
			SqlSession session=sessionFactory.openSession();
	        StudentMapper studentMapper=session.getMapper(StudentMapper.class);
	        List<HashMap<String, Object>> student=studentMapper.selectAllStudentHashMap();
			System.out.println(student+"\n");
			session.close();
		}
        
        //带sql标签的动态sql查询
        public static void selectWithSqlTag() throws IOException {
			Reader reader = Resources.getResourceAsReader("config.xml");
			SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
			SqlSession session=sessionFactory.openSession();
	        StudentMapper studentMapper=session.getMapper(StudentMapper.class);
	        Student student=new Student();
	        student.setStuAge(23);
	        student.setStuName("lj");
	        Student students=studentMapper.selectWithSqlTag(student);
			System.out.println(students);
			session.close();
		}
        
        //一次查询多个学生，属性方式
        public static void selectWithForeach() throws IOException {
			Reader reader = Resources.getResourceAsReader("config.xml");
			SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
			SqlSession session=sessionFactory.openSession();
	        StudentMapper studentMapper=session.getMapper(StudentMapper.class);
	        Grade grade = new Grade();
	        List<Integer> stuNos=new ArrayList<>(); 
            stuNos.add(226);
            stuNos.add(222);
            stuNos.add(322);
            grade.setStuNos(stuNos);
	        List<Student> students=studentMapper.selectWithForeach(grade);
			System.out.println(students);
			session.close();
		}
        
        //一次查询多个学生，数组方式
        public static void selectWithArray() throws IOException {
			Reader reader = Resources.getResourceAsReader("config.xml");
			SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
			SqlSession session=sessionFactory.openSession();
	        StudentMapper studentMapper=session.getMapper(StudentMapper.class);
	        int[] Nos= {226,222,322};
	        List<Student> students=studentMapper.selectWithArray(Nos);
			System.out.println(students);
			session.close();
		}
        
        
        //一次查询多个学生，集合方式
        public static void selectWithList() throws IOException {
			Reader reader = Resources.getResourceAsReader("config.xml");
			SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
			SqlSession session=sessionFactory.openSession();
	        StudentMapper studentMapper=session.getMapper(StudentMapper.class);
	        List<Integer> Nos= new ArrayList<>();
	        Nos.add(222);
	        Nos.add(226);
	        Nos.add(322);
	        List<Student> students=studentMapper.selectWithList(Nos);
			System.out.println(students);
			session.close();
		}
        
        //一次查询多个学生，对象数组方式
        public static void selectWithTargetList() throws IOException {
			Reader reader = Resources.getResourceAsReader("config.xml");
			SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
			SqlSession session=sessionFactory.openSession();
	        StudentMapper studentMapper=session.getMapper(StudentMapper.class);
	        Student stu0=new Student();
	        stu0.setStuNo(222);
	        Student stu1=new Student();
	        stu1.setStuNo(222);
	        Student stu2=new Student();
	        stu2.setStuNo(222);
	        Student[] students=new Student[] {stu0,stu1,stu2};
            studentMapper.selectWithTargetList(students);
	        System.out.println(students);
			session.close();
		}
        
        //关联查询,业务扩展方式 ，查询多张表的信息
        public static void selectConnection() throws IOException {
			Reader reader = Resources.getResourceAsReader("config.xml");
			SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
			SqlSession session=sessionFactory.openSession();
	        StudentMapper studentMapper=session.getMapper(StudentMapper.class);
	        StudentWithCard students=studentMapper.selectConnection(226);
	        System.out.println(students);
			session.close();
		}
        
        //利用mybatis提供的resultmap实现一对一关联查询
        public static void selectConnectionWithresultMap() throws IOException {
			Reader reader = Resources.getResourceAsReader("config.xml");
			SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
			SqlSession session=sessionFactory.openSession();
	        StudentMapper studentMapper=session.getMapper(StudentMapper.class);
	        Student students=studentMapper.selectConnectionWithresultMap(226);
	        System.out.println(students);
			session.close();
		}
        
        //一对多关联查询
        public static void selectOneToMany() throws IOException {
			Reader reader = Resources.getResourceAsReader("config.xml");
			SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
			SqlSession session=sessionFactory.openSession();
	        StudentMapper studentMapper=session.getMapper(StudentMapper.class);
	        //班级
            StudentClass studentClass=studentMapper.selectOneToMany(1);
	        System.out.println(studentClass.getClassId()+";"+studentClass.getClassName());
			//对应的student集合
	        List<Student> students=studentClass.getStudents();
	            for(Student student:students) {
	            	System.out.println(student.getStuNo()+";"+student.getStuName()+";"+student.getStuAge());
	            }
	        session.close();
		}
        
        //使用延迟加载的一对一查询全部学生，并根据需要查询对应的学生证信息
        public static void selectWithLazyload() throws IOException {
			Reader reader = Resources.getResourceAsReader("config.xml");
			SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
			SqlSession session=sessionFactory.openSession();
	        StudentMapper studentMapper=session.getMapper(StudentMapper.class);
	        List<Student> students=studentMapper.selectWithLazyload();
	           for(Student student:students) {
	        	   System.out.println(student.getStuNo()+","+student.getStuName()+","+student.getStuAge());
	        	   
	        	   //获取学生证
	        	   StudentCard stuCard=student.getStudentCard();
	        	   System.out.println(stuCard.getCardId()+","+stuCard.getCardInfo());
	        			   
	           }
			session.close();
		}
       
       
        //带延迟加载的一对多，先查班级然后查班级中的学生
        public static void selectOneToManyWithLazyLoad() throws IOException {
			Reader reader = Resources.getResourceAsReader("config.xml");
			SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
			SqlSession session=sessionFactory.openSession();
	        StudentMapper studentMapper=session.getMapper(StudentMapper.class);
	        //全部班级
            List<StudentClass> studentClass=studentMapper.selectOneToManyWithLazyLoad();
            //遍历
	            for(StudentClass stuclass:studentClass) {
	            	System.out.println(stuclass.getClassId()+";"+stuclass.getClassName());
	            	//遍历输出班级中的全部学生
	            	for(Student stu:stuclass.getStudents()) {
	            		System.out.println(stu.getStuNo()+";"+stu.getStuName()+";"+stu.getStuAge());
	            		
	            	}
	            }
	        session.close();
		}
        
	    //根据address查询学生,输入是级联属性（学生关联的address）
        public static void selectStudentByAddress2() throws IOException {
        	Reader reader = Resources.getResourceAsReader("config.xml");
        	SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
        	SqlSession session=sessionFactory.openSession();
        	//		String statement="thirdclass.PersonMapper.insertStudent";//statement注明要执行的sql ,用下面的StudentMapper来代替statement
        	StudentMapper studentMapper=session.getMapper(StudentMapper.class);
        	Student student=new Student();
        	Address address=new Address();
        	address.setHomeaddress("xa");
        	address.setSchooladdress("la");
        	student.setAddress(address);
        	List<Student> students=studentMapper.selectStudentByAddress2(student);//接口中的方法
        	System.out.println(students);	
        	session.close();
        }
	
	public static void insertStudent() throws IOException {
		Reader reader = Resources.getResourceAsReader("config.xml");
		SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
		SqlSession session=sessionFactory.openSession();
		StudentMapper studentMapper=session.getMapper(StudentMapper.class);
		Student student=new Student(2,22,"jy","python");
		//int count=session.insert(student,student);
		studentMapper.insertStudent(student);
		session.commit();//提交事务
		System.out.println("增加了学生");
		session.close();
	}
	//带转换器的增加
	public static void insertWithConverter() throws IOException {
		Reader reader = Resources.getResourceAsReader("config.xml");
		SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
		SqlSession session=sessionFactory.openSession();
		StudentMapper studentMapper=session.getMapper(StudentMapper.class);
		Student student=new Student(2,22,"jy","python");
		student.setStuSex(true);
		studentMapper.insertWithConverter(student);
		session.commit();//提交事务
		System.out.println("增加了学生");
		session.close();
	}
	
	public static void deleteStudent() throws IOException {
		Reader reader = Resources.getResourceAsReader("config.xml");
		SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
		SqlSession session=sessionFactory.openSession();
        StudentMapper studentMapper=session.getMapper(StudentMapper.class);
        studentMapper.deleteByNo(2);
        session.commit();
		System.out.println("删除成功");
		session.close();
	}
	
	public static void selectAll() throws IOException {
		Reader reader = Resources.getResourceAsReader("config.xml");
		SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
		SqlSession session=sessionFactory.openSession();
        StudentMapper studentMapper=session.getMapper(StudentMapper.class);
		List<Student> students=studentMapper.selectAll();
		System.out.println(students);
		session.close();
	}

	public static void updateStudent() throws IOException {
		Reader reader = Resources.getResourceAsReader("config.xml");
		SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
		SqlSession session=sessionFactory.openSession();
		
        StudentMapper studentMapper=session.getMapper(StudentMapper.class);
		Student student=new Student();
		student.setStuNo(226);//修改226号
		
		//属性重新修改成多少
		student.setStuName("lj");
		student.setStuAge(12);
		student.setStuClass("JAVA");
        
        studentMapper.updateByNo(student);
		session.commit();
		System.out.println("已成功修改");
		session.close();
	}

	public static void main(String[] args) throws IOException{
		selectStudentOne();
		//insertStudent();
		//deleteStudent();
		//selectAll();
		//updateStudent();
		//selectOne();
		//insertWithConverter();
		//selectStudentOrderByName();
		//selectStudentOrderByColumn();
		//selectStudentBystuNameOrstuAge();
		//selectStudentBystuNameOrstuAgeWithHashMap();
		//selectStudentByAddress();
		//selectStudentByAddress2();
		//selectStudentCount();
		//selectStudentOutHashMap();
		//selectAllStudentHashMap();
		//selectWithSqlTag();
		//selectWithForeach();
		//selectWithArray();
		//selectWithList();
		//selectWithTargetList();
		//selectConnection();
		//selectConnectionWithresultMap();
		//selectOneToMany();
		//selectWithLazyload();
		//selectOneToManyWithLazyLoad();
	}

}

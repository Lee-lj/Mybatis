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
		//		String statement="thirdclass.PersonMapper.insertStudent";//statementע��Ҫִ�е�sql ,�������StudentMapper������statement
		StudentMapper studentMapper=session.getMapper(StudentMapper.class);
		Student student=studentMapper.selectByNo(226);//�ӿ��еķ���
		System.out.println(student.getStuNo());	
		session.close();
		//��ѯ�ڶ�����֤��������
		/*SqlSession session1=sessionFactory.openSession();
		StudentMapper studentMapper1=session1.getMapper(StudentMapper.class);
		Student student1=studentMapper.selectByNo(226);
		System.out.println(student1.getStuNo());	
		session1.close();*/
	}
	
	//ʹ��ת�����Ĳ�ѯ�����ݿ��е�O,1��java�з���true��false����ʾ��Ů
	public static void selectOne() throws IOException {
		Reader reader = Resources.getResourceAsReader("config.xml");
		SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
		SqlSession session=sessionFactory.openSession();
		//		String statement="thirdclass.PersonMapper.insertStudent";//statementע��Ҫִ�е�sql ,�������StudentMapper������statement
		StudentMapper studentMapper=session.getMapper(StudentMapper.class);
		Student student=studentMapper.selectOne(226);//�ӿ��еķ���
		System.out.println(student);	
		session.close();
	}
	//��ѯȫ��ѧ������������������̬����
	public static void selectStudentOrderByName() throws IOException {
		Reader reader = Resources.getResourceAsReader("config.xml");
		SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
		SqlSession session=sessionFactory.openSession();
		//		String statement="thirdclass.PersonMapper.insertStudent";//statementע��Ҫִ�е�sql ,�������StudentMapper������statement
		StudentMapper studentMapper=session.getMapper(StudentMapper.class);
		List<Student> student=studentMapper.selectStudentOrderByName();//�ӿ��еķ���
		System.out.println(student);	
		session.close();
	}
	//��ѯȫ��ѧ������������������̬����
	public static void selectStudentOrderByColumn() throws IOException {
		Reader reader = Resources.getResourceAsReader("config.xml");
		SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
		SqlSession session=sessionFactory.openSession();
		//		String statement="thirdclass.PersonMapper.insertStudent";//statementע��Ҫִ�е�sql ,�������StudentMapper������statement
		StudentMapper studentMapper=session.getMapper(StudentMapper.class);
		List<Student> student=studentMapper.selectStudentOrderByColumn("stuNo");//�ӿ��еķ���
		System.out.println(student);	
		session.close();
	}
	//����ѧ�Ż���������ѯѧ��
	public static void selectStudentBystuNameOrstuAge() throws IOException {
		Reader reader = Resources.getResourceAsReader("config.xml");
		SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
		SqlSession session=sessionFactory.openSession();
		//		String statement="thirdclass.PersonMapper.insertStudent";//statementע��Ҫִ�е�sql ,�������StudentMapper������statement
		StudentMapper studentMapper=session.getMapper(StudentMapper.class);
		Student student=new Student();
		student.setStuAge(22);
		student.setStuName("%l%");
		List<Student> students=studentMapper.selectStudentBystuNameOrstuAge(student);//�ӿ��еķ���
		System.out.println(students);	
		session.close();
	}
	
	//����ѧ�Ż���������ѯѧ��,ʹ��hashMap�����������hashmap��
	public static void selectStudentBystuNameOrstuAgeWithHashMap() throws IOException {
		Reader reader = Resources.getResourceAsReader("config.xml");
		SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
		SqlSession session=sessionFactory.openSession();
		StudentMapper studentMapper=session.getMapper(StudentMapper.class);
		HashMap<String,Object> studentMap=new HashMap<>();
		studentMap.put("stuName", "lj"); 
		studentMap.put("stuAge", 2);
		
		List<Student> students=studentMapper.selectStudentBystuNameOrstuAgeWithHashMap (studentMap);//�ӿ��еķ���
		System.out.println(students);	
		session.close();
	}
	
	    //��ѯѧ������
		public static void selectStudentCount() throws IOException {
			Reader reader = Resources.getResourceAsReader("config.xml");
			SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
			SqlSession session=sessionFactory.openSession();
			StudentMapper studentMapper=session.getMapper(StudentMapper.class);
			int count=studentMapper.selectStudentCount();
			System.out.println(count);	
			session.close();
		}
		
		//���ݵ�ַ��ѯѧ����Ƕ�ײ�ѯ����������ѧ��
		public static void selectStudentByAddress() throws IOException {
			Reader reader = Resources.getResourceAsReader("config.xml");
			SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
			SqlSession session=sessionFactory.openSession();
			//		String statement="thirdclass.PersonMapper.insertStudent";//statementע��Ҫִ�е�sql ,�������StudentMapper������statement
			StudentMapper studentMapper=session.getMapper(StudentMapper.class);
			Address address=new Address();
			address.setHomeaddress("xa");
			address.setSchooladdress("la");
			List<Student> students=studentMapper.selectStudentByAddress(address);//�ӿ��еķ���
			System.out.println(students);	
			session.close();
		}
	
	//���ô洢���̣�����ĳ���꼶��������
		public static void querystudentByGrade() throws IOException {
			Reader reader = Resources.getResourceAsReader("config.xml");
			SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
			SqlSession session=sessionFactory.openSession();
			//		String statement="thirdclass.PersonMapper.insertStudent";//statementע��Ҫִ�е�sql ,�������StudentMapper������statement
			StudentMapper studentMapper=session.getMapper(StudentMapper.class);
			Map<String,Object> studentMap=new HashMap<>();//��map���洢�����������
			
			studentMap.put("grade", "6");//���洢���̵�����grade����6
			//����ֵcount����ֱ�ӵ���int count=studentMapper.querystudentByGrade(studentMap);
			studentMapper.querystudentByGrade(studentMap);
			//��ȡ�洢���̵��������
			Object count = studentMap.get("scount");
			System.out.println(count);	
			session.close();
		}
		
		//���Ϊhashmap����
        public static void selectStudentOutHashMap() throws IOException {
			Reader reader = Resources.getResourceAsReader("config.xml");
			SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
			SqlSession session=sessionFactory.openSession();
	        StudentMapper studentMapper=session.getMapper(StudentMapper.class);
	        HashMap<String, Object> student=studentMapper.selectStudentOutHashMap();
			System.out.println(student);
			session.close();
		}
		
        //ʹ��hashmap������ѧ���Ķ������
        public static void selectAllStudentHashMap() throws IOException {
			Reader reader = Resources.getResourceAsReader("config.xml");
			SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
			SqlSession session=sessionFactory.openSession();
	        StudentMapper studentMapper=session.getMapper(StudentMapper.class);
	        List<HashMap<String, Object>> student=studentMapper.selectAllStudentHashMap();
			System.out.println(student+"\n");
			session.close();
		}
        
        //��sql��ǩ�Ķ�̬sql��ѯ
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
        
        //һ�β�ѯ���ѧ�������Է�ʽ
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
        
        //һ�β�ѯ���ѧ�������鷽ʽ
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
        
        
        //һ�β�ѯ���ѧ�������Ϸ�ʽ
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
        
        //һ�β�ѯ���ѧ�����������鷽ʽ
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
        
        //������ѯ,ҵ����չ��ʽ ����ѯ���ű����Ϣ
        public static void selectConnection() throws IOException {
			Reader reader = Resources.getResourceAsReader("config.xml");
			SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
			SqlSession session=sessionFactory.openSession();
	        StudentMapper studentMapper=session.getMapper(StudentMapper.class);
	        StudentWithCard students=studentMapper.selectConnection(226);
	        System.out.println(students);
			session.close();
		}
        
        //����mybatis�ṩ��resultmapʵ��һ��һ������ѯ
        public static void selectConnectionWithresultMap() throws IOException {
			Reader reader = Resources.getResourceAsReader("config.xml");
			SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
			SqlSession session=sessionFactory.openSession();
	        StudentMapper studentMapper=session.getMapper(StudentMapper.class);
	        Student students=studentMapper.selectConnectionWithresultMap(226);
	        System.out.println(students);
			session.close();
		}
        
        //һ�Զ������ѯ
        public static void selectOneToMany() throws IOException {
			Reader reader = Resources.getResourceAsReader("config.xml");
			SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
			SqlSession session=sessionFactory.openSession();
	        StudentMapper studentMapper=session.getMapper(StudentMapper.class);
	        //�༶
            StudentClass studentClass=studentMapper.selectOneToMany(1);
	        System.out.println(studentClass.getClassId()+";"+studentClass.getClassName());
			//��Ӧ��student����
	        List<Student> students=studentClass.getStudents();
	            for(Student student:students) {
	            	System.out.println(student.getStuNo()+";"+student.getStuName()+";"+student.getStuAge());
	            }
	        session.close();
		}
        
        //ʹ���ӳټ��ص�һ��һ��ѯȫ��ѧ������������Ҫ��ѯ��Ӧ��ѧ��֤��Ϣ
        public static void selectWithLazyload() throws IOException {
			Reader reader = Resources.getResourceAsReader("config.xml");
			SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
			SqlSession session=sessionFactory.openSession();
	        StudentMapper studentMapper=session.getMapper(StudentMapper.class);
	        List<Student> students=studentMapper.selectWithLazyload();
	           for(Student student:students) {
	        	   System.out.println(student.getStuNo()+","+student.getStuName()+","+student.getStuAge());
	        	   
	        	   //��ȡѧ��֤
	        	   StudentCard stuCard=student.getStudentCard();
	        	   System.out.println(stuCard.getCardId()+","+stuCard.getCardInfo());
	        			   
	           }
			session.close();
		}
       
       
        //���ӳټ��ص�һ�Զ࣬�Ȳ�༶Ȼ���༶�е�ѧ��
        public static void selectOneToManyWithLazyLoad() throws IOException {
			Reader reader = Resources.getResourceAsReader("config.xml");
			SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
			SqlSession session=sessionFactory.openSession();
	        StudentMapper studentMapper=session.getMapper(StudentMapper.class);
	        //ȫ���༶
            List<StudentClass> studentClass=studentMapper.selectOneToManyWithLazyLoad();
            //����
	            for(StudentClass stuclass:studentClass) {
	            	System.out.println(stuclass.getClassId()+";"+stuclass.getClassName());
	            	//��������༶�е�ȫ��ѧ��
	            	for(Student stu:stuclass.getStudents()) {
	            		System.out.println(stu.getStuNo()+";"+stu.getStuName()+";"+stu.getStuAge());
	            		
	            	}
	            }
	        session.close();
		}
        
	    //����address��ѯѧ��,�����Ǽ������ԣ�ѧ��������address��
        public static void selectStudentByAddress2() throws IOException {
        	Reader reader = Resources.getResourceAsReader("config.xml");
        	SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
        	SqlSession session=sessionFactory.openSession();
        	//		String statement="thirdclass.PersonMapper.insertStudent";//statementע��Ҫִ�е�sql ,�������StudentMapper������statement
        	StudentMapper studentMapper=session.getMapper(StudentMapper.class);
        	Student student=new Student();
        	Address address=new Address();
        	address.setHomeaddress("xa");
        	address.setSchooladdress("la");
        	student.setAddress(address);
        	List<Student> students=studentMapper.selectStudentByAddress2(student);//�ӿ��еķ���
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
		session.commit();//�ύ����
		System.out.println("������ѧ��");
		session.close();
	}
	//��ת����������
	public static void insertWithConverter() throws IOException {
		Reader reader = Resources.getResourceAsReader("config.xml");
		SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
		SqlSession session=sessionFactory.openSession();
		StudentMapper studentMapper=session.getMapper(StudentMapper.class);
		Student student=new Student(2,22,"jy","python");
		student.setStuSex(true);
		studentMapper.insertWithConverter(student);
		session.commit();//�ύ����
		System.out.println("������ѧ��");
		session.close();
	}
	
	public static void deleteStudent() throws IOException {
		Reader reader = Resources.getResourceAsReader("config.xml");
		SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
		SqlSession session=sessionFactory.openSession();
        StudentMapper studentMapper=session.getMapper(StudentMapper.class);
        studentMapper.deleteByNo(2);
        session.commit();
		System.out.println("ɾ���ɹ�");
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
		student.setStuNo(226);//�޸�226��
		
		//���������޸ĳɶ���
		student.setStuName("lj");
		student.setStuAge(12);
		student.setStuClass("JAVA");
        
        studentMapper.updateByNo(student);
		session.commit();
		System.out.println("�ѳɹ��޸�");
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

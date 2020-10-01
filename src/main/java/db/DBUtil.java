package db;

import model.dao.StudentDao;
import model.entites.Student;

public class DBUtil {
	
	public static <T> void refleshData(Student entity) {
		DBFactory.getConnection().getTransaction().begin();
		try {
			entity = new StudentDao(DBFactory.getConnection()).findById(entity.getId());
		} catch (DbException e) {
			entity = null;
			e.printStackTrace();
		}
		DBFactory.getConnection().refresh(entity);
		DBFactory.getConnection().getTransaction().commit();
	}

}

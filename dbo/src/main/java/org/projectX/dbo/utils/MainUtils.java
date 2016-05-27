package org.projectX.dbo.utils;

import org.hibernate.Session;
import org.projectX.dbo.customException.ProjectXException;
import org.projectX.dbo.profile.dao.ProfileDAOImplementations;
import org.projectX.dbo.profile.dao.request.InsertUserProfileRequestTO;
import org.projectX.dbo.utils.InterFace.ProjectX;

public class MainUtils {

	
	
	public static void main(String[] args) throws ProjectXException {
		// TODO Auto-generated method stub
		ProjectX projectX = ProjectXHibernateSpringSessionFactoryUtils.INSTANCE;
		Session session = projectX.getSessionFactoryInstance().openSession();
		ProjectXHibernateSpringSessionFactoryUtils.INSTANCE.getApplicationContextInstance();
		session.beginTransaction();
		/*InsertOccupationRequestTO insertOccupationRequestTO = new InsertOccupationRequestTO();
		insertOccupationRequestTO.setOccupationType("");
		CommonDAO commonDAO = (CommonDAO)ProjectXHibernateSpringSessionFactoryUtils.INSTANCE.getApplicationContextInstance().getBean("commonDAO");
		try{
			commonDAO.insertOccupation(insertOccupationRequestTO, session);
		}catch(ProjectXException p){
			System.out.println(p.getMessage());
		}*/
		InsertUserProfileRequestTO insertUserProfileRequestTO = new InsertUserProfileRequestTO("Mitisha","Patel",null,"bhevesh0@gmail.com");
		insertUserProfileRequestTO.setMiddleName("R");
		insertUserProfileRequestTO.setEmployer("KPMG");
		insertUserProfileRequestTO.setOccupationId(3);
		ProfileDAOImplementations profileDAO = (ProfileDAOImplementations)ProjectXHibernateSpringSessionFactoryUtils.INSTANCE.getApplicationContextInstance().getBean("profileDAO");
		try{
			profileDAO.insertUserProfile(insertUserProfileRequestTO, session);
		}catch(ProjectXException p){
			System.out.println(p.getMessage());
		}
		session.getTransaction().commit();
		session.close();

	}

}

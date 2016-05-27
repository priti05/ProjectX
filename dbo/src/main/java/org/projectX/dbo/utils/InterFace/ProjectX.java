package org.projectX.dbo.utils.InterFace;

import org.hibernate.SessionFactory;
import org.springframework.context.support.AbstractApplicationContext;

public interface ProjectX {

	public SessionFactory getSessionFactoryInstance();
	public AbstractApplicationContext getApplicationContextInstance();
	
	
	
}

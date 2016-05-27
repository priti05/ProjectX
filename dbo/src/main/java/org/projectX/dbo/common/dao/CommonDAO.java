package org.projectX.dbo.common.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.projectX.dbo.common.dao.helper.CommonDAOConstant;
import org.projectX.dbo.common.dao.helper.CommonDAOHelper;
import org.projectX.dbo.common.dao.request.InsertOccupationRequestTO;
import org.projectX.dbo.common.dao.response.InsertOccupationResponseTO;
import org.projectX.dbo.common.dto.Occupation;
import org.projectX.dbo.customException.ProjectXException;
import org.projectX.dbo.utils.ProjectXUtils;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>This class performs <em>CRUD</em> operation on Common Database Entity of <em>ProjectX</em></p>
 *
 * @author  Priti Patel
 * @see     org.projectX.dbo.common.dao.helper.CommonDAOHelper
 * @see     org.projectX.dbo.common.dao.helper.CommonDAOErrorGenerator
 * @since   0.0.1
 */
public class CommonDAO {

	@Autowired
	private CommonDAOHelper commonDAOHelper;
	
	@Autowired
	private ProjectXUtils projectXUtils;
	
	private Logger logger = LogManager.getLogger(CommonDAO.class);
	
	/**
     * <p>Populate OCCUPATION table ({@link Occupation}) with new entry</p>
     *
     * @param  {@link InsertOccupationRequestTO}
     * @param  {{@link Session}
     * @since  0.0.1
     * @return {@link InsertOccupationResponseTO}
     * @throws {@link ProjectXException}
     * 
     */
	public InsertOccupationResponseTO insertOccupation(InsertOccupationRequestTO insertOccupationRequestTO, Session session) throws ProjectXException{
		logger.info("Inside insertOccupation");
		InsertOccupationResponseTO insertOccupationResponseTO = null;
			try{
				insertOccupationResponseTO  = commonDAOHelper.insertOccupation(insertOccupationRequestTO,session);	
			}catch(ProjectXException p){
				logger.error("Exception at insertOccupation::", p.getMessage());
				logger.error("stack trace::", p);
				throw p;
			}catch(Exception e){
				logger.error("Exception at insertOccupation::", e.getMessage());
				logger.error("stack trace::", e);
				throw projectXUtils.genericError(CommonDAOConstant.PROJX_COMMON_IVLD_000, insertOccupationResponseTO.getProjXTrackingId(), 
						CommonDAOConstant.INSERT_OCCUPATION, null, session);
			}
		logger.info("End of insertOccupation");
		return insertOccupationResponseTO;
			
	}
}

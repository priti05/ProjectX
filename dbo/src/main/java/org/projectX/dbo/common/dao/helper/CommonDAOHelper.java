package org.projectX.dbo.common.dao.helper;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.projectX.dbo.common.dao.CommonDAO;
import org.projectX.dbo.common.dao.request.InsertOccupationRequestTO;
import org.projectX.dbo.common.dao.response.InsertOccupationResponseTO;
import org.projectX.dbo.common.dto.Occupation;
import org.projectX.dbo.common.dto.ProjectXTrackingIdGeneratorTracker;
import org.projectX.dbo.customException.ProjectXException;
import org.projectX.dbo.utils.ProjectXUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>This class has supportive operation for {@link CommonDAO}. It handles all the heavy 
 * lifting. As an Example, Convert request and response to JSON, Create TRCK_ID, update {@link Session} etc...</p>
 *
 * @author  Priti Patel
 * @see     org.projectX.dbo.common.dao.CommonDAO
 * @see     org.projectX.dbo.common.dao.helper.CommonDAOConstant
 * @since   0.0.1
 */
public class CommonDAOHelper {
	
	private Logger logger = LogManager.getLogger(CommonDAOHelper.class);
	
	@Autowired
	private ProjectXUtils projectXUtils;
	
	
	
	/**
     * <p>This is the supportive method of {@link CommonDAO#insertOccupation(InsertOccupationRequestTO, Session)}.
     * It saves {@link Occupation} in {@link Session}</p>
     *
     * @param  {@link InsertOccupationRequestTO}
     * @param  {@link Session}
     * @since  0.0.1
     * @return {@link Occupation}
	 * @throws ProjectXException 
     * 
     */
	public InsertOccupationResponseTO insertOccupation(InsertOccupationRequestTO insertOccupationRequestTO ,Session session) throws ProjectXException{
		String trackingId =null;
		try{
			logger.info("Inside insertOccupation");
			trackingId = projectXUtils.getTrackingId(insertOccupationRequestTO,CommonDAOConstant.INSERT_OCCUPATION,session);
			InsertOccupationResponseTO insertOccupationResponseTO = new InsertOccupationResponseTO(trackingId);
			Occupation occupation = null;
			if(insertOccupationRequestTO!=null){
				String occupationType = insertOccupationRequestTO.getOccupationType();
				if(StringUtils.isNotBlank(occupationType)){
					occupation = new Occupation();
					occupation.setOccupationType(occupationType);
					session.persist(occupation);
				}
			}	
			if(occupation!=null){
				insertOccupationResponseTO.setInserted(true);
				updateTrackingIdTableWithOccupation(trackingId, occupation, insertOccupationResponseTO,session);
			}else{
				logger.warn("Invalid Input");
				throw projectXUtils.genericError(CommonDAOConstant.PROJX_COMMON_IVLD_000, trackingId, 
						CommonDAOConstant.INSERT_OCCUPATION, null, session);
			}
			logger.info("End of insertOccupation");
			return insertOccupationResponseTO;
		}catch(Exception e){
			logger.error("Exception at genericError",e.getMessage());
			logger.error("stack traces::",e);
			throw projectXUtils.genericError(CommonDAOConstant.PROJX_COMMON_GEN_000, trackingId, 
					CommonDAOConstant.INSERT_OCCUPATION, null, session);
		}
	}
	
	/**
     * <p>This method update entry of {@link ProjectXTrackingIdGeneratorTracker} with response and foreign key Entity &
     * flush, refresh session</p>
     *
     * @param  {@link String}  ProjectX's Tracking Id
     * @param  {@link Object} foreign key Entity e.g. {@link Occupation} , {@link LoginBioGraphy}
     * @param  {@link Object} response to convert into JSON e.g. {@link InsertOccupationResponseTO}
     * @param  {@link Session}
     * @since  0.0.1
     * @return Void
     * 
     */
	public void updateTrackingIdTableWithOccupation(String trackingId,  Occupation occupation, InsertOccupationResponseTO insertOccupationResponseTO
			,Session session){
		logger.info("Inside updateTrackingIdTableWithOccupation");
		ProjectXTrackingIdGeneratorTracker projectXTrckIdTracker = 	(ProjectXTrackingIdGeneratorTracker)
				projectXUtils.loadFromSession(trackingId,session,ProjectXTrackingIdGeneratorTracker.class);
		projectXTrckIdTracker.setResponse(projectXUtils.convertObJTOJson(insertOccupationResponseTO));
		logger.info("End of updateTrackingIdTableWithOccupation");
	}
	
		
	

}

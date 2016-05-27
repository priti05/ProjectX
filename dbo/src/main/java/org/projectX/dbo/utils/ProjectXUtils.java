package org.projectX.dbo.utils;

import java.io.Serializable;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.projectX.dbo.common.dao.helper.CommonDAOConstant;
import org.projectX.dbo.common.dto.PXE;
import org.projectX.dbo.common.dto.PXE_Report;
import org.projectX.dbo.common.dto.ProjectXTrackingIdGeneratorTracker;
import org.projectX.dbo.customException.ProjectXException;

import com.google.gson.Gson;

public class ProjectXUtils {
	
	private Logger logger = LogManager.getLogger(ProjectXUtils.class);
	
	
	public String getTrackingId(Object object, String api,Session session) throws ProjectXException{
		String trackingId = null;
		logger.info("Inside getTrackingId");
			String jsonRequest = convertObJTOJson(object);
			Date requestDate = new Date();
			ProjectXTrackingIdGeneratorTracker projectXTrackingIdGeneratorTracker = new ProjectXTrackingIdGeneratorTracker();
			projectXTrackingIdGeneratorTracker.setRequest(jsonRequest);
			projectXTrackingIdGeneratorTracker.setRequestDate(requestDate);
			projectXTrackingIdGeneratorTracker.setApiName(api);
			trackingId = (String)session.save(projectXTrackingIdGeneratorTracker);
			if(logger.isDebugEnabled()){
				logger.debug(trackingId +"::"+ api);
				logger.debug(jsonRequest);
			}
			logger.info("End of getTrackingId");
		return trackingId;
	}
	
	@SuppressWarnings("rawtypes")
	public Object loadFromSession(Object Id, Session session, Class clazz ){
		if(Id!=null){
			return (Object) session.load(clazz, (Serializable)Id);
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public Object getObjectById(Object Id, Session session, Class clazz ){
		if(Id!=null){
			return (Object) session.get(clazz, (Serializable)Id);
		}
		return null;
	}
	
	public String convertObJTOJson(Object response){
		String jsonResponse = "response has null value";
		if(response!=null){
			Gson gson = new Gson();
			jsonResponse = gson.toJson(response);
		}
		logger.info("Object to JSON::" + jsonResponse);
		return jsonResponse;
	}
	
	public  ProjectXException genericError(String errorCode,String trckId, String api ,Object response, Session session) throws ProjectXException{
		try{
			PXE pxe = (PXE)session.get(PXE.class, errorCode);
			PXE_Report pxe_Report = new PXE_Report();
			pxe_Report.setPxe(pxe);
			pxe_Report.setReportedOn(new Date());
			updateTrackingIdTableWithPXEReport(trckId,pxe_Report , response,session);
			return new ProjectXException(api, trckId ,errorCode, pxe.getErrorDecription());
		}catch(Exception e){
			logger.error("Exception at genericError",e.getMessage());
			logger.error("stack traces::",e);
			PXE pxe = new PXE();
			pxe.setErrorCode(CommonDAOConstant.PROJX_ERROR_CODE_NOT_FOUND);
			pxe.setErrorDecription("Looks like There was a error and error code not found in DB, Please check with"
					+ "ProjectX team with TrackingID");
			pxe.setModule("common");
			PXE_Report pxe_Report = new PXE_Report();
			pxe_Report.setPxe(pxe);
			pxe_Report.setReportedOn(new Date());
			updateTrackingIdTableWithPXEReport(trckId,pxe_Report , response,session);
			return new ProjectXException(api, trckId ,CommonDAOConstant.PROJX_ERROR_CODE_NOT_FOUND
					, errorCode+" not found in DB, Please enter");
		}
	}
	
	
	public void updateTrackingIdTableWithPXEReport(String trackingId,  PXE_Report pxe_Report, Object response ,Session session){
		logger.info("Inside updateTrackingIdTableWithPXEReport");
		ProjectXTrackingIdGeneratorTracker projectXTrckIdTracker = 	(ProjectXTrackingIdGeneratorTracker)
				loadFromSession(trackingId,session,ProjectXTrackingIdGeneratorTracker.class);
		pxe_Report.setProjectXTrackingIdGeneratorTracker(projectXTrckIdTracker);
		projectXTrckIdTracker.setPxe_report(pxe_Report);
		projectXTrckIdTracker.setResponse(convertObJTOJson(response));
		logger.info("End of updateTrackingIdTableWithPXEReport");
	}
	
	public void updateTrackingIdTableWithUser(String trackingId, Object response,Session session){
		logger.info("Inside updateTrackingIdTableWithOccupation");
		ProjectXTrackingIdGeneratorTracker projectXTrckIdTracker = 	(ProjectXTrackingIdGeneratorTracker)loadFromSession(trackingId,session,
				ProjectXTrackingIdGeneratorTracker.class);
		projectXTrckIdTracker.setResponse(convertObJTOJson(response));
		logger.info("End of updateTrackingIdTableWithOccupation");
	}

}

package com.reusecompany.oslc.km.services.utils;

import org.eclipse.lyo.oslc4j.core.model.OslcConstants;

public interface OSLC_KM{
	public static String PATH_KNOWLEDGE_MANAGEMENT_REQUEST = "knowledgeManagementRequest";
	public static final String HDR_OSLC_VERSION = "OSLC-Core-Version";
	public static final String NEXT_PAGE = "org.eclipse.lyo.oslc4j.bugzilla.NextPage";
	/**OSLC Core**/
	public static final String OSLC_CORE = OslcConstants.OSLC_CORE_NAMESPACE_PREFIX;
	public static final String OSLC_CORE_SHORT_TITLE = OSLC_CORE+"shortTitle";

}
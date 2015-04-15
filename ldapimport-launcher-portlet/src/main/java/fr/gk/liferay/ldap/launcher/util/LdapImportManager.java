package fr.gk.liferay.ldap.launcher.util;

import javax.portlet.PortletPreferences;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;

public final class LdapImportManager {
	
	private static Log logger = LogFactoryUtil.getLog(LdapImportManager.class);

	public static void activateAndStartImport(long companyId) {
		try {
			logger.info("LDAP Import Launch : started");
			long lStartTime = System.currentTimeMillis();
			
			PortletPreferences preferences = PrefsPropsUtil.getPreferences(companyId, false);
			
			String isImportEnabled = preferences.getValue(PropsKeys.LDAP_IMPORT_ENABLED, "Error");
			if (! isImportEnabled.equalsIgnoreCase("true")) {
				preferences.setValue(PropsKeys.LDAP_IMPORT_ENABLED, "true");
				preferences.store();				
			}
													
			com.liferay.portal.security.ldap.PortalLDAPImporterUtil.importFromLDAP();
			
			//Then we disable the import if it was disabled by default
			if ( isImportEnabled.equalsIgnoreCase("false")) {
				preferences.setValue(PropsKeys.LDAP_IMPORT_ENABLED, "false");
				preferences.store();
			}
		
			long lEndTime = System.currentTimeMillis();
			long difference = lEndTime - lStartTime;
						
			logger.info("LDAP Import Launch : finished (took " + difference + " ms)");
			
		} catch (Exception e) {
			logger.error("Error while starting manually LDAP Import" + e.getMessage());
			e.printStackTrace();
		}
	}
}

package fr.gk.liferay.ldap.launcher.cron;

import java.util.Date;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.util.PortalUtil;

import fr.gk.liferay.ldap.launcher.util.LdapImportManager;


public class LDAPImportScheduler implements MessageListener{

	private static Log logger = LogFactoryUtil.getLog(LDAPImportScheduler.class);
	
	@Override
	public void receive(Message arg0) throws MessageListenerException {
		logger.info("LDAP Import Launcher : starting cron at " + new Date());
		// sauvegarde des nouvelles statistiques
		long companyId = PortalUtil.getDefaultCompanyId();
		LdapImportManager.activateAndStartImport(companyId);
	}

	
}

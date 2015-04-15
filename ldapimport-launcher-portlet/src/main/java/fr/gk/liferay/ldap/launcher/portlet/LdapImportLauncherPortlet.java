package fr.gk.liferay.ldap.launcher.portlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.mvc.MVCPortlet;

import fr.gk.liferay.ldap.launcher.util.LdapImportManager;


public class LdapImportLauncherPortlet extends MVCPortlet {

	private static Log logger = LogFactoryUtil.getLog(LdapImportLauncherPortlet.class);

	@Override
	public void doView(RenderRequest renderRequest,
			RenderResponse renderResponse) throws IOException, PortletException {
		super.doView(renderRequest, renderResponse);
	}

	public void launchImport(ActionRequest actionRequest,
			ActionResponse actionResponse) throws IOException, PortletException {
		
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);			
			long companyId = themeDisplay.getCompanyId();
			logger.info("LDAP Import Launcher : launched manually for companyId " + companyId);
			LdapImportManager.activateAndStartImport(companyId);			
	}
}
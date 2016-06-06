package com.hresource.app.server.repository.appbasicsetup.usermanagement;
import com.hresource.app.shared.appbasicsetup.usermanagement.ArtAppNotificationTemplate;



public interface ArtAppNotificationTemplateRepository {

	public ArtAppNotificationTemplate findById(String templateId) throws Exception;
}

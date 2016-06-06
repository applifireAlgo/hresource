package com.hresource.app.server.service.appbasicsetup.usermanagement;
import com.hresource.app.config.annotation.Complexity;
import com.hresource.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import com.hresource.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import java.util.List;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "2", comments = "Service for UserAccessDomain Master table Entity", complexity = Complexity.LOW)
public abstract class UserAccessDomainService {

    public HttpEntity<ResponseBean> findAll() throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(UserAccessDomain entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(List<UserAccessDomain> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> delete(String id) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(UserAccessDomain entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(List<UserAccessDomain> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception {
        return null;
    }
}

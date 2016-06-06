package com.hresource.app.server.service.humanresourceboundedcontext.employee;
import com.hresource.app.config.annotation.Complexity;
import com.hresource.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import com.hresource.app.shared.humanresourceboundedcontext.employee.EmployeeInfo;
import java.util.List;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "2", comments = "Service for EmployeeInfo Master table Entity", complexity = Complexity.LOW)
public abstract class EmployeeInfoService {

    public HttpEntity<ResponseBean> findAll() throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(EmployeeInfo entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(List<EmployeeInfo> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> delete(String id) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(EmployeeInfo entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(List<EmployeeInfo> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findByDeptTypeCode(FindByBean findByBean) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findByDesignationTypeCode(FindByBean findByBean) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findByJobTypeCode(FindByBean findByBean) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findByContactId(FindByBean findByBean) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception {
        return null;
    }
}

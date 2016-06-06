package com.hresource.app.server.repository.humanresourceboundedcontext.employee;
import com.hresource.app.server.repository.core.SearchInterface;
import com.hresource.app.config.annotation.Complexity;
import com.hresource.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "2", comments = "Repository for DesignationType Master table Entity", complexity = Complexity.LOW)
public interface DesignationTypeRepository<T> extends SearchInterface {

    public List<T> findAll() throws Exception;

    public T save(T entity) throws Exception;

    public List<T> save(List<T> entity) throws Exception;

    public void delete(String id) throws Exception;

    public void update(T entity) throws Exception;

    public void update(List<T> entity) throws Exception;

    public T findById(String designationTypeCode) throws Exception;
}

package com.hresource.app.server.repository.humanresourceboundedcontext.attendance;
import com.hresource.app.server.repository.core.SearchInterface;
import com.hresource.app.config.annotation.Complexity;
import com.hresource.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "2", comments = "Repository for AttendanceSummary Master table Entity", complexity = Complexity.LOW)
public interface AttendanceSummaryRepository<T> extends SearchInterface {

    public List<T> findAll() throws Exception;

    public T save(T entity) throws Exception;

    public List<T> save(List<T> entity) throws Exception;

    public void delete(String id) throws Exception;

    public void update(T entity) throws Exception;

    public void update(List<T> entity) throws Exception;

    public List<T> findByEmpId(String empId) throws Exception;

    public T findById(String attendenceSummaryId) throws Exception;
}

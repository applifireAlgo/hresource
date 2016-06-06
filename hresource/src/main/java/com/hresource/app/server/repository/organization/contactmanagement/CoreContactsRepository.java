package com.hresource.app.server.repository.organization.contactmanagement;
import com.hresource.app.server.repository.core.SearchInterface;
import com.hresource.app.config.annotation.Complexity;
import com.hresource.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;
import com.hresource.app.shared.organization.locationmanagement.Address;
import com.hresource.app.shared.organization.contactmanagement.CommunicationData;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "2", comments = "Repository for CoreContacts Transaction table", complexity = Complexity.MEDIUM)
public interface CoreContactsRepository<T> extends SearchInterface {

    public List<T> findAll() throws Exception;

    public T save(T entity) throws Exception;

    public List<T> save(List<T> entity) throws Exception;

    public void delete(String id) throws Exception;

    public void deleteAddress(List<Address> address);

    public void deleteCommunicationData(List<CommunicationData> communicationdata);

    public void update(T entity) throws Exception;

    public void update(List<T> entity) throws Exception;

    public List<T> findByTitleId(String titleId) throws Exception;

    public List<T> findByNativeLanguageCode(String nativeLanguageCode) throws Exception;

    public List<T> findByGenderId(String genderId) throws Exception;

    public List<T> findByTimeZoneId(String timeZoneId) throws Exception;

    public T findById(String contactId) throws Exception;
}

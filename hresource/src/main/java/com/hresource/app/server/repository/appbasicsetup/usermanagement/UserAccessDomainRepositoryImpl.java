package com.hresource.app.server.repository.appbasicsetup.usermanagement;
import com.hresource.app.server.repository.core.SearchInterfaceImpl;
import com.hresource.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import org.springframework.stereotype.Repository;
import com.hresource.app.config.annotation.Complexity;
import com.hresource.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spartan.pluggable.logger.alarms.AppAlarm;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import java.lang.Override;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Repository
@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "2", comments = "Repository for UserAccessDomain Master table Entity", complexity = Complexity.LOW)
public class UserAccessDomainRepositoryImpl extends SearchInterfaceImpl implements UserAccessDomainRepository<UserAccessDomain> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Override
    @Transactional
    public List<UserAccessDomain> findAll() throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        java.util.List<com.hresource.app.shared.appbasicsetup.usermanagement.UserAccessDomain> query = emanager.createQuery("select u from UserAccessDomain u where u.systemInfo.activeStatus=1").getResultList();
        Log.out.println("ABSUM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserAccessDomainRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    @Override
    @Transactional
    public UserAccessDomain save(UserAccessDomain entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("ABSUM321990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserAccessDomainRepositoryImpl", "save", entity);
        return entity;
    }

    @Override
    @Transactional
    public List<UserAccessDomain> save(List<UserAccessDomain> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.hresource.app.shared.appbasicsetup.usermanagement.UserAccessDomain obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("ABSUM321990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserAccessDomainRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    @Transactional
    @Override
    public void delete(String id) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        com.hresource.app.shared.appbasicsetup.usermanagement.UserAccessDomain s = emanager.find(com.hresource.app.shared.appbasicsetup.usermanagement.UserAccessDomain.class, id);
        emanager.remove(s);
        Log.out.println("ABSUM328990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserAccessDomainRepositoryImpl", "delete", "Record Deleted");
    }

    @Override
    @Transactional
    public void update(UserAccessDomain entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("ABSUM322990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserAccessDomainRepositoryImpl", "update", entity);
    }

    @Override
    @Transactional
    public void update(List<UserAccessDomain> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.hresource.app.shared.appbasicsetup.usermanagement.UserAccessDomain obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("ABSUM322990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserAccessDomainRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    @Transactional
    public UserAccessDomain findById(String userAccessDomainId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("UserAccessDomain.findById");
        query.setParameter("userAccessDomainId", userAccessDomainId);
        com.hresource.app.shared.appbasicsetup.usermanagement.UserAccessDomain listOfUserAccessDomain = (com.hresource.app.shared.appbasicsetup.usermanagement.UserAccessDomain) query.getSingleResult();
        Log.out.println("ABSUM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserAccessDomainRepositoryImpl", "findById", "Total Records Fetched = " + listOfUserAccessDomain);
        return listOfUserAccessDomain;
    }
}

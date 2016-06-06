package com.hresource.app.server.service.appbasicsetup.usermanagement;
import com.hresource.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.hresource.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.hresource.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.hresource.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.hresource.app.server.service.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.springframework.mock.web.MockServletContext;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.spartan.pluggable.logger.event.RequestHeaderBean;
import com.spartan.pluggable.logger.api.RuntimeLogUserInfoBean;
import com.athena.server.pluggable.interfaces.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class UserAccessDomainTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    private static List<EntityTestCriteria> entityContraint;

    @Autowired
    private ArtMethodCallStack methodCallStack;

    protected MockHttpSession session;

    protected MockHttpServletRequest request;

    protected MockHttpServletResponse response;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        MockServletContext mockServletContext = new MockServletContext("file:src/main/webapp");
        try {
            String _path = mockServletContext.getRealPath("/WEB-INF/conf/");
            LogManagerFactory.createLogManager(_path, AppLoggerConstant.LOGGER_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
        session = null;
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        org.springframework.web.context.request.RequestContextHolder.setRequestAttributes(new org.springframework.web.context.request.ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()).requestCompleted();
        org.springframework.web.context.request.RequestContextHolder.resetRequestAttributes();
        request = null;
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo("customer", "AAAAA", request.getRemoteHost());
        org.junit.Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityContraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private UserAccessDomain createUserAccessDomain(Boolean isSave) throws Exception {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainIcon("pd9otXAk2Jl1Q6LiSduVvtGL5ifaUXmVmOCOY3Sb7hBMmLpg9R");
        useraccessdomain.setDomainHelp("LbaY02SsIfu8UTIvj18yGYwBIJw0k99iwcYZTk5XI14r3odfal");
        useraccessdomain.setDomainDescription("XdPPT5yf89wNvGSnXWlgCO5efDbybEXzJgSYhghhCEx2lDbFkg");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainName("Bdn9BnES7DD0W4dTi6pELCRbWOn3HCOWrXCW02eiXb0mnK41A3");
        useraccessdomain.setEntityValidator(entityValidator);
        return useraccessdomain;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessDomain useraccessdomain = createUserAccessDomain(true);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccessdomain.isValid();
            useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            UserAccessDomain useraccessdomain = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            useraccessdomain.setDomainIcon("1lOPYxS0MJY7tbmj82sGjCvloixSzdyVFMWU96w0aJpZMJGnEr");
            useraccessdomain.setVersionId(1);
            useraccessdomain.setDomainHelp("HwZAuj1COPYT0eFVbI3QU9RzlYfDKJ4vn4BiBNm1lMV2A005zC");
            useraccessdomain.setDomainDescription("Zq3E42X7aIG9Yfef8KSzVlB2VzIQ74NdDbiWgkmIng5uy77l7Q");
            useraccessdomain.setUserAccessDomain(96336);
            useraccessdomain.setDomainName("7L226q7LJHcCfSfDwAJ1qpkENQHtAqegn8YErjY7oNt7m46R12");
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccessdomainRepository.update(useraccessdomain);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessDomain(EntityTestCriteria contraints, UserAccessDomain useraccessdomain) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccessdomainRepository.save(useraccessdomain);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessDomain", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessDomain", 151825));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "domainName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "domainName", "Zih3fjdl47CMj0Imu6ITyfcCpZETPQ9J2bD91vMPzcrtdbWRafjzZ27yTFWuWrlVfc51T1jIQc8mfkuhKC269IWUEKiWyLeO9O8bEIVvuHJfvOaANTAxbohK5uTbgY33ePIBaqziuOTvBbD29wfHdVDrZFQvQmbEyLksLg8NwffRkqfLh7oAh5gByTtVsQRa8z0pzxT6YrREP8B8jJfd71T8vU4Xlcu45FAC7OT9Dmf9KsJg3LZlGshKINXsWskg5"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "domainDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "domainDescription", "3vdGvYcDe3e2qYIqIgbqFV5pY6PxmPs08jm5VaZg3HiSYj6TWGiYopOcs9AUvIfSStkPJ65FPlY0qtlm1RMkEHtONGSOozBiBrYkrm08xBpY7Bz1T5UNIGiEnHTcoD0jZ9GOatYk8MCnV6qbq8BmYtZItcvZF6ybtfMrP0IWgvpygd62zw09LYV3VEMTa3lG3lnaoNQiBm7bl8FwG5mhUPsRXZSsmivjJW7MipE85q1HlZX6XDS8BjHYLGmPNmXOm"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainIcon", "K8EcpmxAsHmbPoX1Dr24RMx8HCyJBeWrJA6fPZeeZjtySsSjvV49xhuKU2oUEMChq0pdJuDrFixEV6SMhcvUzoVlTuir5FUyI2IxTLSnA6TWUGs2oxFSMIjsNN45bIQ2DYdUw0k6rbuAmkUAnSqfCqZHNBqPcMeUiol3Ea4gvTOTthO4OdQiyGCfTYkAhXqYi9DfJ1LZcEVIRrB58ZPGgyVKUpNdMnTV77ADdBVxmKaKWo9jWzWElPx0ZHLgvyz5C"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessDomain useraccessdomainUnique = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessDomain useraccessdomain = createUserAccessDomain(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccessdomain.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 2:
                        useraccessdomain.setUserAccessDomain(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 4:
                        useraccessdomain.setDomainName(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 6:
                        useraccessdomain.setDomainDescription(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 7:
                        useraccessdomain.setDomainHelp(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 8:
                        useraccessdomain.setDomainIcon(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 9:
                        useraccessdomain.setUserAccessDomain(useraccessdomainUnique.getUserAccessDomain());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            org.junit.Assert.fail();
        }
    }
}

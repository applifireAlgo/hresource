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
import com.hresource.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.hresource.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
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
public class UserAccessLevelTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

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

    private UserAccessLevel createUserAccessLevel(Boolean isSave) throws Exception {
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelDescription("Jh10OKDuUD7shfwwHJAWlRKKxnykLDoZZSnP39dIBGzaMsPP5G");
        useraccesslevel.setLevelIcon("ola6TyOMBfRn6oAv8UCUjVxo0NN0Jp5LD8Q7PAOcdIAVpswAHm");
        useraccesslevel.setLevelHelp("yTZGWbWXw6SwFBENRj8TR5ah24NYneBMwKzSflPqse8YcMdWNL");
        useraccesslevel.setLevelName("Mk4URkEvvdDy58F87KpjXOzXL2AvTj5frYwuSvJhHLyhckCfbl");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setEntityValidator(entityValidator);
        return useraccesslevel;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessLevel useraccesslevel = createUserAccessLevel(true);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccesslevel.isValid();
            useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            UserAccessLevel useraccesslevel = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            useraccesslevel.setLevelDescription("qn3Dn3vHMbvMUWbjAxTFIw8h4pdZkZlkqjLALORxVlWAUz6g42");
            useraccesslevel.setLevelIcon("Pkya6wKLpaHU92OIpVIL52jhsitEOoUCSkGfL3MKpnJPQ3X1qY");
            useraccesslevel.setLevelHelp("KDUZDWlQv5bgLlipMuqHFCB3P0GoRJpVT5FuLvkPflk9eS6ZwU");
            useraccesslevel.setLevelName("5pgQ426bgAhJr06CU4s8AsaNPTvaf3TenHfVOwwdgA8tckltAa");
            useraccesslevel.setVersionId(1);
            useraccesslevel.setUserAccessLevel(72539);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccesslevelRepository.update(useraccesslevel);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessLevel(EntityTestCriteria contraints, UserAccessLevel useraccesslevel) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccesslevelRepository.save(useraccesslevel);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessLevel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessLevel", 195374));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "levelName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "levelName", "UoY7SLdR8bK56SzgcDwAlgubkQRLOTSMT4hxbbO6vYG1BQbjMR0anVtdXjudkeeB1lnAYxht88yhuQ7H6ylA7K5ryede8wUECXI34zs59glZjNJTU2KoA5hC03PsCkk8CGD7LFrq7Op9Ht9DtcWi6zOHr9YAw8G4fHh1fxQl5rtfLOrkErO5d5IhWas6FbKVJx79csOH3mXwxfzzV2B6eBVsgeWkDLWjCDl29u5Lxp0YHqn9iRKJEdPTb7Zy4WmRp"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "levelDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "levelDescription", "Cc6ZmKqfhQ7U9XNMAyNLTofNZYIy94giuyLMKtmPVo4tz8LOb2I7dk3D0iATm53OyEFk2jozHgGWahywxFhoJ36q67ed3kLQdEeK4E5uYyJFEYHJGkw9TnT6tUr4spGqfxzLkboFZheQos1ceK8bS8myn3ZIRxl3JqQr94sq6DOkExlzAOcuodQD6zUP72BVUE7qO3Ap4yt4yHVITkGSMGUdAt3tp8PI6gk2HmAhqho6StyMOjM3yIyOlnYaTcYY0"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelIcon", "HAmPUvcbBOSZRYtHKbZXkGhhN1xF8ZmNbLAswABnei8IRzZYrbqS5qflaRT0NVsvkVRMNaEoARJivBRr4JG90XAPb6RkRPkBEWrfi3stLAYnOCbYEFVIb9Zxq5GBR1JJHRiCY6pT1ZNpfxyVE7Pzn7gynb6TwUF4HwYKpwIyHDsyiV5lEl5ARmwkbF7yXh5MEDmxiRKbzMoQ6kpJpFNNFFePsNMCgGQu71kJmhDERkLLWaimUrHUW03u0YTRwWsg0"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessLevel useraccesslevelUnique = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessLevel useraccesslevel = createUserAccessLevel(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccesslevel.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 2:
                        useraccesslevel.setUserAccessLevel(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 4:
                        useraccesslevel.setLevelName(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 6:
                        useraccesslevel.setLevelDescription(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 7:
                        useraccesslevel.setLevelHelp(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 8:
                        useraccesslevel.setLevelIcon(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 9:
                        useraccesslevel.setUserAccessLevel(useraccesslevelUnique.getUserAccessLevel());
                        validateUserAccessLevel(contraints, useraccesslevel);
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

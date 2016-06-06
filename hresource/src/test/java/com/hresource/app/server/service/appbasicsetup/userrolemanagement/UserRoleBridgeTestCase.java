package com.hresource.app.server.service.appbasicsetup.userrolemanagement;
import com.hresource.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.hresource.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.hresource.app.server.repository.appbasicsetup.userrolemanagement.UserRoleBridgeRepository;
import com.hresource.app.shared.appbasicsetup.userrolemanagement.UserRoleBridge;
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
import com.hresource.app.shared.appbasicsetup.usermanagement.User;
import com.hresource.app.server.repository.appbasicsetup.usermanagement.UserRepository;
import com.hresource.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import com.hresource.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.hresource.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import com.hresource.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.hresource.app.shared.appbasicsetup.usermanagement.PassRecovery;
import com.hresource.app.shared.appbasicsetup.usermanagement.Question;
import com.hresource.app.server.repository.appbasicsetup.usermanagement.QuestionRepository;
import com.hresource.app.shared.appbasicsetup.usermanagement.UserData;
import com.hresource.app.shared.appbasicsetup.userrolemanagement.Roles;
import com.hresource.app.server.repository.appbasicsetup.userrolemanagement.RolesRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class UserRoleBridgeTestCase extends EntityTestCriteria {

    @Autowired
    private UserRoleBridgeRepository<UserRoleBridge> userrolebridgeRepository;

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

    private UserRoleBridge createUserRoleBridge(Boolean isSave) throws Exception {
        User user = new User();
        user.setUserAccessCode(24679);
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelDescription("qnMEwxi0MOKgxWBMlNsI2cDr7O8pmsewIW25gK4v5Ybq7kAy5D");
        useraccesslevel.setLevelIcon("njOtrmrGzC80vCrlKu86GuJkgD0BYe2oB702huXyo5yz2Ap75o");
        useraccesslevel.setLevelHelp("fn7CK3aTiXO0gfSewj2xeMF6rf48tx155WqTem4kbChBuA8mtT");
        useraccesslevel.setLevelName("dOZQeEjOqjOjICg4QUiiFsy2MeBL3sAGtFrNU34edrw0aSEtlQ");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainIcon("A55vwBFSelPL3HJySXNbLTSgmoycqFsJm8lHP37hOzNb0GBxtu");
        useraccessdomain.setDomainHelp("70ipDuXoyFVIoKBNJ6xSvBDk3GDhYELdye5mjJo2n6y2ptGtfM");
        useraccessdomain.setDomainDescription("gglugZrMCcrbmHiV8iRtxJg93Z8vOAiJI1OcUh9ogPber0gc97");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainName("uhvlIWG7rj9FkVs9BV9pDhHyJ1BM4lbbFcP5ENt4ewqdCyAmfq");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        user.setUserAccessCode(44565);
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setIsLocked(1);
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1465218762161l));
        user.setGenTempOneTimePassword(1);
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setIsDeleted(1);
        user.setSessionTimeout(2473);
        user.setMultiFactorAuthEnabled(1);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1465218762206l));
        user.setChangePasswordNextLogin(1);
        user.setPasswordAlgo("8Xqx0DKyy0eaDbKoP1iYL6WRqtLPFHjh0JuwtZKiBMEYNxc3qO");
        user.setAllowMultipleLogin(1);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        passrecovery.setAnswer("LBHOOyRRbIloGdRUdFw4OZ9sOhfO1mOb3gSRcrYLivuwPZ0Y3N");
        Question question = new Question();
        question.setQuestion("aI0Hw76w25JUiah3UDr936sSI6eusvxn6148Nr3yYIAhatkRPf");
        question.setLevelid(5);
        question.setQuestionDetails("N7BuBakCmX");
        question.setQuestionIcon("0XUM88H5mbaw1XiF40TOeHl1ihi4etZeEnLQlTLLvD65CudLOi");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setAnswer("AJrSkFipnzLsqRwUhbUc6GbVXCAPcTsyZ8wMjLkjhEsSXXXdon");
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        passrecovery.setUser(user);
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setLast5Passwords("cTkY8k9k5k7sK5rLK0EZ5x287w1AiuIv7FWWGk1YkQatkjmdRW");
        userdata.setOneTimePasswordExpiry(5);
        userdata.setOneTimePassword("rRaS2dhCdiSnuDjCdEQhkTxUbBtWD71q");
        userdata.setLast5Passwords("9p6upORi1mNtJPY2tHfIZnf5DSvmfvoYRgDcaVpwAGMr7Q075t");
        userdata.setOneTimePasswordExpiry(6);
        userdata.setOneTimePassword("RicZoeL3FG24ruvGbIc9Lin7oMuuc7Uu");
        userdata.setUser(user);
        userdata.setPassword("g6fJ0zgNXIQsE6HcU1UXxAaIaNKFE0LleVBLAGldxdrZ3OAAaR");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1465218762549l));
        user.setUserData(userdata);
        User UserTest = new User();
        if (isSave) {
            UserTest = userRepository.save(user);
            map.put("UserPrimaryKey", user._getPrimarykey());
        }
        Roles roles = new Roles();
        roles.setRoleIcon("0V8xE5dpHZAIR9d3CimzTsHc7jqI4CFnmNJ2SFn5xthQyApV87");
        roles.setRoleName("EjdGOkbL9edMhUyV8TLL2DXIPXnNw9jMRu9fZol4vr9lazCApN");
        roles.setRoleHelp("Wvpb1C6bGE2ytThGGZ3Ph0t1lQkhYzauJ5LufrKLrLv1esyK9H");
        roles.setRoleDescription("YuBYb71A1tGirUNY5oYigygU0NmkJkzFxoJ0nBosTkDdo7kH0M");
        Roles RolesTest = new Roles();
        if (isSave) {
            RolesTest = rolesRepository.save(roles);
            map.put("RolesPrimaryKey", roles._getPrimarykey());
        }
        UserRoleBridge userrolebridge = new UserRoleBridge();
        userrolebridge.setUserId((java.lang.String) UserTest._getPrimarykey()); /* ******Adding refrenced table data */
        userrolebridge.setRoleId((java.lang.String) RolesTest._getPrimarykey());
        userrolebridge.setEntityValidator(entityValidator);
        return userrolebridge;
    }

    @Test
    public void test1Save() {
        try {
            UserRoleBridge userrolebridge = createUserRoleBridge(true);
            userrolebridge.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            userrolebridge.isValid();
            userrolebridgeRepository.save(userrolebridge);
            map.put("UserRoleBridgePrimaryKey", userrolebridge._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Autowired
    private RolesRepository<Roles> rolesRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserRoleBridgePrimaryKey"));
            UserRoleBridge userrolebridge = userrolebridgeRepository.findById((java.lang.String) map.get("UserRoleBridgePrimaryKey"));
            userrolebridge.setVersionId(1);
            userrolebridge.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            userrolebridgeRepository.update(userrolebridge);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserRoleBridgePrimaryKey"));
            userrolebridgeRepository.findById((java.lang.String) map.get("UserRoleBridgePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByuserId() {
        try {
            java.util.List<UserRoleBridge> listofuserId = userrolebridgeRepository.findByUserId((java.lang.String) map.get("UserPrimaryKey"));
            if (listofuserId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByroleId() {
        try {
            java.util.List<UserRoleBridge> listofroleId = userrolebridgeRepository.findByRoleId((java.lang.String) map.get("RolesPrimaryKey"));
            if (listofroleId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserRoleBridgePrimaryKey"));
            userrolebridgeRepository.delete((java.lang.String) map.get("UserRoleBridgePrimaryKey")); /* Deleting refrenced data */
            rolesRepository.delete((java.lang.String) map.get("RolesPrimaryKey")); /* Deleting refrenced data */
            userRepository.delete((java.lang.String) map.get("UserPrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserRoleBridge(EntityTestCriteria contraints, UserRoleBridge userrolebridge) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            userrolebridge.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            userrolebridge.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            userrolebridge.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            userrolebridgeRepository.save(userrolebridge);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
    }
}

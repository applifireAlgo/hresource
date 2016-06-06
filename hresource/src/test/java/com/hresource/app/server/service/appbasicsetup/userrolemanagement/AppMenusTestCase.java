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
import com.hresource.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;
import com.hresource.app.shared.appbasicsetup.userrolemanagement.AppMenus;
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
public class AppMenusTestCase extends EntityTestCriteria {

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

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

    private AppMenus createAppMenus(Boolean isSave) throws Exception {
        AppMenus appmenus = new AppMenus();
        appmenus.setAutoSave(true);
        appmenus.setMenuAction("VfBEO4jdWnG3Xz7J6UnawQjMTJVf2Qw2ZT3ZMh3Jo7WQgOwIqi");
        appmenus.setMenuIcon("XU9lSHMIy4bA5lGKp9RNiC6DRgrBslUfYGKtLhgILgC9Vo6ILL");
        appmenus.setMenuTreeId("5e1qWuUPuUD4EUI5ZIzoMx68AbMzgudTGidAAXTYHzMDnU4hYi");
        appmenus.setAppType(1);
        appmenus.setRefObjectId("Xsu0HE2i3ERHtp4haTwHMUbSgZWObIibmevTW8jAnDf6A40Ble");
        appmenus.setMenuDisplay(true);
        appmenus.setMenuCommands("lZEvlIIVQ9bnTa3XbR7x0mdYzniGhjzFAqsppZFY0pNLpVud11");
        appmenus.setMenuLabel("DlU3wTefE59pdg4NCoU2P6GlL07lUU4mqZ2nDhMEVoPOmeBAp5");
        appmenus.setUiType("5fL");
        appmenus.setMenuAccessRights(4);
        appmenus.setAppId("HaXbaDBmhlveJRjNz4RIgAD16CQ74TCMeNWaqnrpDNT7NX6UmH");
        appmenus.setMenuHead(true);
        appmenus.setEntityValidator(entityValidator);
        return appmenus;
    }

    @Test
    public void test1Save() {
        try {
            AppMenus appmenus = createAppMenus(true);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            appmenus.isValid();
            appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            AppMenus appmenus = appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
            appmenus.setMenuAction("HVXDeYo0lXhMHfW7JQq6wOTyFDImIvS5xJHyGGcj2LqILzxuCW");
            appmenus.setMenuIcon("SeNqLawF9CQr7zc0TLqsfGxiJVEsNEA7HdCKryBbgfuPSOsZj4");
            appmenus.setMenuTreeId("Gz849766tn1WvlY89MizHZXgKqJjUvAUoSJcRKg3FjevXkR9iY");
            appmenus.setAppType(1);
            appmenus.setRefObjectId("PS7j1HfYTU24t9EdRc8JVuKdUe7bWATqSk9jAuLOzcpjNhMhr6");
            appmenus.setMenuCommands("czRysSElAaQp0pGpiu3uX9p2exfBOrvM7BYBtkHPj9p9rPVwAQ");
            appmenus.setMenuLabel("a53u0YiO0DoCHrgXVpmKxNVGTUG3voAzZMjQ0ZdErHzbkhKcpV");
            appmenus.setUiType("KxI");
            appmenus.setMenuAccessRights(7);
            appmenus.setVersionId(1);
            appmenus.setAppId("Rf3dYrq8ZQN5lLCracxLyAql6NXQFIWVmSugeBHu41COw5kINK");
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            appmenusRepository.update(appmenus);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAppMenus(EntityTestCriteria contraints, AppMenus appmenus) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            appmenusRepository.save(appmenus);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "menuTreeId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "pTIRkEbtaph54rDfAmQB1pw7er3HfYZM3Rn526LfgCYpDiWzP5gLNrD0fd62WWdKjaCZvInifd2Qila4hoT4XeOhqMQsvIjEfsRNwOfteHdA08cREsbYakE2lOuQK60lYpE8bUQgw3rOG7IEQREtXQ516DMAgot2QkFiN5l60lBWLetkOxngqSoqKOMwLQ0gIinhNyu5G5FB4BEtI9M1yXExtmYnakgsvnqZc7WYjszxYYl8WbzjbaFJG5AZ6PGcD"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "rwcbpuPXO6jrLTtC7X2qlO4FhDZdR8mEt8KhTVfKjc9kqruKggstQIeI0hMEFPfBZyR2nl7OETVcjmG5MEzuUDcD1fKhmOurhWjnnCs1EQTRbKXDfe7OEcJHFBViUeS8gJK90z7AZzfMgSLD1wyX4Aqc4B9DMqbplPetTqBRVYvtp39QOizArDXHHEWKSMqlcY1G67pqRgJcbDMdCaAnSubtE6gQ1S94X3vmlIYD9Rr3SLuxctWCR4pD1RxBg7Ddn"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "0mAbqFiX39cEOxCgLlyEOsXH5tQQ4UzwPXDJQt48RZiak43CcVfSNuo6VsqkihG6AiyRFdn5FLDtWRMEPZ4clxGZrtqAWBQ4Q8Sq9x7ihJmwFw5LQ1t0i0bQJ9DQeFjjbM6vPuqvkVm93ofkDMdz9cx1s4bkUqb5dciAeoIjMQuePN8vb7zOPuEzUfBtpxLQ9RjgIuXuv6TiCKD7ws7RflQjbaw7oVdTl4OPuKEvtqLGFBTWKlkkas4r3gDJSpfaE"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "1E5fBhWKzbYOCT6C03o2ymLbvjR4e7Wo06OByEvtty0sCqI2JNMxT4YSs8kCknvVh"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "0ubw9hlJZGWXMLm4ji6hrMRpvtN0yvHm7RCxa1o6nEmF6Hqf0c5XoceAZ4aOiPeDe0zHQvseYcuhWo7O73cPryHTTwNxVmhG6KCdtZBTNF1UKpVLwk9opGo20Rhx9AfGAdKW3gx7xi3dqYWGwadCKn0nEkZ5svGi4TQmAI32nDs7kZLDgbLOHYk4mh9bbw4UbYq1uzyAse2gLPw56vgOM9jHNmuve7z5YUtiZ8ePlWp7N9fwjVAfge7kiJ7YRZbwo"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "nh0o"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "EpScQxWAHvZMaSkCQ8fo3S0g2pH0iFEnH9qmDWOalGKwrXSyPUVYNuzVy7TYOowUNodw3cQvVeY2WnWgpZG3qcCA2G2XTvxaF0U3iAD0YA93HPTx7ur5xUmk2cYobNlgDc24pWj7LelMfeAOEyGDaFD9Nxhs3mrabFh1rAdoIu6FiSkrWW42aUtL2hRK8DhX358FIBU7D7sdvesrTFTGBIJylqQZDeC9L8tfBwX8ZX1TJ3i4GesqERVsEBOUqyFIb"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 15));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 3));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "aGWaVb8PE56CcsrVm2hIbVG1LjJzITnJ8PpN5ZXvTUNjjJGGsVu700kjyHJPeF6xgsa8VuXr0WY2YNOQYpAGvkyxo2lfhmg30re5adEV3P2gPGocayuHmU4P3InfSTI3ji9UPhKYy9k3XGK8rcbZBQohJbQsQRLXDPhrgP4bqAdeQqKalZZ1DPVR77Vk1DeIwaGUrU3Sj3YwaqrZyxPk5Lj2jtv9MihmadnLVF3xjoP5KcNxlhSfkZchwFRpSH5Li"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 18, "autoSave", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 19, "autoSave", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                AppMenus appmenus = createAppMenus(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = appmenus.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 2:
                        appmenus.setMenuTreeId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 3:
                        appmenus.setMenuIcon(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 4:
                        appmenus.setMenuAction(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 5:
                        appmenus.setMenuCommands(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 7:
                        break;
                    case 8:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 9:
                        break;
                    case 10:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 11:
                        appmenus.setMenuLabel(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 12:
                        appmenus.setUiType(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 13:
                        appmenus.setRefObjectId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 14:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 15:
                        appmenus.setMenuAccessRights(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 16:
                        appmenus.setAppType(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 17:
                        appmenus.setAppId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 18:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 19:
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

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
import com.hresource.app.server.repository.appbasicsetup.userrolemanagement.RolesRepository;
import com.hresource.app.shared.appbasicsetup.userrolemanagement.Roles;
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
import com.hresource.app.shared.appbasicsetup.userrolemanagement.RoleMenuBridge;
import com.hresource.app.shared.appbasicsetup.userrolemanagement.AppMenus;
import com.hresource.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class RolesTestCase extends EntityTestCriteria {

    @Autowired
    private RolesRepository<Roles> rolesRepository;

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

    private Roles createRoles(Boolean isSave) throws Exception {
        Roles roles = new Roles();
        roles.setRoleIcon("pt6sKSCQWD2nZquDaZOPyWXiaXHUYgwG9Vu0wp9LkzWKEpMTD4");
        roles.setRoleName("z1rf5GLm0iYzEw9SXrdl1SCK3x9N4oUUadOB0QXZtfDwwyHbtg");
        roles.setRoleHelp("N6cXT340elgGTO93P8wqyjRCgfpcM5UMZtdvDJM26Be59wR3Nl");
        roles.setRoleDescription("Uzuii9sHybH0gZpAsOJBY8nPuY439B46RLSjy1SsZbyTHUxCPK");
        java.util.List<RoleMenuBridge> listOfRoleMenuBridge = new java.util.ArrayList<RoleMenuBridge>();
        RoleMenuBridge rolemenubridge = new RoleMenuBridge();
        rolemenubridge.setIsRead(true);
        rolemenubridge.setIsWrite(true);
        AppMenus appmenus = new AppMenus();
        appmenus.setAutoSave(true);
        appmenus.setMenuAction("v8CSojbhNeNoxr5UpnepvCqSasV2OOLDjFIzk7cQuplM9qYSPJ");
        appmenus.setMenuIcon("7U1kGsFV3ZP1jb7XAOmdJzGT25LRP4LMyklMzR7cVSDG5TKGY8");
        appmenus.setMenuTreeId("l84PBLLwvxEi9dNFFGRomWKy3vrxcvgx88ok5ec9SgF2oIIXVm");
        appmenus.setAppType(2);
        appmenus.setRefObjectId("H5E5RvGxoVUPzFEpdmOXCi7xBn20TBg6Grpd2i7W3KrmruPgDG");
        appmenus.setMenuDisplay(true);
        appmenus.setMenuCommands("Wg9Rdx9piCmPkCum59fG2MCV7XTHOoJOXg4L0ZZVYZfJNadUWQ");
        appmenus.setMenuLabel("b40H50d5EIbxIU7xMFs2RhQYjCstoQ2CXOrsVgehIvZSWHkD7q");
        appmenus.setUiType("c6E");
        appmenus.setMenuAccessRights(3);
        appmenus.setAppId("bCJQ581ZQZjNOY5oGc82bUhfdsOa45iVxePFg6Q5AJZoaF4GrN");
        appmenus.setMenuHead(true);
        AppMenus AppMenusTest = new AppMenus();
        if (isSave) {
            AppMenusTest = appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        }
        rolemenubridge.setIsRead(true);
        rolemenubridge.setIsWrite(true);
        rolemenubridge.setMenuId((java.lang.String) AppMenusTest._getPrimarykey());
        rolemenubridge.setRoles(roles);
        rolemenubridge.setIsExecute(true);
        listOfRoleMenuBridge.add(rolemenubridge);
        roles.addAllRoleMenuBridge(listOfRoleMenuBridge);
        roles.setEntityValidator(entityValidator);
        return roles;
    }

    @Test
    public void test1Save() {
        try {
            Roles roles = createRoles(true);
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            roles.isValid();
            rolesRepository.save(roles);
            map.put("RolesPrimaryKey", roles._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            Roles roles = rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
            roles.setRoleIcon("HIHdaGwBExW44yJwFJk7Kw26pJDVBEpNbc08JqpBM0uYcHOmAG");
            roles.setRoleName("CIxqRGMWZOyL2qmWgWTPeryyvP1Fagz0HQkI1ponmLMTmArGuf");
            roles.setRoleHelp("MhBBxACz2sqYfsapb2SIJ3FE2MWrj43MEJ8Agy9PxuEIEn76wz");
            roles.setVersionId(1);
            roles.setRoleDescription("LLEvZCaQYyNrjORBysIbUONkC6fiDV90WVFF9UH4sEs47O3ZQO");
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            rolesRepository.update(roles);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.delete((java.lang.String) map.get("RolesPrimaryKey")); /* Deleting refrenced data */
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateRoles(EntityTestCriteria contraints, Roles roles) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            roles.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            roles.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            roles.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            rolesRepository.save(roles);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "RoleName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "roleName", "a0dtIQTX79ePKZPjumFBSBRNcKyzNMD8KnsSZiV9JmNAwmzXL7o2TFjGQ3Dc99rMJNf8uCkN2YRJVTYWBAjuLGuF9xW4TSLQmaKAueMRyQq45rIL6XearlCEyTDIbhkfT8mTmCimQmD6AtemDZcAZTq8k5B3FSE2HPMX63YUTajpwNIdFHuYvo519aSwlCdzOdsQKlaHI4ZYAt8Qb7rzjU9O07Q67eQYr87pLA3TMGjYaEkBzJxDRXsN2SZVWaQT8"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "RoleDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "roleDescription", "HngYlZHHwWugq1qGEdweaY8CeTWkqiUZzRd942YQPF92f9jYNkzsiNxJFodHBRiziEm1zhIuucc00ard9OY9NrkDpe6CIkGWsQjnIODghEuAupxscfCxvL37t5clbikz7odNbrYxi4s2Tqj0oQ8FB4YqGrBjG9H0hxgeYL4R0qVHTHahqPvRlSDtpEzOjjHYqISsF6jFiJhaJbc45EkkH5fTiGRSpjVy9uyAbIpZngpm7e4meR2JQbMWzBwbFDLkD"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "roleIcon", "L9Nv1IfLh5bwW807iWqjY3dUfJgbw4fm9Qaa8R9m5ayTY9LsJlzWDwmJudntKAagB"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "roleHelp", "ZVXjcJFTWfjgDO6A67Dz6BiOb5Ed9i0ydfTQZSBnmSvA2zfX8038fw88oCeu1W5fHqOqZuA2krwFKf0WQkByEd773SZ8G6mzsesFKijhcMVZTTGaxnrR6n4hn2WGgFNoRr0ZVjUPhpvKfqrKkzNvfUyZcSaPZp8t5dr3GpH7Y15DKBK81nylhd3lhE7uvuE7CbRVDPktiyNAwGBHrjOpiHGcYqBaiQKkKxOfWnZxgA6nLSXZImXhDthZcyzlOFUWt"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Roles roles = createRoles(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = roles.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 2:
                        roles.setRoleName(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 4:
                        roles.setRoleDescription(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 5:
                        roles.setRoleIcon(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 6:
                        roles.setRoleHelp(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
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

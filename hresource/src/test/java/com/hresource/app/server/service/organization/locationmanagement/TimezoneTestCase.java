package com.hresource.app.server.service.organization.locationmanagement;
import com.hresource.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.hresource.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.hresource.app.server.repository.organization.locationmanagement.TimezoneRepository;
import com.hresource.app.shared.organization.locationmanagement.Timezone;
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
public class TimezoneTestCase extends EntityTestCriteria {

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

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

    private Timezone createTimezone(Boolean isSave) throws Exception {
        Timezone timezone = new Timezone();
        timezone.setTimeZoneLabel("cqzLsIlX9OCZ3PhIk4cvdzUWrkdEEy2ct6093ZJazHDpKZP6YK");
        timezone.setUtcdifference(8);
        timezone.setCities("8kQbbpMnnia2rYzVa9KXC5xKgDvHru4QLLHQod4pWSCHOEZh4G");
        timezone.setGmtLabel("M7sGd7OHbvJALfRI5kimFRtPuubG1bwHvvQt6tgxpgh6VexCL7");
        timezone.setCountry("fqXU4VDNU8yt5XuPgsGCPpM20q7k8M7d1lrPDUzZrJUCza5zUX");
        timezone.setEntityValidator(entityValidator);
        return timezone;
    }

    @Test
    public void test1Save() {
        try {
            Timezone timezone = createTimezone(true);
            timezone.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            timezone.isValid();
            timezoneRepository.save(timezone);
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("TimezonePrimaryKey"));
            Timezone timezone = timezoneRepository.findById((java.lang.String) map.get("TimezonePrimaryKey"));
            timezone.setTimeZoneLabel("B0oJJFnPe8XplgTdLM9PewqV0dW1cvHTzE4eMxOluzIfOfH9Ks");
            timezone.setUtcdifference(6);
            timezone.setCities("9wzUlCIRRzUZzWw5eVlw3TcftBXJJqhvmV5ck9gpPmg8oEGRW8");
            timezone.setGmtLabel("fupTbhnsdcYI0XmONwz8aAkPD0eIrq44gaUnTa9u4ttNICvUmh");
            timezone.setCountry("MlOwz4wRTSl8pTGewqwoSQdAWjJY02NG5dCOlaGktjvQx2700w");
            timezone.setVersionId(1);
            timezone.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            timezoneRepository.update(timezone);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("TimezonePrimaryKey"));
            timezoneRepository.findById((java.lang.String) map.get("TimezonePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("TimezonePrimaryKey"));
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateTimezone(EntityTestCriteria contraints, Timezone timezone) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            timezone.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            timezone.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            timezone.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            timezoneRepository.save(timezone);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "utcdifference", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "utcdifference", 19));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "gmtLabel", "OUqciWlqZkozxYMGOrkyWEJbBDhQS3VdDIjF17TfsO2Zr6Ce0fxXI7jsst8MR6fnQkmOfykccpO9y6TQWxiQaWNScUUK0x0n0BS6ASCQiQka2DnkwpfldNujVlMZQPCHRqrgkag1om7i1bfo6Tu4aQgsycYI2AuJAhzsKxXTrAqGqEkp5u1CK2HeLpwdTeNw6OTJzRJrOql9pcCxDB0lVD2RkulvWwjw7lWK6nmNFXYxFuxcrWQ5uQOZPUG54uKJU"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "timeZoneLabel", "wejwfbBM0m1sQJg55aGSQdg3rIruPrpRVS0zfcPZPMQ2RZzoA3FsNdoNujOZeL9aR1WZ14GhBisUKisuY9pWL7ErIbl2XyQUFVHRz3ujQLCoAC2DysXlTy03oyeLGexBdYLLsZlvnw1kgpHjoeZ5bqTJSiuD4ZkccZ2vI1KP47zzQohLqaYNDlDnF5SinixBxrrQHARYDapsXpieNRbmBOIoluFb3myI603U7aIj2ucShAWqPKmJb3TZmZ3qsT0pZ"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "country", "dzCAVg82o9mU9QJyzwRZMCcLDZsCJsgbT76tC1Ydw7C6Od6joWYugLytxaCrLBNWCgEG1dUwH2wUNpgeP0p6IcQ4jCBwt1TEK73FJpSuy25qyADymfWnIxaT70hNj48NeM1koJ5OneNI6Gq7d0cn8RhxkX2Khuji5ScRG9dK6zOIcXACXHQau9dzhh11btO3vIfKeHc5MQ3w7JLGW50BTmOYLJ1SHQRdShn29aQS4Qy3sVytrbVmGvqFamxKXS3nB"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "cities", "f2nzI8COhQP06f6f95TZ71c4a83Xam85ew7OFWGJr2OZ2RD8Kuu21Z02MGthS5MhGkUXG5K7DDsqzouZXlzGcqM9VvErW1uFEWKGRH5l27njZplnzfXk48dlLguXCamZCEC1YozM7YhARSQOCzu8bRSYrPj0aWAyENOZUBccdHyif9akpsfKcTrGA9sGIlz1xxKvVAPVIH9DFuZS3t7GNIfjJYzhu3IJeFurreFWzVyWgJQWa97ZX0tXyNYmpUpui"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Timezone timezone = createTimezone(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = timezone.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(timezone, null);
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 2:
                        timezone.setUtcdifference(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 3:
                        timezone.setGmtLabel(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 4:
                        timezone.setTimeZoneLabel(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 5:
                        timezone.setCountry(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 6:
                        timezone.setCities(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
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

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
import com.hresource.app.server.repository.organization.locationmanagement.CountryRepository;
import com.hresource.app.shared.organization.locationmanagement.Country;
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
public class CountryTestCase extends EntityTestCriteria {

    @Autowired
    private CountryRepository<Country> countryRepository;

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

    private Country createCountry(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCapitalLongitude(9);
        country.setCapitalLatitude(11);
        country.setCountryName("jlTQ0k2BEihayndSjtIVkE8EdVeB7jBGj7DwsTRM2A6RpHYuF1");
        country.setCurrencyCode("7cN");
        country.setCapital("mtAx12BeQjuJfQnilPViW3qY5HGR7ipA");
        country.setIsoNumeric(388);
        country.setCountryCode1("Grv");
        country.setCurrencySymbol("MkbRtLaaTa1UTiyZ83hu66qxPqICbs8Z");
        country.setCountryCode2("e5r");
        country.setCountryFlag("kdsB1vNEqUvglT6nnFb5RC2jJehOvokryX0MTpQRI9zl7mysTh");
        country.setCurrencyName("3lB3kyleDpbhPkVVvgh3kZFsfBm71SERJYsdn4u40XJQqjRtML");
        country.setEntityValidator(entityValidator);
        return country;
    }

    @Test
    public void test1Save() {
        try {
            Country country = createCountry(true);
            country.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            country.isValid();
            countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CountryPrimaryKey"));
            Country country = countryRepository.findById((java.lang.String) map.get("CountryPrimaryKey"));
            country.setCapitalLongitude(4);
            country.setCapitalLatitude(11);
            country.setCountryName("Q9A7txOCfWPpMUiP7YpuTifAQvpSFWxVH7Vk3FdazqpKG4XCu6");
            country.setCurrencyCode("TpB");
            country.setCapital("ww0ugFw7kas3FzAZ5FTpqlksVJhgsZKQ");
            country.setIsoNumeric(270);
            country.setVersionId(1);
            country.setCountryCode1("SLX");
            country.setCurrencySymbol("YtPrv7MURp28RxynqK3tcude1r0wqvoD");
            country.setCountryCode2("KtB");
            country.setCountryFlag("Elyd5EewOmBoUzAlJK5p95T7is2kgQbKf9y9lmkCnYhY4ENKT0");
            country.setCurrencyName("M3MuuJpYpHRDuA1mTUaIUYLqcH8UQ9aho3PrXNWk0vmjTkL3T8");
            country.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            countryRepository.update(country);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CountryPrimaryKey"));
            countryRepository.findById((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CountryPrimaryKey"));
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCountry(EntityTestCriteria contraints, Country country) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            country.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            country.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            country.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            countryRepository.save(country);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "countryName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "countryName", "ErDfP1xhEQHVJj8hVSX1Q2gH8DXjWqsoUgDf8fGxXIlgzKK5M9a3F6QDxPd09M35r8CBTA1yB5jTfeWHvbt0UMDBcWgMxKthJdzOvzTzvPTFj5piCX7yrKmu9DUPbm4yj"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "countryCode1", "enKf"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "countryCode2", "utIr"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "countryFlag", "cysIBZGYZoNpNKCrAEX2basfIwQKfHnP1GRJPFrzZcfS3HJxaZl5Xu3iQhLVkhSDl"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "capital", "z39ttqyR2tPgCAjQl6lzFyavzgJNqGJWj"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "currencyCode", "5I5D"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "currencyName", "1K8vKtjDBzTMPjADXCZ2PKuIZRIOfI1KAJhZ9zXII9IrzOHy6mRmv9I71R5yRajvOGeZqAO79FkrcViUu6OSxOt03f8ux6MIxCPCeTJjbwYT9oWIlULl1ePolrMl6SAVt"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "currencySymbol", "hoe8XK7a3atxivjS8jLWLEndhplsmTrBH"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "capitalLatitude", 15));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "capitalLongitude", 12));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "isoNumeric", 1146));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Country country = createCountry(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = country.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(country, null);
                        validateCountry(contraints, country);
                        failureCount++;
                        break;
                    case 2:
                        country.setCountryName(contraints.getNegativeValue().toString());
                        validateCountry(contraints, country);
                        failureCount++;
                        break;
                    case 3:
                        country.setCountryCode1(contraints.getNegativeValue().toString());
                        validateCountry(contraints, country);
                        failureCount++;
                        break;
                    case 4:
                        country.setCountryCode2(contraints.getNegativeValue().toString());
                        validateCountry(contraints, country);
                        failureCount++;
                        break;
                    case 5:
                        country.setCountryFlag(contraints.getNegativeValue().toString());
                        validateCountry(contraints, country);
                        failureCount++;
                        break;
                    case 6:
                        country.setCapital(contraints.getNegativeValue().toString());
                        validateCountry(contraints, country);
                        failureCount++;
                        break;
                    case 7:
                        country.setCurrencyCode(contraints.getNegativeValue().toString());
                        validateCountry(contraints, country);
                        failureCount++;
                        break;
                    case 8:
                        country.setCurrencyName(contraints.getNegativeValue().toString());
                        validateCountry(contraints, country);
                        failureCount++;
                        break;
                    case 9:
                        country.setCurrencySymbol(contraints.getNegativeValue().toString());
                        validateCountry(contraints, country);
                        failureCount++;
                        break;
                    case 10:
                        country.setCapitalLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCountry(contraints, country);
                        failureCount++;
                        break;
                    case 11:
                        country.setCapitalLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCountry(contraints, country);
                        failureCount++;
                        break;
                    case 12:
                        country.setIsoNumeric(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCountry(contraints, country);
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

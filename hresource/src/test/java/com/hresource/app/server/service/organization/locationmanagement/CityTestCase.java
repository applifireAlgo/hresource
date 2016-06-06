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
import com.hresource.app.server.repository.organization.locationmanagement.CityRepository;
import com.hresource.app.shared.organization.locationmanagement.City;
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
import com.hresource.app.shared.organization.locationmanagement.State;
import com.hresource.app.server.repository.organization.locationmanagement.StateRepository;
import com.hresource.app.shared.organization.locationmanagement.Country;
import com.hresource.app.server.repository.organization.locationmanagement.CountryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CityTestCase extends EntityTestCriteria {

    @Autowired
    private CityRepository<City> cityRepository;

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

    private City createCity(Boolean isSave) throws Exception {
        State state = new State();
        state.setStateCapitalLongitude(6);
        state.setStateCodeChar2("Y0x6mWPHHZOH9QRhlolJditXeEjY7c4s");
        state.setStateCapital("LbYjs3kYJVmICXpIjlc8WyjVrV4mkhwmMFgEfVZtNsgxh4tWb5");
        state.setStateCodeChar3("2iVi29geF5WbX17w9Qtf6vjfEwjFUBCl");
        state.setStateName("UcDZYLTiow5SfLMdgYfPtLA23ETpce6utzT5BYHyXQA5AdrqIu");
        Country country = new Country();
        country.setCapitalLongitude(7);
        country.setCapitalLatitude(4);
        country.setCountryName("B8nLHXWkqrxhZWLv8IfWGuaWbx3ecRfNI8qorV6Z8gdQ3vgcsn");
        country.setCurrencyCode("eVt");
        country.setCapital("t0e8a8X5Cq0T3X7ndEd4e45JWCO6mksr");
        country.setIsoNumeric(481);
        country.setCountryCode1("w1u");
        country.setCurrencySymbol("6L3WRLHIG0Q0uTXlEeMrsYnucwWEnync");
        country.setCountryCode2("lsa");
        country.setCountryFlag("J0mP9tNMcJKQ9UfyJmjwCmQC9nbpnhdHpBP0SAxdwCEYTWEpfI");
        country.setCurrencyName("zqA2zuiRJ8gA7Jd7SwsjxPmsLi76CLiaYNyNI7yJi4XMgqlLZM");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCapitalLongitude(10);
        state.setStateCodeChar2("JF1DuKsZ60G3lNbppEUK2zLFCA5zj5nd");
        state.setStateCapital("vX1xUBrjjOxe5lOd92qNX2DyW7X5YxUzsrXP8Lb9RVHydsFQ8N");
        state.setStateCodeChar3("zC63PeQE6ORpy726ZHPI3Yvt0n5f1eJ0");
        state.setStateName("z1qRTnX4usaLDUgylkgcHVQlW3WV4aO7pWWtMYQHBaoPWEa9sP");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateDescription("KkoQIN9yKQrBhl0Fa07soLMlPR8X68yzQuloORA87gqKONwtLD");
        state.setStateFlag("fVS2n3B5gxHmhlUwVlArQo9OqeUjaZTF5tnq79e84SmYZSSI5a");
        state.setStateCode(1);
        state.setStateCapitalLatitude(11);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("NdCQYgUbEek8iFkzWzjuoep9ieepbN5y");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        city.setCityCode(2);
        city.setCityLongitude(5);
        city.setCityLatitude(1);
        city.setCityFlag("StorSwvm2b0CljpXLCZWF4dn4lugj7Wuo2cVj2TIZx4p4SmVVm");
        city.setCityName("h1qjBCIegWcuJ81j6DHr5Hq1gSMK0gpw99HZgSLOg2honmUXsL");
        city.setCityDescription("VftEhQ9lBbOVt6J96D4iIVw56QtVc9BFMXVpDPnVpp5SgXSkR3");
        city.setEntityValidator(entityValidator);
        return city;
    }

    @Test
    public void test1Save() {
        try {
            City city = createCity(true);
            city.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            city.isValid();
            cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            City city = cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
            city.setCityCodeChar2("2fJu4knqFglo70moNclFAFnS8CRIu8Yq");
            city.setCityCode(1);
            city.setCityLongitude(3);
            city.setCityLatitude(8);
            city.setVersionId(1);
            city.setCityFlag("UV9eCg67upSJGkNYxVlnl9b0Qt44HEgYm31U4mcB3eWQCwfwwv");
            city.setCityName("6mjfRzIMYQLoq4kn2F0hwoM4cgJRcjEhJm6J1DiYyH0o8oKLy2");
            city.setCityDescription("1CRYGcQnbWbh8iTY5CD0vrhLrvgydtYMt1PS5CEGfYmA3Xlycv");
            city.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            cityRepository.update(city);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<City> listofstateId = cityRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<City> listofcountryId = cityRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCity(EntityTestCriteria contraints, City city) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            city.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            city.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            city.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            cityRepository.save(city);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "cityName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "cityName", "yokXB4y1IUDvLiqfCQYtQhropEYAnj70xnH3VM7leqP5jV0oE5rUFBDo7dyAbLeTWPJiyzIOJWs0jKvGQljrVk7i8hJTCr8YVOFwWghZCWPj8MuEn0tmGSLYeIkOF7t62lQ8eux7n0llwMfNWQt8nWy8mwCsvfH8TmFqlSkNzTmiYhYj10EKjocAuZphvswOJMfpbOw0Zxfo7zD6SefqjSZgikaTvHqk0MJEF5pLOdPwdfgNV9tP6FJpjJHTphmX6"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "cityCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "cityCodeChar2", "61hMfvj0dMA7NEcQgf3aMsF7p5icd88yx"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "cityCode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "cityCode", 4));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "cityDescription", "IkkR4rFz9JkCJU8TpvzMVME6aO9koF0ChqCZrPB6UwzUJ52yGiG0IL1dwsTBQCKjZktqYNv2MtSqYZXFNEK4DMM421tCiOBIRtl09BneRr2f6bVAdSG1c7JrEtbjCTdRn"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "cityFlag", "dZj22XVS8meePEFpQDhpiHcRwS5Afsymm0GpHs9yZoxEUMmy6zzr5wgnVrddx5BvtO0lzei7c6QIPcPNhpGwIKhYpsezSkUzpSI8JzqiYRICm5w06IynsRwjQeEKKZ2fA"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "cityLatitude", 12));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "cityLongitude", 16));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                City city = createCity(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = city.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 2:
                        city.setCityName(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 4:
                        city.setCityCodeChar2(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 6:
                        city.setCityCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 7:
                        city.setCityDescription(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 8:
                        city.setCityFlag(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 9:
                        city.setCityLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 10:
                        city.setCityLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
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

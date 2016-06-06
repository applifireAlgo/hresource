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
import com.hresource.app.server.repository.organization.locationmanagement.AddressRepository;
import com.hresource.app.shared.organization.locationmanagement.Address;
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
import com.hresource.app.shared.organization.locationmanagement.AddressType;
import com.hresource.app.server.repository.organization.locationmanagement.AddressTypeRepository;
import com.hresource.app.shared.organization.locationmanagement.City;
import com.hresource.app.server.repository.organization.locationmanagement.CityRepository;
import com.hresource.app.shared.organization.locationmanagement.State;
import com.hresource.app.server.repository.organization.locationmanagement.StateRepository;
import com.hresource.app.shared.organization.locationmanagement.Country;
import com.hresource.app.server.repository.organization.locationmanagement.CountryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class AddressTestCase extends EntityTestCriteria {

    @Autowired
    private AddressRepository<Address> addressRepository;

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

    private Address createAddress(Boolean isSave) throws Exception {
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("5Lqq4DyTHchu08YbQ2h6il9txhEwsAlecvObXYZr2cUhijfFsn");
        addresstype.setAddressTypeIcon("zU63a5u9tasTgNQpiL06kp4x4tTLKoM43M4tHN6QOjowdqVLJx");
        addresstype.setAddressType("X4141TDo0q4Fgo0eHUv1H3xGJ0x9lbV66JVlp9mb8op1WGGYgU");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        State state = new State();
        state.setStateCapitalLongitude(5);
        state.setStateCodeChar2("kmkCSXvbAjEBeKtXPk8BAf6WBsBp5sr7");
        state.setStateCapital("oSZfXVWyzkdltoD5U1WQpYsHWvnEXosSJuY3FIR3Xpa74SVRh0");
        state.setStateCodeChar3("wfhwKRS3AyGuV7R0tb9cUMEoQdMax6AB");
        state.setStateName("Rzx7UGiUpiO7Ye6652uqeoYSDxiffNwFnpQHoJaYD1E5SXTT9l");
        Country country = new Country();
        country.setCapitalLongitude(6);
        country.setCapitalLatitude(8);
        country.setCountryName("HP3Bjmb29k2jTU6SMVWEshrR3VnxOArYophMZvqXSSySwcc4Rd");
        country.setCurrencyCode("d3B");
        country.setCapital("begjRuMdJ3LUkJ53DiZVLApzMYZyyfYL");
        country.setIsoNumeric(747);
        country.setCountryCode1("SdO");
        country.setCurrencySymbol("0MDTBOCpojXdtK3ItjmgqSOBHjrghqh1");
        country.setCountryCode2("il1");
        country.setCountryFlag("HJ7kKWJDEiZDUwhJnEtGyRMfe8ZjpMreiuOgZRzEXeQUHFfgl1");
        country.setCurrencyName("tfwPaLClIMsKL3SXtWn6qt16uVyukZyyQC99aApiJrdYW9N7qb");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCapitalLongitude(9);
        state.setStateCodeChar2("u4Y3Y0TOfGPP1ctu3sg47lEOYXYz7MNG");
        state.setStateCapital("sCuL2P7n2xpnr9W0d2rEZ2q9nygiVNMlkLA7Vx8b6y57q98cvs");
        state.setStateCodeChar3("EXgEydJF8q8LcvOdirdhfOafmPTT3z8q");
        state.setStateName("pjmzgPXE138XO3RvTbG4ePKyQ5CgWZX140a5pJr2xNPM1IL2Av");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateDescription("MfrwAa2zzOOsMGFVEhfPWYAvgbwQcE5F2uk4VvzC8BUVJqHY15");
        state.setStateFlag("0AoxiC9FMqalHsSV6qPc6uaLDqITa3n3z2vAEV9hDQIOa9X6QE");
        state.setStateCode(2);
        state.setStateCapitalLatitude(11);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("tEdAZaJ1oov3ClVJpQGI4FW3wQD19uXu");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCode(3);
        city.setCityLongitude(11);
        city.setCityLatitude(6);
        city.setCityFlag("3IpFpKTnq0a1AFoPXCJbhF6ZTTXsG4UZTzOuFRw3mUldKwScVk");
        city.setCityName("lxQ1Pjk8PQiU7KJOsEjTDtZFfUYlyu17lB9lWyT1LqjXHJYeGA");
        city.setCityDescription("JCzfs2iKqeBNbYbD5MxCdkdiHwArWe4q2IIMxWWxKANlhKSBfS");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        Address address = new Address();
        address.setAddressLabel("qsQOvZHpx0L");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLatitude("5FDSRTvsrBnd4KyqytLvOLm3iU38vKzAS87BahLOCqdOT4AGFx");
        address.setAddress1("4BDUiQVRmwBxhn52UwVBHnzjOkaj6xdp9yGJoG5WReNPv7bSOX");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLongitude("KN7grtutmNvKLtWHhhf1JIqnXEJGF6AA96JLRZ5FXX4tm79pn5");
        address.setAddress3("X9jXRUuchDckBGK4EHpy0idBMfRAn7r0NZAROZrkHYty5kmRZP");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        address.setZipcode("HT2Uhg");
        address.setAddress2("WNQAPjaGOreOJ754XaNGVBpEECgGVWPnTZUetS9el2EsmU1rtk");
        address.setEntityValidator(entityValidator);
        return address;
    }

    @Test
    public void test1Save() {
        try {
            Address address = createAddress(true);
            address.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            address.isValid();
            addressRepository.save(address);
            map.put("AddressPrimaryKey", address._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            Address address = addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
            address.setAddressLabel("TgP9aHiCqEN");
            address.setLatitude("eJ8FIJbFmFVdFimrcjGrBFFNfEb6BdegxJ8imtHU4UrWcwlMHq");
            address.setAddress1("zLcmZShL9xop6g1idzeaV6cx5Iohnq554XownFWy5X7i47wsmr");
            address.setLongitude("q4xSSnTZNxkRWl2ZO03c8hBG2XkFQCipJRJCg6HQwZhLexIiEY");
            address.setAddress3("3Uri0IEOXXJ1dKGVoMyzXvMeyN3du01TBObdBOhDyQyiUMGlsi");
            address.setVersionId(1);
            address.setZipcode("OhNWKE");
            address.setAddress2("ahBILbdqxzQcMycG74jW5K0fUGGBDcDZ18yt4gZReRykCIDNCN");
            address.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            addressRepository.update(address);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByaddressTypeId() {
        try {
            java.util.List<Address> listofaddressTypeId = addressRepository.findByAddressTypeId((java.lang.String) map.get("AddressTypePrimaryKey"));
            if (listofaddressTypeId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycityId() {
        try {
            java.util.List<Address> listofcityId = addressRepository.findByCityId((java.lang.String) map.get("CityPrimaryKey"));
            if (listofcityId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<Address> listofstateId = addressRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Address> listofcountryId = addressRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.delete((java.lang.String) map.get("AddressPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAddress(EntityTestCriteria contraints, Address address) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            address.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            address.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            address.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            addressRepository.save(address);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 1, "addressLabel", "vrwJPtC7eMQ0"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "address1", "SsxODxl0FmPk1BJvh4FaUMMf8ckpgDp1qNbvg1WlCYnqjO4QMIbG45Sx1"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "address2", "Y4xYJfAdGqZKZYQLLRsQ2AINxNB2i8CreMTIGM9Sfu6F0ww13HojGRoJ3"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "address3", "3Cu1PMCA3fMhwGzW5PW1AQ9mtoHBBcxppCRzKObyCuqvyWdVkR3L7bIcW"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "zipcode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "zipcode", "REDttwS"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "latitude", "20SOHNuOekdLRskyh25jKvTl6nVJCLjMfKD4F7a9b17OvBxoNQjCXqBLnmYEWpNai"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "longitude", "oFdo9fvw9nQoS0LqbPaY97z0ecZuodeNuJvokMDylYwjlkFM79aMgCrLIXpRGmGY7"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Address address = createAddress(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = address.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        address.setAddressLabel(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 2:
                        address.setAddress1(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 3:
                        address.setAddress2(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 4:
                        address.setAddress3(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(address, null);
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 6:
                        address.setZipcode(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 7:
                        address.setLatitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 8:
                        address.setLongitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
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

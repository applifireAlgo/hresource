package com.hresource.app.server.service.organization.contactmanagement;
import com.hresource.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.hresource.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.hresource.app.server.repository.organization.contactmanagement.CoreContactsRepository;
import com.hresource.app.shared.organization.contactmanagement.CoreContacts;
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
import com.hresource.app.shared.organization.locationmanagement.Language;
import com.hresource.app.server.repository.organization.locationmanagement.LanguageRepository;
import com.hresource.app.shared.organization.contactmanagement.Gender;
import com.hresource.app.server.repository.organization.contactmanagement.GenderRepository;
import com.hresource.app.shared.organization.contactmanagement.Title;
import com.hresource.app.server.repository.organization.contactmanagement.TitleRepository;
import com.hresource.app.shared.organization.locationmanagement.Timezone;
import com.hresource.app.server.repository.organization.locationmanagement.TimezoneRepository;
import com.hresource.app.shared.organization.locationmanagement.Address;
import com.hresource.app.server.repository.organization.locationmanagement.AddressRepository;
import com.hresource.app.shared.organization.locationmanagement.AddressType;
import com.hresource.app.server.repository.organization.locationmanagement.AddressTypeRepository;
import com.hresource.app.shared.organization.locationmanagement.City;
import com.hresource.app.server.repository.organization.locationmanagement.CityRepository;
import com.hresource.app.shared.organization.locationmanagement.State;
import com.hresource.app.server.repository.organization.locationmanagement.StateRepository;
import com.hresource.app.shared.organization.locationmanagement.Country;
import com.hresource.app.server.repository.organization.locationmanagement.CountryRepository;
import com.hresource.app.shared.organization.contactmanagement.CommunicationData;
import com.hresource.app.shared.organization.contactmanagement.CommunicationGroup;
import com.hresource.app.server.repository.organization.contactmanagement.CommunicationGroupRepository;
import com.hresource.app.shared.organization.contactmanagement.CommunicationType;
import com.hresource.app.server.repository.organization.contactmanagement.CommunicationTypeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CoreContactsTestCase extends EntityTestCriteria {

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    private CoreContacts createCoreContacts(Boolean isSave) throws Exception {
        Language language = new Language();
        language.setAlpha4parentid(3);
        language.setLanguageIcon("Yl3iAm0TP2BRv0KTrHpXpSoCBV9AAQ6k2y8aWh6VbwK63BohyO");
        language.setLanguageDescription("RLN5kbY4HRiHkoomtteCJ4fiQK4ovvUKOIBE5kLtU2eHonnYmB");
        language.setLanguage("KNvoPrc74WjrxlE3v2IMjIPE0uDPzVKthYdya1WfsMWAjXcLTD");
        language.setAlpha4("fBvJ");
        language.setAlpha2("mW");
        language.setLanguageType("rVMNfcx3oIpBIWyBIk3RL7Cx9x8wmSfv");
        language.setAlpha3("6L6");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("X5EqTZBzMtpoHp9hY6dSaX6Akeuwey4fZnIKsaxQCgiGS4CVt2");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("FfoSdiblzhXvTkdcFzDPyI1dRK55OIm5slmb3N6CZwdLfpz3Pf");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setTimeZoneLabel("qp83nzsBV7cje5nzdAOUTI224ATUMCYC1vcbpP44qryfxhtc9q");
        timezone.setUtcdifference(6);
        timezone.setCities("ZguOFSBVkLfWKmkWghD8QLIujYWdxPA22ohPuAdGNuHBKsh9dZ");
        timezone.setGmtLabel("UwVXICq2IVBJfAlqx2DhvWdpAWn8ZQq0cY2QmCZp4Gx1S6MRhy");
        timezone.setCountry("Vo6pqdMhXXxTE6anAXSgIhwxZ447obwtPQm5WoNxEs7WvCK9lT");
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setAge(68);
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeLastName("GiH9YFAAKfZb1xI5tOZNaFdNhLSlOeKARt5STnuAyrhaVprRUP");
        corecontacts.setLastName("hjLCRIEa7NKLWhNTfmEgN2mzTw4qDvM0qb1hTfjEVS9Dj9O7rw");
        corecontacts.setNativeFirstName("dxRd1tHhIqlp7bVaVPWDwlbj30MtUHkvYHfqGG3zVFNVqG0ykX");
        corecontacts.setPhoneNumber("WhKv4S2wwctdBXYu59gb");
        corecontacts.setNativeTitle("LdsUVL6aIK8BnZ8maITacsS1GzZ2TLU4FthneeS76pgu46He3h");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setDateofbirth(new java.sql.Timestamp(1465218724819l));
        corecontacts.setEmailId("gOKybkrDr4xj5jUa59cQHvH0edxEW3VnmDRGSLlWzrzasLyV6n");
        corecontacts.setNativeMiddleName("cd5vjkpCM48m2CfRBrYfPai8e8kURhV54TB9xhG58YOC7pNEt7");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1465218724819l));
        corecontacts.setMiddleName("MRqLrqfZWSF1FWRUqEAHiGYAZnUCdKXcKr2I2PVJdxf9o7n0XX");
        corecontacts.setFirstName("i8Y5HYVw5B47NG1gxJSnhmkITcEcqycYEKQKHwj1nS9fJJWDmX");
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddressLabel("QnZYkKhO5TN");
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("V9J90B8AyAHaAHFPaCq3ULJdJaiZ2Y5lsxYZqDIFpHEUhXU0j2");
        addresstype.setAddressTypeIcon("2a9zP1ti2gbSLFLVpRXBuXWOTZKwWiVofAOiOyqg3O8Y2Ccqas");
        addresstype.setAddressType("gytd2E91d37VXtTqo5ocwDrsbmrOfg94rpAVGIsFqLqabrqPug");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        State state = new State();
        state.setStateCapitalLongitude(11);
        state.setStateCodeChar2("GKYvYSNzpjNUVTPijeXCGWLOQM4sMD4u");
        state.setStateCapital("Y8P0EXo7rDTSNvSIwqOdIp1Bi4ewYeIg47m4RdKdINn2YJ77iz");
        state.setStateCodeChar3("RESPjI4W10AEKrwj5WGK6tWSHEUxg1QP");
        state.setStateName("VjNFyyV1oy1LpEbSYLg7y8suH0Pew104IszP9cBDWIgOZtiFup");
        Country country = new Country();
        country.setCapitalLongitude(7);
        country.setCapitalLatitude(6);
        country.setCountryName("NOUXAEsF4kYya8C4s19XW5el4IFjZlc9ys6hqlosRkdtgGXph0");
        country.setCurrencyCode("hAW");
        country.setCapital("E2b6ndz45RtDAz0Jsb6wGwTxRnhcs5QU");
        country.setIsoNumeric(978);
        country.setCountryCode1("Eb1");
        country.setCurrencySymbol("6p5gRwgQTATC6txKLoFgGv9ZLiAItZUI");
        country.setCountryCode2("iOt");
        country.setCountryFlag("T7XqVk3LCsohxa8hnxHRO51cUJnneZB5A3KWArY1SH420Ku7OC");
        country.setCurrencyName("4eR4Ftl62ZkzTPiwIC8WlvfhIWOXWKKfc5ycsfLcAb22StLiT9");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCapitalLongitude(9);
        state.setStateCodeChar2("DBX9sbsYuAWUvVqJhl4H06UEwaxG3AuO");
        state.setStateCapital("A6iTUakWxZH1zVjamboj6c3eqActH3EgWerraTpw1NjSMgSFEB");
        state.setStateCodeChar3("k7r9YjQAducGUre0z55isFbAXecgMowB");
        state.setStateName("yQkMkyvmiEDlGDZr1vNUXytWEQ5oerbyTx1jf7UE6JgoK2vjto");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateDescription("Dn54fSccSpU6rehDlIB8clF1DALxuAVGYsS0AW0pxY6XmS4Drh");
        state.setStateFlag("OUc82sM1u3RpjccgexK1DHeOAgOXqT9f3tB5FFeZhSEnoXyd4G");
        state.setStateCode(1);
        state.setStateCapitalLatitude(11);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("9YY7BZjZXqvDMhFQbdLbkrz7l18pz1M7");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCode(2);
        city.setCityLongitude(3);
        city.setCityLatitude(11);
        city.setCityFlag("xPPGHeHurWLcgt54shfS9vwVAZykG2oWTXlys5CeiDPvyXhNvD");
        city.setCityName("NXk2npv93PuuHX3K7k58nXIoilQVhduQm5aPUNmt1Oew0rUub3");
        city.setCityDescription("0gHZlLMm6Y4nk24WuHfth1P7hnDW4zbGoWaGJfcNN6gOfbzbFM");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setAddressLabel("MwTjJEN8wGB");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLatitude("GfyOYZG4x8JU5RkWgKzHq8FEfZOKgVZ4U0NVU54l9btAimog23");
        address.setAddress1("PYSqD3t26YcOd0DUJz2qL5Ifq1LqtiAQ0ishP0ohOfcx9eEZ9b");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLongitude("h1Y2NVWF40BVEqO0yqHIzntx9a1vZdsWxIZs19MbwYyydH91hO");
        address.setAddress3("CmlAEZ6C5SiMBtnjKKVsbUiklzADodApwYr5dC8l1fAnZXDzyX");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("Nvu2WQ");
        address.setAddress2("dtyzyEEhs6D3zn7cAm9nE60OXqRMBuYpLEtgaZHApYNVnenn98");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("HeREaQcaGAZ94ppimhly7ORvnZETFSEyY3mzCvVD7YqTpwtGgF");
        communicationgroup.setCommGroupDescription("0mVTzGBX6iZlzxzZWyIo7g42x0SvLqpyeaCcp3hC5Hu5o4mMVV");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeName("YnUkEaHSPEa1WTI2wX966U24M6hFK5xMdEbe4ZdveJNAVO4Izq");
        communicationtype.setCommTypeName("ZZbP97vECNzLVmS6Hn15Gy6WFnSklxBAl54zOxGURrSKYt1FCz");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeDescription("8YVbt83XAODvxz3LbZsycyAyWvZXubeJwuF29T5kCeBOAwHDaS");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey());
        communicationdata.setCommData("PCbsDTXvzK");
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        corecontacts.setEntityValidator(entityValidator);
        return corecontacts;
    }

    @Test
    public void test1Save() {
        try {
            CoreContacts corecontacts = createCoreContacts(true);
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            corecontacts.isValid();
            corecontactsRepository.save(corecontacts);
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private TitleRepository<Title> titleRepository;

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

    @Autowired
    private CommunicationTypeRepository<CommunicationType> communicationtypeRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setAge(90);
            corecontacts.setNativeLastName("KfvYaLNpbfLAuVbyiHzDvH4Mz3nqEvl8t7hhgLmbwNdPjqQzBq");
            corecontacts.setLastName("dI8Ed0Px1I97ezaXhfCGVA0tIvUagj4cnIOqa1BChunoGH7Gex");
            corecontacts.setNativeFirstName("MEN9BbA1T7GzAA9eDccHsh0ruzXNkUkzmvk5QUApJaufVFpTWC");
            corecontacts.setPhoneNumber("Zc88WYhUOV0Jly01j9zA");
            corecontacts.setNativeTitle("wcWUhNHqPJ6LJ2BFA4H6P7nB7Zf2cAhxFF1J0LmaI9Xkd2aGWH");
            corecontacts.setVersionId(1);
            corecontacts.setDateofbirth(new java.sql.Timestamp(1465218725348l));
            corecontacts.setEmailId("wazb2JXARwuGRQDTDy2BhoecQEKIfA1WdKI9ABhvZV1oi3eh1k");
            corecontacts.setNativeMiddleName("U8tKkJ4zKbFFXiMxvO3XCLAv6EVGyxo1u2Yi6fZ9ft3Xo5hsC0");
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1465218725386l));
            corecontacts.setMiddleName("PwucqLbqNaNQbCMbMfhxVc0kJBCfBL2p88QhcBPYnJPbjmlzTz");
            corecontacts.setFirstName("3Gd3dp7vbn3hwVr0gtlX8hgDstlCFyS1aIKmws3fLlcB17T0EJ");
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBynativeLanguageCode() {
        try {
            java.util.List<CoreContacts> listofnativeLanguageCode = corecontactsRepository.findByNativeLanguageCode((java.lang.String) map.get("LanguagePrimaryKey"));
            if (listofnativeLanguageCode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBygenderId() {
        try {
            java.util.List<CoreContacts> listofgenderId = corecontactsRepository.findByGenderId((java.lang.String) map.get("GenderPrimaryKey"));
            if (listofgenderId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBytitleId() {
        try {
            java.util.List<CoreContacts> listoftitleId = corecontactsRepository.findByTitleId((java.lang.String) map.get("TitlePrimaryKey"));
            if (listoftitleId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.delete((java.lang.String) map.get("CoreContactsPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCoreContacts(EntityTestCriteria contraints, CoreContacts corecontacts) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            corecontactsRepository.save(corecontacts);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "firstName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "nUvv3bEBZIMks1cAIZuhSw8g0uShwgGj4TPmAhLtrETcxloxtlVWvQazn6uSm0GanR7SYFXKqyqota7RMXAVaXHMioIzPck206wlaLyNElkI7WGb8Sfcd0ScG3ZWYHIF27OWxegHgPX6OVc952VZKNRTWt4Vy9g5S5Zdhgi37ynCmBrbGqwDcmRb2IYjzxkPIs0TAdgrrLvvJnf9CRXqCNWVU3kiFFmJ7JTjSIrud3RNonUHTpLEqDRif68Qn1tnP"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "XA6pDXh5vVuBfLSovVIVdooKHZPmE3EHcOtSEfDRCseqGFqPIcffIQY58dLgYtVwhLgbG7taIRNsESWq5ZnGWY5SRZoPRDgkjC2TD5THx6I2PfLPKtv3MSEmn1TbzMj5Kp76V3WnI3zixEsYyhvU74iKUOsvPCIkXPDUPywmCfSSRV4FpMR27igI0n3azyw4dkQs53tQQgRlsv7FyUOvg4q1W18ozzEbdPGcRU9r7qWLRUy9EWYRk6921qnh2dEeD"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "xGdpJcpF0iNYt0VdAQjjIADZEPa2m88yVSkSdzs1grlDkUpb5VqWqm4r0OKvMsfJY0jKJAPk9JxmvxPwHWe4AW0Kl2vxBLbkRq2X9WSOfkkMsc72z4jXLaWTXRhr3AJWRt7kuiQJZs0Cjb7VDRI3uDaKGHO20NouC5nddeIGPZCnp9SvmozgVivhtHPK2YCeAO4GKz4LovXDgleg2RkMt0Y83tDRtkaJsek6ybS0bLT2dD04wIau4mzypLgv81ugy"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "thfvhn3b5UIsd3ptnMqB7wzT7awWkFlyxDHopa4TN4HnAhRyxYfIdYrmjMThYoHDi"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "O7jnkVQIUMN4ZA6WubKVCdAmmWurbhvtzddAjC1CPQOMmh8bBmzKo4MneTJAvMzAW9YcXfrZ9blRGcBJmIHUfPwevb464egp9eeD9l3pARulLb3vRJWBnVFOYmGuGxv3V4BQMGmY2MyNs1Tsk00azUYClCfpufkS3ZLsYWtEYm9JapnSluPL49A08Om8PDFN7QuzFCGrPK9zvTMKbnlX6Z2JuOJe3vVFvlwNRH0w7YKeX6Icu9yRj6HKWmsF5vJ6x"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "yD0Yr1F586Ln3u0L24ZKd4f0FniqubqQ3UJiH86ZLNKyJk9BW6WaEi2c5EUI1xY9Xpps4BLLvjycdBPXkcAd0mtXmf6ETeyUOh0lPJi4YouiFThwHfJgeVidwW3SPZgZSwUp2vGANmvnLTUjBjWFkUMUC6b4PDWbubISukvUEUkpIuYKZpWxwRFDLhJ4D4qo8guaUnx0zvsHbD5Xep3bA77gW0kqABm0ws6bIBwCdhPhRvy3FicFAaGbhtcGxGcxn"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "uUJT3KRO5Wj1bdTqFKHcVtsEGsTp0xVZyvbSKoyWl6TfMSlHQoWKmy5NC24HZ3IW5ntmJwBGoIDK5JPuLxRkCXgNTjSJe4KJ6xOxXlCMSjjPrKX44AJ8S8sD8PJmh9Bgla3FHka2A9sSP38Nf7SbFl7ucCDrSv7zcpWr5a71ARZRodJNM3UvfHzajrO8qOWMl6aoCJsNiw8xmICljneMcxv5HAnsITWuaUgP6OlWiTToiEB3Cy9tXRRNgA1M5o4HC"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 141));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "XuBs1GWoZwi8TvtZ7TzPGrMurlmGIoPoHCQMVgA2CDFkBNidEsSiaqZpo3Xr3UlEgpTZhiy4tItz7n8zoqZk8JITE7ECaXRnK8NnhEykO4YNNnh1QLzAw3hxvrdwuy3WEbImUYXIT5yCB5Aq3fNZt51Ckxhor3U9EEHjjv3xe4ZjLeNWghuQ8FyWonXPvs2ffCzPqfSHnINHNVeQejczZhVkMsBjbYrGwYUzv8dXT39VRabeRufhizE9L0YDK3kk2"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "m94zZwFe2ucyXbVCvCWAZ"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                CoreContacts corecontacts = createCoreContacts(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = corecontacts.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 2:
                        corecontacts.setFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 3:
                        corecontacts.setMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 5:
                        corecontacts.setLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 6:
                        corecontacts.setNativeTitle(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 7:
                        corecontacts.setNativeFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 8:
                        corecontacts.setNativeMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 9:
                        corecontacts.setNativeLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 10:
                        corecontacts.setAge(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 11:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 12:
                        corecontacts.setEmailId(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 13:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 14:
                        corecontacts.setPhoneNumber(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
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

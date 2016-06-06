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
import com.hresource.app.server.repository.appbasicsetup.usermanagement.LoginRepository;
import com.hresource.app.shared.appbasicsetup.usermanagement.Login;
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
import com.hresource.app.shared.organization.contactmanagement.CoreContacts;
import com.hresource.app.server.repository.organization.contactmanagement.CoreContactsRepository;
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
public class LoginTestCase extends EntityTestCriteria {

    @Autowired
    private LoginRepository<Login> loginRepository;

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

    private Login createLogin(Boolean isSave) throws Exception {
        User user = new User();
        user.setUserAccessCode(17660);
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelDescription("BHow4ylI3twIYiSjvdoXdGwQ4zOgrrgVrtG1gO5gpdORQsmnMt");
        useraccesslevel.setLevelIcon("l0mI6DS7qWNk6dToZNEHUgZn1ZSPMsjn5zmdWqdNCNn6KV6upE");
        useraccesslevel.setLevelHelp("UtGg27DEsgMP1zay1Z4AdbOH8uNvaBKW1L2g3x4ITD6c7D9PQn");
        useraccesslevel.setLevelName("9UNcG1g52M4lMH3Aq6ZZRYJjYVOZtRP7e7B3gCMlZ3WTYQIuB4");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainIcon("po3FTNwZFoGqjg3Fk0pZAVi0ltATEEiqIc6OXgrtd1q0YdD48Q");
        useraccessdomain.setDomainHelp("LUx9juVzUJvBzCnn27WjfF5TiugQSHBjuEPHkAD4v61wOnB32m");
        useraccessdomain.setDomainDescription("KmpmCi4wvidwelVwJRHXeSriEYsyTiO1bOaXgtU8Vc7j99FC3x");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainName("dJRMyC8ME5P6ctJyON4Obxer4Iq7qIJUqkHPaaTMGXGUbVeXW2");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        user.setUserAccessCode(33618);
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setIsLocked(1);
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1465218748772l));
        user.setGenTempOneTimePassword(1);
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setIsDeleted(1);
        user.setSessionTimeout(255);
        user.setMultiFactorAuthEnabled(1);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1465218748838l));
        user.setChangePasswordNextLogin(1);
        user.setPasswordAlgo("2dEVO4MGSMYyJIV8xlf8d9xQceOoUK0R743OFw48Whw4lyzCSS");
        user.setAllowMultipleLogin(1);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        passrecovery.setAnswer("Lcm22Vu67QJMMD5XW5MmL9ml0bd7S4LVNx0QZ5uqIvfjGGtoic");
        Question question = new Question();
        question.setQuestion("ClEvrz4kehN9PVEIJgrmYP1JTCiserNxKaSfKZzrP8GP16T1q0");
        question.setLevelid(4);
        question.setQuestionDetails("2qtPCq6MCJ");
        question.setQuestionIcon("woptYvuMhTemknJV4SGxxIbHeQGcPQEIN9ZMbmn4o4e3EJ98yf");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setAnswer("ZexrkH0epeScdKL7GWODJocRAKsrCI93X3NJZNvZFUSDvbLQKb");
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        passrecovery.setUser(user);
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setLast5Passwords("uMy9c5q2UsEQqDGTdYJKs5Qs7ZRIPyzXcpyVa0J74RY3lnWU73");
        userdata.setOneTimePasswordExpiry(1);
        userdata.setOneTimePassword("nWewgLcexL340we2FlJ5cBj7y0XEt6fu");
        userdata.setLast5Passwords("4iIBcWdgPbHA1mSZzM2utNR7QFnGl6ChuLzi6ZcK9BIWpaqjic");
        userdata.setOneTimePasswordExpiry(1);
        userdata.setOneTimePassword("lpgxbKyGoGrw158qUfBQTgA15bMfSSwe");
        userdata.setUser(user);
        userdata.setPassword("h5AfMR0CZRWuOHiJTyt2TRPkVZQKGEnF7hAg0V011luMNR0l3W");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1465218749304l));
        user.setUserData(userdata);
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setAge(111);
        Language language = new Language();
        language.setAlpha4parentid(1);
        language.setLanguageIcon("Qs3QMfTBZfBg9tizJ7PTSsn6566xG7Rm0H7lV6mZ8fj0uO993i");
        language.setLanguageDescription("KapA6zTJG9X2ZGfiOdiEHuRUpzq4ms6L9OTaIDg2r8L3JtTP21");
        language.setLanguage("l1XEaFQHR8OojECKxepuDIsPszqFM0HiCF5gSWXs5jpZWjuf2l");
        language.setAlpha4("2huP");
        language.setAlpha2("Xq");
        language.setLanguageType("O4dPxOJC6eYPKiODh5PY1xJGhoAO4Tbz");
        language.setAlpha3("PYA");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("hHvkueWDrqns68NNVlM22ulw7Bp2UM6cdsn5RNblSsVdOZ4FMU");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("21VrR7cdTdIl3nbaOjEvtQl6SCWOuHDiEK8Ef2iXiuT1MCLwt6");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setTimeZoneLabel("faQEmyGtMq4o28XsGfUHycC3z6SqkVevecSFC3diNEnVO4MeL1");
        timezone.setUtcdifference(9);
        timezone.setCities("4zfhVdLwcgZVFAn6DSoq9CzxqKuJTNKf9I8Sm2AwBm69B5uYnb");
        timezone.setGmtLabel("IGzA30iZxeZUbNpyvPoFQARCDGeeGv8IiGGvRPNfsgNWeWvxPm");
        timezone.setCountry("AyKncZJFzeQpHFH7IXj4PmpLDVYMtrW9j9hAAV9fpby2qdXdkL");
        corecontacts.setAge(115);
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeLastName("wgKLPNsvh9MbIyOslDPCFOWoKbcTM92lcvOO2eGnA8pTEpMLcw");
        corecontacts.setLastName("K6voBBGzjdMetKTZrDDjiytiJUDpRspuXvc9mXEuR4AG3BLiIV");
        corecontacts.setNativeFirstName("Sm9gbZbaRK73KTCSu6tgQJbAr2cdnvds2M1NnbM7cz6TsCwqiV");
        corecontacts.setPhoneNumber("023eeaaSeGuqii3eGHck");
        corecontacts.setNativeTitle("KuSWok1qqtPYLJ53VGQcUhmpcc6cNOPTS2bf2M8jFgrkK50dNG");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setDateofbirth(new java.sql.Timestamp(1465218749727l));
        corecontacts.setEmailId("7gpzhm6lNQnxyluJGf77MYWNLrVqHofvBsXaPFQ1vVt6cFZKOH");
        corecontacts.setNativeMiddleName("PlKCpL7l1FyPf4fevEMe64OiNUUUYwboyCoK9NX8oUDqvSHwq0");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1465218749727l));
        corecontacts.setMiddleName("P2hzG6wWAK8VAGR7w7uMZ0GuN86MSKW8wYWRGc2ijJqlvw0koS");
        corecontacts.setFirstName("RYAxjBU4OqANJtShsrUFSZg0VjGsNhmrxi3yzCFSI5Xa6EBjXi");
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddressLabel("YaCT3fKZVhd");
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("ZFi9T9FYyOww0P4TPTSH01BTXDzxm73Z4TWSoG0g2VGVx7xxBw");
        addresstype.setAddressTypeIcon("6K6sZlVWVriIKRNK7Dv1kbTg7wfflrTblzST7hiWFTvM4nhTuP");
        addresstype.setAddressType("fsWXbXNWudlgFdDXuw1aVync1dcGPhxxAAc5xdIMMlHE2uCqMB");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        State state = new State();
        state.setStateCapitalLongitude(9);
        state.setStateCodeChar2("05nqQTEljz3j5miE0KfBygershXyQEjo");
        state.setStateCapital("xwFdcraamENHkwteRX9sDCC3sDmW2gsvektV3XYl8vyfuXFfup");
        state.setStateCodeChar3("IgFqvokYTYjjEtaxT5aig4mtsEUfYCnj");
        state.setStateName("TmXoS9ALiozlit6HQUxRh0NoV4Vcvo5vkgEUH3DEGnlslJGnVu");
        Country country = new Country();
        country.setCapitalLongitude(9);
        country.setCapitalLatitude(2);
        country.setCountryName("MLQRrDDa4sUAoIn7FdkuB2K64TdhKPc9TuC0ASE1j2wT3YnIaG");
        country.setCurrencyCode("i8S");
        country.setCapital("rM59NevfYhYf4O22al7MAWhTKtDR9Pfm");
        country.setIsoNumeric(981);
        country.setCountryCode1("aIg");
        country.setCurrencySymbol("8fElNEd8EdmvOBQq3QOmz49e2q4IICFi");
        country.setCountryCode2("n4R");
        country.setCountryFlag("Z4HDCTHDrXaoTIQzXhGcvCj1FmZTnWf9SKt6YU1fm5Ideikgrj");
        country.setCurrencyName("6R9XLSvh43buJoioP36HAqhSXEfFgPcXMHqNJPCWASzDFbO2EM");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCapitalLongitude(1);
        state.setStateCodeChar2("Guqb7awH5EHAkLVXrybPFKhzycBc1e5I");
        state.setStateCapital("WN0a9AbXipp7xQ9lHjtpcQnCPXkxfWsKdo3xGzTyk6p8qwaHJ7");
        state.setStateCodeChar3("AdqKduKJxTiCeiOBtoGT6eQaHJpeeEUn");
        state.setStateName("uwvyf1Y312EKLgWKkIkwQ3tDjYi6P0S8ll2mHR3bLCHOslAg3K");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateDescription("LWGjW6F0V9U2ahptoeDNRsukOXaYy03rQfXcCmCyBQepP20pSI");
        state.setStateFlag("jj68nqVw99QJ6NOcEL0PUTEw1ggCOTxQO42W9L6YFdg0dfv0Tt");
        state.setStateCode(2);
        state.setStateCapitalLatitude(7);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("gnPjPDZGrgtGeVbiOzAfsJWmtHTk2f5w");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCode(3);
        city.setCityLongitude(3);
        city.setCityLatitude(5);
        city.setCityFlag("XGEqlhO8JJ72SnUJu6OJle7hVOrfau0EArrXHl7UC4ZjLxh1PF");
        city.setCityName("eiLBbk7rPwc0KYJaDDyHYKdMZzh8LNGjqKD6Hh2r52QQEcUOBv");
        city.setCityDescription("DnrsWnSXL0agtkrozRQuURbqbOeoXitamImQjGD2bIcRmnEB7G");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setAddressLabel("k3y4WkqdQBn");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLatitude("OvhbKa6XnXpBn3uIzjN640E5pxFBGwn92IHorpvU8GeMACcIA6");
        address.setAddress1("fZ3gebmQujaSL1KR0ModZmEChbX4mfLrlojY6NYGOVIGOt46UU");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLongitude("plpdd2htRCwA464jhjuYcld9IACmRNwi3FJrlNtNc9Azvk9qMT");
        address.setAddress3("jQgCoRppIVU3JvJVZHkEEgqrqt0TiylegjNmoUZUIpG8lfsjCg");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("nZ2kAG");
        address.setAddress2("kEjRzHTdFi98MhYxn8EvV6A9XDMOpY3RigrTZWJPsrZCWz9Twn");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("mS9EG9lYdjEYTkWrTkTfOP1rGjQemGRk3EtDMRI1d1TvOyPS5U");
        communicationgroup.setCommGroupDescription("YpDf8bd2dRJOlyPMqUMcM5ty6tCxnf9iO5pIlripF2lQE6JqO5");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeName("VSnmAj4ktHcrs73nMRa1VDEEf0p4iNvKIuCfEs1LnMdb7BIRnc");
        communicationtype.setCommTypeName("QmkDb9nvQz8Kmk5gNZvQ486meymrWCSPFmz77wLVgcADBiQQho");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeDescription("MadWBsa1Y2uFLcOwO8I6cihSWJX0mbKPCyvtYWNwUY1GDjS1MX");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey());
        communicationdata.setCommData("QDHpBOgpt7");
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        Login login = new Login();
        login.setServerAuthImage("RQbGfb06E1eGQGm8nbMwti4ya9ylkKxu");
        user.setUserId(null);
        login.setUser(user);
        login.setServerAuthText("rf21q3wE4HE8kE01");
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setLoginId("31zbFxStbVtwWY34mpPxFXoLnJCmzDSZR4eLu2zcxXY2ij4RTs");
        login.setFailedLoginAttempts(5);
        login.setIsAuthenticated(true);
        login.setEntityValidator(entityValidator);
        return login;
    }

    @Test
    public void test1Save() {
        try {
            Login login = createLogin(true);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            login.isValid();
            loginRepository.save(login);
            map.put("LoginPrimaryKey", login._getPrimarykey());
            map.put("UserPrimaryKey", login.getUser()._getPrimarykey());
            map.put("CoreContactsPrimaryKey", login.getCoreContacts()._getPrimarykey());
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
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setServerAuthImage("g72ueFhZmJGsY0DOvKH3vlnOwh9zTmBX");
            login.setServerAuthText("GVS9YZEwv3ADhDqV");
            login.setVersionId(1);
            login.setLoginId("0lwFdgVLD0kfqRq0btnfbA8VJUf5Swqivb9pecTubvsUKRwnxx");
            login.setFailedLoginAttempts(5);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            loginRepository.update(login);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testNQFindMappedUser() {
        try {
            loginRepository.FindMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNQFindUnMappedUser() {
        try {
            loginRepository.FindUnMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.delete((java.lang.String) map.get("LoginPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateLogin(EntityTestCriteria contraints, Login login) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            login.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            login.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            login.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            loginRepository.save(login);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "loginId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "mMzvT7ckfQGW7wow6WZqFnSJhwTpZLE73z3fL3aZZJiY38XRTDrhkATZFIQ1kQEW4SDHaKTmgNVJ3Im3zYRhv8WERFlDLqbwNWC9eKUJ1QF2q6vt9J6jEbgJl0HsqL7Q6Osqt7Rhwdq12EiAEvjxxHpWiI1SvFU5Hpmue1LJplbjsOjaUpJ331PC5Ns5CDqYFK17R9Ywr"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "u1gsuMW9wq7qPjy8HGXNMOmRZ0du4T9fc"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "F6es3p6rvrMfVVM3z"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 15));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "isAuthenticated", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Login login = createLogin(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = login.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(login, null);
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 2:
                        login.setLoginId(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 3:
                        login.setServerAuthImage(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 4:
                        login.setServerAuthText(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 5:
                        login.setFailedLoginAttempts(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 6:
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

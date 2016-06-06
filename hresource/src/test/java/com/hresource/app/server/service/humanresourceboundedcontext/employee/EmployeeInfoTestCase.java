package com.hresource.app.server.service.humanresourceboundedcontext.employee;
import com.hresource.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.hresource.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.hresource.app.server.repository.humanresourceboundedcontext.employee.EmployeeInfoRepository;
import com.hresource.app.shared.humanresourceboundedcontext.employee.EmployeeInfo;
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
import com.hresource.app.shared.humanresourceboundedcontext.employee.JobType;
import com.hresource.app.server.repository.humanresourceboundedcontext.employee.JobTypeRepository;
import com.hresource.app.shared.humanresourceboundedcontext.employee.DesignationType;
import com.hresource.app.server.repository.humanresourceboundedcontext.employee.DesignationTypeRepository;
import com.hresource.app.shared.humanresourceboundedcontext.employee.DepartmentType;
import com.hresource.app.server.repository.humanresourceboundedcontext.employee.DepartmentTypeRepository;
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
public class EmployeeInfoTestCase extends EntityTestCriteria {

    @Autowired
    private EmployeeInfoRepository<EmployeeInfo> employeeinfoRepository;

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

    private EmployeeInfo createEmployeeInfo(Boolean isSave) throws Exception {
        JobType jobtype = new JobType();
        jobtype.setJobTypeDesc("nVDQnfGrGKfnEr3fY2Or2onNV4hwvPLeV8BX74jGP7CffYEmQj");
        JobType JobTypeTest = new JobType();
        if (isSave) {
            JobTypeTest = jobtypeRepository.save(jobtype);
            map.put("JobTypePrimaryKey", jobtype._getPrimarykey());
        }
        DesignationType designationtype = new DesignationType();
        designationtype.setDesignationTypeDesc("1nIngzpRnz2heQ6Iy6sElyXwNZJfA8vFgqtBohp6ChfLhxh6KO");
        DesignationType DesignationTypeTest = new DesignationType();
        if (isSave) {
            DesignationTypeTest = designationtypeRepository.save(designationtype);
            map.put("DesignationTypePrimaryKey", designationtype._getPrimarykey());
        }
        DepartmentType departmenttype = new DepartmentType();
        departmenttype.setDepartmentTypeDesc("e2x5k5pQtx0JZaCXDQo8Gjl88zQsLhjJReGTLHZYOBwKRfcIgt");
        DepartmentType DepartmentTypeTest = new DepartmentType();
        if (isSave) {
            DepartmentTypeTest = departmenttypeRepository.save(departmenttype);
            map.put("DepartmentTypePrimaryKey", departmenttype._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setAge(124);
        Language language = new Language();
        language.setAlpha4parentid(10);
        language.setLanguageIcon("A4xZMBwQlg1cFKV9Ag3WkL4TNw2jkk30GNeppFJSKnSONQ6a1W");
        language.setLanguageDescription("jMBIrIqpqEr747onqfANRr6i1q0Gq3SL5nhTTfCcS74wcJV24R");
        language.setLanguage("KBwvNYpQEmSi1NUgUA82mHLLwwZbpWV8uyY3S5cWsStxuafjrS");
        language.setAlpha4("Sl3y");
        language.setAlpha2("Pk");
        language.setLanguageType("wcaNSFg9sWrsBKpVyBHVS4TvTIr52b3q");
        language.setAlpha3("od8");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("rR9pSL0weMeBeHTZNvBrdihEz8C6swqJ1GsDmnsj8IHYIUT6l3");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("Je3au1ByHH2jgheakrShRaUvVZT2VgJp2lQg6cHUONrueBbZQm");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setTimeZoneLabel("PKNl9sokViqCHibBhoT56ewk8so5iiCac4K1b1zwYrshgtyy5H");
        timezone.setUtcdifference(10);
        timezone.setCities("71fmcT1LR4mofp47xRav80NwUqyQAH1uD8S6sQZ6eo5B5yq2w2");
        timezone.setGmtLabel("dRJEDBc7btbU2uBuy0nFKRYItGHZZlX7m316KMOo3Io6VezNTr");
        timezone.setCountry("i7edvhfr6ji3OzpMq3j2yuVyXZ6XfoEk00umGzastwI6Hxvvon");
        corecontacts.setAge(35);
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeLastName("C0dr07AIvk2WfFQqcoyIq853ieBtATa3O3ludWiXV4OkivMoHg");
        corecontacts.setLastName("mCswAaTzytzXHoR0piVk0Vx2q3LxsWV4Iw3QrkVcU9pkVuq2IF");
        corecontacts.setNativeFirstName("z651I1TYIi1E8CDRKgm4SWpBubFOv4FBxHBvH8SuxTWjUeZHRg");
        corecontacts.setPhoneNumber("a5dVk10NhxPm7ej9R9iK");
        corecontacts.setNativeTitle("0bFRVfmQUDgfFmBeRnNFEmwpjyo8l1YGgaPQhhhExFfXaOg07g");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setDateofbirth(new java.sql.Timestamp(1465218775714l));
        corecontacts.setEmailId("2zcUQZ3XT4dAyvQt4isv40ZhmoaZZrqKtCGW0L8SnkK555Nu8Y");
        corecontacts.setNativeMiddleName("M2hNzDAyZCnosebFk6oQdGL8dTEomRueJz2Ba4GszvYfNzDuQ1");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1465218775714l));
        corecontacts.setMiddleName("kceEpt4RXDSuqQNrm6JtP8tcjtGXrOMzfF6S7nG7NBWScfOnp0");
        corecontacts.setFirstName("zv4szYsEG0RoytE5ByToKdYj7YOdmv4Fpiv2I5o5klm7823iug");
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddressLabel("8Ds8DoCaiiU");
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("1OwKyAnyoCXcTmxMjscclxVe2qQC3VHq473Kraobe0uCni4pfA");
        addresstype.setAddressTypeIcon("8aVi13sEVhZiQDWXUuHx6B7lNleQVmfSIkh3JwIbHORoA9UUCG");
        addresstype.setAddressType("rOy9kDT18nA8SxtUF5i1qz3rloPQPoXs62ZFiiIZxK9SuthLk6");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        State state = new State();
        state.setStateCapitalLongitude(10);
        state.setStateCodeChar2("0nHEYPWE3pjBWQZxgxRMs1aycFwZ3Pb7");
        state.setStateCapital("1JGEADzuIxIQqSqIQA3nJ6Gx4tYOUFbi5L7xjTTgnesYLM1xjq");
        state.setStateCodeChar3("27sx8qCnynibiuGmSYjdzEuIbyrjIOhh");
        state.setStateName("WPyAnhYa6XpVfrswYoEB9mLr11mOGUGSP9EJMeYKoNGTFaypHu");
        Country country = new Country();
        country.setCapitalLongitude(3);
        country.setCapitalLatitude(2);
        country.setCountryName("60dhFcAsaocSgP2dvIVANNWtvaQTUg5qfXyghhsDneLFMIbEmg");
        country.setCurrencyCode("mcj");
        country.setCapital("Sbh1vlwmJJnv53hHBYKwaGQGrO7le6y1");
        country.setIsoNumeric(959);
        country.setCountryCode1("ENC");
        country.setCurrencySymbol("f7qNrTULDf2LwvZ2bzNWU23iQGG5p5A2");
        country.setCountryCode2("llZ");
        country.setCountryFlag("H5HJXQsT44pbSVd6TLzfnyw36HD5qFL6ro0F2CLOjPqsS9oLAE");
        country.setCurrencyName("i5cgfRKdFfIMk3XmCTGqWKjsdR9fRwZXBi2SnOw1SVk2OqQxB7");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCapitalLongitude(9);
        state.setStateCodeChar2("ED2a9JU0hDHZMY0s2cleFIAndaiPLTTn");
        state.setStateCapital("CghF8VgMIg8ToFVzALldQC1Jk7NQNidlAw1h9FzauImMSGvXDJ");
        state.setStateCodeChar3("S5jOpRbNerW53RiD5Ub78Fuao600hyUv");
        state.setStateName("vkuBbXmLOef9MiaKqEDGTdMrRex61DT8v40TNtxFRY4sPYLOZz");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateDescription("gMxHN60M1T090cn7JCSOGkDaG53tMS402uYiyNv7qstRauyHzp");
        state.setStateFlag("ftZhAZ6Gccp5hv9dsj3sRC7xJ3bkSfOnwDouMps0XJrdk3GsIt");
        state.setStateCode(1);
        state.setStateCapitalLatitude(10);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("GL4GducJsYOg83ncxFeFyn63O7bIvZpM");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCode(1);
        city.setCityLongitude(10);
        city.setCityLatitude(8);
        city.setCityFlag("KJE9OtO6TzHDVFfesMgmfJFGfdxbj45t2Pr1VSgdy0yeiLalyX");
        city.setCityName("vGZHaHW9duLSC0sZLVg84IRz7MrKQ8fC2EWEoq6yUaZ3Klw8f6");
        city.setCityDescription("ewEOj6hVs6vm8uOULasSq1IMRDHAPgbL70kTSONuS2IxIHblUv");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setAddressLabel("2XDSg2BGhBn");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLatitude("GZf8EWubCDWNVrcyUl3goKxVvzi6UX8d5TbIQy0C6byLDUmk9U");
        address.setAddress1("Id3da5lJ4UR3ib6PuYfTTWukwCfLp3AXx2PXiXqBNRBfGayu9M");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLongitude("KHltqLej2qFFLNYktDZAXrcRFgtEpTjQwf6mzynE0Jbq49YZJ6");
        address.setAddress3("PCOfC3ChiEIN0rSeD4u76IV0OO1OVF0WFHdwJkMaCrayrCs6hm");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("LWoZXO");
        address.setAddress2("QEUc5AzbRFBDbQN5mSmRM8dFzly2BWIn0kILJjEjMqcoGalf30");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("50XDdLCft38Lisoo8UtpbcKqSsDgVgWxXZHdk4zs2HRGWGABdJ");
        communicationgroup.setCommGroupDescription("TwXyfeEWgj97iXdHgyRyGwuJVMuv8gv8Xa8p3laDAudOlr79wS");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeName("qbkrELrMABUY7W5M4fKY2OmWMbgdKKnxybsTeINCDgZNWv7PUq");
        communicationtype.setCommTypeName("1WQMrQem8YtzBoFiL9hifaxi9FI8hPssQHdy8V7jH9BUlNDjUY");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeDescription("dpYBe1Bb62woKK2qc61J7juoptj4m2PGVww1B7Y5J6g1GIqhml");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommData("RSbyTktlzT");
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        EmployeeInfo employeeinfo = new EmployeeInfo();
        employeeinfo.setJobTypeCode((java.lang.String) JobTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        employeeinfo.setReportingOfficer("XjxrS278G9MJTOIt9UI9FZmhZuqPterFrDB2TqNWi46dxpw7W5");
        employeeinfo.setPan(valueGenerator.randomValueGenerate("String", 256, 1));
        employeeinfo.setDesignationTypeCode((java.lang.String) DesignationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        employeeinfo.setDeptTypeCode((java.lang.String) DepartmentTypeTest._getPrimarykey());
        corecontacts.setContactId(null);
        employeeinfo.setCoreContacts(isSave ? corecontactsRepository.save(corecontacts) : corecontacts);
        if (isSave) {
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        }
        employeeinfo.setEntityValidator(entityValidator);
        return employeeinfo;
    }

    @Test
    public void test1Save() {
        try {
            EmployeeInfo employeeinfo = createEmployeeInfo(true);
            employeeinfo.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            employeeinfo.isValid();
            employeeinfoRepository.save(employeeinfo);
            map.put("EmployeeInfoPrimaryKey", employeeinfo._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private JobTypeRepository<JobType> jobtypeRepository;

    @Autowired
    private DesignationTypeRepository<DesignationType> designationtypeRepository;

    @Autowired
    private DepartmentTypeRepository<DepartmentType> departmenttypeRepository;

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
            org.junit.Assert.assertNotNull(map.get("EmployeeInfoPrimaryKey"));
            EmployeeInfo employeeinfo = employeeinfoRepository.findById((java.lang.String) map.get("EmployeeInfoPrimaryKey"));
            employeeinfo.setReportingOfficer("MMvtIhJ1uJZGzTZUbTnFnlwZsXbNb77rYFLHxOtWzmbJRObhkz");
            employeeinfo.setPan("FnIRG7hgcgI6ZfxS2CtuKG1mUUkQaJijbBgd5GO3KkyWUdR3lA");
            employeeinfo.setVersionId(1);
            employeeinfo.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            employeeinfoRepository.update(employeeinfo);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByjobTypeCode() {
        try {
            java.util.List<EmployeeInfo> listofjobTypeCode = employeeinfoRepository.findByJobTypeCode((java.lang.String) map.get("JobTypePrimaryKey"));
            if (listofjobTypeCode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBydesignationTypeCode() {
        try {
            java.util.List<EmployeeInfo> listofdesignationTypeCode = employeeinfoRepository.findByDesignationTypeCode((java.lang.String) map.get("DesignationTypePrimaryKey"));
            if (listofdesignationTypeCode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBydeptTypeCode() {
        try {
            java.util.List<EmployeeInfo> listofdeptTypeCode = employeeinfoRepository.findByDeptTypeCode((java.lang.String) map.get("DepartmentTypePrimaryKey"));
            if (listofdeptTypeCode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("EmployeeInfoPrimaryKey"));
            employeeinfoRepository.findById((java.lang.String) map.get("EmployeeInfoPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("EmployeeInfoPrimaryKey"));
            employeeinfoRepository.delete((java.lang.String) map.get("EmployeeInfoPrimaryKey")); /* Deleting refrenced data */
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
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            departmenttypeRepository.delete((java.lang.String) map.get("DepartmentTypePrimaryKey")); /* Deleting refrenced data */
            designationtypeRepository.delete((java.lang.String) map.get("DesignationTypePrimaryKey")); /* Deleting refrenced data */
            jobtypeRepository.delete((java.lang.String) map.get("JobTypePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateEmployeeInfo(EntityTestCriteria contraints, EmployeeInfo employeeinfo) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            employeeinfo.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            employeeinfo.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            employeeinfo.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            employeeinfoRepository.save(employeeinfo);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "reportingOfficer", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "reportingOfficer", "602buW1ogG3BBFdlXWMw5XsdRuwUMqwek9PNWg7BYnIPabJAgOn1A8u5R5laQ1QaYgwTO2Dud8fDATTJekvSy3hprefDRNTzTZvnw5CgWw8tL1UHIEFmILjWw36Wkt14ILsH0jsxpM8mbZ0KuHCFXOIO5tNETMWXaoAW5nahphbBrZusQUtjPzFc8iY7X1cBHt2dRlRXtP68YXycEggzQTy5AdLDOwMhgC6XAL0URnQJ0L8VFQogqGYBe12XaByxY"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "pan", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "pan", "knV6HIkYHPnVF533bnsTQ5y0efv8ANTBJB3kdFKZElwdhdEsSbL2l8DlyW9QWWyMKOFovYgCIRyWUXyLIiBvxYnCCP4M8dZ0nlL2JC9EE1rIep316HFA57CBiHRgq94z9anffwMIjLx7Aqhxak8QKkBn6OMUDUR6BNShsdXy8Zek4ZZt7fe6yWW9HqVTzcp2iuOQqsjjKJ7rBsNG9I3kWGEvxi4iiSdLNdgRkssqmwkLbs2LftlRJtMyPgMRlz1s7"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 5, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        EmployeeInfo employeeinfoUnique = employeeinfoRepository.findById((java.lang.String) map.get("EmployeeInfoPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                EmployeeInfo employeeinfo = createEmployeeInfo(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = employeeinfo.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(employeeinfo, null);
                        validateEmployeeInfo(contraints, employeeinfo);
                        failureCount++;
                        break;
                    case 2:
                        employeeinfo.setReportingOfficer(contraints.getNegativeValue().toString());
                        validateEmployeeInfo(contraints, employeeinfo);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(employeeinfo, null);
                        validateEmployeeInfo(contraints, employeeinfo);
                        failureCount++;
                        break;
                    case 4:
                        employeeinfo.setPan(contraints.getNegativeValue().toString());
                        validateEmployeeInfo(contraints, employeeinfo);
                        failureCount++;
                        break;
                    case 5:
                        employeeinfo.setPan(employeeinfoUnique.getPan());
                        validateEmployeeInfo(contraints, employeeinfo);
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

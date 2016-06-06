package com.hresource.app.server.service.humanresourceboundedcontext.payroll;
import com.hresource.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.hresource.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.hresource.app.server.repository.humanresourceboundedcontext.payroll.SalaryStucFinWiseRepository;
import com.hresource.app.shared.humanresourceboundedcontext.payroll.SalaryStucFinWise;
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
import com.hresource.app.shared.humanresourceboundedcontext.employee.EmployeeInfo;
import com.hresource.app.server.repository.humanresourceboundedcontext.employee.EmployeeInfoRepository;
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
public class SalaryStucFinWiseTestCase extends EntityTestCriteria {

    @Autowired
    private SalaryStucFinWiseRepository<SalaryStucFinWise> salarystucfinwiseRepository;

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

    private SalaryStucFinWise createSalaryStucFinWise(Boolean isSave) throws Exception {
        EmployeeInfo employeeinfo = new EmployeeInfo();
        JobType jobtype = new JobType();
        jobtype.setJobTypeDesc("XcUs39YzQFnBh6yVPUWWyDli2BCyJFs3NL64syyAeYu9ZfW2lk");
        JobType JobTypeTest = new JobType();
        if (isSave) {
            JobTypeTest = jobtypeRepository.save(jobtype);
            map.put("JobTypePrimaryKey", jobtype._getPrimarykey());
        }
        DesignationType designationtype = new DesignationType();
        designationtype.setDesignationTypeDesc("ndgTfLTj8wiEitNhdogOuXZhIfztv4aFNNXqFCb2UgZrUAa8u8");
        DesignationType DesignationTypeTest = new DesignationType();
        if (isSave) {
            DesignationTypeTest = designationtypeRepository.save(designationtype);
            map.put("DesignationTypePrimaryKey", designationtype._getPrimarykey());
        }
        DepartmentType departmenttype = new DepartmentType();
        departmenttype.setDepartmentTypeDesc("528I4rzt54XX58uIBKMfPVtwEe63DglO4CLnli59tLqVW6ENIw");
        DepartmentType DepartmentTypeTest = new DepartmentType();
        if (isSave) {
            DepartmentTypeTest = departmenttypeRepository.save(departmenttype);
            map.put("DepartmentTypePrimaryKey", departmenttype._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setAge(92);
        Language language = new Language();
        language.setAlpha4parentid(9);
        language.setLanguageIcon("Iq0loTEp8qt89RVNlurURGW2Jeo9FlnYwICByTaEk2LSBxW0dC");
        language.setLanguageDescription("yNGzBpOxy0sPbt9nvpbO0at3yoWh7dMdHfYbyPV1DYbMkOwNPM");
        language.setLanguage("4rbTRU2XaLwMInHdAtC9Rgcu2dOHxUslhqrpkHY67BvYY6zKTr");
        language.setAlpha4("bNCd");
        language.setAlpha2("4O");
        language.setLanguageType("idW6eudKtv052ile5WymJqwwRaWZ9zrg");
        language.setAlpha3("E8c");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("ppRwdxpjsPlVLHCSsyFxJXgjUUiQXYlSXu3Mdb3KICoz97ZBID");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("H4nyAd6XUkc9AUFUmWPW04tWVVR1WZkgGi8hmDLlxz88iCZWAW");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setTimeZoneLabel("DWOdS5JP9vC8cxkBybPZrzf0mvQYemoothxRts0qrjtK1lu16f");
        timezone.setUtcdifference(3);
        timezone.setCities("znj1YTatcXRqPllK25xvzPRPIF9hKoPmaZuFNUMC4uah0G17aG");
        timezone.setGmtLabel("ejVdrBYyXtUSQbRty8iDtRKpgaIpuXo1G4HnnBRQGYZ5FSGKRf");
        timezone.setCountry("zc5nIYLP8f4y40vTiEgpmYwR3qkFlbofNS8T01ViHQBogw35jr");
        corecontacts.setAge(29);
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeLastName("Rdl46twV6IGixXfAGvdpiqdqXehmV0M5hFbRNxzlaRteebMNUI");
        corecontacts.setLastName("XzkY8r1O8y7KmviwJ0uaypFln6rWE6xMVYNd8flWCNAkdghPzr");
        corecontacts.setNativeFirstName("ogfMgjv0KW9OR4xozSfIYO6drwnys7hzIcIySXlAC2L3CX0jYb");
        corecontacts.setPhoneNumber("16m7eEIbR5YeogoO9EhH");
        corecontacts.setNativeTitle("Fgyw9ycQjRJMz0ncOE8jF2a5qgMSyRIQMYem3AP5DYjClGgWzT");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setDateofbirth(new java.sql.Timestamp(1465218786260l));
        corecontacts.setEmailId("jPsTaW0FZsISQ0SuopzN3eJlwu3kzvBwygx51EVYjU1469oWAN");
        corecontacts.setNativeMiddleName("FzsPVToKgHUNFUXZVf38PN3UbJYeo5lfdHUVndzEhSQx6xzM8g");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1465218786261l));
        corecontacts.setMiddleName("A8LwFVnlac65xIeRse2GvXu9Ldh1gXKn5wIXkp9e5WxVoPyslM");
        corecontacts.setFirstName("qoNXpTJIz9LCr1bJC3KpIcou2fzZIATVwGt8jfUcpDaP35eLz8");
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddressLabel("D0Hut4OEa0k");
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("iYjbnxXh0mGyosGI83XKmpT20qCS5Hg27RZOAczpZbX5A3gUNo");
        addresstype.setAddressTypeIcon("APg1L6WSlpoQvaa6CN0l0v49tnHqmWlpP9KRaQGTAkMWRgMR7w");
        addresstype.setAddressType("4yACCL2yRQcEOuq1nGYnXBZKLvUHr1Hd6zgvdnUr64FmwTpU7f");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        State state = new State();
        state.setStateCapitalLongitude(10);
        state.setStateCodeChar2("Pfhgn2ndi9fpBiPzGz4w8GSAbiWwqNXe");
        state.setStateCapital("tt709N4O6H3jh1CyjBCgaD4qRTJUOJntG8WhaC9W7dBXnSTcYF");
        state.setStateCodeChar3("zSl91dQ0lh8gnORUqoloxKzlPmF4SLAP");
        state.setStateName("n1xcrCg7ajfEuNOhf78KxIgNBCbeVb3jGwkPDREb2Hd3Ix92Vj");
        Country country = new Country();
        country.setCapitalLongitude(2);
        country.setCapitalLatitude(7);
        country.setCountryName("jFUojedp4QVmdaeYDvoOgtcon5ChCr4HL3Afcytjlolgyov177");
        country.setCurrencyCode("LCj");
        country.setCapital("cuWYXEAwdrSIM0iEnE2TZ4ohETbeWA7x");
        country.setIsoNumeric(746);
        country.setCountryCode1("1tq");
        country.setCurrencySymbol("7r8BiPOGEigToFgqCNHLIkz1zFR2vday");
        country.setCountryCode2("Knj");
        country.setCountryFlag("L4IN2mUfcrpG1CD49AtMf987YWfd69fSjiICgTKHm50s5m3RI0");
        country.setCurrencyName("gbTCRiFkx0KlIlhAMGknU44WwIPY7qk6PnX4x1gBkAnPXguDtu");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCapitalLongitude(2);
        state.setStateCodeChar2("DdmEtafZIcooruuCFLbwX6VLGSumKPjE");
        state.setStateCapital("nvd5BTLpFO8rjwfkdZea1KMF5j0fEWpIHvAy8qHz8Bm82f8eAN");
        state.setStateCodeChar3("rVCWYR9OGhQwk6wSONY6FfSM9h0uxR8l");
        state.setStateName("fq0YRBaMDYcNWvIEzHu2FR86TatLncYT3iYCSEmbjo6dihduWq");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateDescription("NKhG8U57JT5VTHbxDJcucq9oBnUSFwZ13zbgC6ze5kyIgR5rwo");
        state.setStateFlag("K5sOE39RvK56XQHR6rEVYk1CpjZWEca4HsLfsXMCWQ30msuS7D");
        state.setStateCode(1);
        state.setStateCapitalLatitude(6);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("dwpsdW1jHQydThmvssSVbjVqXM7orWwo");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCode(2);
        city.setCityLongitude(2);
        city.setCityLatitude(6);
        city.setCityFlag("e0BkRNgHzLAvzVD4vsqCwkjmc07f3NX3FA9CGvV1bb52nPc1JD");
        city.setCityName("f9ZVInXgo4au9v8VNL0GZaYMf9hMUJtDkOMy0NnFEbhmmEm8Gs");
        city.setCityDescription("raq0y2Bs64Dqzrl9DEkJlAqBibGFnh9C5vOk1bqQzRZp5c1D4k");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setAddressLabel("Cy1PKj78U0B");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLatitude("87M5RB5xN5xkK8Xz9Is12dKOyqjd4WrZJjySnRUilsRCorzGtW");
        address.setAddress1("arL8LdPAiYbh2tORYV9dMIVxv7hNXt2i8ln3nGJsMcubOG98BB");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLongitude("yTqknSVlnVcrfBCxZ0scW2l91ewETXdaHi65qREbU4sDmqlwnJ");
        address.setAddress3("S1QDRbvV4NBs5arYi3SEZl9dP18tY73l5l9yTKfL6EUZAf4XVO");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("H4HJ8U");
        address.setAddress2("MX13D8OGzFNxSceWjuxGErzDfmvXyGK0E4kAUduY8N9WAdCjxw");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("4j1W2cGz7wU9J9ITHzvRqFphqqHdRrBQOr5OPc54ApNWlULbgH");
        communicationgroup.setCommGroupDescription("wY5XnuOrlrlevQqb7l0bKVllznl94Ia3YSe4YjY7rYnezxFyjN");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeName("h3SX7NnrVSKweARvzM6oLnfKP116vIHaLa4LMM3GzHvowPhhoV");
        communicationtype.setCommTypeName("bIGLJ1XFXuj6HGBeoLNtxrwCA57bjUz7GEBMriACA74oXthzJi");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeDescription("1BvLkPwFwRCTx94gW4ZIYmgFKa3JBUDXQ7X3pQikI6cS2NLoHL");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommData("2DOpCvI3io");
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        employeeinfo.setJobTypeCode((java.lang.String) JobTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        employeeinfo.setReportingOfficer("nbGH149yymIxrNCsjdw3fJmpNAs2hTtJBCkSUa339Ym8V3ltKB");
        employeeinfo.setPan(valueGenerator.randomValueGenerate("String", 256, 1));
        employeeinfo.setDesignationTypeCode((java.lang.String) DesignationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        employeeinfo.setDeptTypeCode((java.lang.String) DepartmentTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setContactId(null);
        employeeinfo.setCoreContacts(isSave ? corecontactsRepository.save(corecontacts) : corecontacts);
        if (isSave) {
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        }
        EmployeeInfo EmployeeInfoTest = new EmployeeInfo();
        if (isSave) {
            EmployeeInfoTest = employeeinfoRepository.save(employeeinfo);
            map.put("EmployeeInfoPrimaryKey", employeeinfo._getPrimarykey());
        }
        SalaryStucFinWise salarystucfinwise = new SalaryStucFinWise();
        salarystucfinwise.setTotalTax(5390.0d);
        salarystucfinwise.setTakeHome(6600.0d);
        salarystucfinwise.setTaxableAmount(-5000.0d);
        salarystucfinwise.setTotalCTC(7400.0d);
        salarystucfinwise.setConvenceAllowance(5800.0d);
        salarystucfinwise.setMedicalAllowance(-3200.0d);
        salarystucfinwise.setEmpId((java.lang.String) EmployeeInfoTest._getPrimarykey());
        salarystucfinwise.setSpecialAllowance(-2800.0d);
        salarystucfinwise.setYearValue(2147483647);
        salarystucfinwise.setEducationalAllowance(-2000.0d);
        salarystucfinwise.setHra(2800.0d);
        salarystucfinwise.setBasic(7500.0d);
        salarystucfinwise.setSalaryDrawn(-8800.0d);
        salarystucfinwise.setPerk(-8800.0d);
        salarystucfinwise.setNonTaxableAmount(-6600.0d);
        salarystucfinwise.setEntityValidator(entityValidator);
        return salarystucfinwise;
    }

    @Test
    public void test1Save() {
        try {
            SalaryStucFinWise salarystucfinwise = createSalaryStucFinWise(true);
            salarystucfinwise.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            salarystucfinwise.isValid();
            salarystucfinwiseRepository.save(salarystucfinwise);
            map.put("SalaryStucFinWisePrimaryKey", salarystucfinwise._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private EmployeeInfoRepository<EmployeeInfo> employeeinfoRepository;

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
            org.junit.Assert.assertNotNull(map.get("SalaryStucFinWisePrimaryKey"));
            SalaryStucFinWise salarystucfinwise = salarystucfinwiseRepository.findById((java.lang.String) map.get("SalaryStucFinWisePrimaryKey"));
            salarystucfinwise.setTotalTax(6200.0d);
            salarystucfinwise.setTakeHome(7300.0d);
            salarystucfinwise.setTaxableAmount(8300.0d);
            salarystucfinwise.setTotalCTC(4300.0d);
            salarystucfinwise.setConvenceAllowance(5200.0d);
            salarystucfinwise.setMedicalAllowance(-4800.0d);
            salarystucfinwise.setSpecialAllowance(-9700.0d);
            salarystucfinwise.setYearValue(2147483647);
            salarystucfinwise.setEducationalAllowance(1700.0d);
            salarystucfinwise.setHra(8800.0d);
            salarystucfinwise.setBasic(5400.0d);
            salarystucfinwise.setVersionId(1);
            salarystucfinwise.setSalaryDrawn(-8900.0d);
            salarystucfinwise.setPerk(800.0d);
            salarystucfinwise.setNonTaxableAmount(-2300.0d);
            salarystucfinwise.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            salarystucfinwiseRepository.update(salarystucfinwise);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("SalaryStucFinWisePrimaryKey"));
            salarystucfinwiseRepository.findById((java.lang.String) map.get("SalaryStucFinWisePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByempId() {
        try {
            java.util.List<SalaryStucFinWise> listofempId = salarystucfinwiseRepository.findByEmpId((java.lang.String) map.get("EmployeeInfoPrimaryKey"));
            if (listofempId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("SalaryStucFinWisePrimaryKey"));
            salarystucfinwiseRepository.delete((java.lang.String) map.get("SalaryStucFinWisePrimaryKey")); /* Deleting refrenced data */
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

    private void validateSalaryStucFinWise(EntityTestCriteria contraints, SalaryStucFinWise salarystucfinwise) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            salarystucfinwise.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            salarystucfinwise.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            salarystucfinwise.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            salarystucfinwiseRepository.save(salarystucfinwise);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "yearValue", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "perk", 1.3493078729359921E19d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "totalCTC", 1.5927623876725731E19d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "takeHome", 1.202004361867205E19d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "salaryDrawn", 1.1763491347706057E19d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "taxableAmount", 1.2055405955922299E19d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "nonTaxableAmount", 9.58857524947506E18d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "totalTax", 1.1471089537349067E19d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "basic", 1.1651991464064733E19d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "hra", 1.186248805235293E19d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "convenceAllowance", 1.8372242560691225E19d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "medicalAllowance", 1.2598472619595985E19d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 13, "educationalAllowance", 1.677720790513145E19d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 14, "specialAllowance", 1.3815168967508808E19d));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                SalaryStucFinWise salarystucfinwise = createSalaryStucFinWise(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = salarystucfinwise.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(salarystucfinwise, null);
                        validateSalaryStucFinWise(contraints, salarystucfinwise);
                        failureCount++;
                        break;
                    case 2:
                        salarystucfinwise.setPerk(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateSalaryStucFinWise(contraints, salarystucfinwise);
                        failureCount++;
                        break;
                    case 3:
                        salarystucfinwise.setTotalCTC(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateSalaryStucFinWise(contraints, salarystucfinwise);
                        failureCount++;
                        break;
                    case 4:
                        salarystucfinwise.setTakeHome(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateSalaryStucFinWise(contraints, salarystucfinwise);
                        failureCount++;
                        break;
                    case 5:
                        salarystucfinwise.setSalaryDrawn(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateSalaryStucFinWise(contraints, salarystucfinwise);
                        failureCount++;
                        break;
                    case 6:
                        salarystucfinwise.setTaxableAmount(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateSalaryStucFinWise(contraints, salarystucfinwise);
                        failureCount++;
                        break;
                    case 7:
                        salarystucfinwise.setNonTaxableAmount(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateSalaryStucFinWise(contraints, salarystucfinwise);
                        failureCount++;
                        break;
                    case 8:
                        salarystucfinwise.setTotalTax(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateSalaryStucFinWise(contraints, salarystucfinwise);
                        failureCount++;
                        break;
                    case 9:
                        salarystucfinwise.setBasic(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateSalaryStucFinWise(contraints, salarystucfinwise);
                        failureCount++;
                        break;
                    case 10:
                        salarystucfinwise.setHra(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateSalaryStucFinWise(contraints, salarystucfinwise);
                        failureCount++;
                        break;
                    case 11:
                        salarystucfinwise.setConvenceAllowance(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateSalaryStucFinWise(contraints, salarystucfinwise);
                        failureCount++;
                        break;
                    case 12:
                        salarystucfinwise.setMedicalAllowance(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateSalaryStucFinWise(contraints, salarystucfinwise);
                        failureCount++;
                        break;
                    case 13:
                        salarystucfinwise.setEducationalAllowance(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateSalaryStucFinWise(contraints, salarystucfinwise);
                        failureCount++;
                        break;
                    case 14:
                        salarystucfinwise.setSpecialAllowance(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateSalaryStucFinWise(contraints, salarystucfinwise);
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

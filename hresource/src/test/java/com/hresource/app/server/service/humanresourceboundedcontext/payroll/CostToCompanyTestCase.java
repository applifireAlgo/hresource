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
import com.hresource.app.server.repository.humanresourceboundedcontext.payroll.CostToCompanyRepository;
import com.hresource.app.shared.humanresourceboundedcontext.payroll.CostToCompany;
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
public class CostToCompanyTestCase extends EntityTestCriteria {

    @Autowired
    private CostToCompanyRepository<CostToCompany> costtocompanyRepository;

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

    private CostToCompany createCostToCompany(Boolean isSave) throws Exception {
        EmployeeInfo employeeinfo = new EmployeeInfo();
        JobType jobtype = new JobType();
        jobtype.setJobTypeDesc("FFwyDUAkHhgQyiaz7uUsdn68bWbj5IaqHVZarvIkIp2JWTTU3a");
        JobType JobTypeTest = new JobType();
        if (isSave) {
            JobTypeTest = jobtypeRepository.save(jobtype);
            map.put("JobTypePrimaryKey", jobtype._getPrimarykey());
        }
        DesignationType designationtype = new DesignationType();
        designationtype.setDesignationTypeDesc("LfDCz652fMiFBrl4URFq4gklW2pYk2sZzAyGhhOjextH2tS6DN");
        DesignationType DesignationTypeTest = new DesignationType();
        if (isSave) {
            DesignationTypeTest = designationtypeRepository.save(designationtype);
            map.put("DesignationTypePrimaryKey", designationtype._getPrimarykey());
        }
        DepartmentType departmenttype = new DepartmentType();
        departmenttype.setDepartmentTypeDesc("5urA68Z7gyvosngetsvPiN2GwicS1hilcAaeXHeUTRsfVNGtVO");
        DepartmentType DepartmentTypeTest = new DepartmentType();
        if (isSave) {
            DepartmentTypeTest = departmenttypeRepository.save(departmenttype);
            map.put("DepartmentTypePrimaryKey", departmenttype._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setAge(78);
        Language language = new Language();
        language.setAlpha4parentid(1);
        language.setLanguageIcon("hioUwMqDtptlaT4DJZyGrlaXPlMrjuYadRpCQOyZ30H8e0tkIT");
        language.setLanguageDescription("ejtxnsuLwoQzcN8HJmUjZaDGVDzYtmWrYcHSqI87YdkAmf6oNP");
        language.setLanguage("j23O9I898GRfhft0svAz5b86Q7eU1rMRvMnVxsDlxkRONapC3C");
        language.setAlpha4("Tpb7");
        language.setAlpha2("nj");
        language.setLanguageType("PS7vOe3DsOxtrMddUT4y16Rp8jRBdyIl");
        language.setAlpha3("NcS");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("mV99uQjmExaP1FqXwTOe9MvFfHDADlzj7gCEAqJye2i5Dy0bjR");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("pdoOWEFnTgRHZki4yF1FhO0JAKSZB5W2F5AESKfzXlkMHkLR19");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setTimeZoneLabel("NYTSZ8RaDyQtz4kNqrwe9x1Lhq31smuyG0YEIG4quVVfDsQHvW");
        timezone.setUtcdifference(5);
        timezone.setCities("upDmjoMJdzVysTva55CmZTQtaUQSZFybBvhsaA6BL6y6BSgQRt");
        timezone.setGmtLabel("XybklHEPqMVEH2IARIYZdbffoXQGTognqxnWfeh1x97YFDrxfk");
        timezone.setCountry("UwcyYYKKcLZhj6skuvAZdPPQ9TCRqpmR7aaYgQGj65i3lYOe8N");
        corecontacts.setAge(79);
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeLastName("8IbjiGw9FM1xukCuQRguky1OSlBloheJW1a3qfknTu9gg5UzJ4");
        corecontacts.setLastName("x4Rvb7FygPzo9Ad3Oet6W3hly6t098av8PcJRNdHwOVp8C4nuC");
        corecontacts.setNativeFirstName("tDv9TzeNpnNzzDWNKSK9kEmgZWjcK7i30LuIddDb3gwo0p1xB9");
        corecontacts.setPhoneNumber("Z5odoBKZrCJo6sjZlTXu");
        corecontacts.setNativeTitle("wXz8iPFpy29hEuBbR8FfccxpbRPelJiBw3hlj9Okt2F8auNPsx");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setDateofbirth(new java.sql.Timestamp(1465218784193l));
        corecontacts.setEmailId("FYsKWkQjaaI93S8LjsFaMisuDGOG8venYtQP5NZqu3HK1QoEB3");
        corecontacts.setNativeMiddleName("vvPvCC7I9pWQ26v2VWsfbS3SmtMjkhJUYOWAzlkkjw9mlolTl0");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1465218784193l));
        corecontacts.setMiddleName("adDHXzN5cFy7NngbjS72alGYMRxAtDm0O3OEwSgEUQe4E62ljq");
        corecontacts.setFirstName("mXl6mCkCpBnXEwwZP5SrHhfpQoeTwxFWoMMjR3Vjj7DZo3BRfE");
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddressLabel("OhudfEwS7nV");
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("i5OLgl7UzCvuvGu7zUuehtXhUI83eTxkWys3dKpL68zNn7lVI4");
        addresstype.setAddressTypeIcon("EoOfKPqeVZMMkmSaTFlUO39uwC4O9ZeCfaFEpUtpUjG52Hb3uE");
        addresstype.setAddressType("p5sAYVrZjdmI7AtwITCngh9pTDxAaLyCHoLpqjLBIlzxosTOmp");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        State state = new State();
        state.setStateCapitalLongitude(3);
        state.setStateCodeChar2("wABFScIhD7LIktN3CmbhU8Vu7pkUxpcy");
        state.setStateCapital("MeE8I5GSAYuj97BGqjDuz8NlCWdjggkiI6KmBjh95AF2fPQJ3U");
        state.setStateCodeChar3("IZvrxgvTC2166emP8Il8xDptESpbxRnw");
        state.setStateName("LOzXzYnca1exBLiH6pjg9O5tFUlXXixl59QirdCAmL44Jf8Iyg");
        Country country = new Country();
        country.setCapitalLongitude(7);
        country.setCapitalLatitude(11);
        country.setCountryName("22au24GAAaiF6RLdzIJ7ZF0r59LrDjcr632Vq8ZqEL4LMVKJ7P");
        country.setCurrencyCode("dIv");
        country.setCapital("pmGXzBcHWwzZ0KyIeyct18rz0CP89Yfl");
        country.setIsoNumeric(624);
        country.setCountryCode1("94l");
        country.setCurrencySymbol("px1HM9GvAtQIl44CSTRkv8yLy62xZU3Z");
        country.setCountryCode2("TS7");
        country.setCountryFlag("d63vs2JRrwJviHD9q59qSOTl1gSaA3t2j6U0yf8ZFfd8NcTJy6");
        country.setCurrencyName("bzuGfJpMpGSm18A9YoNTIk6WHDVgONUKbTlecOOeyxVvm4LCxJ");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCapitalLongitude(5);
        state.setStateCodeChar2("g4haWjSPDIh9Gj4zOQKtSA2RoexKFI6M");
        state.setStateCapital("O9CpfxTCGiU6Mdm69VJCtBIgXR7yTMyVceDR9inBLPlv5xdKQZ");
        state.setStateCodeChar3("2D9fSbZGWv0lOqpd4bT8r10u9AVxhP22");
        state.setStateName("RyjzVheQnXiuAw1Ts1HvpJGYt2XiFYlGBYkKfNzLfCK7G8WlVn");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateDescription("7tOgqgzrus8xSdnb9XfuHruZz4M5gHQbofIrKeO7cyZngstaBX");
        state.setStateFlag("T153fb4ByZUMznlxHz475g1Ma0EuATywVnh9gg6XcXK8gFOmFw");
        state.setStateCode(1);
        state.setStateCapitalLatitude(7);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("vpoXCdzejW7LisT8HlQ9geWyVaKhfj3c");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCode(3);
        city.setCityLongitude(7);
        city.setCityLatitude(7);
        city.setCityFlag("AS2h14IWTex1drHC8CfImitIMdcq0cwHZXoD0Ho0jeyq5L7vHg");
        city.setCityName("OwmI3zkXLKJ3TOTVJGBmfZQzz1GjsT1uOauVworOnY9xORqdvZ");
        city.setCityDescription("m4H9rUn1VLiNxp8lUJAMqDVrWATvczhrgNcvVdOYwv9b9tf9lW");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setAddressLabel("kBrY5dZtDZV");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLatitude("OHAYhfNMOGwG3nwzwZ5LuUjfVonvzZW42GqSWwvzqaGJ389Nw6");
        address.setAddress1("0ZAQJlUIV7PdKWCSfeWt7llKXK3Pg3rjjoCvi1cuMXcWDIqUeI");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLongitude("9jSV6KEuPYpn8bxlxTG8HYl3Ae4JXsBe2hJuGILMiJ1861v7Tz");
        address.setAddress3("ZTluk4fOzrqJQeiMegEAxzYwRXY6WbBhve24XxGobpRL6ZHdLM");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("1x79sW");
        address.setAddress2("bkps2j3zXDTALgMW5C7Btn5aJfOSBAAsYFD0Brz20TfdwS9TRL");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("vSOayAgHTmz9YWZLtUYtFWKZOfOhbCuhUehvqVyX9ir0mUbVAJ");
        communicationgroup.setCommGroupDescription("fRwq8ytXDfr930cRJh2W4y8LFqAMphwqg4VBrAbstldipahwtQ");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeName("SJCkfWDPjMRgkkXabRMPBFxgNT3BScasoolD7kR9EgGVvoKPoc");
        communicationtype.setCommTypeName("QTZRlG1mKlVusdb7N42bKV7qTqCByAwGrvQXLtosz0OcEUzEJc");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeDescription("AsOEqIVPbad3rXGCTk3wXEaVBV0YfNOvzDrSj0nhXytsB5QBq7");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommData("ddnQYwjlQT");
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        employeeinfo.setJobTypeCode((java.lang.String) JobTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        employeeinfo.setReportingOfficer("fPqlVCwqSjJNHSpm4HT5obYYAXki8Vi0RWdujeLmk4HXE8gqUG");
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
        CostToCompany costtocompany = new CostToCompany();
        costtocompany.setMedicalAllowance(5600.0d);
        costtocompany.setPerk(7040.0d);
        costtocompany.setSpecialAllowance(4700.0d);
        costtocompany.setConvenceAllowance(-3600.0d);
        costtocompany.setTakeHome(-9800.0d);
        costtocompany.setTotalCTC(-300.0d);
        costtocompany.setYearValue(2147483647);
        costtocompany.setEducationalAllowance(-5700.0d);
        costtocompany.setBasic(4100.0d);
        costtocompany.setEmpId((java.lang.String) EmployeeInfoTest._getPrimarykey());
        costtocompany.setHra(6500.0d);
        costtocompany.setEntityValidator(entityValidator);
        return costtocompany;
    }

    @Test
    public void test1Save() {
        try {
            CostToCompany costtocompany = createCostToCompany(true);
            costtocompany.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            costtocompany.isValid();
            costtocompanyRepository.save(costtocompany);
            map.put("CostToCompanyPrimaryKey", costtocompany._getPrimarykey());
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
            org.junit.Assert.assertNotNull(map.get("CostToCompanyPrimaryKey"));
            CostToCompany costtocompany = costtocompanyRepository.findById((java.lang.String) map.get("CostToCompanyPrimaryKey"));
            costtocompany.setMedicalAllowance(8300.0d);
            costtocompany.setPerk(-900.0d);
            costtocompany.setSpecialAllowance(-9410.0d);
            costtocompany.setConvenceAllowance(6000.0d);
            costtocompany.setTakeHome(800.0d);
            costtocompany.setTotalCTC(-9300.0d);
            costtocompany.setYearValue(2147483647);
            costtocompany.setEducationalAllowance(7200.0d);
            costtocompany.setBasic(-6480.0d);
            costtocompany.setHra(4020.0d);
            costtocompany.setVersionId(1);
            costtocompany.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            costtocompanyRepository.update(costtocompany);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CostToCompanyPrimaryKey"));
            costtocompanyRepository.findById((java.lang.String) map.get("CostToCompanyPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByempId() {
        try {
            java.util.List<CostToCompany> listofempId = costtocompanyRepository.findByEmpId((java.lang.String) map.get("EmployeeInfoPrimaryKey"));
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
            org.junit.Assert.assertNotNull(map.get("CostToCompanyPrimaryKey"));
            costtocompanyRepository.delete((java.lang.String) map.get("CostToCompanyPrimaryKey")); /* Deleting refrenced data */
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

    private void validateCostToCompany(EntityTestCriteria contraints, CostToCompany costtocompany) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            costtocompany.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            costtocompany.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            costtocompany.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            costtocompanyRepository.save(costtocompany);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 1, "perk", 1.4405766261805849E19d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "totalCTC", 1.1497821292695941E19d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "takeHome", 1.2545746283787383E19d));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "yearValue", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "basic", 1.5360815849465295E19d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "hra", 1.5359789955568181E19d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "convenceAllowance", 1.4979661098448101E19d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "medicalAllowance", 1.8090432466450592E19d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "educationalAllowance", 1.1027090352085832E19d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "specialAllowance", 1.0105762300010287E19d));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                CostToCompany costtocompany = createCostToCompany(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = costtocompany.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        costtocompany.setPerk(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateCostToCompany(contraints, costtocompany);
                        failureCount++;
                        break;
                    case 2:
                        costtocompany.setTotalCTC(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateCostToCompany(contraints, costtocompany);
                        failureCount++;
                        break;
                    case 3:
                        costtocompany.setTakeHome(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateCostToCompany(contraints, costtocompany);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(costtocompany, null);
                        validateCostToCompany(contraints, costtocompany);
                        failureCount++;
                        break;
                    case 5:
                        costtocompany.setBasic(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateCostToCompany(contraints, costtocompany);
                        failureCount++;
                        break;
                    case 6:
                        costtocompany.setHra(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateCostToCompany(contraints, costtocompany);
                        failureCount++;
                        break;
                    case 7:
                        costtocompany.setConvenceAllowance(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateCostToCompany(contraints, costtocompany);
                        failureCount++;
                        break;
                    case 8:
                        costtocompany.setMedicalAllowance(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateCostToCompany(contraints, costtocompany);
                        failureCount++;
                        break;
                    case 9:
                        costtocompany.setEducationalAllowance(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateCostToCompany(contraints, costtocompany);
                        failureCount++;
                        break;
                    case 10:
                        costtocompany.setSpecialAllowance(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateCostToCompany(contraints, costtocompany);
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

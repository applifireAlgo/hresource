package com.hresource.app.server.service.humanresourceboundedcontext.attendance;
import com.hresource.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.hresource.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.hresource.app.server.repository.humanresourceboundedcontext.attendance.AttendanceSummaryRepository;
import com.hresource.app.shared.humanresourceboundedcontext.attendance.AttendanceSummary;
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
public class AttendanceSummaryTestCase extends EntityTestCriteria {

    @Autowired
    private AttendanceSummaryRepository<AttendanceSummary> attendancesummaryRepository;

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

    private AttendanceSummary createAttendanceSummary(Boolean isSave) throws Exception {
        EmployeeInfo employeeinfo = new EmployeeInfo();
        JobType jobtype = new JobType();
        jobtype.setJobTypeDesc("lrIIrDeUiL0VejStaqVVdixhcxli1VgzlvbVnmshhAXrsrqdeS");
        JobType JobTypeTest = new JobType();
        if (isSave) {
            JobTypeTest = jobtypeRepository.save(jobtype);
            map.put("JobTypePrimaryKey", jobtype._getPrimarykey());
        }
        DesignationType designationtype = new DesignationType();
        designationtype.setDesignationTypeDesc("rmt76xm3ZvZyyKxHeoqJG2FsOXsj7qdNz2eoj0gVhCZ6ZPujrq");
        DesignationType DesignationTypeTest = new DesignationType();
        if (isSave) {
            DesignationTypeTest = designationtypeRepository.save(designationtype);
            map.put("DesignationTypePrimaryKey", designationtype._getPrimarykey());
        }
        DepartmentType departmenttype = new DepartmentType();
        departmenttype.setDepartmentTypeDesc("KabU280RvteezfMl6hhrOeAmt3PCuxKprFvtIfy2kdZybqD9Mo");
        DepartmentType DepartmentTypeTest = new DepartmentType();
        if (isSave) {
            DepartmentTypeTest = departmenttypeRepository.save(departmenttype);
            map.put("DepartmentTypePrimaryKey", departmenttype._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setAge(78);
        Language language = new Language();
        language.setAlpha4parentid(8);
        language.setLanguageIcon("iTuAiNimoJk1IwxmFbUEjKJDa881jdCt4QjgkyW29qC6ldt7Xl");
        language.setLanguageDescription("jqs7GfoilGYXkrwLHd9WrjkX9H8jIr1DzvbyAKJukWNaWk9F2P");
        language.setLanguage("A60l9iqYbaBMyRdrJDRdPMs6JnY4rAJf1Ph2m1f9hxqNqJGpNw");
        language.setAlpha4("HYLq");
        language.setAlpha2("Ci");
        language.setLanguageType("8L5DaEimpAsKOu1NBxACp5BAFQ2RRCdA");
        language.setAlpha3("95l");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("o2lGQ7UL0RWkBVqnnjH9AYBSFvFmNFYgQdpJPbuOQBiMgp3XfP");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("XaIfKrR8Cb4NtWBWosYiFVqJiLOtcmiLXFXIvX7IjNxHPFOEc2");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setTimeZoneLabel("B6sfqx1blik5j3ohBqxZ7d9zw4w8lotuVfuD6rM5KeHsFdbux7");
        timezone.setUtcdifference(2);
        timezone.setCities("nsuz4ga0m8Tlvzy5tnRhY56pvdcNN2oxr9LKdv8AReLvHNN6L6");
        timezone.setGmtLabel("I8RnTTKXuNXP9j4LEJKTJkjEwpWhthX5eRYSABhVidTPDzIbmp");
        timezone.setCountry("IMXnaPcyZXaKVaMSuIr9qrW8q3kRfkm9XL8b6cBuLcmJJw2dAd");
        corecontacts.setAge(99);
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeLastName("k7U3Rtv5H1DIjzxJhEUXntVVVgl1fLbZJM8ugOKJUATc34HrUc");
        corecontacts.setLastName("QdjOcO7dpXi9FeKAj0b27x09lMgenohyfJIbVX3GMlMcroC2m3");
        corecontacts.setNativeFirstName("oZjC2ncWiFUBvrFZRcWZbwKzoYQETU13F7Ir4MFCzqu5eK5645");
        corecontacts.setPhoneNumber("uAAioCub2hbX3zLWY30t");
        corecontacts.setNativeTitle("nEKZAe09ekP91VN0ovKqGU9m2JVTn9ljvpuestOVuz9oN3IjLM");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setDateofbirth(new java.sql.Timestamp(1465218788770l));
        corecontacts.setEmailId("5VQQ3pOTrvFVjhG0RsNN7atdsJZ4ezvaQpvx7wqZCVKxdbDKrF");
        corecontacts.setNativeMiddleName("558FO500FFjzZUwENpbyeuf50YCg7Z2OZakLb5mqbaIS4PkEEp");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1465218788770l));
        corecontacts.setMiddleName("oHtdF7TPJfEAmbNwc5KhJ5bETOfQwXpl88kMKGmLvZry6xy6M8");
        corecontacts.setFirstName("ESGqKDbeCNTU0ksaOSNnslc93yKwSh3iXYnEuyDxJwc7UJacMJ");
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddressLabel("qvhiCr7logI");
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("fIPQzNK77ZXDwY0fkf5WSXXVNj8K0unVaVOqSxdTcfJcEyNKTv");
        addresstype.setAddressTypeIcon("gJVWe799rtDrtStmR15jQIaXzT3Npmp51AwPsVsByEll9zMuDv");
        addresstype.setAddressType("6MOPu8xheiJrBpAcN5uG9e5PJFCk2pD1bgbntPXHFy8tqhrQE7");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        State state = new State();
        state.setStateCapitalLongitude(3);
        state.setStateCodeChar2("kjZYv4WH42NlVea6SIpYcKdtV6SpsfS4");
        state.setStateCapital("q8bJoXXxTe8EaBl8gYIR7ArbezWxaiYS9OixvRuBC9YUpVMJ3o");
        state.setStateCodeChar3("pZumbWKKSDWngFxMYR7mk3kssIBVmXCe");
        state.setStateName("e5C03zXgI8CGDAm856xiX8nz2ZB43QJiBi1J4OhdXRpq8zRQys");
        Country country = new Country();
        country.setCapitalLongitude(10);
        country.setCapitalLatitude(8);
        country.setCountryName("RZ0UC6lPIP2ps0Utij9DgBY4wQwj2emL13Dys26s6EV6sJnxjM");
        country.setCurrencyCode("NJH");
        country.setCapital("Jd7ewFnBdj8wgh3uGUsTeHv0bjYmjeWS");
        country.setIsoNumeric(247);
        country.setCountryCode1("hBt");
        country.setCurrencySymbol("EPkfU2u3TIYwRhLdwbUhK8dcrU0X7An0");
        country.setCountryCode2("SZZ");
        country.setCountryFlag("6qTPf7FsIqzcNVCfuOirSd9VGoT72cAxQdHiGJmzjnjwS2t3Ld");
        country.setCurrencyName("jsMgkJgCnpNatQi1pE55fzxUMkJu0Ozesl2u0iycUEkuB50BSo");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCapitalLongitude(6);
        state.setStateCodeChar2("w9PqfNDZ5YZtcygrdSe9iPlZUwvb5n8q");
        state.setStateCapital("7VxdtMTFQfjKwxb9VHg4sZjRDuPFUFfckrDD3KzveKfT3wOSSz");
        state.setStateCodeChar3("ljAn1sGfEL85oAC23ou3jDJZrmXFfJmB");
        state.setStateName("4asaGUOxlFmPUoySsPffGXDdOZ9mDY3h0a02CS2b84qTnEmAYu");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateDescription("NOKqaNgKkyOE9KTYoU2Ioezb5CeEskp2h91YDxcnF4xtE5iiaW");
        state.setStateFlag("NRXgLGiK2RBMeGgxN8Z1FAMa9bkjMjJj5cLbkHkztZmfxdWe4Q");
        state.setStateCode(2);
        state.setStateCapitalLatitude(2);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("C2WyDXAZwTUpp6WBeGorIB38n2ncgU4p");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCode(3);
        city.setCityLongitude(6);
        city.setCityLatitude(1);
        city.setCityFlag("SwQHH9E4TmRXKfQzbXeOGwJ7XmPmG9o1WB3V0Jec7w0Ywq1GBj");
        city.setCityName("qOXvJk7JmvsBwL41hro2nyH1vNhHtf2TIsbRblukrQhf2B36FF");
        city.setCityDescription("zd3HLARZUBKOlZkW1IgmLGu9ZBpCmMQfvbWFIAAFaA1MZkIdBk");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setAddressLabel("xosW14sKOWD");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLatitude("I2Ujs4WYCYJbj7ETRZYCSLkbCt4VzxJdLo8JYYar3uy9PtZTmK");
        address.setAddress1("j7uX5yZgQSlogquj6D4KE5hSqHWNieG293WkscgRi45HJxT3Bx");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLongitude("OwlzYD5cVhFCLt5pkBipvavGWitadZQrFvWrJkGieA9zpnjyL3");
        address.setAddress3("PKHTJz9C2TF9DSC4JfK91NnhTjEsvTItAIk16HJw1RETUk6sZK");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("wSni0K");
        address.setAddress2("A4uWTZWSF1XZnAO46C9aBTkb5AHXgf5xVj2LHusw3VP4E5GmyE");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("bnRfsJmafbFRYiExYQSVeYIHhSkQA8Gioju3PwifOLklSRaZyT");
        communicationgroup.setCommGroupDescription("qsH4mIb2rRGalfdzV1R0C1e8jhtwvxKrRZSeGkgsj5YlE3redD");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeName("5vCyWZIJJFRbVo9JtK6gTgPsNMT2ABZXnNeTnUfrdNTq6NG38q");
        communicationtype.setCommTypeName("vXSzsOVLR0jLOFFEOji3P41dgxqKkl40Bd8HeJetLvzhEv4EkU");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeDescription("rN8gc92CxwfDXjlkIOKtbtsXzdkk4RnpdLMAsaWlS4kYhLE3YX");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommData("gmcDPjcMAs");
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        employeeinfo.setJobTypeCode((java.lang.String) JobTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        employeeinfo.setReportingOfficer("j0qV89OVcOhgNSoGPva2XiGgHajCICg0V14iY5k8cZlzavU3oA");
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
        AttendanceSummary attendancesummary = new AttendanceSummary();
        attendancesummary.setEmpId((java.lang.String) EmployeeInfoTest._getPrimarykey());
        attendancesummary.setMonthValue(2147483647);
        attendancesummary.setPresent(2147483647);
        attendancesummary.setSickLeave(2147483647);
        attendancesummary.setAbsent(2147483647);
        attendancesummary.setMaternityLeave(2147483647);
        attendancesummary.setCasualLeave(2147483647);
        attendancesummary.setYearValue(2147483647);
        attendancesummary.setPrivilegeLeave(2147483647);
        attendancesummary.setEntityValidator(entityValidator);
        return attendancesummary;
    }

    @Test
    public void test1Save() {
        try {
            AttendanceSummary attendancesummary = createAttendanceSummary(true);
            attendancesummary.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            attendancesummary.isValid();
            attendancesummaryRepository.save(attendancesummary);
            map.put("AttendanceSummaryPrimaryKey", attendancesummary._getPrimarykey());
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
            org.junit.Assert.assertNotNull(map.get("AttendanceSummaryPrimaryKey"));
            AttendanceSummary attendancesummary = attendancesummaryRepository.findById((java.lang.String) map.get("AttendanceSummaryPrimaryKey"));
            attendancesummary.setMonthValue(2147483647);
            attendancesummary.setPresent(2147483647);
            attendancesummary.setSickLeave(2147483647);
            attendancesummary.setAbsent(2147483647);
            attendancesummary.setVersionId(1);
            attendancesummary.setMaternityLeave(2147483647);
            attendancesummary.setCasualLeave(2147483647);
            attendancesummary.setYearValue(2147483647);
            attendancesummary.setPrivilegeLeave(2147483647);
            attendancesummary.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            attendancesummaryRepository.update(attendancesummary);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByempId() {
        try {
            java.util.List<AttendanceSummary> listofempId = attendancesummaryRepository.findByEmpId((java.lang.String) map.get("EmployeeInfoPrimaryKey"));
            if (listofempId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AttendanceSummaryPrimaryKey"));
            attendancesummaryRepository.findById((java.lang.String) map.get("AttendanceSummaryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AttendanceSummaryPrimaryKey"));
            attendancesummaryRepository.delete((java.lang.String) map.get("AttendanceSummaryPrimaryKey")); /* Deleting refrenced data */
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

    private void validateAttendanceSummary(EntityTestCriteria contraints, AttendanceSummary attendancesummary) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            attendancesummary.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            attendancesummary.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            attendancesummary.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            attendancesummaryRepository.save(attendancesummary);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "monthValue", null));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 2, "yearValue", null));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "present", null));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "absent", null));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "privilegeLeave", null));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "casualLeave", null));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 7, "sickLeave", null));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                AttendanceSummary attendancesummary = createAttendanceSummary(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = attendancesummary.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(attendancesummary, null);
                        validateAttendanceSummary(contraints, attendancesummary);
                        failureCount++;
                        break;
                    case 2:
                        field.setAccessible(true);
                        field.set(attendancesummary, null);
                        validateAttendanceSummary(contraints, attendancesummary);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(attendancesummary, null);
                        validateAttendanceSummary(contraints, attendancesummary);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(attendancesummary, null);
                        validateAttendanceSummary(contraints, attendancesummary);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(attendancesummary, null);
                        validateAttendanceSummary(contraints, attendancesummary);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(attendancesummary, null);
                        validateAttendanceSummary(contraints, attendancesummary);
                        failureCount++;
                        break;
                    case 7:
                        field.setAccessible(true);
                        field.set(attendancesummary, null);
                        validateAttendanceSummary(contraints, attendancesummary);
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

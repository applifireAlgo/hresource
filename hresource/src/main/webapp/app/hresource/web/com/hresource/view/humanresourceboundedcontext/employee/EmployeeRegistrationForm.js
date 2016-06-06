Ext.define('Hresource.hresource.web.com.hresource.view.humanresourceboundedcontext.employee.EmployeeRegistrationForm', {
     "xtype": "employeeRegistrationFormView",
     "name": "employeeReg",
     "items": [{
          "xtype": "tabpanel",
          "name": "Tablayout",
          "items": [{
               "xtype": "panel",
               "name": "tab1",
               "items": [{
                    "xtype": "panel",
                    "name": "empInfo",
                    "items": [{
                         "xtype": "combo",
                         "name": "title",
                         "margin": 5,
                         "bindable": "title",
                         "typeAhead": true,
                         "queryMode": "local",
                         "minChars": 1,
                         "fieldLabel": "Title<span style=\"color:red;\">*<\/span>",
                         "displayField": "titles",
                         "valueField": "titleId",
                         "text": "title",
                         "fieldName": "title",
                         "displayName": "title",
                         "fieldType": "java.lang.String",
                         "widget": "combo",
                         "isField": true,
                         "rowspan": 0,
                         "colspan": 0,
                         "allowModify": true,
                         "itemId": "combo_ext_8978",
                         "store": {
                              "model": "Hresource.hresource.shared.com.hresource.model.organization.contactmanagement.TitleModel",
                              "autoLoad": true,
                              "autoSync": true,
                              "sorters": [{
                                   "property": "titles",
                                   "direction": "ASC"
                              }],
                              "proxy": {
                                   "type": "ajax",
                                   "url": "secure/Title/findAll",
                                   "serviceId": "504FBB06-A779-4BA2-AEBC-3589FD87ADBC",
                                   "serviceType": 1,
                                   "serviceOperationId": "E4EBF7B3-F970-440B-AF1A-D836E5F91FAB",
                                   "operationName": "findAll",
                                   "operationDisplayName": "findAll",
                                   "actionMethods": {
                                        "read": "GET"
                                   },
                                   "headers": {
                                        "Content-Type": "application/json"
                                   },
                                   "extraParams": {},
                                   "reader": {
                                        "type": "json",
                                        "rootProperty": "response.data"
                                   }
                              }
                         }
                    }, {
                         "xtype": "textfield",
                         "fieldLabel": "First Name<span style=\"color:red;\">*<\/span>",
                         "margin": 5,
                         "bindable": "firstName",
                         "name": "firstName",
                         "text": "firstName",
                         "fieldName": "firstName",
                         "displayName": "firstName",
                         "fieldType": "java.lang.String",
                         "widget": "textfield",
                         "isField": true,
                         "rowspan": 0,
                         "colspan": 0,
                         "allowModify": true,
                         "itemId": "textfield_ext_9067"
                    }, {
                         "xtype": "textfield",
                         "fieldLabel": "Last Name<span style=\"color:red;\">*<\/span>",
                         "margin": 5,
                         "bindable": "lastname",
                         "name": "lastname",
                         "text": "lastname",
                         "fieldName": "lastname",
                         "displayName": "lastname",
                         "fieldType": "java.lang.String",
                         "widget": "textfield",
                         "isField": true,
                         "rowspan": 0,
                         "colspan": 0,
                         "allowModify": true,
                         "itemId": "textfield_ext_9158"
                    }, {
                         "xtype": "combo",
                         "name": "gender",
                         "margin": 5,
                         "bindable": "gender",
                         "typeAhead": true,
                         "queryMode": "local",
                         "minChars": 1,
                         "fieldLabel": "Gender<span style=\"color:red;\">*<\/span>",
                         "displayField": "gender",
                         "valueField": "genderId",
                         "text": "gender",
                         "fieldName": "gender",
                         "displayName": "gender",
                         "fieldType": "java.lang.String",
                         "widget": "combo",
                         "isField": true,
                         "rowspan": 0,
                         "colspan": 0,
                         "allowModify": true,
                         "itemId": "combo_ext_9251",
                         "store": {
                              "model": "Hresource.hresource.shared.com.hresource.model.organization.contactmanagement.GenderModel",
                              "autoLoad": true,
                              "autoSync": true,
                              "sorters": [{
                                   "property": "gender",
                                   "direction": "ASC"
                              }],
                              "proxy": {
                                   "type": "ajax",
                                   "url": "secure/Gender/findAll",
                                   "serviceId": "0283FB25-976F-4CF5-9F57-63F915FA63D6",
                                   "serviceType": 1,
                                   "serviceOperationId": "EBDD8B42-A842-463F-99C5-2DFB9B694AAA",
                                   "operationName": "findAll",
                                   "operationDisplayName": "findAll",
                                   "actionMethods": {
                                        "read": "GET"
                                   },
                                   "headers": {
                                        "Content-Type": "application/json"
                                   },
                                   "extraParams": {},
                                   "reader": {
                                        "type": "json",
                                        "rootProperty": "response.data"
                                   }
                              }
                         }
                    }, {
                         "xtype": "combo",
                         "name": "timezone",
                         "margin": 5,
                         "bindable": "timezone",
                         "typeAhead": true,
                         "queryMode": "local",
                         "minChars": 1,
                         "fieldLabel": "Timezone<span style=\"color:red;\">*<\/span>",
                         "displayField": "timeZoneLabel",
                         "valueField": "timeZoneId",
                         "text": "timezone",
                         "fieldName": "timezone",
                         "displayName": "timezone",
                         "fieldType": "java.lang.String",
                         "widget": "combo",
                         "isField": true,
                         "rowspan": 0,
                         "colspan": 0,
                         "allowModify": true,
                         "itemId": "combo_ext_9346",
                         "store": {
                              "model": "Hresource.hresource.shared.com.hresource.model.organization.locationmanagement.TimezoneModel",
                              "autoLoad": true,
                              "autoSync": true,
                              "sorters": [{
                                   "property": "timeZoneLabel",
                                   "direction": "ASC"
                              }],
                              "proxy": {
                                   "type": "ajax",
                                   "url": "secure/Timezone/findAll",
                                   "serviceId": "3D794414-7DE9-45DB-914A-AA783BB0B777",
                                   "serviceType": 1,
                                   "serviceOperationId": "629F3D77-7EE2-4233-845D-F9A990E209FA",
                                   "operationName": "findAll",
                                   "operationDisplayName": "findAll",
                                   "actionMethods": {
                                        "read": "GET"
                                   },
                                   "headers": {
                                        "Content-Type": "application/json"
                                   },
                                   "extraParams": {},
                                   "reader": {
                                        "type": "json",
                                        "rootProperty": "response.data"
                                   }
                              }
                         }
                    }, {
                         "xtype": "textfield",
                         "fieldLabel": "Phone No.<span style=\"color:red;\">*<\/span>",
                         "margin": 5,
                         "bindable": "phno",
                         "name": "phno",
                         "text": "phno",
                         "fieldName": "phno",
                         "displayName": "phno",
                         "fieldType": "java.lang.String",
                         "widget": "textfield",
                         "isField": true,
                         "rowspan": 0,
                         "colspan": 0,
                         "allowModify": true,
                         "itemId": "textfield_ext_9443"
                    }, {
                         "xtype": "textfield",
                         "fieldLabel": "Email Id<span style=\"color:red;\">*<\/span>",
                         "margin": 5,
                         "bindable": "emailid",
                         "name": "emailid",
                         "text": "emailid",
                         "fieldName": "emailid",
                         "displayName": "emailid",
                         "fieldType": "java.lang.String",
                         "widget": "textfield",
                         "isField": true,
                         "rowspan": 0,
                         "colspan": 0,
                         "allowModify": true,
                         "itemId": "textfield_ext_9542"
                    }],
                    "layout": {
                         "type": "table",
                         "columns": 3,
                         "tableAttrs": {
                              "style": {
                                   "width": "100%"
                              }
                         }
                    },
                    "border": true,
                    "margin": 20,
                    "dockedItems": [],
                    "allowModify": true,
                    "itemId": "panel_ext_8884"
               }],
               "layout": "fit",
               "bodyBorder": true,
               "bodyPadding": 0,
               "border": true,
               "title": "<b>Employee Information<\/b>",
               "tabId": 1,
               "isTabLayoutItem": true,
               "dockedItems": [],
               "allowModify": true,
               "itemId": "panel_ext_26707"
          }, {
               "xtype": "panel",
               "name": "tab2",
               "items": [{
                    "xtype": "panel",
                    "name": "addressDet",
                    "items": [{
                         "xtype": "combo",
                         "name": "addressType",
                         "margin": 5,
                         "bindable": "addressType",
                         "typeAhead": true,
                         "queryMode": "local",
                         "minChars": 1,
                         "fieldLabel": "Address Type<span style=\"color:red;\">*<\/span>",
                         "displayField": "addressType",
                         "valueField": "addressTypeId",
                         "text": "addressType",
                         "fieldName": "addressType",
                         "displayName": "addressType",
                         "fieldType": "java.lang.String",
                         "widget": "combo",
                         "isField": true,
                         "rowspan": 0,
                         "colspan": 0,
                         "allowModify": true,
                         "itemId": "combo_ext_10038",
                         "store": {
                              "model": "Hresource.hresource.shared.com.hresource.model.organization.locationmanagement.AddressTypeModel",
                              "autoLoad": true,
                              "autoSync": true,
                              "sorters": [{
                                   "property": "addressType",
                                   "direction": "ASC"
                              }],
                              "proxy": {
                                   "type": "ajax",
                                   "url": "secure/AddressType/findAll",
                                   "serviceId": "3963FA4B-BD79-447E-90EE-17EF14BB4E6C",
                                   "serviceType": 1,
                                   "serviceOperationId": "D860448A-30DA-4C71-BFD3-7464F7B43FB9",
                                   "operationName": "findAll",
                                   "operationDisplayName": "findAll",
                                   "actionMethods": {
                                        "read": "GET"
                                   },
                                   "headers": {
                                        "Content-Type": "application/json"
                                   },
                                   "extraParams": {},
                                   "reader": {
                                        "type": "json",
                                        "rootProperty": "response.data"
                                   }
                              }
                         }
                    }, {
                         "xtype": "textfield",
                         "fieldLabel": "Address Line1",
                         "margin": 5,
                         "bindable": "address1",
                         "name": "address1",
                         "text": "address1",
                         "fieldName": "address1",
                         "displayName": "address1",
                         "fieldType": "java.lang.String",
                         "widget": "textfield",
                         "isField": true,
                         "rowspan": 0,
                         "colspan": 0,
                         "allowModify": true,
                         "itemId": "textfield_ext_10130"
                    }, {
                         "xtype": "textfield",
                         "fieldLabel": "Address Line 2",
                         "margin": 5,
                         "bindable": "address2",
                         "name": "address2",
                         "text": "address2",
                         "fieldName": "address2",
                         "displayName": "address2",
                         "fieldType": "java.lang.String",
                         "widget": "textfield",
                         "isField": true,
                         "rowspan": 0,
                         "colspan": 0,
                         "allowModify": true,
                         "itemId": "textfield_ext_10224"
                    }, {
                         "xtype": "combo",
                         "name": "country",
                         "margin": 5,
                         "bindable": "country",
                         "typeAhead": true,
                         "queryMode": "local",
                         "minChars": 1,
                         "fieldLabel": "Country<span style=\"color:red;\">*<\/span>",
                         "displayField": "countryName",
                         "valueField": "countryId",
                         "text": "country",
                         "fieldName": "country",
                         "displayName": "country",
                         "fieldType": "java.lang.String",
                         "widget": "combo",
                         "isField": true,
                         "rowspan": 0,
                         "colspan": 0,
                         "allowModify": true,
                         "itemId": "combo_ext_10320",
                         "store": {
                              "model": "Hresource.hresource.shared.com.hresource.model.organization.locationmanagement.CountryModel",
                              "autoLoad": true,
                              "autoSync": true,
                              "sorters": [{
                                   "property": "countryName",
                                   "direction": "ASC"
                              }],
                              "proxy": {
                                   "type": "ajax",
                                   "url": "secure/Country/findAll",
                                   "serviceId": "7FF76F86-185F-48EF-AC7E-AA0BFEE7CBFD",
                                   "serviceType": 1,
                                   "serviceOperationId": "3ABBF912-56E9-460A-99B1-8BD0604B0E11",
                                   "operationName": "findAll",
                                   "operationDisplayName": "findAll",
                                   "actionMethods": {
                                        "read": "GET"
                                   },
                                   "headers": {
                                        "Content-Type": "application/json"
                                   },
                                   "extraParams": {},
                                   "reader": {
                                        "type": "json",
                                        "rootProperty": "response.data"
                                   }
                              }
                         },
                         "listeners": {
                              "change": "onCountryChange"
                         }
                    }, {
                         "xtype": "combo",
                         "name": "state",
                         "margin": 5,
                         "bindable": "state",
                         "typeAhead": true,
                         "queryMode": "local",
                         "minChars": 1,
                         "fieldLabel": "State<span style=\"color:red;\">*<\/span>",
                         "displayField": "stateName",
                         "valueField": "stateId",
                         "text": "state",
                         "fieldName": "state",
                         "displayName": "state",
                         "fieldType": "java.lang.String",
                         "widget": "combo",
                         "isField": true,
                         "rowspan": 0,
                         "colspan": 0,
                         "allowModify": true,
                         "isRelatedWith": "pfghijcfi",
                         "itemId": "combo_ext_10418",
                         "listeners": {
                              "change": "onStateChange"
                         }
                    }, {
                         "xtype": "combo",
                         "name": "city",
                         "margin": 5,
                         "bindable": "city",
                         "typeAhead": true,
                         "queryMode": "local",
                         "minChars": 1,
                         "fieldLabel": "City<span style=\"color:red;\">*<\/span>",
                         "displayField": "cityName",
                         "valueField": "cityId",
                         "text": "city",
                         "fieldName": "city",
                         "displayName": "city",
                         "fieldType": "java.lang.String",
                         "widget": "combo",
                         "isField": true,
                         "rowspan": 0,
                         "colspan": 0,
                         "allowModify": true,
                         "isRelatedWith": "jfohddcci",
                         "itemId": "combo_ext_10519"
                    }, {
                         "xtype": "textfield",
                         "fieldLabel": "Zipcode<span style=\"color:red;\">*<\/span>",
                         "margin": 5,
                         "bindable": "zipcode",
                         "name": "zipcode",
                         "text": "zipcode",
                         "fieldName": "zipcode",
                         "displayName": "zipcode",
                         "fieldType": "java.lang.String",
                         "widget": "textfield",
                         "isField": true,
                         "rowspan": 0,
                         "colspan": 0,
                         "allowModify": true,
                         "itemId": "textfield_ext_10621"
                    }],
                    "layout": {
                         "type": "table",
                         "columns": 3,
                         "tableAttrs": {
                              "style": {
                                   "width": "100%"
                              }
                         }
                    },
                    "border": true,
                    "margin": 5,
                    "dockedItems": [],
                    "allowModify": true,
                    "itemId": "panel_ext_9849"
               }],
               "layout": "fit",
               "bodyBorder": true,
               "bodyPadding": 0,
               "border": true,
               "title": "<b>Address<\/b>",
               "tabId": 2,
               "isTabLayoutItem": true,
               "dockedItems": [],
               "allowModify": true,
               "margin": 20,
               "itemId": "panel_ext_27095"
          }, {
               "xtype": "panel",
               "name": "tab3",
               "items": [{
                    "xtype": "panel",
                    "name": "comdata",
                    "items": [{
                         "xtype": "combo",
                         "name": "comGrp",
                         "margin": 5,
                         "bindable": "comGrp",
                         "typeAhead": true,
                         "queryMode": "local",
                         "minChars": 1,
                         "fieldLabel": "Communication Group<span style=\"color:red;\">*<\/span>",
                         "displayField": "commGroupName",
                         "valueField": "commGroupId",
                         "text": "comGrp",
                         "fieldName": "comGrp",
                         "displayName": "comGrp",
                         "fieldType": "java.lang.String",
                         "widget": "combo",
                         "isField": true,
                         "allowModify": true,
                         "rowspan": 0,
                         "colspan": 0,
                         "itemId": "combo_ext_11140",
                         "store": {
                              "model": "Hresource.hresource.shared.com.hresource.model.organization.contactmanagement.CommunicationGroupModel",
                              "autoLoad": true,
                              "autoSync": true,
                              "sorters": [{
                                   "property": "commGroupName",
                                   "direction": "ASC"
                              }],
                              "proxy": {
                                   "type": "ajax",
                                   "url": "secure/CommunicationGroup/findAll",
                                   "serviceId": "40C8CD97-7BDC-4995-AD27-D6AF457097A7",
                                   "serviceType": 1,
                                   "serviceOperationId": "D4C7E6AA-8D23-403C-BDA9-A4D0F1BE34D4",
                                   "operationName": "findAll",
                                   "operationDisplayName": "findAll",
                                   "actionMethods": {
                                        "read": "GET"
                                   },
                                   "headers": {
                                        "Content-Type": "application/json"
                                   },
                                   "extraParams": {},
                                   "reader": {
                                        "type": "json",
                                        "rootProperty": "response.data"
                                   }
                              }
                         },
                         "listeners": {
                              "change": "onComGrpChange"
                         }
                    }, {
                         "xtype": "combo",
                         "name": "comType",
                         "margin": 5,
                         "bindable": "comType",
                         "typeAhead": true,
                         "queryMode": "local",
                         "minChars": 1,
                         "fieldLabel": "Communication Type<span style=\"color:red;\">*<\/span>",
                         "displayField": "commTypeName",
                         "valueField": "commType",
                         "text": "comType",
                         "fieldName": "comType",
                         "displayName": "comType",
                         "fieldType": "java.lang.String",
                         "widget": "combo",
                         "isField": true,
                         "rowspan": 0,
                         "colspan": 0,
                         "allowModify": true,
                         "isRelatedWith": "ifejmjiai",
                         "itemId": "combo_ext_11040"
                    }, {
                         "xtype": "textfield",
                         "fieldLabel": "Communication Data<span style=\"color:red;\">*<\/span>",
                         "margin": 5,
                         "bindable": "comData",
                         "name": "comData",
                         "text": "comData",
                         "fieldName": "comData",
                         "displayName": "comData",
                         "fieldType": "java.lang.String",
                         "widget": "textfield",
                         "isField": true,
                         "rowspan": 0,
                         "colspan": 0,
                         "allowModify": true,
                         "itemId": "textfield_ext_11242"
                    }],
                    "layout": {
                         "type": "table",
                         "columns": 2,
                         "tableAttrs": {
                              "style": {
                                   "width": "100%"
                              }
                         }
                    },
                    "border": true,
                    "margin": 5,
                    "dockedItems": [],
                    "allowModify": true,
                    "itemId": "panel_ext_10836"
               }],
               "layout": "fit",
               "bodyBorder": true,
               "bodyPadding": 0,
               "border": true,
               "title": "<b>Communication Data<\/b>",
               "tabId": 3,
               "isTabLayoutItem": true,
               "dockedItems": [],
               "allowModify": true,
               "margin": 20,
               "itemId": "panel_ext_27147"
          }, {
               "xtype": "panel",
               "name": "tab4",
               "items": [{
                    "xtype": "panel",
                    "name": "empdet",
                    "items": [{
                         "xtype": "combo",
                         "name": "depttype",
                         "margin": 5,
                         "bindable": "depttype",
                         "typeAhead": true,
                         "queryMode": "local",
                         "minChars": 1,
                         "fieldLabel": "Departtment Type<span style=\"color:red;\">*<\/span>",
                         "displayField": "departmentTypeDesc",
                         "valueField": "departmentTypeCode",
                         "text": "depttype",
                         "fieldName": "depttype",
                         "displayName": "depttype",
                         "fieldType": "java.lang.String",
                         "widget": "combo",
                         "isField": true,
                         "allowModify": true,
                         "rowspan": 0,
                         "colspan": 0,
                         "itemId": "combo_ext_11854",
                         "store": {
                              "model": "Hresource.hresource.shared.com.hresource.model.humanresourceboundedcontext.employee.DepartmentTypeModel",
                              "autoLoad": true,
                              "autoSync": true,
                              "sorters": [{
                                   "property": "departmentTypeDesc",
                                   "direction": "ASC"
                              }],
                              "proxy": {
                                   "type": "ajax",
                                   "url": "secure/DepartmentType/findAll",
                                   "serviceId": "8E88AEBA-0449-41C7-BD71-00F776AA1B32",
                                   "serviceType": 1,
                                   "serviceOperationId": "E86A3284-5686-47C4-9D19-075C6BB48BBB",
                                   "operationName": "findAll",
                                   "operationDisplayName": "findAll",
                                   "actionMethods": {
                                        "read": "GET"
                                   },
                                   "headers": {
                                        "Content-Type": "application/json"
                                   },
                                   "extraParams": {},
                                   "reader": {
                                        "type": "json",
                                        "rootProperty": "response.data"
                                   }
                              }
                         }
                    }, {
                         "xtype": "combo",
                         "name": "desgType",
                         "margin": 5,
                         "bindable": "desgType",
                         "typeAhead": true,
                         "queryMode": "local",
                         "minChars": 1,
                         "fieldLabel": "Designation Type<span style=\"color:red;\">*<\/span>",
                         "displayField": "designationTypeDesc",
                         "valueField": "designationTypeCode",
                         "text": "desgType",
                         "fieldName": "desgType",
                         "displayName": "desgType",
                         "fieldType": "java.lang.String",
                         "widget": "combo",
                         "isField": true,
                         "rowspan": 0,
                         "colspan": 0,
                         "allowModify": true,
                         "itemId": "combo_ext_11760",
                         "store": {
                              "model": "Hresource.hresource.shared.com.hresource.model.humanresourceboundedcontext.employee.DesignationTypeModel",
                              "autoLoad": true,
                              "autoSync": true,
                              "sorters": [{
                                   "property": "designationTypeDesc",
                                   "direction": "ASC"
                              }],
                              "proxy": {
                                   "type": "ajax",
                                   "url": "secure/DesignationType/findAll",
                                   "serviceId": "B8CBA271-2BB3-43F1-8F3F-B090985BDBDC",
                                   "serviceType": 1,
                                   "serviceOperationId": "58B89FF0-B1A3-42AB-B806-57190363D57B",
                                   "operationName": "findAll",
                                   "operationDisplayName": "findAll",
                                   "actionMethods": {
                                        "read": "GET"
                                   },
                                   "headers": {
                                        "Content-Type": "application/json"
                                   },
                                   "extraParams": {},
                                   "reader": {
                                        "type": "json",
                                        "rootProperty": "response.data"
                                   }
                              }
                         }
                    }, {
                         "xtype": "combo",
                         "name": "jobtype",
                         "margin": 5,
                         "bindable": "jobtype",
                         "typeAhead": true,
                         "queryMode": "local",
                         "minChars": 1,
                         "fieldLabel": "Job Type<span style=\"color:red;\">*<\/span>",
                         "displayField": "jobTypeDesc",
                         "valueField": "jobTypeCode",
                         "text": "jobtype",
                         "fieldName": "jobtype",
                         "displayName": "jobtype",
                         "fieldType": "java.lang.String",
                         "widget": "combo",
                         "isField": true,
                         "rowspan": 0,
                         "colspan": 0,
                         "allowModify": true,
                         "itemId": "combo_ext_12047",
                         "store": {
                              "model": "Hresource.hresource.shared.com.hresource.model.humanresourceboundedcontext.employee.JobTypeModel",
                              "autoLoad": true,
                              "autoSync": true,
                              "sorters": [{
                                   "property": "jobTypeDesc",
                                   "direction": "ASC"
                              }],
                              "proxy": {
                                   "type": "ajax",
                                   "url": "secure/JobType/findAll",
                                   "serviceId": "A1AD6A1D-3D61-4ACF-8049-4E7229B34D14",
                                   "serviceType": 1,
                                   "serviceOperationId": "38D25C25-20C1-41EE-BE91-4FA936907B9B",
                                   "operationName": "findAll",
                                   "operationDisplayName": "findAll",
                                   "actionMethods": {
                                        "read": "GET"
                                   },
                                   "headers": {
                                        "Content-Type": "application/json"
                                   },
                                   "extraParams": {},
                                   "reader": {
                                        "type": "json",
                                        "rootProperty": "response.data"
                                   }
                              }
                         }
                    }, {
                         "xtype": "textfield",
                         "fieldLabel": "Reporting Officer<span style=\"color:red;\">*<\/span>",
                         "margin": 5,
                         "bindable": "reportofficer",
                         "name": "reportofficer",
                         "text": "reportofficer",
                         "fieldName": "reportofficer",
                         "displayName": "reportofficer",
                         "fieldType": "java.lang.String",
                         "widget": "textfield",
                         "isField": true,
                         "rowspan": 0,
                         "colspan": 0,
                         "allowModify": true,
                         "itemId": "textfield_ext_12145"
                    }, {
                         "xtype": "textfield",
                         "fieldLabel": "PAN Number<span style=\"color:red;\">*<\/span>",
                         "margin": 5,
                         "bindable": "pan",
                         "name": "pan",
                         "text": "pan",
                         "fieldName": "pan",
                         "displayName": "pan",
                         "fieldType": "java.lang.String",
                         "widget": "textfield",
                         "isField": true,
                         "rowspan": 0,
                         "colspan": 0,
                         "allowModify": true,
                         "itemId": "textfield_ext_12245"
                    }, {
                         "xtype": "textfield",
                         "fieldLabel": "contactId",
                         "margin": 5,
                         "bindable": "contactId",
                         "name": "contactId",
                         "text": "contactId",
                         "fieldName": "contactId",
                         "displayName": "contactId",
                         "fieldType": "java.lang.String",
                         "widget": "textfield",
                         "isField": true,
                         "rowspan": 0,
                         "colspan": 0,
                         "allowModify": true,
                         "hidden": true,
                         "itemId": "textfield_ext_12347"
                    }],
                    "layout": {
                         "type": "table",
                         "columns": 3,
                         "tableAttrs": {
                              "style": {
                                   "width": "100%"
                              }
                         }
                    },
                    "border": true,
                    "margin": 20,
                    "dockedItems": [],
                    "allowModify": true,
                    "itemId": "panel_ext_11452"
               }],
               "layout": "fit",
               "bodyBorder": true,
               "bodyPadding": 0,
               "border": true,
               "title": "<b>Employee Detail<\/b>",
               "tabId": 4,
               "isTabLayoutItem": true,
               "dockedItems": [],
               "allowModify": true,
               "itemId": "panel_ext_28259"
          }],
          "autoScroll": true,
          "activeItem": 0,
          "activeTab": 0,
          "margin": 5,
          "dockedItems": [],
          "allowModify": true,
          "itemId": "tabpanel_ext_26685"
     }],
     "border": true,
     "autoScroll": true,
     "title": "<b>Employee Registration Form<\/b>",
     "margin": 5,
     "dockedItems": [{
          "xtype": "toolbar",
          "dock": "bottom",
          "ui": "footer",
          "isToolBar": true,
          "isDockedItem": true,
          "items": [{
               "xtype": "tbfill",
               "allowModify": true,
               "itemId": "tbfill_ext_29049"
          }, {
               "xtype": "button",
               "name": "Save",
               "text": "<b>Save<\/b>",
               "margin": 5,
               "allowModify": true,
               "itemId": "button_ext_29152",
               "listeners": {
                    "click": "onSaveClick"
               }
          }, {
               "xtype": "button",
               "text": "Reset",
               "isResetButton": true,
               "margin": 5,
               "name": "Reset",
               "itemId": "button_ext_4275",
               "listeners": {
                    "click": "onResetClick"
               }
          }, {
               "xtype": "tbfill",
               "allowModify": true,
               "itemId": "tbfill_ext_29257"
          }],
          "dockedItems": [],
          "allowModify": true,
          "itemId": "toolbar_ext_26671"
     }],
     "allowModify": true,
     "itemId": "form_ext_26632",
     "requires": ["Hresource.hresource.shared.com.hresource.model.organization.contactmanagement.TitleModel", "Hresource.hresource.shared.com.hresource.model.organization.locationmanagement.TimezoneModel", "Hresource.hresource.shared.com.hresource.model.organization.contactmanagement.GenderModel", "Hresource.hresource.shared.com.hresource.model.organization.locationmanagement.AddressTypeModel", "Hresource.hresource.shared.com.hresource.model.organization.locationmanagement.StateModel", "Hresource.hresource.shared.com.hresource.model.organization.locationmanagement.CountryModel", "Hresource.hresource.shared.com.hresource.model.organization.locationmanagement.CityModel", "Hresource.hresource.shared.com.hresource.model.organization.contactmanagement.CommunicationGroupModel", "Hresource.hresource.shared.com.hresource.model.organization.contactmanagement.CommunicationTypeModel", "Hresource.hresource.shared.com.hresource.model.humanresourceboundedcontext.employee.DepartmentTypeModel", "Hresource.hresource.shared.com.hresource.model.humanresourceboundedcontext.employee.DesignationTypeModel", "Hresource.hresource.shared.com.hresource.model.humanresourceboundedcontext.employee.JobTypeModel", "Hresource.hresource.web.com.hresource.controller.humanresourceboundedcontext.employee.EmployeeRegistrationFormController", "Hresource.hresource.shared.com.hresource.viewmodel.humanresourceboundedcontext.employee.EmployeeRegistrationFormViewModel", "Hresource.hresource.shared.com.hresource.model.humanresourceboundedcontext.employee.EmployeeRegistrationFormModel"],
     "extend": "Ext.form.Panel",
     "viewModel": "EmployeeRegistrationFormViewModel",
     "controller": "EmployeeRegistrationFormController"
});
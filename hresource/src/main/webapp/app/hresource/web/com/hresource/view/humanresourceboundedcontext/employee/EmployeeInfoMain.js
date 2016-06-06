Ext.define('Hresource.hresource.web.com.hresource.view.humanresourceboundedcontext.employee.EmployeeInfoMain', {
     "xtype": "employeeInfoMainView",
     "extend": "Ext.tab.Panel",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "EmployeeInfoMainController",
     "restURL": "/EmployeeInfo",
     "defaults": {
          "split": true
     },
     "requires": ["Hresource.hresource.shared.com.hresource.model.humanresourceboundedcontext.employee.EmployeeInfoModel", "Hresource.hresource.web.com.hresource.controller.humanresourceboundedcontext.employee.EmployeeInfoMainController", "Hresource.hresource.shared.com.hresource.model.humanresourceboundedcontext.employee.DepartmentTypeModel", "Hresource.hresource.shared.com.hresource.model.humanresourceboundedcontext.employee.DesignationTypeModel", "Hresource.hresource.shared.com.hresource.model.humanresourceboundedcontext.employee.JobTypeModel", "Hresource.hresource.shared.com.hresource.model.organization.contactmanagement.CoreContactsModel", "Hresource.hresource.shared.com.hresource.viewmodel.humanresourceboundedcontext.employee.EmployeeInfoViewModel"],
     "communicationLog": [],
     "tabPosition": "bottom",
     "items": [{
          "title": "Data Browser",
          "layout": "border",
          "defaults": {
               "split": true
          },
          "autoScroll": false,
          "customWidgetType": "vdBorderLayout",
          "items": [{
               "xtype": "tabpanel",
               "customWidgetType": "vdTabLayout",
               "margin": "5 0 5 5",
               "border": 1,
               "style": {
                    "borderColor": "#f6f6f6",
                    "borderStyle": "solid",
                    "borderWidth": "1px"
               },
               "displayName": "EmployeeInfo",
               "name": "EmployeeInfoTreeContainer",
               "itemId": "EmployeeInfoTreeContainer",
               "restURL": "/EmployeeInfo",
               "autoScroll": false,
               "collapsible": true,
               "titleCollapse": true,
               "collapseMode": "header",
               "collapsed": false,
               "items": [{
                    "xtype": "treepanel",
                    "customWidgetType": "vdTree",
                    "title": "Browse",
                    "name": "entityTreePanel",
                    "useArrows": true,
                    "rootVisible": false,
                    "itemId": "EmployeeInfoTree",
                    "listeners": {
                         "select": "treeClick"
                    },
                    "tbar": [{
                         "xtype": "triggerfield",
                         "customWidgetType": "vdTriggerField",
                         "emptyText": "Search",
                         "triggerCls": "",
                         "listeners": {
                              "change": "onTriggerfieldChange",
                              "buffer": 250
                         }
                    }, "->", {
                         "xtype": "tool",
                         "type": "refresh",
                         "tooltip": "Refresh Tree Data",
                         "handler": "onTreeRefreshClick"
                    }]
               }, {
                    "title": "Advance Search",
                    "xtype": "form",
                    "customWidgetType": "vdFormpanel",
                    "itemId": "queryPanel",
                    "buttons": [{
                         "text": "Filter",
                         "handler": "onFilterClick",
                         "name": "filterButton"
                    }],
                    "items": []
               }],
               "region": "west",
               "width": "20%"
          }, {
               "region": "center",
               "layout": "border",
               "defaults": {
                    "split": true
               },
               "customWidgetType": "vdBorderLayout",
               "items": [{
                    "customWidgetType": "vdFormpanel",
                    "xtype": "form",
                    "displayName": "EmployeeInfo",
                    "title": "EmployeeInfo",
                    "name": "EmployeeInfo",
                    "itemId": "EmployeeInfoForm",
                    "bodyPadding": 10,
                    "items": [{
                         "name": "empId",
                         "itemId": "empId",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "Employee Id",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Employee Id<font color='red'> *<\/font>",
                         "fieldId": "BD56E463-14E3-483B-ACE7-4BF15FC68CEA",
                         "minLength": "1",
                         "maxLength": "256",
                         "hidden": true,
                         "value": "",
                         "bindable": "empId"
                    }, {
                         "name": "deptTypeCode",
                         "itemId": "deptTypeCode",
                         "xtype": "combo",
                         "customWidgetType": "vdCombo",
                         "displayName": "Dept Type Code",
                         "margin": "5 5 5 5",
                         "valueField": "primaryKey",
                         "displayField": "primaryDisplay",
                         "typeAhead": true,
                         "queryMode": "local",
                         "minChars": 2,
                         "store": {
                              "data": [],
                              "model": "Hresource.hresource.shared.com.hresource.model.humanresourceboundedcontext.employee.DepartmentTypeModel"
                         },
                         "allowBlank": false,
                         "fieldLabel": "Dept Type Code<font color='red'> *<\/font>",
                         "fieldId": "7758AE04-9CE7-4E49-8969-6C3BF3A20979",
                         "restURL": "DepartmentType",
                         "bindable": "deptTypeCode",
                         "columnWidth": 0.5
                    }, {
                         "name": "designationTypeCode",
                         "itemId": "designationTypeCode",
                         "xtype": "combo",
                         "customWidgetType": "vdCombo",
                         "displayName": "Designation Type Code",
                         "margin": "5 5 5 5",
                         "valueField": "primaryKey",
                         "displayField": "primaryDisplay",
                         "typeAhead": true,
                         "queryMode": "local",
                         "minChars": 2,
                         "store": {
                              "data": [],
                              "model": "Hresource.hresource.shared.com.hresource.model.humanresourceboundedcontext.employee.DesignationTypeModel"
                         },
                         "allowBlank": false,
                         "fieldLabel": "Designation Type Code<font color='red'> *<\/font>",
                         "fieldId": "55D1865A-2B15-4EC4-A35D-208F6FC35D6A",
                         "restURL": "DesignationType",
                         "bindable": "designationTypeCode",
                         "columnWidth": 0.5
                    }, {
                         "name": "reportingOfficer",
                         "itemId": "reportingOfficer",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "Reporting Officer",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Reporting Officer<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "4529F0C2-D320-4DDA-8A52-8BA196A2E12F",
                         "minLength": "1",
                         "maxLength": "256",
                         "bindable": "reportingOfficer",
                         "columnWidth": 0.5
                    }, {
                         "name": "pan",
                         "itemId": "pan",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "PAN",
                         "margin": "5 5 5 5",
                         "fieldLabel": "PAN<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "460C7D7A-73C0-48DC-8E5E-35710C424589",
                         "minLength": "1",
                         "maxLength": "256",
                         "bindable": "pan",
                         "columnWidth": 0.5
                    }, {
                         "name": "jobTypeCode",
                         "itemId": "jobTypeCode",
                         "xtype": "combo",
                         "customWidgetType": "vdCombo",
                         "displayName": "Job Type Code",
                         "margin": "5 5 5 5",
                         "valueField": "primaryKey",
                         "displayField": "primaryDisplay",
                         "typeAhead": true,
                         "queryMode": "local",
                         "minChars": 2,
                         "store": {
                              "data": [],
                              "model": "Hresource.hresource.shared.com.hresource.model.humanresourceboundedcontext.employee.JobTypeModel"
                         },
                         "allowBlank": false,
                         "fieldLabel": "Job Type Code<font color='red'> *<\/font>",
                         "fieldId": "0E3AD062-F425-4CAB-844F-B9F42381ACD2",
                         "restURL": "JobType",
                         "bindable": "jobTypeCode",
                         "columnWidth": 0.5
                    }, {
                         "name": "versionId",
                         "itemId": "versionId",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "versionId",
                         "margin": "5 5 5 5",
                         "value": "-1",
                         "fieldLabel": "versionId",
                         "fieldId": "A99084A9-0A6B-433D-9AF2-98173D9E7411",
                         "bindable": "versionId",
                         "hidden": true
                    }, {
                         "xtype": "combo",
                         "name": "CoreContacts",
                         "displayField": "primaryDisplay",
                         "valueField": "primaryKey",
                         "margin": 5,
                         "bindable": "coreContacts.contactId",
                         "typeAhead": true,
                         "columnWidth": 0.5,
                         "queryMode": "local",
                         "minChars": 1,
                         "fieldLabel": "Core Contacts<font color='red'> *<\/font>",
                         "title": "Core Contacts",
                         "itemId": "coreContacts",
                         "store": {
                              "data": [],
                              "model": "Hresource.hresource.shared.com.hresource.model.organization.contactmanagement.CoreContactsModel"
                         }
                    }],
                    "layout": "column",
                    "defaults": {
                         "columnWidth": 0.5,
                         "labelAlign": "left",
                         "labelWidth": 200
                    },
                    "autoScroll": true,
                    "dockedItems": [{
                         "xtype ": "toolbar",
                         "customWidgetType": "vdBBar",
                         "dock": "bottom",
                         "ui": "footer",
                         "isToolBar": true,
                         "isDockedItem": true,
                         "parentId": 1,
                         "customId": 20,
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill",
                              "parentId": 20,
                              "customId": 419
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "saveFormButton",
                              "margin": 5,
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "parentId": 20,
                              "customId": 420,
                              "listeners": {
                                   "click": "saveForm"
                              }
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "resetFormButton",
                              "margin": 5,
                              "text": "Reset",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "resetFormButton",
                              "parentId": 20,
                              "customId": 421,
                              "listeners": {
                                   "click": "resetForm"
                              }
                         }]
                    }],
                    "listeners": {
                         "scope": "controller"
                    },
                    "tools": [{
                         "type": "help",
                         "tooltip": "Console",
                         "handler": "onConsoleClick"
                    }, {
                         "type": "refresh",
                         "tooltip": "Refresh Tab",
                         "handler": "init"
                    }],
                    "extend": "Ext.form.Panel",
                    "region": "center"
               }, {
                    "xtype": "gridpanel",
                    "customWidgetType": "vdGrid",
                    "displayName": "EmployeeInfo",
                    "title": "Details Grid",
                    "name": "EmployeeInfoGrid",
                    "itemId": "EmployeeInfoGrid",
                    "restURL": "/EmployeeInfo",
                    "store": [],
                    "columns": [{
                         "header": "Employee Id",
                         "dataIndex": "empId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "primaryDisplay",
                         "dataIndex": "primaryDisplay",
                         "hidden": true
                    }, {
                         "header": "primaryKey",
                         "dataIndex": "primaryKey",
                         "hidden": true
                    }, {
                         "header": "Dept Type Code",
                         "dataIndex": "deptTypeCode",
                         "renderer": "renderFormValue",
                         "flex": 1
                    }, {
                         "header": "Designation Type Code",
                         "dataIndex": "designationTypeCode",
                         "renderer": "renderFormValue",
                         "flex": 1
                    }, {
                         "header": "Reporting Officer",
                         "dataIndex": "reportingOfficer",
                         "flex": 1
                    }, {
                         "header": "PAN",
                         "dataIndex": "pan",
                         "flex": 1
                    }, {
                         "header": "Job Type Code",
                         "dataIndex": "jobTypeCode",
                         "renderer": "renderFormValue",
                         "flex": 1
                    }, {
                         "header": "createdBy",
                         "dataIndex": "createdBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "createdDate",
                         "dataIndex": "createdDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedBy",
                         "dataIndex": "updatedBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedDate",
                         "dataIndex": "updatedDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "versionId",
                         "dataIndex": "versionId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "activeStatus",
                         "dataIndex": "activeStatus",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "txnAccessCode",
                         "dataIndex": "txnAccessCode",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "xtype": "actioncolumn",
                         "customWidgetType": "vdActionColumn",
                         "sortable": false,
                         "menuDisable": true,
                         "items": [{
                              "icon": "images/delete.gif",
                              "tooltip": "Delete Record",
                              "handler": "onDeleteActionColumnClickMainGrid"
                         }]
                    }],
                    "listeners": {
                         "itemclick": "onGridItemClick"
                    },
                    "tools": [{
                         "type": "refresh",
                         "tooltip": "Refresh Grid Data",
                         "handler": "onGridRefreshClick"
                    }],
                    "collapsible": true,
                    "titleCollapse": true,
                    "collapseMode": "header",
                    "region": "south",
                    "height": "40%"
               }]
          }]
     }, {
          "title": "Add New",
          "itemId": "addNewForm",
          "layout": "border",
          "customWidgetType": "vdBorderLayout",
          "autoScroll": false,
          "items": [{
               "customWidgetType": "vdFormpanel",
               "xtype": "form",
               "displayName": "EmployeeInfo",
               "title": "EmployeeInfo",
               "name": "EmployeeInfo",
               "itemId": "EmployeeInfoForm",
               "bodyPadding": 10,
               "items": [{
                    "name": "empId",
                    "itemId": "empId",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "Employee Id",
                    "margin": "5 5 5 5",
                    "fieldLabel": "Employee Id<font color='red'> *<\/font>",
                    "fieldId": "BD56E463-14E3-483B-ACE7-4BF15FC68CEA",
                    "minLength": "1",
                    "maxLength": "256",
                    "hidden": true,
                    "value": "",
                    "bindable": "empId"
               }, {
                    "name": "deptTypeCode",
                    "itemId": "deptTypeCode",
                    "xtype": "combo",
                    "customWidgetType": "vdCombo",
                    "displayName": "Dept Type Code",
                    "margin": "5 5 5 5",
                    "valueField": "primaryKey",
                    "displayField": "primaryDisplay",
                    "typeAhead": true,
                    "queryMode": "local",
                    "minChars": 2,
                    "store": {
                         "data": [],
                         "model": "Hresource.hresource.shared.com.hresource.model.humanresourceboundedcontext.employee.DepartmentTypeModel"
                    },
                    "allowBlank": false,
                    "fieldLabel": "Dept Type Code<font color='red'> *<\/font>",
                    "fieldId": "7758AE04-9CE7-4E49-8969-6C3BF3A20979",
                    "restURL": "DepartmentType",
                    "bindable": "deptTypeCode",
                    "columnWidth": 0.5
               }, {
                    "name": "designationTypeCode",
                    "itemId": "designationTypeCode",
                    "xtype": "combo",
                    "customWidgetType": "vdCombo",
                    "displayName": "Designation Type Code",
                    "margin": "5 5 5 5",
                    "valueField": "primaryKey",
                    "displayField": "primaryDisplay",
                    "typeAhead": true,
                    "queryMode": "local",
                    "minChars": 2,
                    "store": {
                         "data": [],
                         "model": "Hresource.hresource.shared.com.hresource.model.humanresourceboundedcontext.employee.DesignationTypeModel"
                    },
                    "allowBlank": false,
                    "fieldLabel": "Designation Type Code<font color='red'> *<\/font>",
                    "fieldId": "55D1865A-2B15-4EC4-A35D-208F6FC35D6A",
                    "restURL": "DesignationType",
                    "bindable": "designationTypeCode",
                    "columnWidth": 0.5
               }, {
                    "name": "reportingOfficer",
                    "itemId": "reportingOfficer",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "Reporting Officer",
                    "margin": "5 5 5 5",
                    "fieldLabel": "Reporting Officer<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "4529F0C2-D320-4DDA-8A52-8BA196A2E12F",
                    "minLength": "1",
                    "maxLength": "256",
                    "bindable": "reportingOfficer",
                    "columnWidth": 0.5
               }, {
                    "name": "pan",
                    "itemId": "pan",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "PAN",
                    "margin": "5 5 5 5",
                    "fieldLabel": "PAN<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "460C7D7A-73C0-48DC-8E5E-35710C424589",
                    "minLength": "1",
                    "maxLength": "256",
                    "bindable": "pan",
                    "columnWidth": 0.5
               }, {
                    "name": "jobTypeCode",
                    "itemId": "jobTypeCode",
                    "xtype": "combo",
                    "customWidgetType": "vdCombo",
                    "displayName": "Job Type Code",
                    "margin": "5 5 5 5",
                    "valueField": "primaryKey",
                    "displayField": "primaryDisplay",
                    "typeAhead": true,
                    "queryMode": "local",
                    "minChars": 2,
                    "store": {
                         "data": [],
                         "model": "Hresource.hresource.shared.com.hresource.model.humanresourceboundedcontext.employee.JobTypeModel"
                    },
                    "allowBlank": false,
                    "fieldLabel": "Job Type Code<font color='red'> *<\/font>",
                    "fieldId": "0E3AD062-F425-4CAB-844F-B9F42381ACD2",
                    "restURL": "JobType",
                    "bindable": "jobTypeCode",
                    "columnWidth": 0.5
               }, {
                    "name": "versionId",
                    "itemId": "versionId",
                    "xtype": "numberfield",
                    "customWidgetType": "vdNumberfield",
                    "displayName": "versionId",
                    "margin": "5 5 5 5",
                    "value": "-1",
                    "fieldLabel": "versionId",
                    "fieldId": "A99084A9-0A6B-433D-9AF2-98173D9E7411",
                    "bindable": "versionId",
                    "hidden": true
               }, {
                    "xtype": "combo",
                    "name": "CoreContacts",
                    "displayField": "primaryDisplay",
                    "valueField": "primaryKey",
                    "margin": 5,
                    "bindable": "coreContacts.contactId",
                    "typeAhead": true,
                    "columnWidth": 0.5,
                    "queryMode": "local",
                    "minChars": 1,
                    "fieldLabel": "Core Contacts<font color='red'> *<\/font>",
                    "title": "Core Contacts",
                    "itemId": "coreContacts",
                    "store": {
                         "data": [],
                         "model": "Hresource.hresource.shared.com.hresource.model.organization.contactmanagement.CoreContactsModel"
                    }
               }],
               "layout": "column",
               "defaults": {
                    "columnWidth": 0.5,
                    "labelAlign": "left",
                    "labelWidth": 200
               },
               "autoScroll": true,
               "dockedItems": [{
                    "xtype ": "toolbar",
                    "customWidgetType": "vdBBar",
                    "dock": "bottom",
                    "ui": "footer",
                    "isToolBar": true,
                    "isDockedItem": true,
                    "parentId": 1,
                    "customId": 20,
                    "items": [{
                         "xtype": "tbfill",
                         "customWidgetType": "vdTbFill",
                         "parentId": 20,
                         "customId": 419
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "saveFormButton",
                         "margin": 5,
                         "text": "Save",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "saveFormButton",
                         "parentId": 20,
                         "customId": 420,
                         "listeners": {
                              "click": "saveForm"
                         }
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "resetFormButton",
                         "margin": 5,
                         "text": "Reset",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "resetFormButton",
                         "parentId": 20,
                         "customId": 421,
                         "listeners": {
                              "click": "resetForm"
                         }
                    }]
               }],
               "listeners": {
                    "scope": "controller"
               },
               "tools": [{
                    "type": "help",
                    "tooltip": "Console",
                    "handler": "onConsoleClick"
               }, {
                    "type": "refresh",
                    "tooltip": "Refresh Tab",
                    "handler": "init"
               }],
               "extend": "Ext.form.Panel",
               "region": "center"
          }]
     }]
});
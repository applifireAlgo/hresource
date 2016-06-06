Ext.define('Hresource.hresource.web.com.hresource.view.humanresourceboundedcontext.employee.JobTypeMain', {
     "xtype": "jobTypeMainView",
     "extend": "Ext.tab.Panel",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "JobTypeMainController",
     "restURL": "/JobType",
     "defaults": {
          "split": true
     },
     "requires": ["Hresource.hresource.shared.com.hresource.model.humanresourceboundedcontext.employee.JobTypeModel", "Hresource.hresource.web.com.hresource.controller.humanresourceboundedcontext.employee.JobTypeMainController", "Hresource.hresource.shared.com.hresource.viewmodel.humanresourceboundedcontext.employee.JobTypeViewModel"],
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
               "displayName": "Job Type",
               "name": "JobTypeTreeContainer",
               "itemId": "JobTypeTreeContainer",
               "restURL": "/JobType",
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
                    "itemId": "JobTypeTree",
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
                    "displayName": "Job Type",
                    "title": "Job Type",
                    "name": "JobType",
                    "itemId": "JobTypeForm",
                    "bodyPadding": 10,
                    "items": [{
                         "name": "jobTypeCode",
                         "itemId": "jobTypeCode",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "jobTypeCode",
                         "margin": "5 5 5 5",
                         "fieldLabel": "jobTypeCode<font color='red'> *<\/font>",
                         "fieldId": "F3410232-877E-4FDC-9936-4F0DF2891629",
                         "hidden": true,
                         "value": "",
                         "bindable": "jobTypeCode"
                    }, {
                         "name": "jobTypeDesc",
                         "itemId": "jobTypeDesc",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "Job Type Desc",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Job Type Desc<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "5C0C919A-D67B-48CE-96DE-A926DF7168B3",
                         "minLength": "1",
                         "maxLength": "256",
                         "bindable": "jobTypeDesc",
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
                         "fieldId": "2A0FCFF3-C491-4154-BBAC-F186FECB9657",
                         "bindable": "versionId",
                         "hidden": true
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
                         "customId": 692,
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill",
                              "parentId": 692,
                              "customId": 735
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "saveFormButton",
                              "margin": 5,
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "parentId": 692,
                              "customId": 736,
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
                              "parentId": 692,
                              "customId": 737,
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
                    "displayName": "Job Type",
                    "title": "Details Grid",
                    "name": "JobTypeGrid",
                    "itemId": "JobTypeGrid",
                    "restURL": "/JobType",
                    "store": [],
                    "columns": [{
                         "header": "jobTypeCode",
                         "dataIndex": "jobTypeCode",
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
                         "header": "Job Type Desc",
                         "dataIndex": "jobTypeDesc",
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
               "displayName": "Job Type",
               "title": "Job Type",
               "name": "JobType",
               "itemId": "JobTypeForm",
               "bodyPadding": 10,
               "items": [{
                    "name": "jobTypeCode",
                    "itemId": "jobTypeCode",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "jobTypeCode",
                    "margin": "5 5 5 5",
                    "fieldLabel": "jobTypeCode<font color='red'> *<\/font>",
                    "fieldId": "F3410232-877E-4FDC-9936-4F0DF2891629",
                    "hidden": true,
                    "value": "",
                    "bindable": "jobTypeCode"
               }, {
                    "name": "jobTypeDesc",
                    "itemId": "jobTypeDesc",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "Job Type Desc",
                    "margin": "5 5 5 5",
                    "fieldLabel": "Job Type Desc<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "5C0C919A-D67B-48CE-96DE-A926DF7168B3",
                    "minLength": "1",
                    "maxLength": "256",
                    "bindable": "jobTypeDesc",
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
                    "fieldId": "2A0FCFF3-C491-4154-BBAC-F186FECB9657",
                    "bindable": "versionId",
                    "hidden": true
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
                    "customId": 692,
                    "items": [{
                         "xtype": "tbfill",
                         "customWidgetType": "vdTbFill",
                         "parentId": 692,
                         "customId": 735
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "saveFormButton",
                         "margin": 5,
                         "text": "Save",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "saveFormButton",
                         "parentId": 692,
                         "customId": 736,
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
                         "parentId": 692,
                         "customId": 737,
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
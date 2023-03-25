(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d22c80b"],{f407:function(e,t,r){"use strict";r.r(t);var l=function(){var e=this,t=e._self._c;return t("div",{staticClass:"app-container"},[t("el-row",{staticClass:"mb8",attrs:{gutter:10}},[t("el-col",{attrs:{span:1.5}},[t("el-button",{attrs:{type:"primary",plain:"",icon:"el-icon-plus",size:"mini"},on:{click:e.handleAdd}},[e._v("新增")])],1),t("el-col",{attrs:{span:1.5}},[t("el-button",{attrs:{type:"success",plain:"",icon:"el-icon-edit",size:"mini",disabled:e.single},on:{click:e.handleUpdate}},[e._v("修改")])],1),t("el-col",{attrs:{span:1.5}},[t("el-button",{attrs:{type:"danger",plain:"",icon:"el-icon-delete",size:"mini",disabled:e.multiple},on:{click:e.handleDelete}},[e._v("删除")])],1),t("right-toolbar",{attrs:{showSearch:e.showSearch},on:{"update:showSearch":function(t){e.showSearch=t},"update:show-search":function(t){e.showSearch=t},queryTable:e.getList}})],1),t("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],attrs:{data:e.cartList},on:{"selection-change":e.handleSelectionChange}},[t("el-table-column",{attrs:{type:"selection",width:"55",align:"center"}}),t("el-table-column",{attrs:{label:"购物车ID",align:"center",prop:"cartId"}}),t("el-table-column",{attrs:{label:"用户ID",align:"center",prop:"userId"}}),t("el-table-column",{attrs:{label:"书籍ID",align:"center",prop:"bookId"}}),t("el-table-column",{attrs:{label:"加入购物车数量",align:"center",prop:"productAmount"}}),t("el-table-column",{attrs:{label:"书籍价格",align:"center",prop:"price"}}),t("el-table-column",{attrs:{label:"加入购物车时间",align:"center",prop:"addTime",width:"180"},scopedSlots:e._u([{key:"default",fn:function(r){return[t("span",[e._v(e._s(e.parseTime(r.row.addTime,"{y}-{m}-{d}")))])]}}])}),t("el-table-column",{attrs:{label:"最后修改时间",align:"center",prop:"modifiedTime",width:"180"},scopedSlots:e._u([{key:"default",fn:function(r){return[t("span",[e._v(e._s(e.parseTime(r.row.modifiedTime,"{y}-{m}-{d}")))])]}}])}),t("el-table-column",{attrs:{label:"操作",align:"center","class-name":"small-padding fixed-width"},scopedSlots:e._u([{key:"default",fn:function(r){return[t("el-button",{attrs:{size:"mini",type:"text",icon:"el-icon-edit"},on:{click:function(t){return e.handleUpdate(r.row)}}},[e._v("修改")]),t("el-button",{attrs:{size:"mini",type:"text",icon:"el-icon-delete"}},[e._v("删除")])]}}])})],1),t("pagination",{directives:[{name:"show",rawName:"v-show",value:e.total>0,expression:"total>0"}],attrs:{total:e.total,page:e.queryParams.pageNum,limit:e.queryParams.pageSize},on:{"update:page":function(t){return e.$set(e.queryParams,"pageNum",t)},"update:limit":function(t){return e.$set(e.queryParams,"pageSize",t)},pagination:e.getList}}),t("el-dialog",{attrs:{title:e.title,visible:e.open,width:"500px","append-to-body":""},on:{"update:visible":function(t){e.open=t}}},[t("el-form",{ref:"form",attrs:{model:e.form,rules:e.rules,"label-width":"80px"}},[t("el-form-item",{attrs:{label:"用户ID",prop:"userId"}},[t("el-input",{attrs:{placeholder:"请输入用户ID"},model:{value:e.form.userId,callback:function(t){e.$set(e.form,"userId",t)},expression:"form.userId"}})],1),t("el-form-item",{attrs:{label:"书籍ID",prop:"bookId"}},[t("el-input",{attrs:{placeholder:"请输入书籍ID"},model:{value:e.form.bookId,callback:function(t){e.$set(e.form,"bookId",t)},expression:"form.bookId"}})],1),t("el-form-item",{attrs:{label:"加入购物车数量",prop:"productAmount"}},[t("el-input",{attrs:{placeholder:"请输入加入购物车数量"},model:{value:e.form.productAmount,callback:function(t){e.$set(e.form,"productAmount",t)},expression:"form.productAmount"}})],1),t("el-form-item",{attrs:{label:"书籍价格",prop:"price"}},[t("el-input",{attrs:{placeholder:"请输入书籍价格"},model:{value:e.form.price,callback:function(t){e.$set(e.form,"price",t)},expression:"form.price"}})],1)],1),t("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{attrs:{type:"primary"},on:{click:e.submitForm}},[e._v("确 定")]),t("el-button",{on:{click:e.cancel}},[e._v("取 消")])],1)],1)],1)},a=[],i=r("b775");function o(e){return Object(i["a"])({url:"/system/cart/list",method:"get",params:e})}function s(e){return Object(i["a"])({url:"/system/cart/"+e,method:"get"})}function n(e){return Object(i["a"])({url:"/system/cart",method:"post",data:e})}function u(e){return Object(i["a"])({url:"/system/cart",method:"put",data:e})}function c(e){return Object(i["a"])({url:"/system/cart/"+e,method:"delete"})}var d={name:"Cart",data(){return{loading:!0,ids:[],single:!0,multiple:!0,showSearch:!0,total:0,cartList:[],title:"",open:!1,queryParams:{pageNum:1,pageSize:10,userId:null,bookId:null,productAmount:null,price:null},form:{},rules:{userId:[{required:!0,message:"用户ID不能为空",trigger:"blur"}],bookId:[{required:!0,message:"书籍ID不能为空",trigger:"blur"}],productAmount:[{required:!0,message:"加入购物车数量不能为空",trigger:"blur"}],price:[{required:!0,message:"书籍价格不能为空",trigger:"blur"}]}}},created(){this.getList()},methods:{getList(){this.loading=!0,o(this.queryParams).then(e=>{this.cartList=e.rows,this.total=e.total,this.loading=!1})},cancel(){this.open=!1,this.reset()},reset(){this.form={cartId:null,userId:null,bookId:null,productAmount:null,price:null,addTime:null,modifiedTime:null},this.resetForm("form")},handleQuery(){this.queryParams.pageNum=1,this.getList()},resetQuery(){this.resetForm("queryForm"),this.handleQuery()},handleSelectionChange(e){this.ids=e.map(e=>e.cartId),this.single=1!==e.length,this.multiple=!e.length},handleAdd(){this.reset(),this.open=!0,this.title="添加购物车"},handleUpdate(e){this.reset();const t=e.cartId||this.ids;s(t).then(e=>{this.form=e.data,this.open=!0,this.title="修改购物车"})},submitForm(){this.$refs.form.validate(e=>{e&&(null!=this.form.cartId?u(this.form).then(e=>{this.$modal.msgSuccess("修改成功"),this.open=!1,this.getList()}):n(this.form).then(e=>{this.$modal.msgSuccess("新增成功"),this.open=!1,this.getList()}))})},handleDelete(e){const t=e.cartId||this.ids;this.$modal.confirm('是否确认删除购物车编号为"'+t+'"的数据项？').then((function(){return c(t)})).then(()=>{this.getList(),this.$modal.msgSuccess("删除成功")}).catch(()=>{})}}},m=d,p=r("2877"),h=Object(p["a"])(m,l,a,!1,null,null,null);t["default"]=h.exports}}]);
//# sourceMappingURL=chunk-2d22c80b.877c8905.js.map
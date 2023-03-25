(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-faa46a64"],{"6fb2":function(e,t,o){},cc94:function(e,t,o){"use strict";o("6fb2")},d63a:function(e,t,o){"use strict";o.r(t);var r=function(){var e=this,t=e._self._c;return t("div",{staticClass:"app-container"},[t("el-form",{directives:[{name:"show",rawName:"v-show",value:e.showSearch,expression:"showSearch"}],ref:"queryForm",attrs:{model:e.queryParams,size:"small",inline:!0,"label-width":"68px"}},[t("el-form-item",{attrs:{label:"图书名称",prop:"bookName"}},[t("el-input",{attrs:{placeholder:"请输入图书名称",clearable:""},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery.apply(null,arguments)}},model:{value:e.queryParams.bookName,callback:function(t){e.$set(e.queryParams,"bookName",t)},expression:"queryParams.bookName"}})],1),t("el-form-item",{attrs:{label:"图书出版社",prop:"bookPress"}},[t("el-input",{attrs:{placeholder:"请输入图书出版社",clearable:""},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery.apply(null,arguments)}},model:{value:e.queryParams.bookPress,callback:function(t){e.$set(e.queryParams,"bookPress",t)},expression:"queryParams.bookPress"}})],1),t("el-form-item",{attrs:{label:"图书作者",prop:"bookAuthor"}},[t("el-input",{attrs:{placeholder:"请输入图书作者",clearable:""},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery.apply(null,arguments)}},model:{value:e.queryParams.bookAuthor,callback:function(t){e.$set(e.queryParams,"bookAuthor",t)},expression:"queryParams.bookAuthor"}})],1),t("el-form-item",{attrs:{label:"分类",prop:"bookCategory"}},[t("el-select",{attrs:{placeholder:"请选择分类",clearable:""},model:{value:e.queryParams.bookCategory,callback:function(t){e.$set(e.queryParams,"bookCategory",t)},expression:"queryParams.bookCategory"}},e._l(e.bookCategory,(function(e){return t("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1)],1),t("el-form-item",[t("el-button",{attrs:{type:"primary",icon:"el-icon-search",size:"mini"},on:{click:e.handleQuery}},[e._v("搜索")]),t("el-button",{attrs:{icon:"el-icon-refresh",size:"mini"},on:{click:e.resetQuery}},[e._v("重置")])],1)],1),t("el-row",{staticClass:"mb8",attrs:{gutter:10}},[t("right-toolbar",{attrs:{showSearch:e.showSearch},on:{"update:showSearch":function(t){e.showSearch=t},"update:show-search":function(t){e.showSearch=t},queryTable:e.getList}})],1),t("el-row",{attrs:{gutter:20}},e._l(e.bookInfoList,(function(o,r){return t("el-col",{key:r,attrs:{span:6}},[t("el-card",[t("el-tooltip",{staticClass:"item",attrs:{effect:"dark",placement:"top-start"}},[t("div",{staticClass:"div-descript",attrs:{slot:"content"},slot:"content"},[e._v("简介："+e._s(o.descript))]),t("div",[t("div",[t("el-image",{staticClass:"el-image-add",attrs:{src:o.coverAddress}})],1),t("el-descriptions",{attrs:{column:2}},[t("el-descriptions-item",{attrs:{label:"图书名称"}},[e._v(e._s(o.bookName))]),t("el-descriptions-item",{attrs:{label:"图书出版社"}},[e._v(e._s(o.bookPress))]),t("el-descriptions-item",{attrs:{label:"图书售格"}},[e._v(e._s(o.price))]),t("el-descriptions-item",{attrs:{label:"图书分类"}},[t("dict-tag",{attrs:{options:e.bookCategory,value:o.bookCategory}})],1),t("el-descriptions-item",{attrs:{label:"图书余量"}},[e._v(e._s(o.bookRemaining))])],1),t("el-button",{staticClass:"el-button-div",attrs:{type:"primary",plain:""},on:{click:function(t){return e.openDialog(o.bookId,o.bookName)}}},[e._v("借阅")])],1)])],1)],1)})),1),t("pagination",{directives:[{name:"show",rawName:"v-show",value:e.total>0,expression:"total>0"}],attrs:{total:e.total,page:e.queryParams.pageNum,limit:e.queryParams.pageSize},on:{"update:page":function(t){return e.$set(e.queryParams,"pageNum",t)},"update:limit":function(t){return e.$set(e.queryParams,"pageSize",t)},pagination:e.getList}}),t("el-dialog",{attrs:{title:e.title,visible:e.open,width:"500px","append-to-body":""},on:{"update:visible":function(t){e.open=t}}},[t("div",[t("label",[e._v("借阅时长")]),t("el-select",{attrs:{placeholder:"请选择借阅时长"},model:{value:e.value,callback:function(t){e.value=t},expression:"value"}},e._l(e.options,(function(e){return t("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1)],1),t("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{attrs:{type:"primary"},on:{click:e.borrowBooks}},[e._v("确 定")]),t("el-button",{on:{click:e.cancel}},[e._v("取 消")])],1)])],1)},a=[],s=o("b775");function l(e){return Object(s["a"])({url:"/book/bookInfo/list",method:"get",params:e})}function i(e){return Object(s["a"])({url:"/book/bookInfo/"+e,method:"get"})}function n(e){return Object(s["a"])({url:"/book/bookInfo",method:"post",data:e})}function u(e){return Object(s["a"])({url:"/book/bookInfo",method:"put",data:e})}function c(e){return Object(s["a"])({url:"/book/bookInfo/"+e,method:"delete"})}function d(e,t){return Object(s["a"])({url:"/book/bookInfo/borrow/"+e+"/"+t,method:"get"})}var b={name:"BookInfo",data(){return{id:"",bookName:"",options:[{value:"1",label:"一周"},{value:"2",label:"两周"},{value:"3",label:"一个月"},{value:"4",label:"两个月"}],value:"",bookCategory:[{label:"科幻",value:"0",raw:{listClass:"primary",isDefault:"Y",default:!0}}],loading:!0,ids:[],single:!0,multiple:!0,showSearch:!0,total:0,bookInfoList:[],title:"",open:!1,queryParams:{pageNum:1,pageSize:10,bookCore:null,bookName:null,barCode:null,bookPress:null,bookAuthor:null,coverAddress:null,bookCategory:null,price:null,publishStatus:null,auditStatus:null,bookPage:null,descript:null,indate:null,modifiedTime:null},form:{},rules:{bookCore:[{required:!0,message:"图书编码不能为空",trigger:"blur"}],bookName:[{required:!0,message:"图书名称不能为空",trigger:"blur"}],barCode:[{required:!0,message:"国条码不能为空",trigger:"blur"}],bookPress:[{required:!0,message:"图书出版社不能为空",trigger:"blur"}],bookAuthor:[{required:!0,message:"图书作者不能为空",trigger:"blur"}],coverAddress:[{required:!0,message:"封面地址不能为空",trigger:"blur"}],bookCategory:[{required:!0,message:"分类不能为空",trigger:"change"}],price:[{required:!0,message:"图书销售价格不能为空",trigger:"blur"}],publishStatus:[{required:!0,message:"上下架状态不能为空",trigger:"blur"}],auditStatus:[{required:!0,message:"审核状态不能为空",trigger:"blur"}],bookPage:[{required:!0,message:"图书页数不能为空",trigger:"blur"}],descript:[{required:!0,message:"图书描述不能为空",trigger:"blur"}],indate:[{required:!0,message:"图书录入时间不能为空",trigger:"blur"}],modifiedTime:[{required:!0,message:"最后修改时间不能为空",trigger:"blur"}]}}},created(){this.getList()},methods:{getList(){this.loading=!0,l(this.queryParams).then(e=>{this.bookInfoList=e.rows,this.total=e.total,this.loading=!1})},cancel(){this.open=!1,this.value="",this.reset()},reset(){this.form={bookId:null,bookCore:null,bookName:null,barCode:null,bookPress:null,bookAuthor:null,coverAddress:null,bookCategory:null,price:null,publishStatus:0,auditStatus:0,bookPage:null,descript:null,indate:null,modifiedTime:null},this.resetForm("form")},handleQuery(){this.queryParams.pageNum=1,this.getList()},resetQuery(){this.resetForm("queryForm"),this.handleQuery()},handleSelectionChange(e){this.ids=e.map(e=>e.bookId),this.single=1!==e.length,this.multiple=!e.length},handleAdd(){this.reset(),this.open=!0,this.title="添加图书信息"},handleUpdate(e){this.reset();const t=e.bookId||this.ids;i(t).then(e=>{this.form=e.data,this.open=!0,this.title="修改图书信息"})},submitForm(){this.$refs.form.validate(e=>{e&&(null!=this.form.bookId?u(this.form).then(e=>{this.$modal.msgSuccess("修改成功"),this.open=!1,this.getList()}):n(this.form).then(e=>{this.$modal.msgSuccess("新增成功"),this.open=!1,this.getList()}))})},handleDelete(e){const t=e.bookId||this.ids;this.$modal.confirm('是否确认删除图书信息编号为"'+t+'"的数据项？').then((function(){return c(t)})).then(()=>{this.getList(),this.$modal.msgSuccess("删除成功")}).catch(()=>{})},openDialog(e,t){this.bookName=t,this.title="是否借阅 "+t+" 这本书",this.id=e,this.open=!0},borrowBooks(){null===this.value||""===this.value?this.$modal.msg("请选择借阅时长"):d(this.id,this.value).then(e=>{this.open=!1,this.$modal.msgSuccess("图书 "+this.bookName+" 借阅成功"),this.bookName=""})}}},m=b,h=(o("cc94"),o("2877")),p=Object(h["a"])(m,r,a,!1,null,"5f643946",null);t["default"]=p.exports}}]);
//# sourceMappingURL=chunk-faa46a64.62e68b84.js.map
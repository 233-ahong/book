import{a as ee}from"./book.a52ed070.js";import{_ as oe,g as le,K as ae,r as m,G as te,z as se,H as ue,c as ne,b as r,o as g,d as B,m as a,B as A,w as o,j as re,e,k as u,t as i,i as c,F,A as q,E as w,v as de,p as ie,q as ce}from"./index.80a9f799.js";import{b as pe}from"./borrow.b2a1b0b0.js";import{a as _e,b as me}from"./order.cf13c88b.js";import{l as fe}from"./addr.4a67968b.js";const x=h=>(ie("data-v-ed5bd203"),h=h(),ce(),h),ve=["src","alt"],ge={class:"dialog-des"},be=x(()=>u("hr",null,null,-1)),ke={class:"dialog-descript"},he=x(()=>u("hr",null,null,-1)),Ve={class:"dialog-price"},Ie=x(()=>u("label",null,"\u501F\u9605\u65F6\u957F",-1)),ye={slot:"footer",class:"dialog-footer"},we={class:"dialog-price"},Be={class:"dialog-footer"},Ae={__name:"BookInfo",setup(h){const{proxy:z}=le(),E=ae(),V=m(""),R=te(),I=m(1),C=m([]),f=m(!1),b=m(!1),v=m(""),L=m([{value:"1",label:"\u4E00\u5468"},{value:"2",label:"\u4E24\u5468"},{value:"3",label:"\u4E00\u4E2A\u6708"},{value:"4",label:"\u4E24\u4E2A\u6708"}]),P=se({bookInfo:null,form:{},queryParams:{pageNum:1,pageSize:30},rules:{shippingUser:[{required:!0,message:"\u6536\u8D27\u4EBA\u59D3\u540D\u4E0D\u80FD\u4E3A\u7A7A",trigger:"blur"}],productAmount:[{required:!0,message:"\u8D2D\u4E70\u6570\u91CF\u4E0D\u80FD\u4E3A\u7A7A",trigger:"blur"}],customerAddrId:[{required:!0,message:"\u6536\u8D27\u5730\u5740\u4E0D\u80FD\u4E3A\u7A7A",trigger:"blur"}]}}),{queryParams:xe,form:n,rules:D,bookInfo:t}=ue(P),G=ne(()=>n.value.productAmount*t.value.price);function M(){j(),V.value="\u7528\u6237\u4FE1\u606F",n.value.shippingUser=E.getStorageSync("userInfo").nickName,n.value.price=t.value.price,n.value.productId=t.value.bookId,n.value.productAmount=I.value,b.value=!0}function T(){V.value="\u8BF7\u9009\u62E9\u501F\u9605\u65F6\u957F",f.value=!0}function j(){fe().then(p=>{C.value=p.rows})}function H(){v.value===null||v.value===""?w.error("\u8BF7\u9009\u62E9\u501F\u9605\u65F6\u957F"):pe(t.value.bookId,v.value).then(p=>{f.value=!1,w.success("\u56FE\u4E66 "+t.value.bookName+" \u501F\u9605\u6210\u529F")})}function U(){f.value=!1,b.value=!1,v.value=""}function K(){const p=R.query.id;ee(p).then(s=>{t.value=s.data})}function O(){_e(t.value.bookId,I.value).then(()=>{w.success("\u52A0\u5165\u8D2D\u7269\u8F66\u6210\u529F")})}function Q(){z.$refs.detailRefss.validate(p=>{p&&de.confirm("\u786E\u5B9A\u8D2D\u4E70 "+n.value.productAmount+" \u672C "+t.value.bookName+" \u5417?","\u7CFB\u7EDF\u63D0\u793A",{type:"warning",confirmButtonText:"\u786E\u5B9A"}).then(()=>{me(n.value).then(s=>{w.success("\u8D2D\u4E70\u6210\u529F!"),f.value=!1,b.value=!1})})})}return K(),(p,s)=>{const d=r("el-col"),k=r("el-row"),N=r("el-input-number"),_=r("el-button"),W=r("el-option"),$=r("el-select"),S=r("el-dialog"),J=r("el-input"),y=r("el-form-item"),X=r("el-radio"),Y=r("el-radio-group"),Z=r("el-form");return g(),B("div",null,[a(t)?(g(),A(k,{key:0,gutter:24},{default:o(()=>[e(d,{span:2}),e(d,{span:8},{default:o(()=>[u("div",null,[u("img",{class:"dialog-image",src:a(t).coverAddress,title:"bookInfo.bookName",alt:a(t).bookName},null,8,ve)])]),_:1}),e(d,{span:12},{default:o(()=>[u("div",ge,[u("h1",null,i(a(t).bookName),1),e(k,{class:"dialog-row",gutter:24},{default:o(()=>[e(d,{span:6},{default:o(()=>[u("h4",null,"\u4F5C\u8005: "+i(a(t).bookAuthor),1)]),_:1}),e(d,{span:8},{default:o(()=>[u("h4",null,"\u51FA\u7248\u793E: "+i(a(t).bookPress),1)]),_:1}),e(d,{span:10},{default:o(()=>[u("h4",null,"\u4E0A\u67B6\u65F6\u95F4:"+i(a(t).indate),1)]),_:1})]),_:1}),be,e(k,{class:"dialog-row",gutter:24},{default:o(()=>[e(d,{span:24},{default:o(()=>[u("div",ke,i(a(t).descript),1)]),_:1})]),_:1}),he,e(k,{class:"dialog-row",gutter:24},{default:o(()=>[e(d,{span:24},{default:o(()=>[u("div",Ve,"\xA5 "+i(a(t).price),1)]),_:1})]),_:1}),e(k,{class:"dialog-row",gutter:24},{default:o(()=>[e(d,{span:24},{default:o(()=>[e(N,{class:"dialog-number",modelValue:I.value,"onUpdate:modelValue":s[0]||(s[0]=l=>I.value=l),"controls-position":"right",min:1,max:10},null,8,["modelValue"]),e(_,{size:"large",icon:"ShoppingCart",color:"#FF2832",onClick:O},{default:o(()=>[c("\u52A0\u5165\u8D2D\u7269\u8F66")]),_:1}),e(_,{size:"large",icon:"Goods",color:"#FF2832",onClick:M,plain:""},{default:o(()=>[c("\u7ACB\u5373\u8D2D\u4E70")]),_:1})]),_:1}),e(_,{onClick:T,class:"dialog-borrow",size:"large",icon:"WalletFilled"},{default:o(()=>[c("\u7ACB\u5373\u501F\u9605")]),_:1})]),_:1})])]),_:1}),e(d,{span:2})]),_:1})):re("",!0),e(S,{title:V.value,modelValue:f.value,"onUpdate:modelValue":s[2]||(s[2]=l=>f.value=l),width:"500px","append-to-body":""},{default:o(()=>[u("div",null,[Ie,e($,{modelValue:v.value,"onUpdate:modelValue":s[1]||(s[1]=l=>v.value=l),placeholder:"\u8BF7\u9009\u62E9\u501F\u9605\u65F6\u957F"},{default:o(()=>[(g(!0),B(F,null,q(L.value,l=>(g(),A(W,{key:l.value,label:l.label,value:l.value},null,8,["label","value"]))),128))]),_:1},8,["modelValue"])]),u("div",ye,[e(_,{type:"primary",onClick:H},{default:o(()=>[c("\u786E \u5B9A")]),_:1}),e(_,{onClick:U},{default:o(()=>[c("\u53D6 \u6D88")]),_:1})])]),_:1},8,["title","modelValue"]),e(S,{title:V.value,modelValue:b.value,"onUpdate:modelValue":s[6]||(s[6]=l=>b.value=l),width:"680px","append-to-body":""},{default:o(()=>[u("div",null,[e(Z,{ref:"detailRefss",model:a(n),rules:a(D),"label-width":"80px"},{default:o(()=>[e(y,{label:"\u6536\u8D27\u4EBA",prop:"shippingUser"},{default:o(()=>[e(J,{modelValue:a(n).shippingUser,"onUpdate:modelValue":s[3]||(s[3]=l=>a(n).shippingUser=l),placeholder:"\u8BF7\u8F93\u5165\u6536\u8D27\u4EBA\u59D3\u540D"},null,8,["modelValue"])]),_:1}),e(y,{label:"\u6536\u8D27\u5730\u5740",prop:"customerAddrId"},{default:o(()=>[e(Y,{modelValue:a(n).customerAddrId,"onUpdate:modelValue":s[4]||(s[4]=l=>a(n).customerAddrId=l),class:"ml-4"},{default:o(()=>[(g(!0),B(F,null,q(C.value,l=>(g(),A(X,{label:l.customerAddrId,size:"large"},{default:o(()=>[c(i(l.province+" "+l.city+" "+l.district+" "+l.address),1)]),_:2},1032,["label"]))),256))]),_:1},8,["modelValue"])]),_:1}),e(y,{label:"\u8D2D\u4E70\u6570\u91CF",prop:"productAmount"},{default:o(()=>[e(N,{min:1,max:10,modelValue:a(n).productAmount,"onUpdate:modelValue":s[5]||(s[5]=l=>a(n).productAmount=l)},null,8,["modelValue"])]),_:1}),e(y,{label:"\u603B\u4EF7",prop:"price"},{default:o(()=>[u("div",we,"\xA5 "+i(a(G)),1)]),_:1})]),_:1},8,["model","rules"]),u("div",Be,[e(_,{type:"primary",onClick:Q},{default:o(()=>[c("\u786E \u5B9A")]),_:1}),e(_,{onClick:U},{default:o(()=>[c("\u53D6 \u6D88")]),_:1})])])]),_:1},8,["title","modelValue"])])}}},qe=oe(Ae,[["__scopeId","data-v-ed5bd203"]]);export{qe as default};

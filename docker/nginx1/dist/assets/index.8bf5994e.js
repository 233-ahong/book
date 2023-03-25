import{_ as x,g as V,r as c,b as n,o as m,d as p,e as o,w as r,l as y,k as f,i as k,p as N,q as P,s as q,v as B}from"./index.80a9f799.js";const C=u=>(N("data-v-2236b3cf"),u=u(),P(),u),I={class:"login"},U=C(()=>f("h3",{class:"title"},"\u4E91\u4E0A\u4E66\u57CE\u6CE8\u518C",-1)),R={key:0},T={key:1},E={style:{width:"100%"}},S={__name:"index",setup(u){const{proxy:_}=V(),d=c(!1),e=c({userName:"",password:"",confirmPassword:"",nickName:"",mobilePhone:""}),g={userName:[{required:!0,trigger:"blur",message:"\u8BF7\u8F93\u5165\u60A8\u7684\u8D26\u53F7"},{min:8,max:20,message:"\u7528\u6237\u8D26\u53F7\u957F\u5EA6\u5FC5\u987B\u4ECB\u4E8E 8 \u548C 20 \u4E4B\u95F4",trigger:"blur"}],password:[{required:!0,trigger:"blur",message:"\u8BF7\u8F93\u5165\u60A8\u7684\u5BC6\u7801"},{min:16,max:32,message:"\u7528\u6237\u5BC6\u7801\u957F\u5EA6\u5FC5\u987B\u4ECB\u4E8E 16 \u548C 32 \u4E4B\u95F4",trigger:"blur"}],confirmPassword:[{required:!0,trigger:"blur",message:"\u8BF7\u518D\u6B21\u8F93\u5165\u60A8\u7684\u5BC6\u7801"},{required:!0,validator:(i,l,a)=>{e.value.password!==l?a(new Error("\u4E24\u6B21\u8F93\u5165\u7684\u5BC6\u7801\u4E0D\u4E00\u81F4")):a()},trigger:"blur"}],nickName:[{required:!0,trigger:"blur",message:"\u8BF7\u8F93\u5165\u60A8\u7684\u6635\u79F0"},{min:5,max:32,message:"\u7528\u6237\u6635\u79F0\u957F\u5EA6\u5FC5\u987B\u4ECB\u4E8E 5 \u548C 32 \u4E4B\u95F4",trigger:"blur"}],mobilePhone:[{required:!0,message:"\u624B\u673A\u53F7\u7801\u4E0D\u80FD\u4E3A\u7A7A",trigger:"blur"},{pattern:/^1[3|4|5|6|7|8|9][0-9]\d{8}$/,message:"\u8BF7\u8F93\u5165\u6B63\u786E\u7684\u624B\u673A\u53F7\u7801",trigger:"blur"}]};function v(){_.$refs.registerRef.validate(i=>{i&&(d.value=!0,q(e.value).then(l=>{B.confirm("\u606D\u559C\u4F60: \u60A8\u7684\u8D26\u53F7 "+e.value.userName+" \u6CE8\u518C\u6210\u529F","\u7CFB\u7EDF\u63D0\u793A",{confirmButtonText:"\u786E\u5B9A",type:"success"}).then(()=>{location.href="/login"}).catch(()=>{})}))})}return(i,l)=>{const a=n("el-input"),s=n("el-form-item"),w=n("el-button"),b=n("router-link"),h=n("el-form");return m(),p("div",I,[o(h,{ref:"registerRef",model:e.value,rules:g,class:"login-form"},{default:r(()=>[U,o(s,{prop:"userName"},{default:r(()=>[o(a,{modelValue:e.value.userName,"onUpdate:modelValue":l[0]||(l[0]=t=>e.value.userName=t),type:"text","auto-complete":"off",placeholder:"\u8D26\u53F7"},null,8,["modelValue"])]),_:1}),o(s,{prop:"nickName"},{default:r(()=>[o(a,{modelValue:e.value.nickName,"onUpdate:modelValue":l[1]||(l[1]=t=>e.value.nickName=t),type:"text","auto-complete":"off",placeholder:"\u6635\u79F0"},null,8,["modelValue"])]),_:1}),o(s,{prop:"mobilePhone"},{default:r(()=>[o(a,{modelValue:e.value.mobilePhone,"onUpdate:modelValue":l[2]||(l[2]=t=>e.value.mobilePhone=t),type:"text","auto-complete":"off",placeholder:"\u624B\u673A\u53F7"},null,8,["modelValue"])]),_:1}),o(s,{prop:"password"},{default:r(()=>[o(a,{modelValue:e.value.password,"onUpdate:modelValue":l[3]||(l[3]=t=>e.value.password=t),type:"password","auto-complete":"off",placeholder:"\u5BC6\u7801"},null,8,["modelValue"])]),_:1}),o(s,{prop:"confirmPassword"},{default:r(()=>[o(a,{modelValue:e.value.confirmPassword,"onUpdate:modelValue":l[4]||(l[4]=t=>e.value.confirmPassword=t),type:"password","auto-complete":"off",placeholder:"\u786E\u8BA4\u5BC6\u7801"},null,8,["modelValue"])]),_:1}),o(s,null,{default:r(()=>[o(w,{loading:d.value,size:"large",type:"primary",style:{width:"100%"},onClick:y(v,["prevent"])},{default:r(()=>[d.value?(m(),p("span",T,"\u6CE8 \u518C \u4E2D...")):(m(),p("span",R,"\u6CE8 \u518C"))]),_:1},8,["loading","onClick"]),f("div",E,[o(b,{class:"link-type",to:"/login"},{default:r(()=>[k("\u5DF2\u6709\u8D26\u53F7?\u7ACB\u5373\u767B\u5F55")]),_:1})])]),_:1})]),_:1},8,["model"])])}}},z=x(S,[["__scopeId","data-v-2236b3cf"]]);export{z as default};